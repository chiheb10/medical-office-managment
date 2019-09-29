package com.example.admin.login;

import com.google.gson.annotations.SerializedName;

public class appointment_u {
    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String dName;
    @SerializedName("phone")
    private String dPhoneNumber;
    @SerializedName("address")
    private String daddress;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;

    public appointment_u(int id, String dName, String dPhoneNumber, String daddress, String date, String time) {
        Id = id;
        this.dName = dName;
        this.dPhoneNumber = dPhoneNumber;
        this.daddress = daddress;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return Id;
    }

    public String getdName() {
        return dName;
    }

    public String getdPhoneNumber() {
        return dPhoneNumber;
    }

    public String getDaddress() {
        return daddress;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public void setdPhoneNumber(String dPhoneNumber) {
        this.dPhoneNumber = dPhoneNumber;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
