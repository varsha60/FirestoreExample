package com.example.android.firestoreexample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context mCtx;
    private List<Product> products;

    public ProductAdapter(Context mCtx, List<Product> products) {
        this.mCtx = mCtx;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
      View view = inflater.inflate(R.layout.list_item,parent,false);
      ProductHolder holder = new ProductHolder(view);
      return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
         Product product = products.get(position);
         holder.textviewname.setText(product.getName());
         holder.textviewbrand.setText(product.getBrand());
         holder.textviewdesc.setText(product.getDesc());
         holder.textviewqty.setText("Number of units available:"+String.valueOf(product.getQty()));
         holder.textviewprice.setText("INR "+String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder
    {
        TextView textviewname,textviewbrand,textviewdesc,textviewqty,textviewprice;

        public ProductHolder(View itemView) {
            super(itemView);

            textviewname =itemView.findViewById(R.id.textViewName);
            textviewbrand = itemView.findViewById(R.id.textViewBrand);
            textviewdesc = itemView.findViewById(R.id.textViewDesc);
            textviewqty = itemView.findViewById(R.id.textViewQty);
            textviewprice= itemView.findViewById(R.id.textViewPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = products.get(getAdapterPosition());
                    Intent intent = new Intent(mCtx,UpdateActivity.class);
                    intent.putExtra("product",product);
                    mCtx.startActivity(intent);
                }
            });

        }
    }
}
