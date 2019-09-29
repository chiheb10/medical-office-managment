package com.example.admin.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_user_get_app {
    public static final String base_URl="http://10.0.2.2/";
    public static Retrofit retrofit;
    public static Retrofit getApiapp() {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(base_URl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
