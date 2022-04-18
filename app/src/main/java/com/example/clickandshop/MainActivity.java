package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Display splash screen for 1.5s on app startup
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }

}