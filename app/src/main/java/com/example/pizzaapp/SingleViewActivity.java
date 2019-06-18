package com.example.pizzaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingleViewActivity extends AppCompatActivity {
    private static String sts_now ="";
    private static int check_availability;
    private static String title_gone;
    ArrayList<Double> checkout_item_prices_send;
    ArrayList<String> checkout_item_names_send;
    private static double[] checkout_item_prices = new double[6];
    private static String[] checkout_item_names = new String[6];
    private static int checkout_item_count = 0;
    public int countItem = 1;
    private static double cartTotal = 0;
    private static double DouPrice = 0;
    private static double newDouPrice = 0;
    Button plus,minus ,addtoCart ,checkout_btn, button_logout;
    private Context mCtx;
    private List<ProductClass> productClassList;
    ImageView imageView;
    TextView count , title, description ,rating , status , price ,items_count , warning;
    private static final String TAG = "SingleViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkout_item_prices_send = new ArrayList<>();
        checkout_item_names_send = new ArrayList<>();
        setContentView(R.layout.activity_single_product_view);
        addtoCart = findViewById(R.id.addtocart);
        count = findViewById(R.id.count);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        items_count = findViewById(R.id.itemCount);
        checkout_btn = findViewById(R.id.btn_checkout);
        warning = findViewById(R.id.warning);

        button_logout = findViewById(R.id.btn_logout);

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SingleViewActivity.this);
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


        if(checkout_item_count == 0 || checkout_item_count == 5){
            if(checkout_item_count ==0){
                warning.setText("Cart Empty !");
            }else{
                warning.setText("Cart Full !");
            }
        }else{
            warning.setText("");
        }


        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkout_item_count!=0){
                    checkout_item_prices_send.clear();
                    checkout_item_names_send.clear();
                    int length = checkout_item_count;
                    for(int i=0; i<length; i++){
                        checkout_item_names_send.add(i,checkout_item_names[i]);
                        //Toast.makeText(SingleViewActivity.this,"Index"+String.valueOf(i)+"Value "+checkout_item_names[i] ,Toast.LENGTH_SHORT).show();
                    }

                    for(int y=0; y<length; y++){
                        checkout_item_prices_send.add(y,checkout_item_prices[y]);
                        //Toast.makeText(SingleViewActivity.this,"Index"+String.valueOf(y)+"Value "+String.valueOf(checkout_item_prices[y]) ,Toast.LENGTH_SHORT).show();
                    }
                    checkoutOpen();
                    //Check values
                    /*for(int z=0; z<checkout_item_names_send.size(); z++){
                        Toast.makeText(SingleViewActivity.this,"Index"+String.valueOf(z)+"Value "+checkout_item_prices_send.get(z) ,Toast.LENGTH_SHORT).show();
                    }
                    for(int z=0; z<checkout_item_names_send.size(); z++){
                        Toast.makeText(SingleViewActivity.this,"Index"+String.valueOf(z)+"Value "+checkout_item_names_send.get(z) ,Toast.LENGTH_SHORT).show();
                    }*/
                }else{
                    Toast.makeText(SingleViewActivity.this,"Cart empty!" ,Toast.LENGTH_SHORT).show();
                }

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sta = 1;
                ActiveCount(sta);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sta = 0;
                ActiveCount(sta);
            }
        });


        imageView = findViewById(R.id.image);
        Intent intent = getIntent();
        String exTitle =intent.getStringExtra("title");
        String exDescription =intent.getStringExtra("description" );
        String exRating =intent.getStringExtra("rating");
        String exStatus =intent.getStringExtra("status");
        String exImage =intent.getStringExtra("image");
        String exPrice =intent.getStringExtra("price");
        sts_now = intent.getStringExtra("sts");
        DouPrice = intent.getDoubleExtra("DouPrice",DouPrice);

        items_count.setText("Items:"+checkout_item_count);
        title = findViewById(R.id.title);
        rating = findViewById(R.id.Rating);
        description = findViewById(R.id.description_text);
        price = findViewById(R.id.Price);
        status = findViewById(R.id.Status);
        title_gone = exDescription;
        title.setText(exDescription);
        rating.setText(exRating);
        price.setText("Price : Rs."+DouPrice);
        newDouPrice = +DouPrice;
        status.setText(exStatus);
        description.setText(exTitle);
        loadImage(exImage);


        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_availability=0;
                for(int y=0; y<checkout_item_count; y++){
                    if(checkout_item_names[y]==title_gone){
                        check_availability = 1;
                    }else{
                        check_availability = 0;
                    }
                }

                if(check_availability == 1){
                    int count = 100;
                    for(int i=0; i<checkout_item_count; i++){
                        if(checkout_item_names[i]==title_gone){
                            count = i;
                        }
                    }
                    if(count==100){
                        checkout_item_prices[checkout_item_count]=newDouPrice;
                        checkout_item_names[checkout_item_count]=title_gone;
                        checkout_item_count++;
                        items_count.setText("Items:"+checkout_item_count);
                    }else{
                        checkout_item_prices[count]=checkout_item_prices[count] + newDouPrice;
                        items_count.setText("Items:"+checkout_item_count);
                        Toast.makeText(SingleViewActivity.this,"Updated!" ,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(checkout_item_count!=5 || checkout_item_count<5){
                        int count = 100;
                        for(int i=0; i<checkout_item_count; i++){
                            if(checkout_item_names[i]==title_gone){
                                count = i;
                            }
                        }
                        if(count==100){
                            checkout_item_prices[checkout_item_count]=newDouPrice;
                            checkout_item_names[checkout_item_count]=title_gone;
                            checkout_item_count++;
                            items_count.setText("Items:"+checkout_item_count);
                            warning.setText("");
                            Toast.makeText(SingleViewActivity.this,"Added to Cart! Amount :"+String.valueOf(newDouPrice),Toast.LENGTH_SHORT).show();

                        }else{
                            checkout_item_prices[count]=checkout_item_prices[count] + newDouPrice;
                            items_count.setText("Items:"+checkout_item_count);
                            warning.setText("");
                            Toast.makeText(SingleViewActivity.this,"Updated!"+checkout_item_names[count] ,Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(SingleViewActivity.this,"You have exceeded cart limit.Please checkout first" ,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    private void loadImage(String exImage) {

        Picasso.with(this).load(exImage).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    public void ActiveCount(int sta){
        if(sta==1){
            countItem ++;
            newDouPrice+=DouPrice;
            price.setText("Price : Rs."+newDouPrice);
            count.setText(String.valueOf(countItem));
        }else{
            if(countItem!=1){
                countItem--;
                newDouPrice-=DouPrice;
                price.setText("Price : Rs."+newDouPrice);
                count.setText(String.valueOf(countItem));
            }
        }
    }

    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Do you want add more?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartTotal+=newDouPrice;
                        newDouPrice = 0;
                        SingleViewActivity.super.onBackPressed();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();


    }

    public void checkoutOpen(){
        Intent intent = new Intent(this, CartViewActivity.class);
        intent.putExtra("checkout_item_prices", checkout_item_prices_send);
        intent.putExtra("checkout_item_names", checkout_item_names_send);
        intent.putExtra("checkout_item_count", checkout_item_count);
        intent.putExtra("sts", sts_now);
        for(int z = 1; z<checkout_item_names.length; z++){
            checkout_item_names[z] = "";
        }
        for(int z = 1; z<checkout_item_prices.length; z++){
            checkout_item_prices[z] = 0;
        }
        checkout_item_count=0;
        startActivity(intent);
    }

    public void logout(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
