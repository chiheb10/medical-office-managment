package com.example.admin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class appo_details extends AppCompatActivity {
TextView first,last,ph,age,m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appo_details);
        first=(TextView)findViewById(R.id.p_firstname_t);
        last=(TextView)findViewById(R.id.p_lastname_t);
        ph=(TextView)findViewById(R.id.p_phone_t);
        age=(TextView) findViewById(R.id.p_age_t);
        m=(TextView)findViewById(R.id.p_matter_t);
        String firstn=getIntent().getExtras().getString("firstname");
        String lastn=getIntent().getExtras().getString("lastname");
        String pho=getIntent().getExtras().getString("phone");
        String ageo=getIntent().getExtras().getString("age");
        String mo=getIntent().getExtras().getString("matter");
        first.setText(firstn);
        last.setText(lastn);
        ph.setText(pho);
        age.setText(ageo);
        m.setText(mo);
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
        if(id_m==R.id.db){Intent intent=new Intent(appo_details.this,secretary_area.class);
            intent.putExtra("id_sec",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(appo_details.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }
}
