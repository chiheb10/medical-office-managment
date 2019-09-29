package com.example.admin.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiUser_get_int_app {
    @GET("getappo_user.php")
    Call<List<appointment_u>> app(@Query("id") String id);
}
