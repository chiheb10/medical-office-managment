package com.example.admin.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apidoctor_get_sch {
    @GET("getscheduler_d.php")
    Call<List<Scheduleruser>> s(@Query("info") String info);
}
