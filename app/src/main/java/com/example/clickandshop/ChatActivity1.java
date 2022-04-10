package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChatActivity1 extends AppCompatActivity {

    private View chatContainer1;
    private View chatContainer2;
    private View backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1);

        // Chat navigation
        chatContainer1 = findViewById(R.id.chatContainer1);
        chatContainer2 = findViewById(R.id.chatContainer2);

        chatContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ChatActivity1.this, ChatActivity2.class);
                startActivity(intent);
            }
        });

        chatContainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ChatActivity1.this, ChatActivity2.class);
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