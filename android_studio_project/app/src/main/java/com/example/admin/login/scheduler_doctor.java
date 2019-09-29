package com.example.admin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class scheduler_doctor extends AppCompatActivity  {
    private static final String scheduler_url="http://10.0.2.2/getscheduler_d.php";
    RecyclerView recyclerView;
    scheduler_doctor_adapter adapter;
    java.util.List<Scheduleruser> List;
    private Apidoctor_get_sch apiuser_get_appinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_doctor);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List=new ArrayList<>();
        if( getIntent().getExtras()!=null){
            String date=getIntent().getExtras().getString("date");
            String username=getIntent().getExtras().getString("username");
            String info=username+"+"+date;

            //Toast.makeText(scheduler_doctor.this,info,Toast.LENGTH_LONG).show();


           fetchinformation(info);


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.drawermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id_m = item.getItemId();
        Intent i = getIntent();
        String username = i.getExtras().getString("username");
        if(id_m==R.id.db){Intent intent=new Intent(scheduler_doctor.this,doctorAreaActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(scheduler_doctor.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }


    public void fetchinformation(String info){
        apiuser_get_appinterface=Api_user_get_app.getApiapp().create(Apidoctor_get_sch.class);
        Call<List<Scheduleruser>> call= apiuser_get_appinterface.s(info);
        call.enqueue(new Callback<List<Scheduleruser>>() {
            @Override
            public void onResponse(Call<List<Scheduleruser>> call, retrofit2.Response<List<Scheduleruser>> response) {
                List=response.body();
                adapter=new scheduler_doctor_adapter(scheduler_doctor.this,List);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Scheduleruser>> call, Throwable t) {

            }
        });

    }
    }

