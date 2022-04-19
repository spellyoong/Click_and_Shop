package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements RecyclerViewInterface3{

    // Initiate variables
    private RecyclerView recyclerView;
    private CartRecyclerAdapter cartRecyclerAdapter;
    private NestedScrollView cartScrollView;
    private View checkOutContainer;
    private View emptyCartContainer;
    private TextView cartItemCountTextView;
    private TextView subTotal;
    private TextView shippingFee;
    private TextView totalPrice;
    private static double totalPriceDouble = 0;
    private View backArrow;
    private View checkOutBtn;
    private Dialog checkOutDialog;
    private View closePopUp;
    private TextView checkOutPrice;
    private View placeOrderBtn;
    private int clickCount = 0;
    private View addressArrow;
    private View paymentArrow;
    private View voucherArrow;
    private Button shopNowBtn;
    public static List<Cart> cartList = new ArrayList<>();
    private Toast toast;
    private Toast prototypeToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Hide contents if cart is empty || Show cart contents if cart is not empty
        cartScrollView = findViewById(R.id.cartScrollView);
        checkOutContainer = findViewById(R.id.checkOutContainer);
        emptyCartContainer = findViewById(R.id.emptyCartContainer);

        if (cartList.isEmpty()){
            cartScrollView.setVisibility(View.GONE);
            checkOutContainer.setVisibility(View.GONE);
            emptyCartContainer.setVisibility(View.VISIBLE);
        }
        else {
            displayCartItems();
            // Reset all prodCheck to true
            for (int i=0; i<cartList.size(); i++){
                cartList.get(i).setProdCheck(true);
            }
            displayTotalPrice();
        }

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Checkout navigation (pop up dialog)
        checkOutBtn = findViewById(R.id.checkOutBtn);
        checkOutDialog = new Dialog(this, R.style.Theme_AppCompat_Translucent);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalPriceDouble == 0){
                    makeToast("Please select at least 1 item to proceed checkout");
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

                    checkOutPrice = checkOutDialog.findViewById(R.id.checkOutPrice);
                    checkOutPrice.setText("RM " + String.format("%.2f", totalPriceDouble));
                    checkOutDialog.show();

                    // Place order button action
                    placeOrderBtn = checkOutDialog.findViewById(R.id.placeOrderBtn);
                    placeOrderBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            clickCount++; // To avoid user spam "Place Order" button
                            if (clickCount <= 1) {
                                makeToast("Thank You!" + "\n" + "Order Successfully Placed");
                                cartList.clear();

                                Handler handler = new Handler();

                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        clickCount = 0;
                                        Intent intent = new Intent(CartActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 1);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                }, 2000);
                            }
                        }
                    });

                    // Select address
                    addressArrow = checkOutDialog.findViewById(R.id.addressArrow);
                    addressArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayPrototypeMessage();
                        }
                    });

                    // Payment mode
                    paymentArrow = checkOutDialog.findViewById(R.id.paymentArrow);
                    paymentArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayPrototypeMessage();
                        }
                    });

                    // Voucher
                    voucherArrow = checkOutDialog.findViewById(R.id.voucherArrow);
                    voucherArrow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayPrototypeMessage();
                        }
                    });
                }
            }
        });

        // Direct purchase - navigate from Product Page "Buy Now" button
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            if (intent.getBooleanExtra("buyNow", false)) {
                int prodID = intent.getIntExtra("prodID", 0);
                for (int i=0; i<cartList.size(); i++){
                    if (cartList.get(i).productID != prodID) {
                        cartList.get(i).setProdCheck(false);
                        displayTotalPrice();
                    }
                    else {
                        cartList.get(i).setProdQuantity(1);
                        displayTotalPrice();
                    }
                }
                checkOutBtn.performClick();
            }
        }

        // Continue Shopping button navigation (when cart is empty)
        shopNowBtn = findViewById(R.id.shopNowBtn);

        shopNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (CartActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
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
        cartRecyclerAdapter = new CartRecyclerAdapter(CartActivity.this, cartList, this);
        recyclerView.setAdapter(cartRecyclerAdapter);
    }

    // Set cart quantity, subtotal, shipping fee, total into respective view
    public void displayTotalPrice(){
        int cartItemCount = 0;

        for (int i=0; i<cartList.size(); i++){
            if (cartList.get(i).isProdCheck()) {
                cartItemCount += cartList.get(i).getProdQuantity();
            }
        }

        cartItemCountTextView = findViewById((R.id.cartItemCountTextView));
        cartItemCountTextView.setText("Sub total (" + cartItemCount + " items)");

        double subTotalDouble = 0;

        for (int i=0; i<cartList.size(); i++){
            if (cartList.get(i).isProdCheck()) {
                subTotalDouble += cartList.get(i).getProductPrice() * cartList.get(i).getProdQuantity();
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

    // Checkbox action
    @Override
    public void onCheckBoxClick(int position) {
        displayTotalPrice();
    }

    // Deduct cart item quantity
    @Override
    public void onSubtractQtyClick(int position) {
        // Display "Your Cart Is Empty" message when no item in cart
        if (cartList.isEmpty()){
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

    // Add to cart - from Product Page "Add to Cart" button
    public static void addCartItem(Products currentProd){
        Cart cartItem = new Cart();
        boolean alreadyInCart = false;

        for (int i=0; i<cartList.size(); i++){
            if (currentProd.getProductID() == cartList.get(i).getProductID()){
                alreadyInCart = true;
                cartList.get(i).setProdQuantity(cartList.get(i).getProdQuantity()+1);
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
            cartList.add(cartItem);
        }
    }

    // Function to avoid toast delay
    private void makeToast(String toastText){
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(CartActivity.this, toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(CartActivity.this, "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}