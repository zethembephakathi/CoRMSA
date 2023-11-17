package com.example.cormsa;

// PermitApplication.java


public class PermitApplication {

    private String name;
    private String email;
    private String cellphone;
    private String passportId;
    private String permitType;

    // Default constructor required for Firebase
    public PermitApplication() {
    }

    public PermitApplication(String name, String email, String cellphone, String passportId, String permitType) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.passportId = passportId;
        this.permitType = permitType;
    }

    // Add getters and setters as needed

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

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }
}


