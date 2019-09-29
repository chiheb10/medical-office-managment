package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;

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

public class loginActivity extends AppCompatActivity {
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];

            String login_url = "http://10.0.2.2/login_g.php";
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
            //alertDialog.show();
            if(result.equals("login not success")){Toast.makeText(loginActivity.this,"wrong username or password",Toast.LENGTH_LONG).show();}
            else {int k=result.indexOf('+');
            String level=result.substring(0,k);
            if(level.equals("user")){
            int j;
            j=result.indexOf('+',k+1);
            String name =result.substring(k+1,j);

            String id=result.substring(j+1);
            Intent intent = new Intent(loginActivity.this,user.class);
            intent.putExtra("name",name);
            intent.putExtra("id",id);
            startActivity(intent);}
         else if(level.equals("doctor")){
                int j;
                j=result.indexOf('+',k+1);

                String name =result.substring(k+1,j);
                String username=result.substring(j+1);
                Intent intent=new Intent(loginActivity.this,doctorAreaActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("username",username);
                startActivity(intent);

            }else if(level.equals("secretary")){

                String id=result.substring(k+1);
                Intent intent=new Intent(loginActivity.this,secretary_area.class);
                intent.putExtra("id_sec",id);
                startActivity(intent);
            }
            }}




        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    public class BackgroundWorker_d extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker_d (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://10.0.2.2/login_d.php";
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
                    //String username=user_name;
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
            alertDialog.show();
            //Intent intent = new Intent(loginActivity.this,doctorAreaActivity.class);
            //intent.putExtra("name",result);
            //prg.setProgress(50);
            //android.os.SystemClock.sleep(1000);
            //prg.setProgress(100);

            //if(result.equals("login not success")){

                //Toast.makeText(loginActivity.this,"wrong username or password",Toast.LENGTH_LONG).show();}

            /*else{


                int j;
                j=result.indexOf('+');

               String name =result.substring(0,j);
               String username=result.substring(j+1);
               intent.putExtra("name",name);
                intent.putExtra("username",username);




                startActivity(intent);}*/

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
private static final String TAG = "loginActivity";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    /*final  EditText etUsername = (EditText) findViewById(R.id.etUsername);
    final  EditText etpassword = (EditText) findViewById(R.id.etpassword);
    final Button bLogin = (Button) findViewById(R.id.bLogin);*/

    public EditText etUsername;
    private Button bLogin;


    int counter = 5;

    private EditText etPassword;
    //private ProgressBar prg;
    private int mProgressStatus = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etpassword);
        bLogin = (Button)findViewById(R.id.bLogin);
        final  Button tvRegisterHere = (Button) findViewById(R.id.tvRegisterHere);

        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //prg=(ProgressBar)findViewById(R.id.progressBar);

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(loginActivity.this, RegisterAvtivity.class);
                startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String type = "login";
                final BackgroundWorker_d backgroundWorker_d = new BackgroundWorker_d(loginActivity.this);
                final BackgroundWorker backgroundWorker = new BackgroundWorker(loginActivity.this);
               backgroundWorker.execute(type,username,password);
            }
        });


    }






    private  void SendTomain() {
        Intent mainIntent = new Intent(loginActivity.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }










    // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.tvRegisterHere);
        //tv.setText(stringFromJNI());


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

}
