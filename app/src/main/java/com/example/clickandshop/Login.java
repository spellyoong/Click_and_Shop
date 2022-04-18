package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private Button googleBtn;
    private Button fbBtn;
    private View developerLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    private TextView forgotPassword;
    private TextView signUp;
    private Toast toast;
    private Toast prototypeToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        // Email login
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        });

        // Google login
        googleBtn = findViewById(R.id.googleBtn);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrototypeMessage();
            }
        });

        // Facebook login
        fbBtn = findViewById(R.id.fbBtn);

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrototypeMessage();
            }
        });

        // Forgot password
        forgotPassword = findViewById(R.id.forgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrototypeMessage();
            }
        });

        // Sign up
        signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrototypeMessage();
            }
        });

        // Developer login (Click shopping bag on hero banner)
        developerLogin = findViewById(R.id.developerLogin);

        developerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, bottomNavActivity.class).putExtra("developerLogin", true);
                startActivity(intent);
            }
        });

    }

    // Validate email login
    private void validate(String userName, String userPassword){
        if (userName.equals("admin@oum.edu.my") && userPassword.equals("admin")){
            Intent intent = new Intent(Login.this, bottomNavActivity.class);
            startActivity(intent);
        }
        else if (userName.isEmpty())
            makeToast("Please enter your email address.");
        else if (userPassword.isEmpty())
            makeToast("Please enter your password.");
        else
            makeToast("Incorrect email or password. Please check your login credentials.");
    }

    // Function to avoid toast delay
    private void makeToast(String toastText){
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(Login.this, toastText, Toast.LENGTH_SHORT);
        toast.show();

    }

    // Prototype Message
    private void displayPrototypeMessage(){
        if (prototypeToast != null)
            prototypeToast.cancel();
        prototypeToast = Toast.makeText(Login.this, "Function not implemented in current prototype version", Toast.LENGTH_SHORT);
        prototypeToast.show();

    }
}