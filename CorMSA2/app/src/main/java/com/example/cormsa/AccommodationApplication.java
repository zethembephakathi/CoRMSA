package com.example.cormsa;

// AccommodationApplication.java


public class AccommodationApplication {
    private String name, email, cellphone, familyNumber, nationality, duration, accommodationType;

    public AccommodationApplication() {
        // Default constructor required for Firebase
    }

    public AccommodationApplication(String name, String email, String cellphone, String familyNumber, String nationality, String duration, String accommodationType) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.familyNumber = familyNumber;
        this.nationality = nationality;
        this.duration = duration;
        this.accommodationType = accommodationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }
}
