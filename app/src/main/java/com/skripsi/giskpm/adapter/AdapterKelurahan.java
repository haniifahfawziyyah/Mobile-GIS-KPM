package com.skripsi.giskpm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skripsi.giskpm.R;
import com.skripsi.giskpm.model.DataModelKelurahan;

import java.util.List;

public class AdapterKelurahan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModelKelurahan> item;

    public AdapterKelurahan(Activity activity, List<DataModelKelurahan> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.listkelurahan, null);

        TextView txtid = (TextView) convertView.findViewById(R.id.txtid_kelurahan);
        TextView txt_namakelurahan = (TextView) convertView.findViewById(R.id.txt_kelurahan);
        TextView txt_jumlahkpm = (TextView) convertView.findViewById(R.id.txt_jumlahkpm);

        txtid.setText(item.get(position).getId_kelurahan());
        txt_namakelurahan.setText(item.get(position).getNama_kelurahan());
        txt_jumlahkpm.setText(item.get(position).getJumlahkpm());

        return convertView;
    }
}