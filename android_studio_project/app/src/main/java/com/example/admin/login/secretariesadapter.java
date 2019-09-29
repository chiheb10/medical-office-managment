package com.example.admin.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.login.model.Secretaries;

import java.util.List;

public class secretariesadapter extends RecyclerView.Adapter<secretariesadapter.SecretariesViewHolder>{
    private Context sctx;
    private List<Secretaries> secretarieslist;

    public secretariesadapter(Context sctx, List<Secretaries> secretarieslist) {
        this.sctx = sctx;
        this.secretarieslist = secretarieslist;
    }

    @NonNull
    @Override
    public SecretariesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sctx);
        View view= inflater.inflate(R.layout.secretaries_list_layout,null);
        SecretariesViewHolder holder = new SecretariesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecretariesViewHolder holder, int position) {
        Secretaries secretary=secretarieslist.get(position);
        holder.tvfirstname.setText(secretary.getFirstName());
        holder.tvlastname.setText(secretary.getLastName());
        holder.tvusername.setText(secretary.getUsername());
        holder.tvpassword.setText(secretary.getPassword());
        holder.tvaddress.setText(secretary.getAddress());
        holder.tvphone.setText(secretary.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return secretarieslist.size();
    }

    class SecretariesViewHolder extends RecyclerView.ViewHolder{
        TextView tvfirstname,tvlastname,tvusername,tvpassword,tvaddress,tvphone;
        Button bdelete;


        public SecretariesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvfirstname=itemView.findViewById(R.id.tvfirstname);
            tvlastname=itemView.findViewById(R.id.tvlastname);
            tvusername=itemView.findViewById(R.id.tvusername);
            tvpassword=itemView.findViewById(R.id.tvpassword);
            tvaddress=itemView.findViewById(R.id.tvaddress);
            tvphone=itemView.findViewById(R.id.tvphone);
            bdelete=itemView.findViewById(R.id.delete);
        }
    }
}
