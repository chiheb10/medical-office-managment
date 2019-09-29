package com.example.admin.login;

import com.example.admin.login.model.Secretaries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIUSER_get_appinterface {
    @GET("getscheduler.php")
     Call<List<Scheduleruser>>s(@Query("info") String info);
}
