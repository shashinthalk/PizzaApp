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

public class HotProductAdapter extends RecyclerView.Adapter<HotProductAdapter.HotProductViewHolder> {
    private static double DouPrice = 0;
    private Context mCtx;
    private List<HotProductsClass> hotProductsClassList;

    public HotProductAdapter(Context mCtx, List<HotProductsClass> hotProductsClassList) {
        this.mCtx = mCtx;
        this.hotProductsClassList = hotProductsClassList;
    }

    @Override
    public HotProductAdapter.HotProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.hot_product_list, null);
        return new HotProductAdapter.HotProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final HotProductAdapter.HotProductViewHolder hotProductViewHolder, int position) {

        final HotProductsClass HotProductClass = hotProductsClassList.get(position);

        hotProductViewHolder.textViewDesc.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ");
        hotProductViewHolder.textViewTitle.setText(HotProductClass.getShortdesc());
        //hotProductViewHolder.textViewTitle.setText("");
        hotProductViewHolder.textViewPrice.setText(String.valueOf(HotProductClass.getPrice()));
        //hotProductViewHolder.textViewPrice.setText("");
        hotProductViewHolder.textViewRating.setText(String.valueOf(HotProductClass.getRating()));
        //hotProductViewHolder.textViewRating.setText("");
        hotProductViewHolder.textViewstatus.setText(HotProductClass.getStatus());
        //hotProductViewHolder.textViewstatus.setText("");


        hotProductViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, SingleViewActivity.class);
                intent.putExtra("title", HotProductClass.getTitle());
                intent.putExtra("description", HotProductClass.getShortdesc());
                intent.putExtra("rating", String.valueOf(HotProductClass.getRating()));
                intent.putExtra("status", HotProductClass.getStatus());
                intent.putExtra("image", HotProductClass.getImage());
                intent.putExtra("price", String.valueOf(HotProductClass.getPrice()));
                DouPrice = HotProductClass.getPrice();
                intent.putExtra("DouPrice", DouPrice);
                mCtx.startActivity(intent);
            }
        });


        Glide.with(mCtx)
                .load(HotProductClass.getImage())
                .into(hotProductViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return hotProductsClassList.size();
    }

    class HotProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice, textViewstatus;
        RelativeLayout relativeLayout;

        public HotProductViewHolder(View itemView) {
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
