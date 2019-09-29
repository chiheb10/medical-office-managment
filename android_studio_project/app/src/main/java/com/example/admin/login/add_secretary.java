package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class add_secretary extends AppCompatActivity {
    public class BackgroundWorker_s extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker_s (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://10.0.2.2/login.php";
            String register_url = "http://10.0.2.2/add_secre.php";
            if(type.equals("login")){
                try {
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
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
            else if(type.equals("register_secretary")){
                try {
                    String firstname = params[1];
                    String lastname = params[2];
                    String s_username = params[3];
                    String password = params[4];
                    String s_address = params[5];
                    String phone = params[6];
                    String doc=params[7];

                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data1 = URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                            +URLEncoder.encode("lastname", "UTF-8")+"="+URLEncoder.encode(lastname, "UTF-8")+"&"
                            +URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(s_username, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                            +URLEncoder.encode("d_user", "UTF-8")+"="+URLEncoder.encode(doc, "UTF-8")+"&"
                            +URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(s_address, "UTF-8")+"&"
                            +URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(phone, "UTF-8")+"&";
                    bufferedWriter.write(post_data1);
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
            alertDialog.setTitle("register secretary status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);

            //alertDialog.show();
            if(result.equals("registration not success")){
                alertDialog.show();}
                //Toast.makeText(add_secretary.this,"registration success",Toast.LENGTH_LONG).show();
           else{ Intent intent = new Intent(add_secretary.this,doctorAreaActivity.class);
            intent.putExtra("username",result);
                Toast.makeText(add_secretary.this,"registration success",Toast.LENGTH_LONG).show();
            startActivity(intent);}
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private EditText s_firstname;
    private EditText s_lastname;
    private EditText s_username;
    private EditText s_password;
    private EditText s_address;
    private EditText s_phone;
    private Button badd_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secretary);
        s_firstname=(EditText)findViewById(R.id.s_firstname);
        s_lastname=(EditText)findViewById(R.id.s_lastname);
        s_username=(EditText)findViewById(R.id.s_username);
        s_password=(EditText)findViewById(R.id.s_password);
        s_address=(EditText)findViewById(R.id.s_address);
        s_phone=(EditText)findViewById(R.id.s_phone);
        Bundle bundle=getIntent().getExtras();
        final String doc= bundle.getString("_username");

        badd_s=(Button)findViewById(R.id.add_s);

        badd_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = s_firstname.getText().toString();
                String lastname = s_lastname.getText().toString();
                String username = s_username.getText().toString();
                String password = s_password.getText().toString();
                String address = s_address.getText().toString();
                String phone = s_phone.getText().toString();
                final String type ="register_secretary";
                if(firstname.isEmpty()||lastname.isEmpty()||username.isEmpty()||password.isEmpty()||address.isEmpty()||phone.isEmpty()){
                    Toast.makeText(add_secretary.this, "please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
                else{

               BackgroundWorker_s backgroundWorker_s=new BackgroundWorker_s(add_secretary.this);
                backgroundWorker_s.execute(type,firstname,lastname,username,password,address,phone,doc);}



            }
        });
    }
}
