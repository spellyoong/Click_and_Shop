package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SellerActivity extends AppCompatActivity implements RecyclerViewInterface{

    // Initiate variables for recycler view
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    NestedScrollView sellerScrollView;
    RelativeLayout sellerSubContainer;
    private View backArrow;
    private View chatBtn;
    private View cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        // Retrieve products list from Products.java
        List<Products> topProdList = new ArrayList<Products>();
        topProdList = new Products().getProdList();

        // Sort top products list by soldQty in descending order
        Collections.sort(topProdList, new Comparator<Products>() {
            @Override
            public int compare(Products products, Products t1) {
                if (products.getSoldQty() > t1.getSoldQty()){
                    return -1;
                }
                else if (products.getSoldQty() < t1.getSoldQty()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });

        recyclerView = findViewById(R.id.recycler_view);

        // RecyclerView layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                SellerActivity.this, LinearLayoutManager.HORIZONTAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView Adapter
        recyclerAdapter = new RecyclerAdapter(SellerActivity.this, topProdList, this);
        recyclerView.setAdapter(recyclerAdapter);

        // =========== ScrollView Start ===========
        // Explanation: To set background on header when user scroll down

        sellerScrollView = findViewById(R.id.sellerScrollView);
        sellerSubContainer = findViewById(R.id.sellerSubContainer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sellerScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY != 0) {
                        // Check if scroll position is not reaches the top
                        sellerSubContainer.setBackgroundResource(R.drawable.seller_header_bg);
                    }
                    else {
                        sellerSubContainer.setBackground(null);
                    }
                }
            });
        }
        // =========== ScrollView End ===========

        // =========== GridView Start ===========
        // Explanation: Display products in GridView

        // Retrieve products list from Products.java
        List<Products> list = new ArrayList<Products>();
        list = new Products().getProdList();

        // Get list length
        int listSize = list.size();

        // Initiate productPhoto
        int[] productPhoto = new int[listSize];
        for (int i=0; i<productPhoto.length;i++){
            productPhoto[i] = list.get(i).productPhoto;
        }

        // Initiate productName
        String[] productName = new String[listSize];
        for (int i=0; i<productName.length;i++){
            productName[i] = list.get(i).productName;
        }

        // Initiate productPrice
        double[] productPrice = new double[listSize];
        for (int i=0; i<productPrice.length;i++){
            productPrice[i] = list.get(i).productPrice;
        }

        // Initiate soldQty
        double[] soldQty = new double[listSize];
        for (int i=0; i<soldQty.length;i++){
            soldQty[i] = list.get(i).soldQty;
        }

        ExpandableHeightGridView gridView = (ExpandableHeightGridView) findViewById(R.id.gridView);

        // GridView Adapter
        GridAdapter gridAdapter = new GridAdapter(productName, productPhoto, productPrice, soldQty, SellerActivity.this);
        gridView.setAdapter(gridAdapter);
        gridView.setExpanded(true);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(SellerActivity.this, ProductActivity.class).putExtra("position", i));
            }
        });
        // =========== GridView End ===========

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Cart navigation
        cartIcon = findViewById(R.id.cartIcon);

        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Chat with seller navigation
        chatBtn = findViewById(R.id.chatBtn);

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerActivity.this, ChatActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SellerActivity.this, ProductActivity.class).putExtra("position", position).putExtra("sortTopProd", true);
        startActivity(intent);
    }
}