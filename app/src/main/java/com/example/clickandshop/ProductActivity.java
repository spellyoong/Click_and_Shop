package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    // Initiate views (activity_product)
    private GridView gridView;
    private View chatIcon;
    private View cartIcon;
    private View viewShopTxt;
    private View chatWithSeller;
    private View backArrow;
    private View ratingContainer;
    private TextView productNameView;
    private ImageView productImageView;
    private TextView productPriceView;
    private TextView soldQtyView;
    private TextView productDescView;
    private RatingBar productRateView;
    private View addCartButton;
    private View buyButton;
    private Products currentProd;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // Retrieve products list from Products.java
        List<Products> list = new ArrayList<Products>();
        list = new Products().getProdList();

        // Get list length
        int listSize = list.size();

        // Initiate productPhoto
        int[] productPhoto = new int[listSize];
        for (int i = 0; i < productPhoto.length; i++) {
            productPhoto[i] = list.get(i).productPhoto;
        }

        // Initiate productName
        String[] productName = new String[listSize];
        for (int i = 0; i < productName.length; i++) {
            productName[i] = list.get(i).productName;
        }

        // Initiate productPrice
        double[] productPrice = new double[listSize];
        for (int i = 0; i < productPrice.length; i++) {
            productPrice[i] = list.get(i).productPrice;
        }

        // Initiate soldQty
        double[] soldQty = new double[listSize];
        for (int i = 0; i < soldQty.length; i++) {
            soldQty[i] = list.get(i).soldQty;
        }

        ExpandableHeightGridView gridView = (ExpandableHeightGridView) findViewById(R.id.gridView);

        // GridView Adapter
        GridAdapter gridAdapter = new GridAdapter(productName, productPhoto, productPrice, soldQty, this);
        gridView.setAdapter(gridAdapter);
        gridView.setExpanded(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(ProductActivity.this, ProductActivity.class).putExtra("position", i));
            }
        });

        // Chat navigation
        chatIcon = findViewById(R.id.chatIcon);

        chatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, ChatActivity2.class);
                startActivity(intent);
            }
        });

        // Cart navigation
        cartIcon = findViewById(R.id.cartIcon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Review navigation
        ratingContainer = findViewById(R.id.ratingContainer);

        ratingContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        // Seller page navigation
        viewShopTxt = findViewById(R.id.viewShopTxt);

        viewShopTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, SellerActivity.class);
                startActivity(intent);
            }
        });

        // Chat with seller navigation
        chatWithSeller = findViewById(R.id.chatWithSeller);

        chatWithSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, ChatActivity2.class);
                startActivity(intent);
            }
        });

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Capture views (activity_product)
        productNameView = findViewById(R.id.productNameView);
        productImageView = findViewById(R.id.productImageView);
        productPriceView = findViewById(R.id.productPriceView);
        soldQtyView = findViewById(R.id.soldQtyView);
        productDescView = findViewById(R.id.productDescView);
        productRateView = findViewById(R.id.productRateView);

        Intent intent = getIntent();

        // Set contents into target views (product page)
        if (intent.getExtras() != null) {
            int position = intent.getIntExtra("position", 0);
            // Activity start from "Top Product" recycler view: Sort product list by soldQty
            if (intent.getBooleanExtra("sortTopProd", false)) {
                // Sort top products by soldQty in descending order
                Collections.sort(list, new Comparator<Products>() {
                    @Override
                    public int compare(Products products, Products t1) {
                        if (products.getSoldQty() > t1.getSoldQty()) {
                            return -1;
                        } else if (products.getSoldQty() < t1.getSoldQty()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }

                });
            }
            // Activity start from "Product Category" recycler view: Get appropriate product list by the selected category's position
            if (intent.hasExtra("categoryPosition")) {
                int categoryPosition = intent.getIntExtra("categoryPosition", 0);
                switch (categoryPosition) {
                    case 0:
                        list = new Products().getCategoryHotList();
                        break;
                    case 1:
                        list = new Products().getCategoryEssentialList();
                        break;
                    case 2:
                        list = new Products().getCategorySportList();
                        break;
                    case 3:
                        list = new Products().getCategoryBeautyList();
                        break;
                    case 4:
                        list = new Products().getCategoryGamingList();
                        break;
                    case 5:
                        list = new Products().getCategoryElectronicsList();
                        break;
                    case 6:
                        list = new Products().getCategoryFashionList();
                        break;
                    case 7:
                        list = new Products().getCategoryToyList();
                        break;
                    case 8:
                        list = new Products().getCategoryHomeList();
                        break;
                    case 9:
                        list = new Products().getCategoryHealthList();
                        break;
                }
            }
            productNameView.setText(list.get(position).productName);
            productImageView.setImageResource(list.get(position).productPhoto);
            // Convert price into String with 2 decimal value
            String formattedPrice = String.format("%.2f", list.get(position).productPrice);
            productPriceView.setText(formattedPrice);
            // Convert soldQty into String according to the decimal value
            String formattedQty;
            if (list.get(position).soldQty % 1 == 0) {
                formattedQty = String.format("%.0f", list.get(position).soldQty);
            } else {
                formattedQty = Double.toString(list.get(position).soldQty);
            }
            soldQtyView.setText(formattedQty);
            productDescView.setText(list.get(position).productDesc);
            productRateView.setRating((float) list.get(position).productRate);

            currentProd = list.get(position);

        }

        // Add to cart
        addCartButton = findViewById(R.id.addCartButton);

        addCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.addCartItem(currentProd);
                makeToast("Added to Cart");

            }
        });

        // Buy now navigation
        buyButton = findViewById(R.id.buyButton);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.addCartItem(currentProd);
                Intent intent = new Intent(ProductActivity.this, CartActivity.class).putExtra("buyNow", true).putExtra("prodID", currentProd.productID);
                startActivity(intent);

            }
        });


    }

    // Function to avoid toast delay
    private void makeToast(String toastText){
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(ProductActivity.this, toastText, Toast.LENGTH_SHORT);
        toast.show();

    }
}