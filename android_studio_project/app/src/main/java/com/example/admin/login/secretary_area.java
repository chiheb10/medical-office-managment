package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

public class secretary_area extends AppCompatActivity {
ImageButton getscheuler;
ImageView getappo,settings,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary_area);
        getscheuler=(ImageButton)findViewById(R.id.getscheduler);
        getappo=(ImageView)findViewById(R.id.getappo);
        settings=(ImageView)findViewById(R.id.settings);
        logout=(ImageView)findViewById(R.id.logout);
        final String id=getIntent().getExtras().getString("id_sec");
        getscheuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(secretary_area.this,calendar_s.class);
                intent.putExtra("id_sec",id);
                startActivity(intent);
            }
        });
        getappo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(secretary_area.this,appo_sec.class);
                intent.putExtra("id_sec",id);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(secretary_area.this,loginActivity.class));
            }
        });
        //Toast.makeText(secretary_area.this,id,Toast.LENGTH_LONG).show();
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(secretary_area.this,settings_2.class);
                intent.putExtra("id",id);
                intent.putExtra("level","secretary");
                startActivity(intent);
            }
        });

    }
}
