package com.example.admin.login.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
public class Doctor {


    @SerializedName("id")
    private int dId;
    @SerializedName("name")
    private String dName;
    @SerializedName("phone")
    private String dPhoneNumber;

    @SerializedName("address")
    private String daddress;

    public Doctor() {}


    public Doctor(int id,String name,String PhoneNumber,String Address) {
        this.dId=id;
        this.dName = name;
        this.daddress = Address;
        this.dPhoneNumber = PhoneNumber;

    }


    public int getId() {
        return dId;
    }
    public void setId(int mId) {
        this.dId = mId;
    }
    public String getName() {
        return dName;
    }
    public void setName(String mFirstName) {
        this.dName = mFirstName;
    }



    public String getAddress() {
        return daddress;
    }
    public void setAddress(String mAddress) {
        this.daddress = mAddress;
    }


    public String getPhoneNumber() {
        return dPhoneNumber;
    }
    public void setPhoneNumber(String mPhoneNumber) {
        this.dPhoneNumber = mPhoneNumber;
    }

    
}
