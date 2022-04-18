package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements RecyclerViewInterface3{

    RecyclerView recyclerView;
    CartRecyclerAdapter cartRecyclerAdapter;
    NestedScrollView cartScrollView;
    View checkOutContainer;
    View emptyCartContainer;
    private View backArrow;
    View checkOutBtn;
    Button shopNowBtn;
    TextView cartItemCountTextView;
    TextView subTotal;
    TextView shippingFee;
    TextView totalPrice;
    static List<Cart> list = new ArrayList<>();
    Dialog checkOutDialog;
    View closePopUp;
    View placeOrderBtn;
    TextView checkOutPrice;
    static double totalPriceDouble = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Hide cart contents if no item in cart
        cartScrollView = findViewById(R.id.cartScrollView);
        checkOutContainer = findViewById(R.id.checkOutContainer);
        emptyCartContainer = findViewById(R.id.emptyCartContainer);

        if (list.isEmpty()){
            cartScrollView.setVisibility(View.GONE);
            checkOutContainer.setVisibility(View.GONE);
            emptyCartContainer.setVisibility(View.VISIBLE);
        }
        else
            displayCartItems();

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Checkout popup navigation
        checkOutBtn = findViewById(R.id.checkOutBtn);
        checkOutDialog = new Dialog(this, R.style.Theme_AppCompat_Translucent);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalPriceDouble == 0){
                    Toast.makeText(CartActivity.this, "Please select at least 1 item to proceed checkout", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkOutDialog.setContentView(R.layout.popup_checkout);
                    checkOutDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    closePopUp = checkOutDialog.findViewById(R.id.closePopUp);
                    closePopUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkOutDialog.dismiss();
                        }
                    });

                    // Place order button action
                    placeOrderBtn = checkOutDialog.findViewById(R.id.placeOrderBtn);
                    placeOrderBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(CartActivity.this, "Thank You!"+"\n"+"Order Successfully Placed", Toast.LENGTH_SHORT).show();
                            list.clear();

                            Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent (CartActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 1);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }
                            }, 2000);
                        }
                    });
                    checkOutPrice = checkOutDialog.findViewById(R.id.checkOutPrice);
                    checkOutPrice.setText("RM " + String.format("%.2f", totalPriceDouble));
                    checkOutDialog.show();
                }
            }
        });

        // Continue Shopping button navigation
        shopNowBtn = findViewById(R.id.shopNowBtn);

        shopNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (CartActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // Reset all prodCheck to true when entering CartActivity
        for (int i=0; i<list.size(); i++){
            list.get(i).setProdCheck(true);
        }
        displayTotalPrice();
        Intent intent = getIntent();

        // Navigate from Product Page "Buy Now" button
        if (intent.getExtras() != null) {
            if (intent.getBooleanExtra("buyNow", false)) {
                int prodID = intent.getIntExtra("prodID", 0);
                for (int i=0; i<list.size(); i++){
                    if (list.get(i).productID != prodID) {
                        list.get(i).setProdCheck(false);

                        displayTotalPrice();
                    }
                    else {
                        list.get(i).setProdQuantity(1);
                        displayTotalPrice();
                    }
                }
                checkOutBtn.performClick();
            }
        }
    }

    // Cart item checkbox
    @Override
    public void onCheckBoxClick(int position) {
        displayTotalPrice();

    }

    // Deduct cart item quantity
    @Override
    public void onSubtractQtyClick(int position, boolean needRefresh) {
        // Refresh cart item in recycler view when an item remove from list
        if (needRefresh){
            displayCartItems();
        }

        // Display "Your Cart Is Empty" message when no item in cart
        if (list.isEmpty()){
            cartScrollView.setVisibility(View.GONE);
            checkOutContainer.setVisibility(View.GONE);
            emptyCartContainer.setVisibility(View.VISIBLE);
        }
        else
            displayTotalPrice();

    }

    // Add cart item quantity
    @Override
    public void onAddQtyClick(int position) {
        displayTotalPrice();

    }

    // Add to cart from Product Activity
    public static void addCartItem(Products currentProd){
        Cart cartItem = new Cart();
        boolean alreadyInCart = false;

        for (int i=0; i<list.size(); i++){
            if (currentProd.getProductID() == list.get(i).getProductID()){
                alreadyInCart = true;
                list.get(i).setProdQuantity(list.get(i).getProdQuantity()+1);
                break;
            }
        }

        if (alreadyInCart == false){
            cartItem.setProductID(currentProd.getProductID());
            cartItem.setProductPhoto(currentProd.getProductPhoto());
            cartItem.setProductName(currentProd.getProductName());
            cartItem.setProductPrice(currentProd.getProductPrice());
            cartItem.setProdQuantity(1);
            cartItem.setProdCheck(true);
            list.add(cartItem);
        }

    }

    // Set cart quantity, subtotal, shipping fee, total into respective view
    public void displayTotalPrice(){
        int cartItemCount = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).isProdCheck()) {
                cartItemCount += list.get(i).getProdQuantity();
            }
        }

        cartItemCountTextView = findViewById((R.id.cartItemCountTextView));
        cartItemCountTextView.setText("Sub total (" + cartItemCount + " items)");

        double subTotalDouble = 0;

        for (int i=0; i<list.size(); i++){
            if (list.get(i).isProdCheck()) {
                subTotalDouble += list.get(i).getProductPrice() * list.get(i).getProdQuantity();
            }
        }

        String subTotalTxt = "RM " + String.format("%.2f", subTotalDouble);
        subTotal = findViewById((R.id.subTotal));
        subTotal.setText(subTotalTxt);

        double shippingFeeDouble;
        if (subTotalDouble == 0){
            shippingFeeDouble = 0;
        }
        else
            shippingFeeDouble = 15.0;

        shippingFee = findViewById((R.id.shippingFee));
        shippingFee.setText("RM " + String.format("%.2f", shippingFeeDouble));

        //double totalPriceDouble = 0;
        totalPriceDouble = subTotalDouble + shippingFeeDouble;
        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("RM " + String.format("%.2f", totalPriceDouble));
    }

    // Set cart item(s) into recycler view
    public void displayCartItems(){
        // Initiate RecyclerView variables (Categories Products)
        recyclerView = findViewById(R.id.recycler_view);

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
    }
}