package com.example.admin.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class scheduler_doctor_adapter extends RecyclerView.Adapter<scheduler_doctor_adapter.ScheduleruserViewHolder> implements PopupMenu.OnMenuItemClickListener  {
    private Context sctx;
    public class BackgroundWorker extends AsyncTask<String, Void , String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundWorker (Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String action_url = "http://10.0.2.2/doctoraction.php";
            //String register_url = "http://10.2.2/register_user.php";
            if(type.equals("action")){
                try {
                    String sch_id = params[1];
                    String number = params[2];
                    String action=params[3];
                    URL url = new URL(action_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("sch_id", "UTF-8")+"="+URLEncoder.encode(sch_id, "UTF-8")+"&"
                            +URLEncoder.encode("number", "UTF-8")+"="+URLEncoder.encode(number, "UTF-8")+"&"
                            +URLEncoder.encode("action", "UTF-8")+"="+URLEncoder.encode(action, "UTF-8");
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
             if(result.equals("try again")){
                alertDialog.show();
            }
            else{
                 int k=result.indexOf('+');
                 String test=result.substring(0,k);

                     if(test.equals("w")) {

                         int j;
                         j=result.indexOf('+',k+1);
                         String username =result.substring(k+1,j);
                         //int i=result.indexOf('+',j+1);
                         String date=result.substring(j+1);
                         Intent intent=new Intent(sctx,scheduler_doctor.class);
                intent.putExtra("username",username);
                intent.putExtra("date",date);
                sctx.startActivity(intent);}
                 else if(test.equals("nw")) {

                        // int j;
                         //j = result.indexOf('+', k + 1);

                         String data[]={"0","0","0","0","0","0"};
                         int init=k+1;

                         for(int i=0;i<5;i++){
                             int p=result.indexOf('+',init);
                             data[i]=result.substring(init,p);
                             init=p+1;
                         }
                         data[5]=result.substring(init);
                         String p_fname=data[0];
                         String p_lname=data[1];
                         String p_phone=data[2];
                         String d_name=data[3];
                         String username = data[4];
                         String date=data[5];
                         Intent intent=new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",p_phone,null));
                         String value="hello dear patient we regret to inform you that your appointment with doctor "+d_name+" scheduled for "+date+" has been deleted";
                         intent.putExtra("sms_body",value);
                         sctx.startActivity(intent);
                     /*Intent intent=new Intent(sctx,scheduler_doctor.class);
                     intent.putExtra("username",username);
                     intent.putExtra("date",date);*/
                         //sctx.startActivity(intent);}
                     }

            else {
                String data[]={"0","0","0","0","0"};
                int init=k+1;

                for(int i=0;i<4;i++){
                             int p=result.indexOf('+',init);
                             data[i]=result.substring(init,p);
                             init=p+1;
                }
                data[4]=result.substring(init);
                         //alertDialog.show();
                         Intent intent=new Intent(sctx,appo_details.class);
                         intent.putExtra("firstname",data[0]);
                         intent.putExtra("lastname",data[1]);
                         intent.putExtra("phone",data[2]);
                         intent.putExtra("age",data[3]);
                         intent.putExtra("matter",data[4]);
                         sctx.startActivity(intent);


            }
            /*else {Intent intent=new Intent(sctx,scheduler_doctor.class);
            intent.putExtra("3addii","");
            sctx.startActivity(intent);}}*/
        }}

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    //private Context sctx;
    private List<Scheduleruser> list;

    public scheduler_doctor_adapter(Context sctx, List<Scheduleruser> list) {
        this.sctx = sctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleruserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sctx);
        View view= inflater.inflate(R.layout.scheduler_layout,null);
        ScheduleruserViewHolder holder = new ScheduleruserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final scheduler_doctor_adapter.ScheduleruserViewHolder holder, int position) {
        final Scheduleruser scheduleruser=list.get(position);
        final int id_su=scheduleruser.getId();

        // holder.
        // holder.appo1,app2,app3,app4,app5,app6,app7,app8,app9,app10,app11,app12,app13,app14,app15,app16;
        final TextView t[]={holder.appo1,holder.appo2,holder.appo3,holder.appo4,holder.appo5,holder.appo6,holder.appo7,holder.appo8,holder.appo9,holder.appo10,holder.appo11,holder.appo12,holder.appo13,holder.appo14,holder.appo15,holder.appo16};

        final int id[]={R.id.appo1,R.id.appo2,R.id.appo3,R.id.appo4,R.id.appo5,R.id.appo6,R.id.appo7,R.id.appo8,R.id.appo9,R.id.appo10,R.id.appo11,R.id.appo12,R.id.appo13,R.id.appo14,R.id.appo15,R.id.appo16};
        int a=id.length;

        for(int i=0;i<a;i++){
            holder.t[i].setText(scheduleruser.getApp(i+1));
        }
        for( int j=0;j<a;j++){
            final TextView textView=holder.t[j];
            final String number=Integer.toString(j+1);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt= (String) textView.getText();
                    if(txt.isEmpty()){
                        String verif="details";
                        String sid=Integer.toString(id_su);
                        showPopup(v,verif,sid,number);
                    }


                    else if (txt.equals("non working time")) {
                        String verif="non working";
                        String sid=Integer.toString(id_su);
                        showPopup(v,verif,sid,number);
                    }
                    else {
                        String verif="working";
                        String sid=Integer.toString(id_su);
                        showPopup(v,verif,sid,number);
                }
            }
        });






    }}
    public boolean onPrepareOptionsMenu(Menu menu,String verif)
    {
        if(verif.equals("details"))
        {MenuItem registrar = menu.findItem(R.id.item1);
        registrar.setVisible(false);
            MenuItem registrar1 = menu.findItem(R.id.item3);
            registrar1.setVisible(false);}
         if(verif.equals("non working"))
        {MenuItem registrar = menu.findItem(R.id.item2);
            registrar.setVisible(false);
            MenuItem registrar1 = menu.findItem(R.id.item1);
            registrar1.setVisible(false);}
         if(verif.equals("working"))
        {MenuItem registrar = menu.findItem(R.id.item3);
            registrar.setVisible(false);}
        return true;
    }
    public void showPopup(View v, final String verif, final String sid, final String number){
        PopupMenu popup = new PopupMenu(sctx,v);

        //popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_d);

        onPrepareOptionsMenu(popup.getMenu(),verif);
        popup.show();
        popup.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String type="action";
                String action="appo details";
                BackgroundWorker backgroundWorker=new BackgroundWorker(sctx);
                backgroundWorker.execute(type,sid,number,action);
                //Toast.makeText(sctx,sid,Toast.LENGTH_LONG).show();
                return true;
            }
        });
        popup.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final String type="action";
                final String action="non working";
                AlertDialog.Builder builder=new AlertDialog.Builder(sctx);
                builder.setMessage("Are you sure to set this time as non working").setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BackgroundWorker backgroundWorker=new BackgroundWorker(sctx);
                                backgroundWorker.execute(type,sid,number,action) ;
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


                //BackgroundWorker backgroundWorker=new BackgroundWorker(sctx);
                //backgroundWorker.execute(type,sid,number,action);
                return true;
            }
        });
        popup.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String type="action";
                String action="working";
                //Toast.makeText(sctx,number,Toast.LENGTH_LONG).show();
                BackgroundWorker backgroundWorker=new BackgroundWorker(sctx);
                backgroundWorker.execute(type,sid,number,action);
                return true;

            }
        });
    }

    @Override
   public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ScheduleruserViewHolder extends RecyclerView.ViewHolder {
        TextView appo1,appo2,appo3,appo4,appo5,appo6,appo7,appo8,appo9,appo10,appo11,appo12,appo13,appo14,appo15,appo16;

        TextView t[]={appo1,appo2,appo3,appo4,appo5,appo6,appo7,appo8,appo9,appo10,appo11,appo12,appo13,appo14,appo15,appo16};
        int a=t.length;
        int id[]={R.id.appo1,R.id.appo2,R.id.appo3,R.id.appo4,R.id.appo5,R.id.appo6,R.id.appo7,R.id.appo8,R.id.appo9,R.id.appo10,R.id.appo11,R.id.appo12,R.id.appo13,R.id.appo14,R.id.appo15,R.id.appo16};
        public ScheduleruserViewHolder(@NonNull  View itemView) {
            super(itemView);
            for(int i=0;i<a;i++){
                t[i]=itemView.findViewById(id[i]);
            }



        }
    }
}
