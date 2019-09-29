package com.example.admin.login;

import com.example.admin.login.model.Secretaries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDoctor_getInterface {
    @GET("getsecretaries.php")
    Call<List<Secretaries>>getsecretariesinfo(@Query("d_username") String d_username);

}
