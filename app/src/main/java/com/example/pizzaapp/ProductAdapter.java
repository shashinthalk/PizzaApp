package com.example.pizzaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private static double DouPrice = 0;
    private Context mCtx;
    private List<ProductClass> productClassList;

    public ProductAdapter(Context mCtx, List<ProductClass> productClassList) {
        this.mCtx = mCtx;
        this.productClassList = productClassList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder productViewHolder, int position) {

        final ProductClass ProductClass = productClassList.get(position);

        //productViewHolder.textViewDesc.setText(ProductClass.getTitle());
        productViewHolder.textViewTitle.setText(ProductClass.getShortdesc());
        productViewHolder.textViewPrice.setText(String.valueOf(ProductClass.getPrice()));
        productViewHolder.textViewRating.setText(String.valueOf(ProductClass.getRating()));
        productViewHolder.textViewstatus.setText(ProductClass.getStatus());
        productViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, SingleViewActivity.class);
                intent.putExtra("title", ProductClass.getTitle());
                intent.putExtra("description", ProductClass.getShortdesc());
                intent.putExtra("rating", String.valueOf(ProductClass.getRating()));
                intent.putExtra("status", ProductClass.getStatus());
                intent.putExtra("image", ProductClass.getImage());
                intent.putExtra("price", String.valueOf(ProductClass.getPrice()));
                DouPrice = ProductClass.getPrice();
                intent.putExtra("DouPrice", DouPrice);
                mCtx.startActivity(intent);
            }
        });


        Glide.with(mCtx)
                .load(ProductClass.getImage())
                .into(productViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return productClassList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice, textViewstatus;
        RelativeLayout relativeLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.relative);
            textViewstatus = itemView.findViewById(R.id.textViewstatus);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }


}

