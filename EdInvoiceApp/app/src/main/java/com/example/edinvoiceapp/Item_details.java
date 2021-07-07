package com.example.edinvoiceapp;

public class Item_details {
    private String text_from_ItemDetails;
    private String digit;
    private String total_sum;

    public Item_details(String text_from_ItemDetails, String digit, String total_sum) {
        this.text_from_ItemDetails = text_from_ItemDetails;
        this.digit = digit;
        this.total_sum = total_sum;
    }

    public String getText_from_ItemDetails() {
        return text_from_ItemDetails;
    }

    public void setText_from_ItemDetails(String text_from_ItemDetails) {
        this.text_from_ItemDetails = text_from_ItemDetails;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    public String getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(String total_sum) {
        this.total_sum = total_sum;
    }
}
