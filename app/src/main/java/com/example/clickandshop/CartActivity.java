package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements RecyclerViewInterface{

    // Initiate variables for recycler view
    RecyclerView recyclerView;
    CartRecyclerAdapter cartRecyclerAdapter;
    private View backArrow;
    String cartCount;
    TextView cartItemCountTextView;
    TextView subTotal;
    TextView shippingFee;
    TextView totalPrice;
    static List<Cart> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // ======================================
        // Initiate RecyclerView variables (Categories Products)
        recyclerView = findViewById(R.id.recycler_view);

        // Retrieve products list from Product.java TEMPORARY GET HEALTH
        //list = new Products().getProdList();


//        cart.cartList.add(list.get(1));
//        cart.cartList.add(list.get(2));
//        cart.cartList.add(list.get(3));
//        cart.cartList.add(list.get(4));
//        Cart.cartList.add(list.get(1));

        // RecyclerView layout (Categories Products)
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                CartActivity.this,LinearLayoutManager.VERTICAL, false
        );

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // RecyclerView Adapter (Categories Products)
        cartRecyclerAdapter = new CartRecyclerAdapter(CartActivity.this, list, this);
        recyclerView.setAdapter(cartRecyclerAdapter);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Set cart quantity, subtotal, shipping fee, total into respective view
        cartItemCountTextView = findViewById((R.id.cartItemCountTextView));
        cartItemCountTextView.setText("Sub total (" + list.size() + " products)");

        double subTotalDouble = 0;

        for (int i=0; i<list.size(); i++){
            subTotalDouble += list.get(i).getProductPrice();
        }

        String subTotalTxt = "RM " + String.format("%.2f", subTotalDouble);
        subTotal = findViewById((R.id.subTotal));
        subTotal.setText(subTotalTxt);

        double shippingFeeDouble = 15.0;
        shippingFee = findViewById((R.id.shippingFee));
        shippingFee.setText("RM " + String.format("%.2f", shippingFeeDouble));

        double totalPriceDouble = 0;
        totalPriceDouble = subTotalDouble + shippingFeeDouble;
        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("RM " + String.format("%.2f", totalPriceDouble));
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(CartActivity.this, "Checked position: " + position, Toast.LENGTH_SHORT).show();

    }

    // Add to cart from Product Activity
    public static void addCartItem(Products currentProd){
        Cart cartItem = new Cart();
        cartItem.setProductPhoto(currentProd.getProductPhoto());
        cartItem.setProductName(currentProd.getProductName());
        cartItem.setProductPrice(currentProd.getProductPrice());
        cartItem.setProdQuantity(1);
        list.add(cartItem);

//        Products productItem = new Products();
//        productItem.productName = currentProd.productName;
//        productItem.setProductPhoto(currentProd.productPhoto);
//        productItem.setProductPrice(currentProd.productPrice);
//        list.add(cartItem);
    }
}