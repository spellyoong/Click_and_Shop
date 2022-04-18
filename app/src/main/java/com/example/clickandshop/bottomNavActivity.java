package com.example.clickandshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomNavActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        // Bottom navigation bar
        navigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerActivity, new homeFragment()).commit();
        navigationView.setSelectedItemId(R.id.homeFragment);

        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        fragment = new homeFragment();
                        break;
                    case R.id.highlightFragment:
                        fragment = new highlightFragment();
                        break;
                    case R.id.notificationFragment:
                        fragment = new notificationFragment();
                        break;
                    case R.id.profileFragment:
                        fragment = new profileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerActivity, fragment).commit();

                return true;
            }
        });

        // Navigate to specified fragment when called by other activities' Intent
        Intent intent = getIntent();

        int frgOption = intent.getIntExtra("frgToLoad", 1);
        switch (frgOption){
            case 1:
                navigationView.setSelectedItemId(R.id.homeFragment);
                break;
            case 2:
                navigationView.setSelectedItemId(R.id.highlightFragment);
                break;
            case 3:
                navigationView.setSelectedItemId(R.id.notificationFragment);
                break;
            case 4:
                navigationView.setSelectedItemId(R.id.profileFragment);
                break;

        }

        // Developer login message
        Boolean developerLogin = intent.getBooleanExtra("developerLogin", false);
        if (developerLogin)
            Toast.makeText(bottomNavActivity.this, "Developer Login", Toast.LENGTH_SHORT).show();

    }
}