package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class VoucherActivity extends AppCompatActivity {

    // Initiate variables
    private View backArrow;
    private View voucherClaimButton1;
    private View voucherClaimButton2;
    private TextView voucherTermsTxt1;
    private TextView voucherTermsTxt2;
    private Toast prototypeToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        // Voucher terms and conditions
        voucherTermsTxt1 = findViewById(R.id.voucherTermsTxt1);
        voucherTermsTxt2 = findViewById(R.id.voucherTermsTxt2);

        voucherTermsTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayPrototypeMessage();
            }
        });

        voucherTermsTxt2.setOnClickListener(new View.OnClickListener() {
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
        prototypeToast = Toast.makeText(VoucherActivity.this, "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}