package com.skripsi.giskpm;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.skripsi.giskpm.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DataKelurahan extends Fragment implements
        OnMapReadyCallback,
        SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener {

    private ProgressDialog pDialog;
    private SwipeRefreshLayout swipe;

    private SupportMapFragment mapFragment;
    private GoogleMap gMap;
    private MarkerOptions markerOptions = new MarkerOptions();
    private CameraPosition cameraPosition;
    private LatLng center, latLng;
    private String title, jumlahkpm, id;

    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    public static final String url_data = URL_SERVER.Ctampilkelurahan;
    public static final String url_cari = URL_SERVER.Ccarikelurahan;

    public static final String ID = "id";
    public static final String TITLE = "nama";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String JML = "jumlah";

    public static final String TAG_RESULTS = "results";
    public static final String TAG_MESSAGE = "message";
    public static final String TAG_VALUE = "value";

    private String tag_json_obj = "json_obj_req";

    private String LOW_TOOLTIP = "Rendah";
    private String MEDIUM_TOOLTIP = "Sedang";
    private String HIGH_TOOLTIP = "Tinggi";

    private int totalDataKPM; // Deklarasi variabel untuk menyimpan total data KPM

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mapskelurahan, container, false);

        setHasOptionsMenu(true);
        swipe = rootView.findViewById(R.id.swipe_refreshmapkelurahan);

        // Inisialisasi MapFragment
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapkelurahan);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                callData();
            }
        });

        getActivity().setTitle("Peta Kelurahan Kota Depok");

        return rootView;
    }

    private void askPermissionsAndShowMyLocation() {
        // With API >= 23, you have to ask the user for permission to view their location.
        if (Build.VERSION.SDK_INT >= 23) {
            int accessCoarsePermission
                    = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission
                    = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED
                    || accessFinePermission != PackageManager.PERMISSION_GRANTED) {
                // The Permissions to ask user.
                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION};
                // Show a dialog asking the user to allow the above permissions.
                requestPermissions(permissions, REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);
                return;
            }
        }

        // Show current location on Map.
        this.callData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_ACCESS_COURSE_FINE_LOCATION: {
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_LONG).show();
                    // Show current location on Map.
                    this.callData();
                } else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private double calculateProporsi(String jumlahKPM, int totalKPM) {
        int jumlah = Integer.parseInt(jumlahKPM);
        return (double) jumlah / totalKPM;
    }

    private void addMarker(LatLng latlng, final String title, final String jumlahkpm, final String id,
                           double proporsi) {
        markerOptions.position(latlng);
        markerOptions.title("Kelurahan " + title);

        // Mengatur warna marker berdasarkan proporsi
        float markerColor = BitmapDescriptorFactory.HUE_BLUE; // Default color
        String proporsiInfo = "Data Kosong"; // Default proporsi info

        if (proporsi > 0) {
            if (proporsi > 0.25) {
                markerColor = BitmapDescriptorFactory.HUE_RED; // Tinggi
                proporsiInfo = "Tinggi";
            } else if (proporsi > 0.10) {
                markerColor = BitmapDescriptorFactory.HUE_YELLOW; // Sedang
                proporsiInfo = "Sedang";
            } else {
                markerColor = BitmapDescriptorFactory.HUE_GREEN; // Rendah
                proporsiInfo = "Rendah";
            }
        }

        String formattedProporsi = String.format("%.4f", proporsi * 100); // Format to 4 decimal places
        String tooltipContent = jumlahkpm + " KPM - Proporsi " + proporsiInfo + ": " + formattedProporsi + "%";

        markerOptions.snippet(tooltipContent); // Set tooltip with combined info
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(markerColor));

        Marker marker = gMap.addMarker(markerOptions);
        marker.setTag(id);

        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String clikTag = (String) marker.getTag();
                Intent kotak = new Intent(getActivity(), DataKPM.class);
                kotak.putExtra("id_kelurahan", clikTag);
                kotak.putExtra("nama_kelurahan", marker.getTitle());
                startActivity(kotak);
                Toasty.info(getActivity(), clikTag, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callData() {
        swipe.setRefreshing(true);

        StringRequest jArr = new StringRequest(Request.Method.POST, url_data, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    int value = jObj.getInt(TAG_VALUE);
                    if (value == 1) {
                        totalDataKPM = jObj.getInt("total_kpm"); // Mengambil total data KPM dari respons JSON
                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            id = obj.getString(ID);
                            title = obj.getString(TITLE);
                            jumlahkpm = obj.getString(JML);
                            latLng = new LatLng(Double.parseDouble(obj.getString(LAT)), Double.parseDouble(obj.getString(LNG)));

                            double proporsi = calculateProporsi(jumlahkpm, totalDataKPM); // Menghitung proporsi
                            addMarker(latLng, title, jumlahkpm, id, proporsi);
                        }
                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
            }
        });

        AppController.getInstance().addToRequestQueue(jArr, tag_json_obj);
    }

    @Override
    public void onRefresh() {
        callData();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        cariData(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Cari Kelurahan");
        searchView.setOnQueryTextListener(this);
    }

    private void cariData(final String keyword) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_cari, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Response: ", response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);

                    int value = jObj.getInt(TAG_VALUE);

                    if (value == 1) {
                        gMap.clear();

                        String getObject = jObj.getString(TAG_RESULTS);
                        JSONArray jsonArray = new JSONArray(getObject);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            id = obj.getString(ID);
                            title = obj.getString(TITLE);
                            jumlahkpm = obj.getString(JML);
                            latLng = new LatLng(Double.parseDouble(obj.getString(LAT)), Double.parseDouble(obj.getString(LNG)));

                            double proporsi = calculateProporsi(jumlahkpm, totalDataKPM); // Menghitung proporsi
                            addMarker(latLng, title, jumlahkpm, id, proporsi);
                        }
                        cameraPosition = new CameraPosition.Builder().target(latLng).zoom(10).build();
                        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    } else {
                        Toast.makeText(getActivity(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("keyword", keyword);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        center = new LatLng(-6.402905, 106.778419);
        cameraPosition = new CameraPosition.Builder().target(center).zoom(10).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        askPermissionsAndShowMyLocation();
    }
}