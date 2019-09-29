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

public class doctorsadapter extends RecyclerView.Adapter<doctorsadapter.DoctorViewHolder>{
    private Context sctx;
    private List<Doctor> doctorslist;

    public doctorsadapter(Context sctx, List<Doctor> doctorslist) {
        this.sctx = sctx;
        this.doctorslist = doctorslist;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sctx);
        View view= inflater.inflate(R.layout.doctors_list_layout,null);
        DoctorViewHolder holder = new DoctorViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        final Doctor doctor=doctorslist.get(position);
        holder.tvname.setText(doctor.getName());
        holder.tvphnum.setText(doctor.getPhoneNumber());
        holder.tvaddress_d.setText(doctor.getAddress());
        holder.get.setOnClickListener(new View.OnClickListener() {
        int id=doctor.getId();
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(sctx,calendar.class);
                intent.putExtra("id",id);
                sctx.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return doctorslist.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder{
        TextView tvname,tvphnum,tvaddress_d;
        Button get;


        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvphnum=itemView.findViewById(R.id.tvphnum);
            tvaddress_d=itemView.findViewById(R.id.tvaddress_d);
            get=itemView.findViewById(R.id.get);

        }
    }
}

