package com.intellicloudpps.crimerepoart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intellicloudpps.crimerepoart.Beans.ViewAllCrimeBeans;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCrimeAdapter extends RecyclerView.Adapter<ViewAllCrimeAdapter.ViewHolder>{


    public Context context;
    public List<ViewAllCrimeBeans> viewAllCrimeBeans;

    public ViewAllCrimeAdapter(Context context, List<ViewAllCrimeBeans> viewAllCrimeBeans) {
        this.context = context;
        this.viewAllCrimeBeans = viewAllCrimeBeans;
    }

    @NonNull
    @Override
    public ViewAllCrimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.viewallcrimecardstyle,parent,false);
        ViewAllCrimeAdapter.ViewHolder viewHolder=new ViewAllCrimeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllCrimeAdapter.ViewHolder holder, int position) {
        holder.vac_styleId.setText(viewAllCrimeBeans.get(position).getId()+"");
        holder.vac_styletitle.setText(viewAllCrimeBeans.get(position).getTitle());
        holder.vac_styledescription.setText(viewAllCrimeBeans.get(position).getDescription());
        holder.vac_stylecrimedate.setText(viewAllCrimeBeans.get(position).getCrimedate());
        holder.vac_stylecomplentdate.setText(viewAllCrimeBeans.get(position).getComplentDate());
        holder.vac_stylecategory.setText(viewAllCrimeBeans.get(position).getCategory());
        holder.vac_stylearea.setText(viewAllCrimeBeans.get(position).getArea());
        holder.vac_stylepincode.setText(viewAllCrimeBeans.get(position).getPincode());
        holder.vac_stylestatus.setText(viewAllCrimeBeans.get(position).getStatus());



        String sdes=viewAllCrimeBeans.get(position).getDescription();
        if(sdes.isEmpty()){
            holder.vac_styledescription.setHint(Constant.nodata);
        }

        holder.vac_styledescription.setMaxLines(2);

        holder.vac_styledescription.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = holder.vac_styledescription.getLineCount();
                if(lineCount<2){

                    holder.vac_readmore.setVisibility(View.GONE);
                }else {
                    holder.vac_readmore.setVisibility(View.VISIBLE);
                }

            }
        });



        holder.vac_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.vac_readmore.setVisibility(View.GONE);
                holder.vac_readless.setVisibility(View.VISIBLE);
                holder.vac_styledescription.setMaxLines(Integer.MAX_VALUE);


            }
        });
        holder.vac_readless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.vac_readless.setVisibility(View.GONE);
                holder.vac_readmore.setVisibility(View.VISIBLE);
                holder.vac_styledescription.setMaxLines(2);


            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllCrimeBeans.size();
    }

    public void filtered(ArrayList<ViewAllCrimeBeans> filterlist) {
        this.viewAllCrimeBeans=filterlist;
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView vac_styletitle,vac_stylestatus,vac_styledescription,vac_stylecrimedate,
                vac_stylecomplentdate,vac_stylecategory,vac_stylearea,vac_stylepincode
                ,vac_readmore,vac_readless,vac_styleId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vac_readless=itemView.findViewById(R.id.vac_readless);
            vac_readmore=itemView.findViewById(R.id.vac_readmore);
            vac_styletitle=(itemView).findViewById(R.id.vac_styletitle);
            vac_styledescription=(itemView).findViewById(R.id.vac_styledescription);
            vac_stylestatus=itemView.findViewById(R.id.vac_stylestatus);
            vac_stylecrimedate=itemView.findViewById(R.id.vac_stylecrimedate);
            vac_stylecomplentdate=(itemView).findViewById(R.id.vac_stylecomplentdate);
            vac_stylecategory=(itemView).findViewById(R.id.vac_stylecategory);
            vac_stylearea=(itemView).findViewById(R.id.vac_stylearea);
            vac_stylepincode=(itemView).findViewById(R.id.vac_stylepincode);
            vac_styleId=(itemView).findViewById(R.id.vac_styleId);
        }
    }
}
