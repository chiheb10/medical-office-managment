package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class settings_2 extends AppCompatActivity {
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://10.0.2.2/settings.php";
            String register_url = "http://10.2.2/register_user.php";
            if(type.equals("sett")){
                try {
                    String id = params[1];
                    String level = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8")+"&"
                            +URLEncoder.encode("level", "UTF-8")+"="+URLEncoder.encode(level, "UTF-8");;
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
            alertDialog.setTitle("settings status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            if(result.equals("try again")){alertDialog.show();}
            else{
                int k=result.indexOf('+');
                String username=result.substring(0,k);
                int j;
                j=result.indexOf('+',k+1);
                String password =result.substring(k+1,j);
                int i;
                i=result.indexOf('+',j+1);
                String address =result.substring(j+1,i);
                //int w;
                //w=result.indexOf('+',i+1);
                String val=result.substring(i+1);
                suser.setText("  username : "+username);
                spass.setText("  password : "+password);
                spho.setText("  address : "+address);
                se.setText("  "+val);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private TextView suser,spass,spho,se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_2);
        suser=(TextView)findViewById(R.id.susername);
        spass=(TextView)findViewById(R.id.spass);
        spho=(TextView)findViewById(R.id.sphone);
        se=(TextView)findViewById(R.id.semail);
        final String type="sett";
        final String id=getIntent().getExtras().getString("id");
        final String username=getIntent().getExtras().getString("username");
        final String level=getIntent().getExtras().getString("level");
        BackgroundWorker backgroundWorker=new BackgroundWorker(settings_2.this);
        backgroundWorker.execute(type,id,level);

    }
}
