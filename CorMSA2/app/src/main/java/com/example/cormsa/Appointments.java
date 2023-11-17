package com.example.cormsa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Appointments extends AppCompatActivity {

    private EditText editTextName, editTextID, editTextEmail, editTextReason, editTextDate, editTextTime;
    private Button buttonSetAppointment;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        editTextName = findViewById(R.id.editTextName);
        editTextID = findViewById(R.id.editTextID);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextReason = findViewById(R.id.editTextReason);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        buttonSetAppointment = findViewById(R.id.buttonSetAppointment);
        databaseReference = FirebaseDatabase.getInstance().getReference("appointments");

        buttonSetAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAppointment();
            }
        });
    }

    private void saveAppointment() {
        // ... (existing code)

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String idOrAsylumNo = editTextID.getText().toString();
        String reason = editTextReason.getText().toString();
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();

        // Validate email format
        if (!isValidEmail(email)) {
            Toast.makeText(Appointments.this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate name
        if (!isValidName(name)) {
            Toast.makeText(Appointments.this, "Invalid name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate ID or Asylum number
        if (!isValidIdOrAsylumNo(idOrAsylumNo)) {
            Toast.makeText(Appointments.this, "Invalid ID or Asylum number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate reason
        if (!isValidReason(reason)) {
            Toast.makeText(Appointments.this, "Invalid reason", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date parsedDate = dateFormat.parse(date);
            if (parsedDate == null) {
                Toast.makeText(Appointments.this, "Invalid date format", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(Appointments.this, "Invalid date format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate time format
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        try {
            Date parsedTime = timeFormat.parse(time);
            if (parsedTime == null) {
                Toast.makeText(Appointments.this, "Invalid time format", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(Appointments.this, "Invalid time format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Continue with the rest of the code if email, name, ID, reason, date, and time formats are valid
        // ...

        // Create Appointment object
        Appointment appointment = new Appointment(name, idOrAsylumNo, email, reason, date, time);

        // Save to Firebase
        String appointmentId = databaseReference.push().getKey();
        if (appointmentId != null) {
            databaseReference.child(appointmentId).setValue(appointment)
                    .addOnSuccessListener(aVoid -> {
                        // Clear input fields
                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextID.setText("");
                        editTextReason.setText("");
                        editTextDate.setText("");
                        editTextTime.setText("");

                        // Show success message
                        Toast.makeText(Appointments.this, "Appointment set successfully!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Handle failure (e.g., show an error message)
                        Toast.makeText(Appointments.this, "Failed to set appointment. Please try again.", Toast.LENGTH_SHORT).show();
                    });
        }
    }
    private boolean isValidIdOrAsylumNo(String idOrAsylumNo) {
        // Customize this validation based on your requirements
        // For example, check if it's a numeric value or has a specific length

        // Check if the ID or Asylum number is not empty
        if (idOrAsylumNo.isEmpty()) {
            return false;
        }

        // Check if the ID or Asylum number is numeric
        if (!idOrAsylumNo.matches("\\d+")) {
            return false;
        }

        // Check if the ID or Asylum number has a specific length (e.g., 10 characters)
        if (idOrAsylumNo.length() != 13) {
            return false;
        }

        // Add more conditions as needed based on your requirements

        return true;
    }


    private boolean isValidReason(String reason) {
        // Customize this validation based on your requirements
        // For example, check if it meets a certain length criteria or pattern

        // Check if the reason is not empty
        if (reason.isEmpty()) {
            return false;
        }

        // Check if the reason length is between 5 and 100 characters
        if (reason.length() < 5 || reason.length() > 100) {
            return false;
        }

        // Check if the reason contains only letters and spaces
        // Modify the regex pattern based on your specific requirements
        String reasonRegex = "^[a-zA-Z\\s]+$";
        return reason.matches(reasonRegex);
    }


    private boolean isValidEmail(String email) {
        // Use a simple regex for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidName(String name) {

        if (name.isEmpty()) {
            return false;
        }

        // Check if the name length is between 2 and 50 characters
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }

        // Check if the name contains only letters and spaces
        // Modify the regex pattern based on your specific requirements
        String nameRegex = "^[a-zA-Z\\s]+$";
        return name.matches(nameRegex);
    }

}