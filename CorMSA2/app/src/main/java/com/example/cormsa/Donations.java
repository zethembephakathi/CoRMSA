package com.example.cormsa;

public class Donations {
    private String donorName;
    private boolean isAnonymous;

    private String dons;

    public Donations() {
        // Default constructor required for Firebase
    }

    public Donations(String donorName, boolean isAnonymous, String dons) {
        this.donorName = donorName;
        this.dons = dons;
        this.isAnonymous = isAnonymous;

    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDons() {
        return dons;
    }

    public void setDons(String dons) {
        this.donorName = dons;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
}
