package com.example.pizzaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    //private static String PRODUCT_URL="http://msrpromotion.lk/pizza.lk/api.php";
    //private static String PRODUCT_URL="http://192.168.43.66/api.php";
    //private static final String PRODUCT_URL = ;
    private static String sts_now = "";
    //private static String PRODUCT_URL = "";
    //SwipeRefreshLayout swipe;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<ProductClass> productClassList;
    private Button button;
    private Button button_logout;
    private TextView name;
    private static String ip = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        name = findViewById(R.id.text_view_login);
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");
        String extraName = intent.getStringExtra("name");
        //PRODUCT_URL = intent.getStringExtra("sentURL");
        name.setText("Hi " + extraName);
        final String sts = intent.getStringExtra("sts");
        sts_now = sts;
        productClassList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadProducts();
        adapter = new ProductAdapter(this, productClassList);
        recyclerView.setAdapter(adapter);
        button_logout = findViewById(R.id.btn_logout);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProductsViewActivity.this);
                builder.setTitle("Warning!")
                        .setMessage("Do you want logout now ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                logout();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).create().show();

            }
        });


        ImageView mimageView = (ImageView) findViewById(R.id.image_view);

        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.addvertiestment)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);
    }

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://172.19.4.122:8080/system/getAllProducts",
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
                            recyclerView.setAdapter(adapter);
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
}
