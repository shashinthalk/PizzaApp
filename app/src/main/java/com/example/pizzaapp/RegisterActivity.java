package com.example.pizzaapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name_t , email_t , password_t , c_password_t ,mobile_number_t;
    private Button btn_regist;
    private ProgressBar loading;
    //private static String URL_REGIST="http://192.168.43.66/register.php";
    //private static String URL_REGIST="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        name_t = findViewById(R.id.name);
        email_t = findViewById(R.id.email);
        password_t = findViewById(R.id.password);
        c_password_t = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);
        mobile_number_t = findViewById(R.id.mobile);
        Intent intent = getIntent();
        //URL_REGIST = intent.getStringExtra("url");

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });



    }




    @Override
    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Are you want to go LOGIN page ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RegisterActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();


    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name = this.name_t.getText().toString().trim();
        final String email = this.email_t.getText().toString().trim();
        final String password = this.password_t.getText().toString().trim();
        final String c_password = this.c_password_t.getText().toString().trim();
        final String mobile_number = this.mobile_number_t.getText().toString().trim();



        if(!name.isEmpty()){
            if(!email.equals("")){
                if(!password.equals("")){
                    if(!c_password.equals("")){
                        if(!mobile_number.equals("")){
                            if(mobile_number.length()==10){
                                if(password.length()>5){
                                    if (password.equals(c_password)){
                                        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://172.19.4.122:8080/system/addNewUser?name="+name+"&email="+email+"&mobile="+mobile_number+"&password="+password+"",
                                                new Response.Listener<String>(){
                                                    @Override
                                                    public void onResponse(String response){
                                                        if(response.equals("Successfully Registered")){
                                                            name_t.setText("");
                                                            email_t.setText("");
                                                            password_t.setText("");
                                                            c_password_t.setText("");
                                                            mobile_number_t.setText("");
                                                            loading.setVisibility(View.GONE);
                                                            btn_regist.setVisibility(View.VISIBLE);
                                                            Toast.makeText(RegisterActivity.this,response, Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            loading.setVisibility(View.GONE);
                                                            btn_regist.setVisibility(View.VISIBLE);
                                                            Toast.makeText(RegisterActivity.this,response, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                },
                                                new Response.ErrorListener(){
                                                    @Override
                                                    public void onErrorResponse(VolleyError error){
                                                        Toast.makeText(RegisterActivity.this,"RegisterActivity Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                                                        loading.setVisibility(View.GONE);
                                                        btn_regist.setVisibility(View.VISIBLE);
                                                    }
                                                })
                                        {
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {

                                                Map<String, String> params = new HashMap<>();
                                                params.put("name",name);
                                                params.put("email",email);
                                                params.put("password",password);
                                                params.put("mobile_number" ,mobile_number);
                                                return params;
                                            }
                                        };

                                        RequestQueue requestQueue = Volley.newRequestQueue(this);
                                        requestQueue.add(stringRequest);

                                    }else{
                                        Toast.makeText(getApplicationContext(),"Password and Confirmation Password does not match",Toast.LENGTH_SHORT).show();
                                        password_t.setText("");
                                        c_password_t.setText("");
                                        loading.setVisibility(View.GONE);
                                        btn_regist.setVisibility(View.VISIBLE);

                                    }
                                }else{
                                    Toast.makeText(getApplicationContext(),"Password should be greater than five characters",Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);
                                    password_t.setText("");
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Mobile number is incorrect",Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                                mobile_number_t.setText("");
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"Please enter mobile number",Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Please enter password confirmation",Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btn_regist.setVisibility(View.VISIBLE);
                }
            }else{
                Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btn_regist.setVisibility(View.VISIBLE);
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
            loading.setVisibility(View.GONE);
            btn_regist.setVisibility(View.VISIBLE);
        }
    }
}
