package com.example.clickandshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.Viewholder> {

    List<Products> list;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public ProductRecyclerAdapter(Context context, List<Products> list, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_4, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Set contents into target views
        holder.prodNameTextView.setText(list.get(position).getProductName());
        holder.prodImageView.setImageResource(list.get(position).getProductPhoto());
        // Convert price into String with 2 decimal value
        String formattedPrice = String.format("%.2f", list.get(position).getProductPrice());
        holder.prodPriceTextView.setText(formattedPrice);
        // Convert soldQty into String according to the decimal value
        String formattedQty;
        if (list.get(position).getSoldQty() % 1 == 0){
            formattedQty = String.format("%.0f", list.get(position).getSoldQty());
        }
        else {
            formattedQty = Double.toString(list.get(position).getSoldQty());
        }
        holder.soldQtyTextView.setText(formattedQty);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView prodNameTextView;
        ImageView prodImageView;
        TextView prodPriceTextView;
        TextView soldQtyTextView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            prodNameTextView = itemView.findViewById(R.id.prodNameTextView);
            prodImageView = itemView.findViewById(R.id.prodImageView);
            prodPriceTextView = itemView.findViewById(R.id.prodPriceTextView);
            soldQtyTextView = itemView.findViewById(R.id.soldQtyTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
