package com.example.pizzaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartViewActivity extends AppCompatActivity {
    private static int count = 0;
    private static String sts_now ="";
    TextView tv_details01,tv_details02,tv_details03,tv_details04,tv_details05,tv_amount1,tv_amount2,tv_amount3,tv_amount4,tv_amount5,tv_Total_amount;
    Button tv_close_01,tv_close_02,tv_close_03,tv_close_04,tv_close_05,confirm,home;
    private static double total = 0;
    private static double pr1=0,pr2=0,pr3=0,pr4=0,pr5=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        home = findViewById(R.id.btn_home);
        confirm = findViewById(R.id.btn_confirm);
        tv_details01 = findViewById(R.id.tvdetails_01);
        tv_details02 = findViewById(R.id.tvdetails_02);
        tv_details03 = findViewById(R.id.tvdetails_03);
        tv_details04 = findViewById(R.id.tvdetails_04);
        tv_details05 = findViewById(R.id.tvdetails_05);
        tv_amount1 = findViewById(R.id.amount1);
        tv_amount2 = findViewById(R.id.amount2);
        tv_amount3 = findViewById(R.id.amount3);
        tv_amount4 = findViewById(R.id.amount4);
        tv_amount5 = findViewById(R.id.amount5);
        tv_close_01 = findViewById(R.id.close_01);
        tv_close_02 = findViewById(R.id.close_02);
        tv_close_03 = findViewById(R.id.close_03);
        tv_close_04 = findViewById(R.id.close_04);
        tv_close_05 = findViewById(R.id.close_05);
        tv_Total_amount = findViewById(R.id.Total_amount);

        tv_details01.setVisibility(View.GONE);
        tv_details02.setVisibility(View.GONE);
        tv_details03.setVisibility(View.GONE);
        tv_details04.setVisibility(View.GONE);
        tv_details05.setVisibility(View.GONE);
        tv_amount1.setVisibility(View.GONE);
        tv_amount2.setVisibility(View.GONE);
        tv_amount3.setVisibility(View.GONE);
        tv_amount4.setVisibility(View.GONE);
        tv_amount5.setVisibility(View.GONE);
        tv_close_01.setVisibility(View.GONE);
        tv_close_02.setVisibility(View.GONE);
        tv_close_03.setVisibility(View.GONE);
        tv_close_04.setVisibility(View.GONE);
        tv_close_05.setVisibility(View.GONE);

        Intent receivedIntent = getIntent();
        ArrayList<String> checkout_item_names_received = (ArrayList<String>) receivedIntent.getSerializableExtra("checkout_item_names");
        ArrayList<Double> checkout_item_prices_received = (ArrayList<Double>) receivedIntent.getSerializableExtra("checkout_item_prices");
        count = receivedIntent.getIntExtra("checkout_item_count",count);
        sts_now = receivedIntent.getStringExtra("sts");


        if(count!=0){
            if(count!=1){
                if(count!=2) {
                    if (count != 3) {
                        if (count != 4) {
                            if (count == 5) {
                                //five items
                                tv_details01.setText(checkout_item_names_received.get(0));
                                tv_amount1.setText("" + checkout_item_prices_received.get(0));
                                tv_details01.setVisibility(View.VISIBLE);
                                tv_amount1.setVisibility(View.VISIBLE);
                                tv_close_01.setVisibility(View.VISIBLE);


                                tv_details02.setText(checkout_item_names_received.get(1));
                                tv_amount2.setText("" + checkout_item_prices_received.get(1));
                                tv_details02.setVisibility(View.VISIBLE);
                                tv_amount2.setVisibility(View.VISIBLE);
                                tv_close_02.setVisibility(View.VISIBLE);

                                tv_details03.setText(checkout_item_names_received.get(2));
                                tv_amount3.setText("" + checkout_item_prices_received.get(2));
                                tv_details03.setVisibility(View.VISIBLE);
                                tv_amount3.setVisibility(View.VISIBLE);
                                tv_close_03.setVisibility(View.VISIBLE);

                                tv_details04.setText(checkout_item_names_received.get(3));
                                tv_amount4.setText("" + checkout_item_prices_received.get(3));
                                tv_details04.setVisibility(View.VISIBLE);
                                tv_amount4.setVisibility(View.VISIBLE);
                                tv_close_04.setVisibility(View.VISIBLE);

                                tv_details05.setText(checkout_item_names_received.get(4));
                                tv_amount5.setText("" + checkout_item_prices_received.get(4));
                                tv_details05.setVisibility(View.VISIBLE);
                                tv_amount5.setVisibility(View.VISIBLE);
                                tv_close_05.setVisibility(View.VISIBLE);

                                pr1 = checkout_item_prices_received.get(0);
                                pr2 = checkout_item_prices_received.get(1);
                                pr3 = checkout_item_prices_received.get(2);
                                pr4 = checkout_item_prices_received.get(3);
                                pr5 = checkout_item_prices_received.get(4);
                            } else {
                                Toast.makeText(CartViewActivity.this, "error!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //four items
                            tv_details01.setText(checkout_item_names_received.get(0));
                            tv_amount1.setText("" + checkout_item_prices_received.get(0));
                            tv_details01.setVisibility(View.VISIBLE);
                            tv_amount1.setVisibility(View.VISIBLE);
                            tv_close_01.setVisibility(View.VISIBLE);

                            tv_details02.setText(checkout_item_names_received.get(1));
                            tv_amount2.setText("" + checkout_item_prices_received.get(1));
                            tv_details02.setVisibility(View.VISIBLE);
                            tv_amount2.setVisibility(View.VISIBLE);
                            tv_close_02.setVisibility(View.VISIBLE);

                            tv_details03.setText(checkout_item_names_received.get(2));
                            tv_amount3.setText("" + checkout_item_prices_received.get(2));
                            tv_details03.setVisibility(View.VISIBLE);
                            tv_amount3.setVisibility(View.VISIBLE);
                            tv_close_03.setVisibility(View.VISIBLE);

                            tv_details04.setText(checkout_item_names_received.get(3));
                            tv_amount4.setText("" + checkout_item_prices_received.get(3));
                            tv_details04.setVisibility(View.VISIBLE);
                            tv_amount4.setVisibility(View.VISIBLE);
                            tv_close_04.setVisibility(View.VISIBLE);

                            pr1 = checkout_item_prices_received.get(0);
                            pr2 = checkout_item_prices_received.get(1);
                            pr3 = checkout_item_prices_received.get(2);
                            pr4 = checkout_item_prices_received.get(3);
                        }
                    } else {
                        //three items
                        tv_details01.setText(checkout_item_names_received.get(0));
                        tv_amount1.setText("" + checkout_item_prices_received.get(0));
                        tv_details01.setVisibility(View.VISIBLE);
                        tv_amount1.setVisibility(View.VISIBLE);
                        tv_close_01.setVisibility(View.VISIBLE);

                        tv_details02.setText(checkout_item_names_received.get(1));
                        tv_amount2.setText("" + checkout_item_prices_received.get(1));
                        tv_details02.setVisibility(View.VISIBLE);
                        tv_amount2.setVisibility(View.VISIBLE);
                        tv_close_02.setVisibility(View.VISIBLE);

                        tv_details03.setText(checkout_item_names_received.get(2));
                        tv_amount3.setText("" + checkout_item_prices_received.get(2));
                        tv_details03.setVisibility(View.VISIBLE);
                        tv_amount3.setVisibility(View.VISIBLE);
                        tv_close_03.setVisibility(View.VISIBLE);

                        pr1 = checkout_item_prices_received.get(0);
                        pr2 = checkout_item_prices_received.get(1);
                        pr3 = checkout_item_prices_received.get(2);

                    }
                }else{
                    //two items
                    tv_details01.setText(checkout_item_names_received.get(0));
                    tv_amount1.setText(""+checkout_item_prices_received.get(0));
                    tv_details01.setVisibility(View.VISIBLE);
                    tv_amount1.setVisibility(View.VISIBLE);
                    tv_close_01.setVisibility(View.VISIBLE);

                    tv_details02.setText(checkout_item_names_received.get(1));
                    tv_amount2.setText(""+checkout_item_prices_received.get(1));
                    tv_details02.setVisibility(View.VISIBLE);
                    tv_amount2.setVisibility(View.VISIBLE);
                    tv_close_02.setVisibility(View.VISIBLE);

                    pr1 = checkout_item_prices_received.get(0);
                    pr2 = checkout_item_prices_received.get(1);
                }
            }else{
                //one item
                tv_details01.setText(checkout_item_names_received.get(0));
                tv_amount1.setText(""+checkout_item_prices_received.get(0));
                tv_details01.setVisibility(View.VISIBLE);
                tv_amount1.setVisibility(View.VISIBLE);
                tv_close_01.setVisibility(View.VISIBLE);

                pr1 = checkout_item_prices_received.get(0);
            }
        }else{
            Toast.makeText(CartViewActivity.this,"No Items!" ,Toast.LENGTH_SHORT).show();
        }

        double  prices;

        for(int i=0; i<count; i++){
            prices = checkout_item_prices_received.get(i);
            total+=prices;
        }tv_Total_amount.setText(""+total);


        tv_close_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickClose01();
            }
        });
        tv_close_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickClose02();
            }
        });
        tv_close_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickClose03();
            }
        });
        tv_close_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickClose04();
            }
        });
        tv_close_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isclickClose05();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exitnow();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkout
            }
        });


    }




    public void isclickClose01(){
        total = total -pr1;
        tv_Total_amount.setText(""+total);
        tv_details01.setVisibility(View.GONE);
        tv_amount1.setVisibility(View.GONE);
        tv_close_01.setVisibility(View.GONE);
    }
    public void isclickClose02(){
        total = total -pr2;
        tv_Total_amount.setText(""+total);
        tv_details02.setVisibility(View.GONE);
        tv_amount2.setVisibility(View.GONE);
        tv_close_02.setVisibility(View.GONE);
    }
    public void isclickClose03(){
        total = total -pr3;
        tv_Total_amount.setText(""+total);
        tv_details03.setVisibility(View.GONE);
        tv_amount3.setVisibility(View.GONE);
        tv_close_03.setVisibility(View.GONE);
    }
    public void isclickClose04(){
        total = total -pr4;
        tv_Total_amount.setText(""+total);
        tv_details04.setVisibility(View.GONE);
        tv_amount4.setVisibility(View.GONE);
        tv_close_04.setVisibility(View.GONE);
    }
    public void isclickClose05(){
        total = total -pr5;
        tv_Total_amount.setText(""+total);
        tv_details05.setVisibility(View.GONE);
        tv_amount5.setVisibility(View.GONE);
        tv_close_05.setVisibility(View.GONE);
    }
    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning!")
                .setMessage("Use interface icons").setCancelable(false)

                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();


    }



    public void Exitnow(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("sts", sts_now);
        startActivity(intent);
    }
}
