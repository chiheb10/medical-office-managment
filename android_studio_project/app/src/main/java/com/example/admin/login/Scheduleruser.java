package com.example.admin.login;



import com.google.gson.annotations.SerializedName;

public class Scheduleruser {
    @SerializedName("id")
    private int id;
    @SerializedName("did")
    private int dId;
    @SerializedName("date")
    private String date;

    @SerializedName("app1")
    private String appo1;
    @SerializedName("app2")
    private String appo2;
    @SerializedName("app3")
    private String appo3;
    @SerializedName("app4")
    private String appo4;
    @SerializedName("app5")
    private String appo5;
    @SerializedName("app6")
    private String appo6;
    @SerializedName("app7")
    private String appo7;
    @SerializedName("app8")
    private String appo8;
    @SerializedName("app9")
    private String appo9;
    @SerializedName("app10")
    private String appo10;
    @SerializedName("app11")
    private String appo11;
    @SerializedName("app12")
    private String appo12;
    @SerializedName("app13")
    private String appo13;
    @SerializedName("app14")
    private String appo14;
    @SerializedName("app15")
    private String appo15;
    @SerializedName("app16")
    private String appo16;

    public Scheduleruser(int id, int dId,String date, String appo1, String appo2, String appo3, String appo4, String appo5, String appo6, String appo7, String appo8, String appo9, String appo10, String appo11, String appo12, String appo13, String appo14, String appo15, String appo16) {
        this.id = id;
        this.dId = dId;
        this.date=date;
        this.appo1 = appo1;
        this.appo2 = appo2;
        this.appo3 = appo3;
        this.appo4 = appo4;
        this.appo5 = appo5;
        this.appo6 = appo6;
        this.appo7 = appo7;
        this.appo8 = appo8;
        this.appo9 = appo9;
        this.appo10 = appo10;
        this.appo11 = appo11;
        this.appo12 = appo12;
        this.appo13 = appo13;
        this.appo14 = appo14;
        this.appo15 = appo15;
        this.appo16 = appo16;
    }

    public int getId() {
        return id;
    }

    public int getdId() {
        return dId;
    }
    public String getdate(){return date;}
public String getApp(int i){
         String result="";
        switch (i){
            case 1:
                result=appo1;
                break;
            case 2:
                result=appo2;
            break;
            case 3:
                result=appo3;
            break;
            case 4:
                result=appo4;
            break;
            case 5:
                result=appo5;
            break;
            case 6:
                result=appo6;
            break;
            case 7:
                result=appo7;
            break;
            case 8:
                result=appo8;
            break;
            case 9:
                result=appo9;
            break;
            case 10:
                result=appo10;
            break;
            case 11:
                result=appo11;
            break;
            case 12:
                result=appo12;
            break;
            case 13:
                result=appo13;
            break;
            case 14:
                result=appo14;
            break;
            case 15:
                result=appo15;
            break;
            case 16:
                result=appo16;
            break;

        }
        return result;
}
    public String getAppo1() {
        return appo1;
    }

    public String getAppo2() {
        return appo2;
    }

    public String getAppo3() {
        return appo3;
    }

    public String getAppo4() {
        return appo4;
    }

    public String getAppo5() {
        return appo5;
    }

    public String getAppo6() {
        return appo6;
    }

    public String getAppo7() {
        return appo7;
    }

    public String getAppo8() {
        return appo8;
    }

    public String getAppo9() {
        return appo9;
    }

    public String getAppo10() {
        return appo10;
    }

    public String getAppo11() {
        return appo11;
    }

    public String getAppo12() {
        return appo12;
    }


    public String getAppo13() {
        return appo13;
    }

    public String getAppo14() {
        return appo14;
    }

    public String getAppo15() {
        return appo15;
    }

    public String getAppo16() {
        return appo16;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public void setdate(String date){this.date=date;}
    public void setAppo1(String appo1) {
        this.appo1 = appo1;
    }

    public void setAppo2(String appo2) {
        this.appo2 = appo2;
    }

    public void setAppo3(String appo3) {
        this.appo3 = appo3;
    }

    public void setAppo4(String appo4) {
        this.appo4 = appo4;
    }

    public void setAppo5(String appo5) {
        this.appo5 = appo5;
    }

    public void setAppo6(String appo6) {
        this.appo6 = appo6;
    }

    public void setAppo7(String appo7) {
        this.appo7 = appo7;
    }

    public void setAppo8(String appo8) {
        this.appo8 = appo8;
    }

    public void setAppo9(String appo9) {
        this.appo9 = appo9;
    }

    public void setAppo10(String appo10) {
        this.appo10 = appo10;
    }

    public void setAppo11(String appo11) {
        this.appo11 = appo11;
    }

    public void setAppo12(String appo12) {
        this.appo12 = appo12;
    }

    public void setAppo13(String appo13) {
        this.appo13 = appo13;
    }

    public void setAppo14(String appo14) {
        this.appo14 = appo14;
    }

    public void setAppo15(String appo15) {
        this.appo15 = appo15;
    }

    public void setAppo16(String appo16) {
        this.appo16 = appo16;
    }
}
