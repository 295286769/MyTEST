package com.richfinancial.qiyiplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_main);
/*
        findViewById(R.id.go_qyc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAty.this, com.richfinancial.qiyifinance.activity.main.MainAty.class));
                MainAty.this.overridePendingTransition(R.anim.popup_show,0);
            }
        });*/
    }
}
