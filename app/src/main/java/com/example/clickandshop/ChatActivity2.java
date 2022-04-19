package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChatActivity2 extends AppCompatActivity {

    // Initiate variables
    private View backArrow;
    private View attachIcon;
    private View sendIcon;
    private Toast prototypeToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Send message (icon)
        sendIcon = findViewById(R.id.sendIcon);

        sendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayPrototypeMessage();
            }
        });

        // Send message (enter)
        EditText msg_Box = findViewById(R.id.msg_Box);

        msg_Box.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    displayPrototypeMessage();
                    msg_Box.setText("");
                    return true;
                }
                return false;
            }
        });

        // Attachment
        attachIcon = findViewById(R.id.attachIcon);

        attachIcon.setOnClickListener(new View.OnClickListener() {
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
        prototypeToast = Toast.makeText(ChatActivity2.this, "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();
    }
}