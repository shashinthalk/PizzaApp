package com.example.pizzaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotConnectionActivity extends AppCompatActivity {

    Button retry;
    Connection_Detector connection_detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_connection);
        retry = findViewById(R.id.btn_retry);
        connection_detector=new Connection_Detector(this);
        if (connection_detector.isConnected())
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else
        {

        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connection_detector.isConnected())
                {
                    connected();
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please connect internet first",Toast.LENGTH_SHORT).show();
                    /*android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setTitle("Warning!")
                            .setMessage("Please connect internet first").setCancelable(false)

                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            }).create().show();*/
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

    }

    public void connected(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}


