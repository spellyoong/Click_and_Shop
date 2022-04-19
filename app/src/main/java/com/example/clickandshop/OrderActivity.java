package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    // Initiate variables
    private View backArrow;
    private View orderArrow1;
    private View orderArrow2;
    private Toast prototypeToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Order detail
        orderArrow1 = findViewById(R.id.orderArrow1);
        orderArrow2 = findViewById(R.id.orderArrow2);

        orderArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayPrototypeMessage();
            }
        });

        orderArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayPrototypeMessage();
            }
        });
    }

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(OrderActivity.this, "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}