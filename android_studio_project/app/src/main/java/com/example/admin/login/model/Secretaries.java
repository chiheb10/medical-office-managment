package com.example.admin.login.model;


import com.google.gson.annotations.SerializedName;

public class Secretaries  {

    private long sId;
    @SerializedName("firstname")
    private String sFirstName;
    @SerializedName("lastname")
    private String sLastName;
    @SerializedName("username")
    private String susername;
    @SerializedName("password")
    private String spassword;
    @SerializedName("address")
    private String sAddress;
    @SerializedName("phone")
    private String sPhoneNumber;




    public Secretaries() {

    }

    public Secretaries(String firstName, String lastName,String usernmae,String password, String address, String phoneNumber) {
        this.sFirstName = firstName;
        this.sLastName = lastName;
        this.susername = usernmae;
        this.spassword = password;
        this.sAddress = address;
        this.sPhoneNumber = phoneNumber;


    }

    public long getId() {
        return sId;
    }

    public void setId(long mId) {
        this.sId = mId;
    }

    public String getFirstName() {
        return sFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.sFirstName = mFirstName;
    }

    public String getLastName() {
        return sLastName;
    }

    public void setLastName(String mLastName) {
        this.sLastName = mLastName;
    }

    public String getUsername(){return susername;}
    public void setUsername(String username){this.susername = username;}
    public String getPassword(){return spassword;}
    public void setPassword(String password){this.spassword = password;}
    public String getAddress() {
        return sAddress;
    }

    public void setAddress(String mAddress) {
        this.sAddress = mAddress;
    }

    public String getPhoneNumber() {
        return sPhoneNumber;
    }

    public void setPhoneNumber(String mPhoneNumber) {
        this.sPhoneNumber = mPhoneNumber;
    }


}
