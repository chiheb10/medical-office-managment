package com.example.admin.login;

import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
//import com.example.admin.login.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class searchdoctors extends AppCompatActivity {

    ArrayList<String> listItems=new ArrayList<>();
    ArrayList<String> listItems2=new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    Spinner sp;
    Spinner sp2;
    TextView txt;
    TextView txt2;
    Button search;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdoctors);
        search=(Button)findViewById(R.id.search);
        final String[] reg = new String[2];
        final String id_user=getIntent().getExtras().getString("id_user");
        //txt2=(TextView)findViewById(R.id.txt2);
        sp=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listItems);
        adapter2=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt2,listItems2);
        sp.setAdapter(adapter);
        sp2.setAdapter(adapter2);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sSelected=parent.getItemAtPosition(position).toString();
                reg[0] =sSelected;
                Intent sIntent=new Intent(searchdoctors.this,MainActivity.class);
                //sIntent.putExtra("region",sSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sSelected=parent.getItemAtPosition(position).toString();

                reg[1]=sSelected;
                //Intent intent=new Intent(searchdoctors.this,MainActivity.class);
                ///intent.putExtra("spec",sSelected);
                //startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(searchdoctors.this,doctors_list.class);
                intent.putExtra("region",reg[0]);
                intent.putExtra("spec",reg[1]);
                intent.putExtra("id_user",id_user);
                startActivity(intent);
            }
        });


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
        if(id_m==R.id.db){Intent intent=new Intent(searchdoctors.this,user.class);
            intent.putExtra("id",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(searchdoctors.this,loginActivity.class));}
        if(id_m==R.id.set){
            Intent intent=new Intent(searchdoctors.this,settings_2.class);
            intent.putExtra("id",id);
            intent.putExtra("level","user");
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute();
        BackTask2 bt2=new BackTask2();
        bt2.execute();
    }



    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        protected void onPreExecute(){
            super.onPreExecute();
            list=new ArrayList<>();
        }
        protected Void doInBackground(Void...params){
            InputStream is=null;
            String result="";
            try{
                HttpClient httpclient=new DefaultHttpClient();
                HttpPost httppost= new HttpPost("http://10.0.2.2/searchdoctors.php");
                HttpResponse response=httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            }catch(IOException e){
                e.printStackTrace();
            }

            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result+=line;
                }
                is.close();
                //result=sb.toString();
            }catch(Exception e){
                e.printStackTrace();
            }
            // parse json data
            try{
                JSONArray jArray =new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("region"));
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result){
            listItems.addAll(list);
            adapter.notifyDataSetChanged();

        }
    }
    private class BackTask2 extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        protected void onPreExecute(){
            super.onPreExecute();
            list=new ArrayList<>();
        }
        protected Void doInBackground(Void...params){
            InputStream is=null;
            String result="";
            try{
                HttpClient httpclient=new DefaultHttpClient();
                HttpPost httppost= new HttpPost("http://10.0.2.2/searchdoctors_2.php");
                HttpResponse response=httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            }catch(IOException e){
                e.printStackTrace();
            }

            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result+=line;
                }
                is.close();
                //result=sb.toString();
            }catch(Exception e){
                e.printStackTrace();
            }
            // parse json data
            try{
                JSONArray jArray =new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("speciality"));
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result){
            listItems2.addAll(list);
            adapter2.notifyDataSetChanged();

        }
    }
}