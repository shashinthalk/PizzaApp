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
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Dialog myDialog;
    private EditText email, password;
    private ProgressBar loading;
    private static String sentURL="";
    private Button signUpButton,loginButton;
    Connection_Detector connection_detector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDialog = new Dialog(this);
        connection_detector=new Connection_Detector(this);
        loading = findViewById(R.id.log_loading);
        email = findViewById(R.id.log_email);
        password = findViewById(R.id.log_password);
        ShowPopup();
        if (connection_detector.isConnected())
        {
        }else
        {
            connectionStatus();
        }
        loginButton = findViewById(R.id.btn_products);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connection_detector.isConnected())
                {
                    String mEmail = email.getText().toString().trim();
                    String mPassword = password.getText().toString().trim();

                    if(!mEmail.isEmpty()){
                        if(mEmail.indexOf('@')!=-1){
                            if(mEmail.indexOf('.')!=-1){
                                if(!mPassword.isEmpty()){
                                    loginFuncton(mEmail,mPassword);
                                }else{
                                    password.setError("Please insert Password");
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Email is incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Email is incorrect",Toast.LENGTH_SHORT).show();
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

    private void loginFuncton(final String email, final String password){
        loading.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+UserIdSession.getIpAdress()+":8080/system/loginUser?email="+email+"&password="+password+"",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String res = response;
                            if(res.equals("Email is incorrect")||res.equals("Password is incorrect")){
                                loading.setVisibility(View.GONE);
                                loginButton.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this,res,Toast.LENGTH_SHORT).show();
                            }else if(!res.equals("")){
                                Toast.makeText(LoginActivity.this,res,Toast.LENGTH_SHORT).show();
                                openProductActivity();
                                UserIdSession.setUsId(res);
                                loading.setVisibility(View.GONE);
                                loginButton.setVisibility(View.VISIBLE);
                            }else{
                                loading.setVisibility(View.GONE);
                                loginButton.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this,"User name or Password is incorrect!" ,Toast.LENGTH_SHORT).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"Sever not-respond!",Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            loginButton.setVisibility(View.VISIBLE);
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


    public void openSigninActivity(String url){
        Intent intent = new Intent(this, RegisterActivity.class);
        email.setText("");
        password.setText("");
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void openProductActivity(){
        Intent intent = new Intent(this, ProductsViewActivity.class);
        startActivity(intent);
        email.setText("");
        password.setText("");
    }

    public void Exitnow(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }



    public void ShowPopup() {
        TextView txtclose;Button popUpIpGet;
        final EditText ipAddressGet;
        myDialog.setContentView(R.layout.activity_ippopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        popUpIpGet = myDialog.findViewById(R.id.getIpAdrss);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        ipAddressGet = myDialog.findViewById(R.id.ipadress);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

        popUpIpGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserIdSession.setIpAdress(String.valueOf(ipAddressGet.getText()));
                if(UserIdSession.getIpAdress().equals(String.valueOf(ipAddressGet.getText()))){
                    Toast.makeText(LoginActivity.this,"Ip successfully added!",Toast.LENGTH_SHORT).show();
                }
                myDialog.dismiss();
            }
        });
    }

    public void openSettingsPopup(View view) {

        myDialog.setContentView(R.layout.settings_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();



    }

    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}
