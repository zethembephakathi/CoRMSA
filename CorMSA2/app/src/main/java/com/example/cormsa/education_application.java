package com.example.cormsa;
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

public class education_application extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextCellphone, editTextNationality,
            editTextSkills, editTextPassportNumber, editTextEducationPermitNumber;
    private Spinner spinnerEducationType;
    private Button btnSave;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_application);

        databaseReference = FirebaseDatabase.getInstance().getReference("education_applications");

        editTextName = findViewById(R.id.editTextEducationName);
        editTextEmail = findViewById(R.id.editTextEducationEmail);
        editTextCellphone = findViewById(R.id.editTextEducationCellphone);
        editTextNationality = findViewById(R.id.editTextEducationNationality);
        editTextSkills = findViewById(R.id.editTextEducationSkills);
        editTextPassportNumber = findViewById(R.id.editTextEducationPassportNumber);
        spinnerEducationType = findViewById(R.id.spinnerEducationType);
        editTextEducationPermitNumber = findViewById(R.id.editTextEducationPermitNumber);
        btnSave = findViewById(R.id.btnSaveEducation);

        // Populate the spinner with education types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.education_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEducationType.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEducationApplication();
            }
        });
    }

    private void saveEducationApplication() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String cellphone = editTextCellphone.getText().toString().trim();
        String nationality = editTextNationality.getText().toString().trim();
        String skills = editTextSkills.getText().toString().trim();
        String passportNumber = editTextPassportNumber.getText().toString().trim();
        String educationType = spinnerEducationType.getSelectedItem().toString();
        String permitNumber = editTextEducationPermitNumber.getText().toString().trim();

        if (isValidInput(name, email, cellphone, nationality, skills, passportNumber, educationType, permitNumber)) {

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            EducationApplication educationApplication = new EducationApplication(
                    name, email, cellphone, nationality, skills, passportNumber, educationType, permitNumber);

            databaseReference.child(userId).setValue(educationApplication);

            // Clear input fields
            clearInputFields();

            Toast.makeText(this, "Education application saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill in all the fields with valid data", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidInput(String name, String email, String cellphone, String nationality,
                                 String skills, String passportNumber, String educationType, String permitNumber) {
        return isValidName(name) &&
                isValidEmail(email) &&
                isValidCellphone(cellphone) &&
                isValidNationality(nationality) &&
                isValidSkills(skills) &&
                isValidPassportNumber(passportNumber) &&
                isValidEducationType(educationType) &&
                isValidPermitNumber(permitNumber);
    }

    private boolean isValidName(String name) {
        // Allow only letters and ensure it has a length between 2 and 50
        return !name.isEmpty() && name.matches("^[a-zA-Z]+$") && name.length() >= 2 && name.length() <= 50;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return !TextUtils.isEmpty(email) && email.matches(emailRegex);
    }

    private boolean isValidCellphone(String cellphone) {
        // Customize this validation based on your requirements
        // For example, check if it's a valid phone number
        return !TextUtils.isEmpty(cellphone) && cellphone.length() >= 10 && cellphone.length() <= 15;
    }

    private boolean isValidNationality(String nationality) {
        // Allow only letters and ensure it has a length greater than 4
        return !TextUtils.isEmpty(nationality) && nationality.matches("^[a-zA-Z]+$") && nationality.length() > 4;
    }

    private boolean isValidSkills(String skills) {
        // Allow letters only and ensure the length is more than 10
        return !TextUtils.isEmpty(skills) && skills.matches("^[a-zA-Z\\s]+$") && skills.length() > 4;
    }
    private boolean isValidPassportNumber(String passportNumber) {
        // Ensure it is not empty, contains only digits, and has a length greater than 10
        return !TextUtils.isEmpty(passportNumber) && passportNumber.matches("^\\d+$") && passportNumber.length() > 9;
    }


    private boolean isValidEducationType(String educationType) {
        // Customize this validation based on your requirements
        // For example, check if it's a valid education type
        return !TextUtils.isEmpty(educationType);
    }

    private boolean isValidPermitNumber(String permitNumber) {
        // Ensure it is not empty, contains only digits, and has a length greater than 10
        return !TextUtils.isEmpty(permitNumber) && permitNumber.matches("^\\d+$") && permitNumber.length() > 9;
    }


    private void clearInputFields() {
        editTextName.setText("");
        editTextEmail.setText("");
        editTextCellphone.setText("");
        editTextNationality.setText("");
        editTextSkills.setText("");
        editTextPassportNumber.setText("");
        editTextEducationPermitNumber.setText("");
    }
}
