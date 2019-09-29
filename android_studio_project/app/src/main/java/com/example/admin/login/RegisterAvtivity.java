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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterAvtivity extends AppCompatActivity {
    private RadioGroup radio;
    private RadioButton radioButton;
    private String check;
    public class BackgroundWorker_r extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker_r (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://10.0.2.2/login.php";
            String register_url = "http://10.0.2.2/register_user.php";
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
            else if(type.equals("register_user")){
                try {
                    String name = params[1];
                    String username = params[2];
                    String pass = params[3];
                    String check = params[4];
                    String address = params[5];
                    String email = params[6];
                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data1 = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                            +URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8")+"&"
                            +URLEncoder.encode("gender", "UTF-8")+"="+URLEncoder.encode(check, "UTF-8")+"&"
                            +URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"
                            +URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&";
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
            alertDialog.setTitle("register status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            if(result.equals("registration not success")||result.equals("username or password already exists")){alertDialog.show();}
            else if(result.equals("registration success")) {
                Toast.makeText(RegisterAvtivity.this,"registration success",Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterAvtivity.this,loginActivity.class));
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private  EditText etName ;

    private  EditText etUsername ;
    private  EditText etpassword ;
    private  EditText etaddress ;
   private EditText etemail ;
    private EditText etrepass ;

    private Button bRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_avtivity);
        etName = (EditText) findViewById(R.id.etName);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etaddress = (EditText) findViewById(R.id.etaddress);
        bRegister = (Button) findViewById(R.id.bRegister);
        etemail=(EditText)findViewById(R.id.etemail);
        etrepass=(EditText)findViewById(R.id.etrepass);
        radio = (RadioGroup) findViewById(R.id.grp);
        check="";
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        check="male";
                        break;
                    case R.id.female:
                        check="female";
                        break;

                }
            }
        });







        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=etName.getText().toString();
                final String username=etUsername.getText().toString();
                final String pass=etpassword.getText().toString();
                final String address=etaddress.getText().toString();
                final String email=etemail.getText().toString();

                final String repass=etrepass.getText().toString();
                final String type = "register_user";
                if(name.isEmpty()||username.isEmpty()||pass.isEmpty()||address.isEmpty()||check.isEmpty()||email.isEmpty()||repass.isEmpty()){
                    Toast.makeText(RegisterAvtivity.this,"empty fields",Toast.LENGTH_LONG).show();
                }else if(!pass.equals(repass)){
                    Toast.makeText(RegisterAvtivity.this,"please enter valid password",Toast.LENGTH_LONG).show();
                }

                else {final BackgroundWorker_r backgroundWorker_r = new BackgroundWorker_r(RegisterAvtivity.this);
                backgroundWorker_r.execute(type, name, username,pass,check,address,email);}
            }
        });

    }
}
