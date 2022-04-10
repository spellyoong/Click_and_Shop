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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {

    List<Products> list;
    Context context;
    private final int limit = 5;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerAdapter(Context context, List<Products> list, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_2, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        //Set Logo to ImageView
        holder.prodImageView.setImageResource(list.get(position).getProductPhoto());
        holder.prodTextView.setText(list.get(position).getProductName());
        // Convert soldQty into String according to the decimal value
        String formattedQty;
        if (list.get(position).soldQty % 1 == 0){
            formattedQty = String.format("%.0f", list.get(position).soldQty);
        }
        else {
            formattedQty = Double.toString(list.get(position).soldQty);
        }
        holder.soldQtytextView.setText(formattedQty);
    }

    @Override
    public int getItemCount() {
        // Limit to display only 5 items under top product recycler view
        if(list.size() > limit){
            return limit;
        }
        else {
            return list.size();
        }
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView prodImageView;
        TextView prodTextView;
        TextView soldQtytextView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            prodImageView = itemView.findViewById(R.id.topProdImageView);
            prodTextView = itemView.findViewById(R.id.topProdTextView);
            soldQtytextView = itemView.findViewById(R.id.topSoldQtyTextView);

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