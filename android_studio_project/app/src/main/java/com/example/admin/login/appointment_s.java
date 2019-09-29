package com.example.admin.login;

import com.google.gson.annotations.SerializedName;

public class appointment_s {
    @SerializedName("id")
    private String Id;
    @SerializedName("firstname")
    private String dfName;
    @SerializedName("lastname")
    private String dlname;
    @SerializedName("phone")
    private String dphone;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("age")
    private String age;
    @SerializedName("matter")
    private String des;
    @SerializedName("gender")
    private String gen;

    public appointment_s(String id, String dfName, String dlname, String dphone, String date, String time, String age, String des, String gen) {
        Id = id;
        this.dfName = dfName;
        this.dlname = dlname;
        this.dphone = dphone;
        this.date = date;
        this.time = time;
        this.age = age;
        this.des = des;
        this.gen = gen;
    }

    public String getId() {
        return Id;
    }

    public String getDfName() {
        return dfName;
    }

    public String getDlname() {
        return dlname;
    }

    public String getDphone() {
        return dphone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAge() {
        return age;
    }

    public String getDes() {
        return des;
    }

    public String getGen() {
        return gen;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setDfName(String dfName) {
        this.dfName = dfName;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
