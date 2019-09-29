package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class formulaire_user extends AppCompatActivity {
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];

            String reserve_url = "http://10.0.2.2/reservation.php";
            if(type.equals("reservation")){
                try {
                    String firstname = params[1];
                    String lastname = params[2];
                    String phone = params[3];
                    String age = params[4];
                    String id = params[5];
                    String number=params[6];
                    String username=params[7];
                    String password=params[8];
                    String des=params[9];
                    URL url = new URL(reserve_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                            +URLEncoder.encode("lastname", "UTF-8")+"="+URLEncoder.encode(lastname, "UTF-8")+"&"
                            +URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(phone, "UTF-8")+"&"
                            +URLEncoder.encode("age", "UTF-8")+"="+URLEncoder.encode(age, "UTF-8")+"&"
                            +URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
                            +URLEncoder.encode("number", "UTF-8")+"="+URLEncoder.encode(number, "UTF-8")+"&"
                            +URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                            +URLEncoder.encode("description", "UTF-8")+"="+URLEncoder.encode(des, "UTF-8");
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
            alertDialog.setTitle("reservation status");
        }

        @Override
        protected void onPostExecute(String result) {
            //alertDialog.setMessage(result);
            //alertDialog.show();
            if(result.equals("reservation not success")){
                alertDialog.setMessage("try again");
                alertDialog.show();
            }
            else if (result.equals("please verify your username and password")){
                alertDialog.setMessage("please verify your username and password");
                alertDialog.show();
            }
            else{
                alertDialog.setMessage("reservation  success");
                //alertDialog.show();
                Toast.makeText(formulaire_user.this,"reservation success",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(formulaire_user.this,user.class);
                intent.putExtra("id",result);
                startActivity(intent);
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private EditText res_firstname,res_lastname,res_phone,res_age,res_username,res_password,res_des;
private Button res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_user);
        res_firstname=(EditText) findViewById(R.id.res_firstname);
        res_lastname=(EditText)findViewById(R.id.res_lastname);
        res_phone=(EditText)findViewById(R.id.res_phone);
        res_age=(EditText)findViewById(R.id.res_age);
        res_username=(EditText)findViewById(R.id.res_username);
        res_password=(EditText)findViewById(R.id.res_password);
        res_des=(EditText)findViewById(R.id.res_des);
        res=(Button)findViewById(R.id.res);

        final String id=getIntent().getExtras().getString("id_su");
        final String number=getIntent().getExtras().getString("number");
        final String id_user=getIntent().getExtras().getString("id_user");
        //Toast.makeText(formulaire_user.this,test,Toast.LENGTH_LONG).show();
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String type="reservation";
                final String firstname=res_firstname.getText().toString();
                final String lastname=res_lastname.getText().toString();
                final String phone=res_phone.getText().toString();
                final String age=res_age.getText().toString();
                final String username=res_username.getText().toString();
                final String password=res_password.getText().toString();
                final String des=res_des.getText().toString();
                if(firstname.isEmpty()||lastname.isEmpty()||phone.isEmpty()||age.isEmpty()||username.isEmpty()||password.isEmpty()||des.isEmpty()){
                    Toast.makeText(formulaire_user.this,"please fill the fields",Toast.LENGTH_LONG).show();
                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(formulaire_user.this);
                    builder.setMessage("Are you sure to confirm the reservation").setCancelable(false)
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final BackgroundWorker backgroundWorker=new BackgroundWorker(formulaire_user.this);
                                    //Toast.makeText(formulaire_user.this,id_user,Toast.LENGTH_LONG).show();
                                    backgroundWorker.execute(type,firstname,lastname,phone,age,id,number,username,password,des);
                                }
                            })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert =builder.create();
                alert.setTitle("confirmation");
                alert.show();}

                //final BackgroundWorker backgroundWorker=new BackgroundWorker(formulaire_user.this);
                //backgroundWorker.execute(type,firstname,lastname,phone,age,id,number,username,password,des);}

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
        if(id_m==R.id.db){Intent intent=new Intent(formulaire_user.this,user.class);
            intent.putExtra("id",id);
            startActivity(intent);}
        if(id_m==R.id.lo){startActivity(new Intent(formulaire_user.this,loginActivity.class));}
        if(id_m==R.id.set){}



        return super.onOptionsItemSelected(item);
    }
}
