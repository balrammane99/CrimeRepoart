package com.intellicloudpps.crimerepoart.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intellicloudpps.crimerepoart.Activitys.Admin_CRV_Activity;
import com.intellicloudpps.crimerepoart.Beans.AdminCrimeBeans;
import com.intellicloudpps.crimerepoart.Fragments.AdminViewReportFragment;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.R;

import java.util.ArrayList;
import java.util.List;

public class Adm_VAC_Adapter extends RecyclerView.Adapter<Adm_VAC_Adapter.ViewHolder>{
    public Context context;
    public List<AdminCrimeBeans> adminCrimeBeans;

    public Adm_VAC_Adapter(Context context, List<AdminCrimeBeans> adminCrimeBeans) {
        this.context = context;
        this.adminCrimeBeans = adminCrimeBeans;
    }


    @NonNull
    @Override
    public Adm_VAC_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.admin_crime_cardstyle,parent,false);
        Adm_VAC_Adapter.ViewHolder viewHolder=new Adm_VAC_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adm_VAC_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.adm_vac_styleId.setText(adminCrimeBeans.get(position).getId()+"");
        holder.adm_vac_styletitle.setText(adminCrimeBeans.get(position).getTitle());
        holder.adm_vac_styledescription.setText(adminCrimeBeans.get(position).getDescription());
        holder.adm_vac_stylecrimedate.setText(adminCrimeBeans.get(position).getCrimedate());
        holder.adm_vac_stylecomplentdate.setText(adminCrimeBeans.get(position).getComplentDate());
        holder.adm_vac_stylecategory.setText(adminCrimeBeans.get(position).getCategory());
        holder.adm_vac_stylearea.setText(adminCrimeBeans.get(position).getArea());
        holder.adm_vac_stylepincode.setText(adminCrimeBeans.get(position).getPincode());
        holder.adm_vac_stylestatus.setText(adminCrimeBeans.get(position).getStatus());
        holder.complentPN.setText(adminCrimeBeans.get(position).getComplentpn());
        holder.complentPE.setText(adminCrimeBeans.get(position).getComplentpe());
        holder.complentPM.setText(adminCrimeBeans.get(position).getComplentpm());



        String sdes=adminCrimeBeans.get(position).getDescription();
        if(sdes.isEmpty()){
            holder.adm_vac_styledescription.setHint(Constant.nodata);
        }

        holder.adm_vac_styledescription.setMaxLines(2);

        holder.adm_vac_styledescription.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = holder.adm_vac_styledescription.getLineCount();
                if(lineCount<2){

                    holder.adm_vac_readmore.setVisibility(View.GONE);
                }else {
                    holder.adm_vac_readmore.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.adm_fvr_textbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String id= adminCrimeBeans.get(position).getId()+"";
                String status=adminCrimeBeans.get(position).getStatus();
                Intent i=new Intent(context, Admin_CRV_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SharedSession.insertData(context,Constant.CRIMEID,id);
                SharedSession.insertData(context,Constant.STATUS,status);
                context.startActivity(i);
            }
        });


        holder.adm_vac_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.adm_vac_readmore.setVisibility(View.GONE);
                holder.adm_vac_readless.setVisibility(View.VISIBLE);
                holder.adm_vac_styledescription.setMaxLines(Integer.MAX_VALUE);


            }
        });
        holder.adm_vac_readless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.adm_vac_readless.setVisibility(View.GONE);
                holder.adm_vac_readmore.setVisibility(View.VISIBLE);
                holder.adm_vac_styledescription.setMaxLines(2);

            }
        });
    }

    @Override
    public int getItemCount() {
        return adminCrimeBeans.size();
    }

    public void filtered(ArrayList<AdminCrimeBeans> filterlist) {
        this.adminCrimeBeans=filterlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adm_vac_styletitle,adm_vac_stylestatus,adm_vac_styledescription,adm_vac_stylecrimedate,
                adm_vac_stylecomplentdate,adm_vac_stylecategory,adm_vac_stylearea,adm_vac_stylepincode
                ,adm_vac_readmore,adm_vac_readless,adm_vac_styleId,adm_fvr_textbtn,complentPN,complentPE,complentPM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            adm_vac_readless=itemView.findViewById(R.id.adm_vac_readless);
            adm_vac_readmore=itemView.findViewById(R.id.adm_vac_readmore);
            adm_vac_styletitle=(itemView).findViewById(R.id.adm_vac_styletitle);
            adm_vac_styledescription=(itemView).findViewById(R.id.adm_vac_styledescription);
            adm_vac_stylestatus=itemView.findViewById(R.id.adm_vac_stylestatus);
            adm_vac_stylecrimedate=itemView.findViewById(R.id.adm_vac_stylecrimedate);
            adm_vac_stylecomplentdate=(itemView).findViewById(R.id.adm_vac_stylecomplentdate);
            adm_vac_stylecategory=(itemView).findViewById(R.id.adm_vac_stylecategory);
            adm_vac_stylearea=(itemView).findViewById(R.id.adm_vac_stylearea);
            adm_vac_stylepincode=(itemView).findViewById(R.id.adm_vac_stylepincode);
            adm_vac_styleId=(itemView).findViewById(R.id.adm_vac_styleId);
            adm_fvr_textbtn=(itemView).findViewById(R.id.adm_fvr_textbtn);

            complentPN=(itemView).findViewById(R.id.complentPN);
            complentPE=(itemView).findViewById(R.id.complentPE);
            complentPM=(itemView).findViewById(R.id.complentPM);

        }
    }
}
