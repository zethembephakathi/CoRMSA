package com.example.cormsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Button logoutButton = findViewById(R.id.logoutButton);

        // Set click listener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform logout actions here if needed

                // Redirect to the login activity (assuming LoginActivity is the login page)
                startActivity(new Intent(LogoutActivity.this, Login.class));

                // Finish the LogoutActivity to prevent going back to it with the back button
                finish();
            }
        });
    }
}
