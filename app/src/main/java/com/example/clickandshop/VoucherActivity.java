package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VoucherActivity extends AppCompatActivity {

    private View voucherClaimButton1;
    private View voucherClaimButton2;
    private View backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        // Voucher claim navigation
        voucherClaimButton1 = findViewById(R.id.voucherClaimButton1);
        voucherClaimButton2 = findViewById(R.id.voucherClaimButton2);

        voucherClaimButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (VoucherActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 2);
                startActivity(intent);
            }
        });

        voucherClaimButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (VoucherActivity.this, bottomNavActivity.class).putExtra("frgToLoad", 2);
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
    }
}