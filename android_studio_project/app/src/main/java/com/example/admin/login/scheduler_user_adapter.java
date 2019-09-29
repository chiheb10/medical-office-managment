package com.example.admin.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class scheduler_user_adapter extends RecyclerView.Adapter<scheduler_user_adapter.ScheduleruserViewHolder>{
    private Context sctx;
    private List<Scheduleruser> list;

    public scheduler_user_adapter(Context sctx, List<Scheduleruser> list) {
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
    public void onBindViewHolder(@NonNull final scheduler_user_adapter.ScheduleruserViewHolder holder, int position) {
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
                     //int id=scheduleruser.getId();
                      String did=Integer.toString(id_su);



                      Intent intent=new Intent(sctx,formulaire_user.class);
                      intent.putExtra("id_su",did);
                      intent.putExtra("number",number);
                      sctx.startActivity(intent);


                     }
              }
          });
      }






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
            /*app1=itemView.findViewById(R.id.appo1);
            app2=itemView.findViewById(R.id.appo2);
            app3=itemView.findViewById(R.id.appo3);
            app4=itemView.findViewById(R.id.appo4);
            app5=itemView.findViewById(R.id.appo5);
            app6=itemView.findViewById(R.id.appo6);
            app7=itemView.findViewById(R.id.appo7);
            app8=itemView.findViewById(R.id.appo8);
            app2=itemView.findViewById(R.id.appo2);*/


        }
    }
}
