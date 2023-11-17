package com.example.cormsa;

public class Appointment {

    private String name;

    private String email;
    private String idOrAsylumNo;
    private String reason;
    private String time;
    private String date;

    public Appointment() {
        // Default constructor required for Firebase
    }

    public Appointment(String name, String idOrAsylumNo, String email, String reason, String date, String time) {
        this.name = name;
        this.email = email;
        this.idOrAsylumNo = idOrAsylumNo;
        this.reason = reason;
        this.date = date;
        this.time = time;
    }


    // Getters
    public String getName() {
        return name;
    }

    public String getIdOrAsylumNo() {
        return idOrAsylumNo;
    }
    public String getEmail() {
        return email;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setIdOrAsylumNo(String idOrAsylumNo) {
        this.idOrAsylumNo = idOrAsylumNo;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
