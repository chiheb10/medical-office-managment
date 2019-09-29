package com.example.admin.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apisec_get_int_app {
    @GET("getappo_sec.php")
    Call<List<appointment_s>> app(@Query("id") String id);
}
