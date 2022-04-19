package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddressActivity1 extends AppCompatActivity {

    // Initiate variables
    private View backArrow;
    private View AddressContainer1;
    private View AddressContainer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address1);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Update address navigation
        AddressContainer1 = findViewById(R.id.AddressContainer1);
        AddressContainer2 = findViewById(R.id.AddressContainer2);

        AddressContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddressActivity1.this, AddressActivity2.class);
                startActivity(intent);
            }
        });

        AddressContainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AddressActivity1.this, AddressActivity2.class);
                startActivity(intent);
            }
        });

    }
}