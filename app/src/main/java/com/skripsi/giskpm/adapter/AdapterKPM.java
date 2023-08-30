package com.skripsi.giskpm.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.skripsi.giskpm.R;
import com.skripsi.giskpm.model.DataModelKPM;

import java.util.List;

public class AdapterKPM extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataModelKPM> item;

    public AdapterKPM(Activity activity, List<DataModelKPM> item) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.listkpm, null);

        //TextView txtidkpm = (TextView) convertView.findViewById(R.id.txtid_kpm);
        //TextView txt_namakelurahankpm = (TextView) convertView.findViewById(R.id.txt_kelurahankpm);
        TextView txt_namausahakpm = (TextView) convertView.findViewById(R.id.txt_nikkpm);
        TextView txt_namakpm = (TextView) convertView.findViewById(R.id.txt_namakpm);
        TextView txt_alamatkpm = (TextView) convertView.findViewById(R.id.txt_alamat);
        TextView txt_telpkpm = (TextView) convertView.findViewById(R.id.txt_telp);


        //txtidkpm.setText(item.get(position).getId_kpm());
        //txt_namakelurahankpm.setText("Kelurahan : "+item.get(position).getNama_kelurahankpm());
        txt_namausahakpm.setText(item.get(position).getNik_kpm());
        txt_namakpm.setText(item.get(position).getNama_kpm());
        txt_alamatkpm.setText(item.get(position).getAlamatkpm());
        txt_telpkpm.setText(item.get(position).getTelpkpm());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, ""+item.get(position).getNama_kpm(), Toast.LENGTH_SHORT).show();
                final String tlp=item.get(position).getTelpkpm();
                final String lat=item.get(position).getLatitudekpm();
                final String lng=item.get(position).getLongitudekpm();
                final CharSequence[] items = {"Lihat Rute Lokasi" , "Telepon KPM"};
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                builder.setTitle("Pilihan Opsi");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO Auto-generated method stub
                        if (items[item].equals("Telepon KPM")) {
                            if (tlp.equals("kosong")){
                                Toast.makeText(activity, "No Telp Tidak ada", Toast.LENGTH_SHORT).show();
                            }else {
                                activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tlp)));
                                activity.finish();
                            }
                        }
                        if(items[item].equals("Lihat Rute Lokasi")){
                            if (lat.equals("kosong")){
                                Toast.makeText(activity, "Lokasi Kosong", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(activity, "Lokasi"+ lat+","+lng, Toast.LENGTH_SHORT).show();
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lat+","+lng);
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                activity.startActivity(mapIntent);
                            }
                        }
                    }
                });
                builder.show();
            }
        });


        return convertView;
    }
}