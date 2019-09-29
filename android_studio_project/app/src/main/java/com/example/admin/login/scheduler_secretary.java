package com.example.admin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class scheduler_secretary extends AppCompatActivity {

    private static final String scheduler_url="http://10.0.2.2/getscheduler_s.php";
    RecyclerView recyclerView;
    scheduler_secretary_adapter adapter;
    java.util.List<Scheduleruser> List;
    private Apisec_get_sch apiuser_get_appinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_secretary);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List=new ArrayList<>();

        if( getIntent().getExtras()!=null){
            String date=getIntent().getExtras().getString("date");
            String id=getIntent().getExtras().getString("id_sec");
            String info=id+"+"+date;
            Intent intent=new Intent(scheduler_secretary.this,appo_details.class);
            intent.putExtra("id_sec",id);

            //Toast.makeText(scheduler_secretary.this,info,Toast.LENGTH_LONG).show();


            fetchinformation(info);


        }
    }


    public void fetchinformation(String info){
        apiuser_get_appinterface=Api_user_get_app.getApiapp().create(Apisec_get_sch.class);
        Call<java.util.List<Scheduleruser>> call= apiuser_get_appinterface.s(info);
        call.enqueue(new Callback<List<Scheduleruser>>() {
            @Override
            public void onResponse(Call<List<Scheduleruser>> call, retrofit2.Response<List<Scheduleruser>> response) {
                List=response.body();
                adapter=new scheduler_secretary_adapter(scheduler_secretary.this,List);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Scheduleruser>> call, Throwable t) {

            }
        });

    }}
