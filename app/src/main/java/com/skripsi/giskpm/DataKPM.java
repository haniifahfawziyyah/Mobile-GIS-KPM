package com.skripsi.giskpm;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.giskpm.adapter.AdapterKPM;
import com.skripsi.giskpm.app.AppController;
import com.skripsi.giskpm.model.DataModelKPM;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataKPM extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener {

    private static final String TAG = DataKPM.class.getSimpleName();
    public static final String TAG_IDKPM = "id_hasil";
    public static final String TAG_kelurahanKPM = "nama_kelurahan";
    public static final String TAG_NIKKPM = "nik";
    public static final String TAG_NAMAKPM = "nama";
    public static final String TAG_ALAMATKPM = "alamat";
    public static final String TAG_TELPKPM = "telp";
    public static final String TAG_LATITUDEKPM = "latitude";
    public static final String TAG_LONGITUDEKPM = "longitude";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_VALUE = "value";

    private ProgressDialog pDialog;
    private List<DataModelKPM> listData;
    private AdapterKPM adapter;
    private SwipeRefreshLayout swipe;
    private ListView listView;
    private String id_kelurahan;
    private String nama_kelurahan;

    private String url_data = URL_SERVER.Ctampilkpm;
    private String url_cari = URL_SERVER.Ccarikpm;
    private String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datakpm);

        Bundle bundle = getIntent().getExtras();
        id_kelurahan = bundle.getString("id_kelurahan");
        nama_kelurahan = getIntent().getStringExtra("nama_kelurahan");

        swipe = findViewById(R.id.swipe_refreshkpm);
        listView = findViewById(R.id.list_viewkpm);

        listData = new ArrayList<>();
        adapter = new AdapterKPM(DataKPM.this, listData);
        listView.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                callData();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(nama_kelurahan);
        }
    }

    private void callData() {
        listData.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int value = jsonObject.getInt(TAG_VALUE);

                    if (value == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray(TAG_RESULTS);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            DataModelKPM item = new DataModelKPM();
                            item.setId_kpm(obj.getString(TAG_IDKPM));
                            item.setNama_kelurahankpm(obj.getString(TAG_kelurahanKPM));
                            item.setNik_kpm(obj.getString(TAG_NIKKPM));
                            item.setNama_kpm(obj.getString(TAG_NAMAKPM));
                            item.setAlamatkpm(obj.getString(TAG_ALAMATKPM));
                            item.setTelpkpm(obj.getString(TAG_TELPKPM));
                            item.setLatitudekpm(obj.getString(TAG_LATITUDEKPM));
                            item.setLongitudekpm(obj.getString(TAG_LONGITUDEKPM));

                            listData.add(item);
                        }

                        adapter.notifyDataSetChanged();
                    } else {
                        String message = jsonObject.getString(TAG_MESSAGE);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_kelurahan);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    @Override
    public void onRefresh() {
        callData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.type_name));
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);

        // Menampilkan tombol kembali di ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
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

    private void cariData(final String keyword) {
        pDialog = new ProgressDialog(DataKPM.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_cari, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int value = jsonObject.getInt(TAG_VALUE);

                    if (value == 1) {
                        listData.clear();
                        JSONArray jsonArray = jsonObject.getJSONArray(TAG_RESULTS);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            DataModelKPM item = new DataModelKPM();
                            item.setId_kpm(obj.getString(TAG_IDKPM));
                            item.setNama_kelurahankpm(obj.getString(TAG_kelurahanKPM));
                            item.setNik_kpm(obj.getString(TAG_NIKKPM));
                            item.setNama_kpm(obj.getString(TAG_NAMAKPM));
                            item.setAlamatkpm(obj.getString(TAG_ALAMATKPM));
                            item.setTelpkpm(obj.getString(TAG_TELPKPM));
                            item.setLatitudekpm(obj.getString(TAG_LATITUDEKPM));
                            item.setLongitudekpm(obj.getString(TAG_LONGITUDEKPM));

                            listData.add(item);
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();
                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("keyword", keyword);
                params.put("id", id_kelurahan);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}