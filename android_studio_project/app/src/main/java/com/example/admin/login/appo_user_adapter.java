package com.example.admin.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.login.model.Doctor;
import com.example.admin.login.model.Secretaries;

import java.util.List;

public class appo_user_adapter extends RecyclerView.Adapter<appo_user_adapter.appointment_uViewHolder>{
    private Context sctx;
    private List<appointment_u> list;

    public appo_user_adapter(Context sctx, List<appointment_u> list) {
        this.sctx = sctx;
        this.list =list;
    }

    @NonNull
    @Override
    public appointment_uViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sctx);
        View view= inflater.inflate(R.layout.appo_user_layout,null);
        appointment_uViewHolder holder = new appointment_uViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull appointment_uViewHolder holder, int position) {
        final appointment_u appo=list.get(position);
        holder.d_name_t.setText(appo.getdName());
       holder.d_phone_t.setText(appo.getdPhoneNumber());
        holder.daddress_t.setText(appo.getDaddress());
        holder.date.setText(appo.getDate());
        holder.time.setText(appo.getTime());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class appointment_uViewHolder extends RecyclerView.ViewHolder{
        TextView d_name_t,d_phone_t,daddress_t,date,time;



        public appointment_uViewHolder(@NonNull View itemView) {
            super(itemView);
            d_name_t=itemView.findViewById(R.id.d_name_t);
            d_phone_t=itemView.findViewById(R.id.d_phone_t);
            daddress_t=itemView.findViewById(R.id.d_address_t);
            date=itemView.findViewById(R.id.date_t);
            time=itemView.findViewById(R.id.time_t);

        }
    }
}