package com.example.admin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class user extends AppCompatActivity {
private TextView welcome;
private Button blog;
    private ImageButton bsearch;
   private ImageView myappo,imgView6,imgView5;
    private Button bapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //welcome = (TextView) findViewById(R.id.welcomeuser);
        //blog = (Button) findViewById(R.id.blog);
        bsearch=(ImageButton)findViewById(R.id.bsearch);
        myappo=(ImageView) findViewById(R.id.myappo);
        imgView6=(ImageView)findViewById(R.id.imgView6);
        imgView5=(ImageView)findViewById(R.id.imgView5);
        //imgView3=(ImageView)findViewById(R.id.imgView3);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        final String id_user=bundle.getString("id");
        //welcome.setText("Welcome  "+name);
        bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user.this,searchdoctors.class);
                intent.putExtra("id_user",id_user);
                startActivity(intent);
            }
        });
        myappo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user.this,appo_user.class);
                intent.putExtra("id",id_user);
                startActivity(intent);
            }
        });
        imgView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user.this,loginActivity.class));
            }
        });
        imgView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(user.this,settings_2.class);
                intent.putExtra("id",id_user);
                intent.putExtra("level","user");
                startActivity(intent);
            }
        });


    }

}
