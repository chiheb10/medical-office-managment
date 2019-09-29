package com.example.admin.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

public class scheduler_user extends AppCompatActivity {
    private static final String scheduler_url="http://10.0.2.2/getscheduler.php";
    RecyclerView recyclerView;
    scheduler_user_adapter adapter;
    List<Scheduleruser> List;
    private APIUSER_get_appinterface apiuser_get_appinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_user);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List=new ArrayList<>();
        if( getIntent().getExtras()!=null){
            String date=getIntent().getExtras().getString("date");
            int id=getIntent().getExtras().getInt("id");
            String id_user=getIntent().getExtras().getString("id_user");
            String did=Integer.toString(id);
            String info=did+"+"+date;
            Intent intent=new Intent(scheduler_user.this,formulaire_user.class);
            intent.putExtra("id_user",id_user);

            fetchinformation(info);}



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
        String id = i.getExtras().getString("id_user");
        if(id_m==R.id.db){Intent intent=new Intent(scheduler_user.this,user.class);
        intent.putExtra("id",id);
        startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(scheduler_user.this,loginActivity.class));}
        if(id_m==R.id.set){
            Intent intent=new Intent(scheduler_user.this,settings_2.class);
            intent.putExtra("id",id);
            intent.putExtra("level","user");
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }


    public void fetchinformation(String info){
        apiuser_get_appinterface=Api_user_get_app.getApiapp().create(APIUSER_get_appinterface.class);
        Call<List<Scheduleruser>> call= apiuser_get_appinterface.s(info);
        call.enqueue(new Callback<List<Scheduleruser>>() {
            @Override
            public void onResponse(Call<List<Scheduleruser>> call, retrofit2.Response<List<Scheduleruser>> response) {
                List=response.body();
                adapter=new scheduler_user_adapter(scheduler_user.this,List);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Scheduleruser>> call, Throwable t) {

            }
        });

    }
}
