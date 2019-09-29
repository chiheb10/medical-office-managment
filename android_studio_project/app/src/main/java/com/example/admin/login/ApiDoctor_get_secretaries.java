package com.example.admin.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiDoctor_get_secretaries {
    public static final String base_URl="http://10.0.2.2/";
    public static Retrofit retrofit;
    public static Retrofit getApiDocotr() {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(base_URl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


}

