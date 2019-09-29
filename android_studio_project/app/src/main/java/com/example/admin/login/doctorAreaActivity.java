package com.example.admin.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.admin.login.model.Secretaries;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class doctorAreaActivity extends loginActivity  {
   private Context context;
   private ImageButton sch;

private ImageView getsecretaries,badd,imgView6;
RecyclerView recyclerView;
secretariesadapter adapter;
List<Secretaries> secretariesList;
    List<Data> dataArray;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_area);

        badd=(ImageView) findViewById(R.id.addsecret);
        getsecretaries=(ImageView)findViewById(R.id.getsecret);
        imgView6=(ImageView)findViewById(R.id.imgView6);
        //final TextView welcomemsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        sch=(ImageButton)findViewById(R.id.sch);
        Bundle bundle=getIntent().getExtras();
        final String name=bundle.getString("name");
        final String d_username=bundle.getString("username");
        sch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(doctorAreaActivity.this,calendar_d.class);
                intent.putExtra("d_username",d_username);
                startActivity(intent);
            }
        });






      // welcomemsg.setText(name);
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(doctorAreaActivity.this,add_secretary.class);
                intent.putExtra("_username",d_username);
                startActivity(intent);
            }
        });
        getsecretaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent(doctorAreaActivity.this,secretaries_list.class);
               intent.putExtra("d_username",d_username);
               startActivity(intent);


            }
        });
        imgView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctorAreaActivity.this,loginActivity.class));
            }
        });
    }



}
