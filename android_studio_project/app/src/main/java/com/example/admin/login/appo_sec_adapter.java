package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;

public class appo_sec_adapter extends RecyclerView.Adapter<appo_sec_adapter.appointment_sViewHolder>{
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String delete_url = "http://10.0.2.2/delete_app_s.php";

            if(type.equals("delete")){
                try {
                    String id = params[1];
                    //String password = params[2];
                    URL url = new URL(delete_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
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
            alertDialog.setTitle("delete status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            //alertDialog.show();
            if(result.equals("delete not success")){alertDialog.show();}
            else{
                int k=result.indexOf('+');
                final String phone=result.substring(0,k);
                int j;
                j=result.indexOf('+',k+1);
                final String date =result.substring(k+1,j);
                int i;
                i=result.indexOf('+',j+1);
                final String time =result.substring(j+1,i);
                //int w;
                //w=result.indexOf('+',i+1);
                final String d_name =result.substring(i+1);
                /*Intent intent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",phone,null));
                String value="hello dear patient we regret to inform you that your appointment with doctor "+d_name+" scheduled for "+date+" at "+time+" has been deleted";
                intent.putExtra("sms_body",value);
                sctx.startActivity(intent);*/
                AlertDialog.Builder builder=new AlertDialog.Builder(sctx);
                builder.setMessage("Are you sure to delete").setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",phone,null));
                                String value="hello dear patient we regret to inform you that your appointment with doctor "+d_name+" scheduled for "+date+" at "+time+" has been deleted";
                                intent.putExtra("sms_body",value);
                                sctx.startActivity(intent);
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
                alert.show();

        }}

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private Context sctx;
    private List<appointment_s> list;

    public appo_sec_adapter(Context sctx, List<appointment_s> list) {
        this.sctx = sctx;
        this.list =list;
    }

    @NonNull
    @Override
    public appointment_sViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sctx);
        View view= inflater.inflate(R.layout.appo_sec_layout,null);
        appointment_sViewHolder holder = new appointment_sViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull appointment_sViewHolder holder, int position) {
        final appointment_s appo=list.get(position);
        holder.p_fname_tt.setText(appo.getDfName());
        holder.p_lname_tt.setText(appo.getDlname());
        holder.p_phone_tt.setText(appo.getDphone());
        holder.date.setText(appo.getDate());
        holder.time.setText(appo.getTime());
        holder.des_tt.setText(appo.getDes());
        holder.p_age_tt.setText(appo.getAge());
        holder.p_gender_tt.setText(appo.getGen());
        final String id=appo.getId();
        final String type="delete";
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BackgroundWorker backgroundWorker=new BackgroundWorker(sctx);
                //Toast.makeText(sctx,id,Toast.LENGTH_LONG).show();
               backgroundWorker.execute(type,id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class appointment_sViewHolder extends RecyclerView.ViewHolder{
        TextView p_fname_tt,p_lname_tt,p_phone_tt,date,time,p_age_tt,des_tt,p_gender_tt;
        Button delete;



        public appointment_sViewHolder(@NonNull View itemView) {
            super(itemView);
            p_fname_tt=itemView.findViewById(R.id.p_firstname_tt);
            p_lname_tt=itemView.findViewById(R.id.p_lastname_tt);
            p_phone_tt=itemView.findViewById(R.id.p_phone_tt);
            date=itemView.findViewById(R.id.p_date_tt);
            time=itemView.findViewById(R.id.p_time_tt);
            p_age_tt=itemView.findViewById(R.id.p_age_tt);
            des_tt=itemView.findViewById(R.id.p_des_tt);
            p_gender_tt=itemView.findViewById(R.id.p_gender_tt);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
