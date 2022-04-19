package com.example.clickandshop;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.Viewholder> {

    String[] categoryName;
    Context context;
    private final RecyclerViewInterface2 recyclerViewInterface;
    private int selected_position = -1;

    public CategoryRecyclerAdapter(Context context, String[] categoryName, RecyclerViewInterface2 recyclerViewInterface){
        this.context = context;
        this.categoryName = categoryName;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_3, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Set categoryName to TextView
        holder.textView.setText(categoryName[position]);

        // Highlight & underline selected category
        if (selected_position == position){
            holder.textView.setTextColor(Color.parseColor("#FF800B"));
            holder.textView.setBackground(ContextCompat.getDrawable(context, R.drawable.category_menu_bg));
        }
        else {
            holder.textView.setTextColor(Color.parseColor("#000000"));
            holder.textView.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return categoryName.length;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView textView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            selected_position = position;
                            notifyDataSetChanged();
                            recyclerViewInterface.onItemCategoryClick(position);
                        }
                    }
                }
            });
        }
    }
}
