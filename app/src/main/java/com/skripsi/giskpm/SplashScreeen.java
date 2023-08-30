package com.skripsi.giskpm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreeen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        int waktu_loading = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke beranda
                Intent home=new Intent(SplashScreeen.this, MainActivity.class);
                startActivity(home);
                finish();
                overridePendingTransition(R.anim.slide_down, R.anim.slide_down);
            }
        }, waktu_loading);
    }
}
