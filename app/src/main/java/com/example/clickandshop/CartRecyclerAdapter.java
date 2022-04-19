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

    List<Cart> cartList;
    Context context;
    private final RecyclerViewInterface3 recyclerViewInterface;

    public CartRecyclerAdapter(Context context, List<Cart> cartList, RecyclerViewInterface3 recyclerViewInterface){
        this.context = context;
        this.cartList = cartList;
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
        holder.prodNameTextView.setText(cartList.get(position).getProductName());
        holder.prodImageView.setImageResource(cartList.get(position).getProductPhoto());
        // Convert price into String with 2 decimal value
        String formattedPrice = String.format("%.2f", cartList.get(position).getProductPrice());
        holder.prodPriceTextView.setText(formattedPrice);
        holder.qtyTextView.setText(String.valueOf(cartList.get(position).getProdQuantity()));
        if (cartList.get(position).isProdCheck())
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
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

            // Checkbox action
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            if (checkBox.isChecked())
                                cartList.get(position).setProdCheck(true);
                            else
                                cartList.get(position).setProdCheck(false);
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
                            if (cartList.get(position).getProdQuantity() == 1){
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(minusBtn.getContext());
                                builder.setMessage("Do you want to remove this product?")
                                        .setCancelable(true)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                cartList.remove(position);
                                                recyclerViewInterface.onSubtractQtyClick(position);
                                                notifyDataSetChanged();
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
                                cartList.get(position).setProdQuantity(cartList.get(position).getProdQuantity()-1);
                                qtyTextView.setText(String.valueOf(cartList.get(position).getProdQuantity()));
                                recyclerViewInterface.onSubtractQtyClick(position);
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
                            cartList.get(position).setProdQuantity(cartList.get(position).getProdQuantity()+1);
                            qtyTextView.setText(String.valueOf(cartList.get(position).getProdQuantity()));
                            recyclerViewInterface.onAddQtyClick(position);
                        }
                    }
                }
            });
        }
    }
}