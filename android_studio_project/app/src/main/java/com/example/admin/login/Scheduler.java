package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


public class Scheduler extends AppCompatActivity {
    public class BackgroundWorker_sc extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker_sc (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String verify_url = "http://10.0.2.2/verifyscheduer.php";
            String register_url = "http://10.2.2/register_user.php";
            if(type.equals("verify")){
                try {
                    String date = params[1];
                    String time = params[2];
                    URL url = new URL(verify_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("date", "UTF-8")+"="+URLEncoder.encode(date, "UTF-8")+"&"
                            +URLEncoder.encode("time", "UTF-8")+"="+URLEncoder.encode(time, "UTF-8");
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
            alertDialog.setTitle("login status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            if(result.equals("free")){

                Toast.makeText(Scheduler.this,"free time",Toast.LENGTH_LONG).show();}
                else{Toast.makeText(Scheduler.this,"no free time",Toast.LENGTH_LONG).show();}
            //alertDialog.show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        Bundle bundle=getIntent().getExtras();
        final String date=bundle.getString("date");
        final int id=bundle.getInt("id");
        String time="8h-8h30";
        String type="verify";
        final BackgroundWorker_sc backgroundWorker_sc=new BackgroundWorker_sc(Scheduler.this);
        backgroundWorker_sc.execute(type,date,time);

        TextView app1=(TextView)findViewById(R.id.appo1);
        TextView app2=(TextView)findViewById(R.id.appo2);
        TextView app3=(TextView)findViewById(R.id.appo3);
        TextView app4=(TextView)findViewById(R.id.appo4);
        TextView app5=(TextView)findViewById(R.id.appo5);
        TextView app6=(TextView)findViewById(R.id.appo6);
        TextView app7=(TextView)findViewById(R.id.appo7);
        TextView app8=(TextView)findViewById(R.id.appo8);
        //final BackgroundWorker_sc backgroundWorker_sc=new BackgroundWorker_sc(Scheduler.this);
        app1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time="8h-8h30";
                String type="verify";
                final BackgroundWorker_sc backgroundWorker_sc=new BackgroundWorker_sc(Scheduler.this);
                backgroundWorker_sc.execute(type,date,time);
                //Toast.makeText(Scheduler.this,date,Toast.LENGTH_LONG).show();


            }
        });


    }
}
