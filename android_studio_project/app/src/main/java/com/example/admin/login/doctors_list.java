package com.example.admin.login;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.login.model.Doctor;
import com.example.admin.login.model.Secretaries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class doctors_list extends AppCompatActivity {

    private static final String doctors_url="http://10.0.2.2/getdoctors.php";
    RecyclerView recyclerView;
    doctorsadapter adapter;
    List<Doctor> doctorsList;
    private ApiUser_getInterface apiUser_getInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        //secretariesList=new ArrayList<>();
        //Bundle bundle=getIntent().getExtras();
        //final String d_username=bundle.getString("username");
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorsList=new ArrayList<>();
        if( getIntent().getExtras()!=null){
            String region=getIntent().getExtras().getString("region");
            String spec=getIntent().getExtras().getString("spec");
            String id_user=getIntent().getExtras().getString("id_user");
            String rspec=region+"+"+spec;
            Intent intent=new Intent(doctors_list.this,calendar.class);
            intent.putExtra("id_user",id_user);
            //Toast.makeText(doctors_list.this,rspec,Toast.LENGTH_LONG).show();

            fetchinformation(rspec);


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
        String id = i.getExtras().getString("id_user");
        if(id_m==R.id.db){Intent intent=new Intent(doctors_list.this,user.class);
            intent.putExtra("id",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(doctors_list.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }

    public void fetchinformation(String rspec){
        apiUser_getInterface=ApiUser_get_doctors.getApiDoctors().create(ApiUser_getInterface.class);
        Call<List<Doctor>> call= apiUser_getInterface.getdoctorsinfo(rspec);
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, retrofit2.Response<List<Doctor>> response) {
                doctorsList=response.body();
                adapter=new doctorsadapter(doctors_list.this,doctorsList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });

    }
}

