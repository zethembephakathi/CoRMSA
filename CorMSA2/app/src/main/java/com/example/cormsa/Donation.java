package com.example.cormsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donation extends AppCompatActivity {
    private EditText donorNameEditText;
    private EditText donsEditText;

    private CheckBox anonymousCheckBox;
    private Button donateButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        donorNameEditText = findViewById(R.id.donorNameEditText);
        donsEditText = findViewById(R.id.donsEditText);
        anonymousCheckBox = findViewById(R.id.anonymousCheckBox);
        donateButton = findViewById(R.id.donateButton);

        databaseReference = FirebaseDatabase.getInstance().getReference("donations");

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorName = donorNameEditText.getText().toString().trim();
                String donationDescription = donsEditText.getText().toString().trim();
                boolean isAnonymous = anonymousCheckBox.isChecked();

                if (TextUtils.isEmpty(donorName) || TextUtils.isEmpty(donationDescription)) {
                    // Show an error message if either donorName or donationDescription is empty
                    Toast.makeText(Donation.this, "Please enter both your name and donation description", Toast.LENGTH_SHORT).show();
                } else if (donorName.length() < 3 || donorName.matches(".*\\d.*")) {
                    // Show an error message if donorName has less than 3 characters or contains numbers
                    Toast.makeText(Donation.this, "Please enter a valid name with at least 3 characters and no numbers", Toast.LENGTH_SHORT).show();
                } else if (donationDescription.length() < 10 || donationDescription.matches(".*\\d.*")) {
                    // Show an error message if donationDescription contains numbers
                    Toast.makeText(Donation.this, "Please enter a donation description without numbers", Toast.LENGTH_SHORT).show();
                } else {
                    // The fields are not empty, proceed with donation creation and database update

                    // Save to Firebase
                    Donations donation = new Donations(donorName, isAnonymous, donationDescription);
                    String donationId = databaseReference.push().getKey();
                    databaseReference.child(donationId).setValue(donation);
                    // Optionally, show a confirmation message or navigate to a thank you page
                    Intent intent = new Intent(Donation.this, Payment.class);
                    startActivity(intent);
                }
            }
        });


    }}