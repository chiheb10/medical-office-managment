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

public class secretaries_list extends AppCompatActivity {
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];

            String get_url = "http://10.0.2.2/getsecretaries.php";
            if(type.equals("get")){
                try {
                    String user_name = params[1];

                    URL url = new URL(get_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("d_username", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result ="";
                    String line ="";
                    while((line = bufferedReader.readLine())!=null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                }catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("get status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);

            alertDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private static final String secretaries_url="http://10.0.2.2/getsecretaries.php";
    RecyclerView recyclerView;
    secretariesadapter adapter;
    List<Secretaries> secretariesList;
    private ApiDoctor_getInterface apiDoctor_getInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretaries_list);
        //secretariesList=new ArrayList<>();
       //Bundle bundle=getIntent().getExtras();
        //final String d_username=bundle.getString("username");
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        secretariesList=new ArrayList<>();
       if( getIntent().getExtras()!=null){
            String d_username=getIntent().getExtras().getString("d_username");
            fetchinformation(d_username);

        }

        //BackgroundWorker backgroundWorker=new BackgroundWorker(secretaries_list.this);
       // backgroundWorker.execute("get",d_username);


       //load_secretaries();
       /* secretariesList.add(new Secretaries("sec_1","sec_1","sec","1234","tunis","0000"));
        secretariesList.add(new Secretaries("sec_2","sec_2","sec","1234","tunis","0000"));
        adapter = new secretariesadapter(this,secretariesList);
        recyclerView.setAdapter(adapter);*/
    }
    private void load_secretaries(){


        StringRequest stringRequest=new StringRequest(Request.Method.GET, secretaries_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray secretaries = new JSONArray(response);
                    for(int i=0;i<secretaries.length();i++){
                        JSONObject secretariesobject = secretaries.getJSONObject(i);
                        String firstname=secretariesobject.getString("firstname");
                        String lastname=secretariesobject.getString("lastname");
                        String username=secretariesobject.getString("username");
                        String password=secretariesobject.getString("password");
                        String address=secretariesobject.getString("address");
                        String phone=secretariesobject.getString("phone");
                        //String id_d=secretariesobject.getString("id_doctor");
                        Secretaries secretary=new Secretaries(firstname,lastname,username,password,address,phone);
                        secretariesList.add(secretary);
                    }
                    adapter=new secretariesadapter(secretaries_list.this,secretariesList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(secretaries_list.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
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
        String username = i.getExtras().getString("d_username");
        if(id_m==R.id.db){Intent intent=new Intent(secretaries_list.this,doctorAreaActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(secretaries_list.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }
    public void fetchinformation(String username){
        apiDoctor_getInterface=ApiDoctor_get_secretaries.getApiDocotr().create(ApiDoctor_getInterface.class);
        Call<List<Secretaries>> call= apiDoctor_getInterface.getsecretariesinfo(username);
        call.enqueue(new Callback<List<Secretaries>>() {
            @Override
            public void onResponse(Call<List<Secretaries>> call, retrofit2.Response<List<Secretaries>> response) {
                secretariesList=response.body();
                adapter=new secretariesadapter(secretaries_list.this,secretariesList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Secretaries>> call, Throwable t) {

            }
        });

    }
}
