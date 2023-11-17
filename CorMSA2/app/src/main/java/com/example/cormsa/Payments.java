package com.example.cormsa;

public class Payments {
    private String debitCardType;
    private double donationAmount;
    private String cardNumber;
    private String cardExpiration;

    public Payments() {
        // Default constructor required for Firebase
    }

    public Payments(String debitCardType, double donationAmount, String cardNumber, String cardExpiration) {
        this.debitCardType = debitCardType;
        this.donationAmount = donationAmount;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
    }

    public String getDebitCardType() {
        return debitCardType;
    }

    public void setDebitCardType(String debitCardType) {
        this.debitCardType = debitCardType;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiration() {
        return cardExpiration;
    }

    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }
}
