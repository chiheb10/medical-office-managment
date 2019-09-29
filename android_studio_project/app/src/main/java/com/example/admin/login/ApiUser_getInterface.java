package com.example.admin.login;

import com.example.admin.login.model.Doctor;
import com.example.admin.login.model.Secretaries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiUser_getInterface {
    @GET("getdoctors.php")
    Call<List<Doctor>>getdoctorsinfo(@Query("rspec") String rspec);

}
