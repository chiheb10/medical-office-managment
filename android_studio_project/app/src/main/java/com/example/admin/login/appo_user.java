package com.example.admin.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class appo_user extends AppCompatActivity {
    private static final String doctors_url="http://10.0.2.2/getappo_user.php";
    RecyclerView recyclerView;
    appo_user_adapter adapter;
    List<appointment_u>List;
    private ApiUser_get_int_app apiUser_get_int_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appo_user);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       List=new ArrayList<>();
       if(getIntent().getExtras()!=null){
           final String id=getIntent().getExtras().getString("id");
           fetchinformation(id);
       }

    }



    public void fetchinformation(String id){
        apiUser_get_int_app=Api_user_get_app.getApiapp().create(ApiUser_get_int_app.class);
        Call<List<appointment_u>> call= apiUser_get_int_app.app(id);
        call.enqueue(new Callback<List<appointment_u>>() {
            @Override
            public void onResponse(Call<List<appointment_u>> call, retrofit2.Response<List<appointment_u>> response) {
                List=response.body();
                adapter=new appo_user_adapter(appo_user.this,List);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<appointment_u>> call, Throwable t) {

            }
        });

    }
}





