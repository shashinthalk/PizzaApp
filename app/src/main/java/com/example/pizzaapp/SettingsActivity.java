package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SettingsActivity extends AppCompatActivity {
    private static String ip;
    EditText getIp;
    LinearLayout ipView,ipViewSelect;
    private static int ipViewStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ipView = findViewById(R.id.ipView);
        ipViewSelect = findViewById(R.id.ipViewSelect);
        ipViewSelect.setVisibility(View.GONE);
        getIp = findViewById(R.id.getIp);
        ip = String.valueOf(getIp.getText());
    }

    public void openIpView(View view) {
        if(ipViewStatus==0){
            ipViewStatus = 1;
            ipViewSelect.setVisibility(View.VISIBLE);
        }else{
            ipViewStatus = 0;
            ipViewSelect.setVisibility(View.GONE);
        }

    }


    public void openLoginWithIp(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("ip",ip);
        startActivity(intent);
    }
}
