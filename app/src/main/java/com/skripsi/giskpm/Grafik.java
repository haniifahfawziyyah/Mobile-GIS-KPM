package com.skripsi.giskpm;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.skripsi.giskpm.app.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Grafik extends Fragment {

    private ProgressDialog pd;

    private ArrayList<BarEntry> yValues;
    private ArrayList<String> xAxis;
    private BarChart chart;
    private BarData data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.grafik, container, false);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading");

        chart = rootView.findViewById(R.id.chart);
        load_data_from_server();

        getActivity().setTitle("Grafik KPM");

        return rootView;
    }

    public void load_data_from_server() {
        pd.show();
        String url = URL_SERVER.Cgrafik;
        xAxis = new ArrayList<>();
        yValues = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("string", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                int score = jsonObject.getInt("jumlah_kpm"); // Mengambil nilai skor sebagai integer
                                String name = jsonObject.getString("name").trim();
                                double proporsi = jsonObject.getDouble("proporsi"); // Mengambil proporsi dari JSON

                                xAxis.add(name);
                                yValues.add(new BarEntry(i, score, (float) proporsi)); // Gunakan nilai skor dan proporsi
                            }

                            List<Integer> colors = new ArrayList<>();
                            List<String> proporsiInfoList = new ArrayList<>();

                            for (int i = 0; i < yValues.size(); i++) {
                                float proporsi = (float) yValues.get(i).getData();
                                int color;
                                String proporsiInfo;

                                if (proporsi > 0) {
                                    if (proporsi > 0.25) {
                                        color = Color.RED; // Tinggi
                                        proporsiInfo = "Tinggi";
                                    } else if (proporsi > 0.10) {
                                        color = Color.YELLOW; // Sedang
                                        proporsiInfo = "Sedang";
                                    } else {
                                        color = Color.GREEN; // Rendah
                                        proporsiInfo = "Rendah";
                                    }
                                } else {
                                    color = Color.BLUE; // Kosong
                                    proporsiInfo = "Data Kosong";
                                }

                                colors.add(color);
                                proporsiInfoList.add(proporsiInfo);
                            }

                            BarDataSet barDataSet = new BarDataSet(yValues, "Total KPM");
                            barDataSet.setColors(colors);
                            barDataSet.setValueTextColor(Color.BLACK);

                            data = new BarData(barDataSet);

                            chart.setData(data);
                            chart.getDescription().setText(getString(R.string.setDescription));
                            chart.animateXY(2000, 2000);
                            chart.invalidate();

                            // Set custom axis value formatter for X-axis labels
                            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
                            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // Posisi sumbu X berada di bawah
                            chart.getXAxis().setGranularity(1f); // Jarak antara label yang ditampilkan
                            chart.getXAxis().setLabelCount(xAxis.size()); // Jumlah label yang ditampilkan
                            chart.getXAxis().setValueFormatter(new MyValueFormatter());

                            // Set the custom legend for color descriptions
                            Legend legend = chart.getLegend();
                            legend.setTextSize(12f);
                            legend.setForm(Legend.LegendForm.CIRCLE);
                            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
                            legend.setWordWrapEnabled(true);

                            // Create custom entries for legend descriptions
                            List<LegendEntry> legendEntries = new ArrayList<>();
                            legendEntries.add(new LegendEntry("Tinggi", Legend.LegendForm.CIRCLE, 12f, 2f, null, Color.RED));
                            legendEntries.add(new LegendEntry("Sedang", Legend.LegendForm.CIRCLE, 12f, 2f, null, Color.YELLOW));
                            legendEntries.add(new LegendEntry("Rendah", Legend.LegendForm.CIRCLE, 12f, 2f, null, Color.GREEN));
                            //legendEntries.add(new LegendEntry("Data Kosong", Legend.LegendForm.CIRCLE, 12f, 2f, null, Color.BLUE));

                            legend.setCustom(legendEntries);

                            // Set custom axis value formatter for X-axis labels
                            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
                            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                            chart.getXAxis().setGranularity(1f);
                            chart.getXAxis().setLabelCount(xAxis.size());
                            chart.getXAxis().setValueFormatter(new MyValueFormatter());

                            chart.setData(data);
                            chart.getDescription().setText(getString(R.string.setDescription));
                            chart.animateXY(2000, 2000);
                            chart.invalidate();

                            pd.dismiss();

                            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                                @Override
                                public void onValueSelected(Entry e, Highlight h) {
                                    int position = (int) e.getX();
                                    String selectedXValue = xAxis.get(position);
                                    String selectedYValue = String.valueOf(e.getY());

                                    Toast.makeText(getActivity(), "Kelurahan: " + selectedXValue + ", Jumlah KPM: " + selectedYValue, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNothingSelected() {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.dismiss();
                            Toast.makeText(getActivity(), "Failed to load data.", Toast.LENGTH_LONG).show();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Connection error.", Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                }
        );

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    public class MyValueFormatter extends IndexAxisValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            int intValue = (int) value;
            if (intValue >= 0 && intValue < xAxis.size()) {
                return xAxis.get(intValue);
            }
            return "";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }
}
