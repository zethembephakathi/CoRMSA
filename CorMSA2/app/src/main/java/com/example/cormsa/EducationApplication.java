package com.example.cormsa;

// EducationApplication.java

// EducationApplication.java


public class EducationApplication {
    private String name, email, cellphone, nationality, skills, passportNumber, educationType, permitNumber;

    public EducationApplication() {
        // Default constructor required for Firebase
    }

    public EducationApplication(String name, String email, String cellphone, String nationality, String skills, String passportNumber, String educationType, String permitDocument) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.nationality = nationality;
        this.skills = skills;
        this.passportNumber = passportNumber;
        this.educationType = educationType;
        this.permitNumber = permitNumber;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getpermitNumber() {
        return permitNumber;
    }

    public void setpermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }
}
