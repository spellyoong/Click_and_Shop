package com.example.clickandshop;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.app.AlertDialog;


public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.Viewholder> {

    List<Cart> list;
    Context context;
    private final RecyclerViewInterface3 recyclerViewInterface;

    public CartRecyclerAdapter(Context context, List<Cart> list, RecyclerViewInterface3 recyclerViewInterface){
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_5, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Set contents into target views
        holder.prodNameTextView.setText(list.get(position).getProductName());
        holder.prodImageView.setImageResource(list.get(position).getProductPhoto());
        // Convert price into String with 2 decimal value
        String formattedPrice = String.format("%.2f", list.get(position).getProductPrice() * list.get(position).getProdQuantity());
        holder.prodPriceTextView.setText(formattedPrice);
        holder.qtyTextView.setText(String.valueOf(list.get(position).getProdQuantity()));
        if (list.get(position).isProdCheck())
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView prodNameTextView;
        ImageView prodImageView;
        TextView prodPriceTextView;
        CheckBox checkBox;
        TextView qtyTextView;
        ImageView minusBtn;
        ImageView plusBtn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            prodNameTextView = itemView.findViewById(R.id.prodNameTextView);
            prodImageView = itemView.findViewById(R.id.prodImageView);
            prodPriceTextView = itemView.findViewById(R.id.prodPriceTextView);
            checkBox = itemView.findViewById(R.id.checkBox);
            qtyTextView = itemView.findViewById(R.id.qtyTextView);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            plusBtn = itemView.findViewById(R.id.plusBtn);

            // Cart item checkbox
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            if (checkBox.isChecked())
                                list.get(position).setProdCheck(true);
                            else
                                list.get(position).setProdCheck(false);
                            recyclerViewInterface.onCheckBoxClick(position);
                        }
                    }
                }
            });

            // Deduct cart item quantity
            minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            if (list.get(position).getProdQuantity() == 1){
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(minusBtn.getContext());
                                builder.setMessage("Do you want to remove this product?")
                                        .setCancelable(true)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                list.remove(position);
                                                recyclerViewInterface.onSubtractQtyClick(position, true);
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        })
                                        .show();
                            }
                            else {
                                list.get(position).setProdQuantity(list.get(position).getProdQuantity()-1);
                                qtyTextView.setText(String.valueOf(list.get(position).getProdQuantity()));
                                recyclerViewInterface.onSubtractQtyClick(position, false);
                            }
                        }
                    }
                }
            });

            // Add cart item quantity
            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            list.get(position).setProdQuantity(list.get(position).getProdQuantity()+1);
                            qtyTextView.setText(String.valueOf(list.get(position).getProdQuantity()));
                            recyclerViewInterface.onAddQtyClick(position);
                        }
                    }
                }
            });
        }
    }
}