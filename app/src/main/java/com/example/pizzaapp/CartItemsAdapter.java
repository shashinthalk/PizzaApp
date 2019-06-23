package com.example.pizzaapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemsViewHolder>{

    private Context mCtx;
    private List<CartItemClass> cartItemClassList;

    public CartItemsAdapter(Context mCtx, List<CartItemClass> cartItemClassList) {
        this.mCtx = mCtx;
        this.cartItemClassList = cartItemClassList;
    }

    @Override
    public CartItemsAdapter.CartItemsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_item_layout, null);
        return new CartItemsAdapter.CartItemsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CartItemsAdapter.CartItemsViewHolder cartItemsViewHolder, int position) {

        final CartItemClass cartItemClass = cartItemClassList.get(position);

        /*hotProductViewHolder.textViewDesc.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ");
        hotProductViewHolder.textViewTitle.setText(HotProductClass.getShortdesc());
        //hotProductViewHolder.textViewTitle.setText("");
        hotProductViewHolder.textViewPrice.setText(String.valueOf(HotProductClass.getPrice()));
        //hotProductViewHolder.textViewPrice.setText("");
        hotProductViewHolder.textViewRating.setText(String.valueOf(HotProductClass.getRating()));
        //hotProductViewHolder.textViewRating.setText("");
        hotProductViewHolder.textViewstatus.setText(HotProductClass.getStatus());
        //hotProductViewHolder.textViewstatus.setText("");*/



        //****************************************************************************
        //Works for done = Develop the CartItemClass and get details from web service.
        //****************************************************************************





        cartItemsViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(mCtx, SingleViewActivity.class);
                intent.putExtra("title", HotProductClass.getTitle());
                intent.putExtra("description", HotProductClass.getShortdesc());
                intent.putExtra("rating", String.valueOf(HotProductClass.getRating()));
                intent.putExtra("status", HotProductClass.getStatus());
                intent.putExtra("image", HotProductClass.getImage());
                intent.putExtra("price", String.valueOf(HotProductClass.getPrice()));
                DouPrice = HotProductClass.getPrice();
                intent.putExtra("DouPrice", DouPrice);
                mCtx.startActivity(intent);*/
            }
        });


        /*Glide.with(mCtx)
                .load(HotProductClass.getImage())
                .into(hotProductViewHolder.imageView);*/

    }

    @Override
    public int getItemCount() {
        return cartItemClassList.size();
    }

    class CartItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtItemName, txtItemTotal, txtItemQuantity;
        RelativeLayout relativeLayout;

        public CartItemsViewHolder(View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.relativeCartLayout);
            imageView = itemView.findViewById(R.id.itemCartImage);
            txtItemName = itemView.findViewById(R.id.itemName);
            txtItemQuantity = itemView.findViewById(R.id.itemQuantity);
            txtItemTotal = itemView.findViewById(R.id.itemTotal);
        }
    }
}
