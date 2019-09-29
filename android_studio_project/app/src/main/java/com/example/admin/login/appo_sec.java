package com.example.admin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class appo_sec extends AppCompatActivity {

    private static final String doctors_url="http://10.0.2.2/getappo_user.php";
    RecyclerView recyclerView;
    appo_sec_adapter adapter;
    java.util.List<appointment_s> List;
    private Apisec_get_int_app apiUser_get_int_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appo_sec);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List=new ArrayList<>();
        if(getIntent().getExtras()!=null){
            final String id=getIntent().getExtras().getString("id_sec");
            fetchinformation(id);
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
        String id = i.getExtras().getString("id_sec");
        if(id_m==R.id.db){Intent intent=new Intent(appo_sec.this,secretary_area.class);
            intent.putExtra("id_sec",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(appo_sec.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }



    public void fetchinformation(String id){
        apiUser_get_int_app=Api_user_get_app.getApiapp().create(Apisec_get_int_app.class);
        Call<List<appointment_s>> call= apiUser_get_int_app.app(id);
        call.enqueue(new Callback<List<appointment_s>>() {
            @Override
            public void onResponse(Call<List<appointment_s>> call, retrofit2.Response<List<appointment_s>> response) {
                List=response.body();
                adapter=new appo_sec_adapter(appo_sec.this,List);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<appointment_s>> call, Throwable t) {

            }
        });

    }
}
