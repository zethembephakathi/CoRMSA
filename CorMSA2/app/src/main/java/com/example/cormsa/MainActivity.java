package com.example.cormsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        TextView dName = findViewById(R.id.dName);
       TextView elecName = findViewById(R.id.elecName);
       TextView homeName= findViewById(R.id.homeName);
        TextView beautyName= findViewById(R.id.beautyName);
        TextView pharmName= findViewById(R.id.pharmName);
        TextView grocName= findViewById(R.id.grocName);

        dName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the DonationActivity when "Donate" textview is clicked
                Intent intent = new Intent(MainActivity.this, Donation.class);
                startActivity(intent);
            }
        });
                   // Start the DonationActivity when "Appointments" textview is clicked
        elecName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Appointments.class);
                startActivity(intent);
            }
        });
        // Start the DonationActivity when "permit_application" textview is clicked
        homeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, permit_application.class);
                startActivity(intent);
            }
        });
        beautyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AccommodationApplicationActivity.class);
                startActivity(intent);
            }
        });
        pharmName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, education_application.class);
                startActivity(intent);
            }
        });
        grocName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
                startActivity(intent);
            }
        });

    }
}

