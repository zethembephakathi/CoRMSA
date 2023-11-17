package com.example.cormsa;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Payment extends AppCompatActivity {
            private Spinner debitCardTypeSpinner;


          String[] debitCardTypes = {"Visa", "MasterCard", "American Express", "Discover", "Other"};

          private EditText donationAmountEditText, cardNumberEditText, cardExpirationEditText;
            private Button confirmPaymentButton;
            private DatabaseReference databaseReference;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_payment);
                debitCardTypeSpinner = findViewById(R.id.debitCardTypeSpinner);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, debitCardTypes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                debitCardTypeSpinner.setAdapter(adapter);


                debitCardTypeSpinner = findViewById(R.id.debitCardTypeSpinner);
                donationAmountEditText = findViewById(R.id.donationAmountEditText);
                cardNumberEditText = findViewById(R.id.cardNumberEditText);
                cardExpirationEditText = findViewById(R.id.cardExpirationEditText);
                confirmPaymentButton = findViewById(R.id.confirmPaymentButton);

                databaseReference = FirebaseDatabase.getInstance().getReference("payments");

                confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validateInputs()) {
                            processPayment();
                        }
                    }
                });
            }

    private boolean validateInputs() {
        // Validate donation amount
        String donationAmountString = donationAmountEditText.getText().toString();
        if (donationAmountString.isEmpty()) {
            Toast.makeText(Payment.this, "Please enter a donation amount.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double donationAmount = Double.parseDouble(donationAmountString);
            if (donationAmount <= 0) {
                Toast.makeText(Payment.this, "Please enter a valid donation amount.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(Payment.this, "Invalid donation amount format.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate card number
        String cardNumber = cardNumberEditText.getText().toString();
        if (cardNumber.isEmpty() || cardNumber.length() < 16) {
            Toast.makeText(Payment.this, "Please enter a valid card number.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate card expiration
        String cardExpiration = cardExpirationEditText.getText().toString();
        if (cardExpiration.isEmpty() || !isValidCardExpiration(cardExpiration)) {
            Toast.makeText(Payment.this, "Please enter a valid card expiration date.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidCardExpiration(String cardExpiration) {
        // You can implement logic to check if the card expiration date is valid.
        // For simplicity, you may just check if it's in the format MM/YY.
        // You can enhance this validation as needed.
        // For example, check if the date is not expired.
        // For now, let's assume MM/YY format.
        return cardExpiration.matches("\\d{2}/[a-zA-Z]\\d{2}");

    }

    private void processPayment() {
        // ... (your existing payment processing code)
        // Make sure to handle the payment securely.
        // Consider using a secure connection (HTTPS) and follow best practices for handling payment information.
    }
}



