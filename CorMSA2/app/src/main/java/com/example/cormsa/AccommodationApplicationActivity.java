package com.example.cormsa;

// AccommodationApplicationActivity.java


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccommodationApplicationActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextCellphone, editTextFamilyNumber, editTextNationality, editTextDuration;
    private Spinner spinnerAccommodationType;
    private Button btnSave;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accommodation_application);

        databaseReference = FirebaseDatabase.getInstance().getReference("accommodation_applications");

        editTextName = findViewById(R.id.editTextAccommodationName);
        editTextEmail = findViewById(R.id.editTextAccommodationEmail);
        editTextCellphone = findViewById(R.id.editTextAccommodationCellphone);
        editTextFamilyNumber = findViewById(R.id.editTextAccommodationFamilyNumber);
        editTextNationality = findViewById(R.id.editTextAccommodationNationality);
        editTextDuration = findViewById(R.id.editTextAccommodationDuration);
        spinnerAccommodationType = findViewById(R.id.spinnerAccommodationType);
        btnSave = findViewById(R.id.btnSaveAccommodation);

        // Populate the spinner with accommodation types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.accommodation_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccommodationType.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccommodationApplication();
            }
        });
    }

    private void saveAccommodationApplication() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String cellphone = editTextCellphone.getText().toString().trim();
        String familyNumber = editTextFamilyNumber.getText().toString().trim();
        String nationality = editTextNationality.getText().toString().trim();
        String duration = editTextDuration.getText().toString().trim();
        String accommodationType = spinnerAccommodationType.getSelectedItem().toString();

        if (isValidInput(name, email, cellphone, familyNumber, nationality, duration)) {

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            AccommodationApplication accommodationApplication = new AccommodationApplication(name, email, cellphone, familyNumber, nationality, duration, accommodationType);

            databaseReference.child(userId).setValue(accommodationApplication);

            // Clear input fields
            clearInputFields();

            Toast.makeText(this, "Accommodation application saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in all the fields with valid data", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearInputFields() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextCellphone.setText("");
        editTextFamilyNumber.setText("");
        editTextNationality.setText("");
        editTextDuration.setText("");
    }
    private boolean isValidInput(String name, String email, String cellphone, String familyNumber, String nationality, String duration) {
        return isValidName(name) && isValidEmail(email) && isValidCellphone(cellphone) &&
                isValidFamilyNumber(familyNumber) && isValidNationality(nationality) && isValidDuration(duration);
    }

    private boolean isValidName(String name) {
        // Allow only letters and ensure it has a length between 2 and 50
        return !name.isEmpty() && name.matches("^[a-zA-Z]+$") && name.length() >= 2 && name.length() <= 50;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return !email.isEmpty() && email.matches(emailRegex);
    }

    private boolean isValidCellphone(String cellphone) {
        // Customize this validation based on your requirements
        // For example, check if it's a valid phone number
        return !cellphone.isEmpty() && cellphone.length() >= 10 && cellphone.length() <= 15;
    }

    private boolean isValidFamilyNumber(String familyNumber) {
        // Customize this validation based on your requirements
        // For example, check if it's a positive integer
        return !familyNumber.isEmpty() && familyNumber.matches("\\d+") && Integer.parseInt(familyNumber) > 0;
    }

    private boolean isValidNationality(String nationality) {
        // Customize this validation based on your requirements
        // For example, check if it contains only letters
        return !TextUtils.isEmpty(nationality) && nationality.matches("^[a-zA-Z]+$") && nationality.length() > 4;
    }

    private boolean isValidDuration(String duration) {
        // Customize this validation based on your requirements
        // For example, check if it's a positive integer
        return !duration.isEmpty() && duration.matches("\\d+") && Integer.parseInt(duration) > 0;
    }


}