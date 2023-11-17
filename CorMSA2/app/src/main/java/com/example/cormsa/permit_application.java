package com.example.cormsa;




import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class permit_application extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextCellphone, editTextPassportId;
    private Spinner spinnerPermitType;
    private Button btnSave;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permit_application);

        databaseReference = FirebaseDatabase.getInstance().getReference("permit_applications");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCellphone = findViewById(R.id.editTextCellphone);
        spinnerPermitType = findViewById(R.id.spinnerPermitType);
        editTextPassportId = findViewById(R.id.editTextPassportId);
        btnSave = findViewById(R.id.btnSave);

        // Populate the spinner with permit types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.permit_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPermitType.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePermitApplication();
            }
        });
    }

    private void savePermitApplication() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String passportId = editTextPassportId.getText().toString().trim();
        String cellphone = editTextCellphone.getText().toString().trim();
        String permitType = spinnerPermitType.getSelectedItem().toString();

        if (isValidInput(name, email, cellphone,passportId)) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            PermitApplication permitApplication = new PermitApplication(name, email, cellphone,passportId, permitType);

            databaseReference.child(userId).setValue(permitApplication);

            // Clear input fields
            clearInputFields();

            Toast.makeText(this, "Permit application saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in all the fields with valid data", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput(String name, String email, String cellphone, String passportId) {
        return isValidName(name) && isValidEmail(email) && isValidCellphone(cellphone) && isValidPassportId(passportId);
    }

    private boolean isValidPassportId(String passportId) {
        // Allow only numbers and ensure it has a length between 8 and 20
        return !passportId.isEmpty() && passportId.matches("^[0-9]+$") && passportId.length() >= 8 && passportId.length() <= 20;
    }



    private void clearInputFields() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextCellphone.setText("");
    }

    private boolean isValidName(String name) {
        // Allow only letters and ensure it has a length between 2 and 50
        return !name.isEmpty() && name.matches("^[a-zA-Z]+$") && name.length() >= 2 && name.length() <= 50;
    }

    private boolean isValidEmail(String email) {
        // Customize this validation based on your requirements
        // For example, use a more robust email validation pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return !email.isEmpty() && email.matches(emailRegex);
    }

    private boolean isValidCellphone(String cellphone) {
        // Customize this validation based on your requirements
        // For example, check if it's a valid phone number starting with a specific digit
        return !cellphone.isEmpty() && cellphone.matches("^\\d{10,15}$");
    }

}