package com.example.clickandshop;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    // Initiate variables
    private String[] imageNames;
    private int[] imagesPhoto;
    private double[] productPrice;
    private double[] soldQty;
    private Context context;
    private LayoutInflater layoutInflater;

    public GridAdapter(String[] imageNames, int[] imagesPhoto, double[] productPrice, double[] soldQty, Context context) {
        this.imageNames = imageNames;
        this.imagesPhoto = imagesPhoto;
        this.productPrice = productPrice;
        this.soldQty = soldQty;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesPhoto.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
        }

        // Initiate views (row_items)
        TextView prodNameTextView = view.findViewById(R.id.prodNameTextView);
        ImageView prodImageView = view.findViewById(R.id.prodImageView);
        TextView prodPriceTextView = view.findViewById(R.id.prodPriceTextView);
        TextView soldQtyTextView = view.findViewById(R.id.soldQtyTextView);

        // Set contents into target views (product CardView)
        prodNameTextView.setText(imageNames[i]);
        prodImageView.setImageResource(imagesPhoto[i]);
        // Convert price into String with 2 decimal value
        String formattedPrice = String.format("%.2f", productPrice[i]);
        prodPriceTextView.setText(formattedPrice);
        // Convert soldQty into String according to the decimal value
        String formattedQty;
        if (soldQty[i] % 1 == 0){
            formattedQty = String.format("%.0f", soldQty[i]);
        }
        else {
            formattedQty = Double.toString(soldQty[i]);
        }
        soldQtyTextView.setText(formattedQty);

        return view;
    }
}