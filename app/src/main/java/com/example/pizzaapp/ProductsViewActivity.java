package com.example.pizzaapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsViewActivity extends AppCompatActivity {
    private static String sts_now = "";
    Dialog dialog;
    RecyclerView recyclerView01, recyclerView02;
    ProductAdapter adapter;
    HotProductAdapter hotAdapter;
    List<ProductClass> productClassList;
    List<HotProductsClass> hotProductClassList;
    Button openSettingsPopup;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        name = findViewById(R.id.text_view_login);
        productClassList = new ArrayList<>();
        hotProductClassList = new ArrayList<>();
        recyclerView01 = (RecyclerView) findViewById(R.id.recycleView01);
        recyclerView02 = (RecyclerView) findViewById(R.id.recycleView02);
        recyclerView01.setHasFixedSize(true);
        recyclerView02.setHasFixedSize(true);
        recyclerView01.setLayoutManager(new LinearLayoutManager(this));
        recyclerView02.setLayoutManager(new LinearLayoutManager(this));
        recyclerView01.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        loadProducts();
        loadHotProducts();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://" + UserIdSession.getIpAdress() + ":8080/system/findByUserId?id="+Integer.parseInt(UserIdSession.getUsId())+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        name.setText("Hi "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProductsViewActivity.this);
                builder.setTitle("Warning!")
                        .setMessage("Server connection error").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ProductsViewActivity.super.onBackPressed();
                            }
                        }).create().show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
        adapter = new ProductAdapter(this, productClassList);
        hotAdapter = new HotProductAdapter(this, hotProductClassList);
        recyclerView01.setAdapter(adapter);
        recyclerView02.setAdapter(hotAdapter);
        openSettingsPopup = findViewById(R.id.btn_open_settings);
        openSettingsPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This button have some error.", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView mimageView = (ImageView) findViewById(R.id.image_view);
        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher_foreground)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);
    }


    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://" + UserIdSession.getIpAdress() + ":8080/system/getAllProducts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for (int i = 0; i < products.length(); i++) {
                                JSONObject productObject = products.getJSONObject(i);

                                int id = productObject.getInt("id");
                                String title = productObject.getString("title");
                                String shortDescription = productObject.getString("shortdesc");
                                double price = productObject.getDouble("price");
                                double rating = productObject.getDouble("rating");
                                String image = productObject.getString("image");
                                String status = productObject.getString("status");
                                ProductClass ProductClass = new ProductClass(id, title, shortDescription, rating, price, image, status, sts_now);
                                productClassList.add(ProductClass);
                            }
                            adapter = new ProductAdapter(ProductsViewActivity.this, productClassList);
                            recyclerView01.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProductsViewActivity.this);
                builder.setTitle("Warning!")
                        .setMessage("Server connection error").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ProductsViewActivity.super.onBackPressed();
                            }
                        }).create().show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void loadHotProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://" + UserIdSession.getIpAdress() + ":8080/system/getAllProducts",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray products = new JSONArray(response);
                            for (int i = 0; i < products.length(); i++) {
                                JSONObject productObject = products.getJSONObject(i);

                                int id = productObject.getInt("id");
                                String title = productObject.getString("title");
                                String shortDescription = productObject.getString("shortdesc");
                                double price = productObject.getDouble("price");
                                double rating = productObject.getDouble("rating");
                                String image = productObject.getString("image");
                                String status = productObject.getString("status");
                                HotProductsClass hotProductsClass = new HotProductsClass(id, title, shortDescription, rating, price, image, status, sts_now);
                                hotProductClassList.add(hotProductsClass);
                            }
                            hotAdapter = new HotProductAdapter(ProductsViewActivity.this, hotProductClassList);
                            recyclerView02.setAdapter(hotAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProductsViewActivity.this);
                builder.setTitle("Warning!")
                        .setMessage("Server connection error").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ProductsViewActivity.super.onBackPressed();
                            }
                        }).create().show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Do you want to logout ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ProductsViewActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();
    }

    public void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    
    public void openSettingsPopup() {
        dialog.setContentView(R.layout.activity_ippopup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void ShowPopup() {
        TextView txtclose;Button popUpIpGet;
        final EditText ipAddressGet;
        dialog.setContentView(R.layout.activity_ippopup);
        txtclose =(TextView) dialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        popUpIpGet = dialog.findViewById(R.id.getIpAdrss);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ipAddressGet = dialog.findViewById(R.id.ipadress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        popUpIpGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserIdSession.setIpAdress(String.valueOf(ipAddressGet.getText()));
                if(UserIdSession.getIpAdress().equals(String.valueOf(ipAddressGet.getText()))){
                    Toast.makeText(ProductsViewActivity.this,"Ip successfully added!",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
}
