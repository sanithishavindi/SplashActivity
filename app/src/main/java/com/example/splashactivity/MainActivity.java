package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the TextView for the message
        TextView msg = findViewById(R.id.msg);
        msg.setText("Starting Census app...");

        // Delay for a few seconds before moving to the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity (e.g., LoginActivity)
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // 3 seconds delay
    }
}