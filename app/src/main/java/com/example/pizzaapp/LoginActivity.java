package com.example.pizzaapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Dialog myDialog;
    private static String ip = "";
    private static String sts_now ="";
    private EditText email, password;
    private ProgressBar loading;
    //private static String URL_LOGIN = "http://192.168.43.66/login.php";
    //private static final String URL_LOGIN = "";
    CheckBox localServer, onlineServer;
    private static String sentURL="";
    private Button button1,button2,btn_after;
    Connection_Detector connection_detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDialog = new Dialog(this);
        connection_detector=new Connection_Detector(this);
        localServer = findViewById(R.id.localServer);
        onlineServer = findViewById(R.id.OnlineServer);
        loading = findViewById(R.id.log_loading);
        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        btn_after = findViewById(R.id.btn_win_after_regist);
        btn_after.setVisibility(View.GONE);
        Intent intent = getIntent();
        ShowPopup();
        ip = intent.getStringExtra("ip");
        if (connection_detector.isConnected())
        {
        }else
        {
            connectionStatus();
        }

        try
        {

            final String sts = intent.getStringExtra("sts");
            if(sts=="1"){
                sts_now="0";
                Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
            }else if(sts=="0"){
                sts_now="1";
                Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
            }else{
                sts_now="1";
                //Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception e)
        {
            sts_now="1";
            Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
        }




        button1 = findViewById(R.id.btn_win_regist);
        btn_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connection_detector.isConnected())
                {
                    String URL = "";
                    int local = 0;
                    int online = 0;
                    if(localServer.isChecked() || onlineServer.isChecked()){
                        if(localServer.isChecked()){
                            local =1;
                        }
                        if(onlineServer.isChecked()){
                            online = 1;
                        }
                    }

                    if(local ==1){
                        if(online ==1){
                            Toast.makeText(getApplicationContext(),"Please check one server",Toast.LENGTH_SHORT).show();
                        }else{
                            //local
                            URL = "http://192.168.43.66/register.php";
                            Toast.makeText(getApplicationContext(),"local server selected",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(online ==1){
                            //online
                            URL = "http://msrpromotion.lk/pizza.lk/register.php";
                            Toast.makeText(getApplicationContext(),"online server selected",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Please select server",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!URL.equals("")){
                        openRegister(URL);
                    }

                }else
                {
                    connectionStatus();
                }

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connection_detector.isConnected())
                {
                    String URL = "";
                    int local = 0;
                    int online = 0;
                    if(localServer.isChecked() || onlineServer.isChecked()){
                        if(localServer.isChecked()){
                            local =1;
                        }
                        if(onlineServer.isChecked()){
                            online = 1;
                        }
                    }

                    if(local ==1){
                        if(online ==1){
                            Toast.makeText(getApplicationContext(),"Please check one server",Toast.LENGTH_SHORT).show();
                        }else{
                            //local
                            URL = "http://192.168.43.66/register.php";
                            Toast.makeText(getApplicationContext(),"local server selected",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(online ==1){
                            //online
                            URL = "http://msrpromotion.lk/pizza.lk/register.php";
                            Toast.makeText(getApplicationContext(),"online server selected",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Please select server",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!URL.equals("")){
                        openRegister(URL);
                    }

                }else
                {
                    connectionStatus();
                }

            }
        });

        button2 = findViewById(R.id.btn_products);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connection_detector.isConnected())
                {
                    String mEmail = email.getText().toString().trim();
                    String mPassword = password.getText().toString().trim();

                    if(!mEmail.isEmpty()){
                        if(!mPassword.isEmpty()){
                            login(mEmail,mPassword);
                        }else{
                            password.setError("Please insert Password");
                        }
                    }else{
                        email.setError("Please insert email");
                    }

                }else
                {
                    connectionStatus();

                }

            }
        });


    }

    private void login(final String email, final String password){
        loading.setVisibility(View.VISIBLE);
        button2.setVisibility(View.GONE);

        String URL = "";
        int local = 0;
        int online = 0;
        if(localServer.isChecked() || onlineServer.isChecked()){
            if(localServer.isChecked()){
                local =1;
            }
            if(onlineServer.isChecked()){
                online = 1;
            }
        }

        if(local ==1){
            if(online ==1){
                Toast.makeText(getApplicationContext(),"Please check one server",Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
            }else{
                //local
                URL = "http://192.168.43.66/login.php";
                sentURL = "http://192.168.43.66:8080/demo/all";
                //Toast.makeText(getApplicationContext(),"local server selected",Toast.LENGTH_SHORT).show();

            }
        }else{
            if(online ==1){
                //online
                URL = "http://msrpromotion.lk/pizza.lk/login.php";
                sentURL = "http://192.168.43.66:8080/demo/all";
                //Toast.makeText(getApplicationContext(),"online server selected",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getApplicationContext(),"Please select server",Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
            }
        }
        if(!URL.equals("")){



            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray Products = new JSONArray(response);

                                for (int i=0; i<Products.length(); i++){
                                    JSONObject productObject = Products.getJSONObject(i);
                                    String status = productObject.getString("status");
                                    String name = productObject.getString("name");

                                    if(status.equals("OK")){
                                        openProducts(name,sentURL);
                                        loading.setVisibility(View.GONE);
                                        button2.setVisibility(View.VISIBLE);
                                    }else{
                                        loading.setVisibility(View.GONE);
                                        button2.setVisibility(View.VISIBLE);
                                        btn_after.setVisibility(View.VISIBLE);
                                        button1.setVisibility(View.GONE);
                                        btn_after.setError("RegisterActivity first");
                                        Toast.makeText(LoginActivity.this,"User name or Password is incorrect!" ,Toast.LENGTH_SHORT).show();
                                    }


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"Sever not-respond!",Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            button2.setVisibility(View.VISIBLE);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("email",email);
                    params.put("password" ,password);
                    return  params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }


    }



    @Override
    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Do you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Exitnow();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();


    }

    public void connectionStatus(){

        Intent intent = new Intent(this, NotConnectionActivity.class);
        startActivity(intent);

    }


    public void openRegister(String url){
        Intent intent = new Intent(this, RegisterActivity.class);
        email.setText("");
        password.setText("");
        btn_after.setVisibility(View.GONE);
        button1.setVisibility(View.VISIBLE);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void openProducts(String name, String sentURL){
        Intent intent = new Intent(this, ProductsViewActivity.class);
        intent.putExtra("sentURL", sentURL);
        intent.putExtra("name", name);
        intent.putExtra("sts", sts_now);
        intent.putExtra("ip", ip);
        startActivity(intent);
        email.setText("");
        password.setText("");
    }

    public void Exitnow(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    public void ShowPopup() {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.activity_ippopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        //btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
