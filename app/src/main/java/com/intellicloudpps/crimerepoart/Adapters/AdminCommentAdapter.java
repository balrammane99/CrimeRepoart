package com.intellicloudpps.crimerepoart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.intellicloudpps.crimerepoart.Beans.AdminCommentBeans;
import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.R;

import java.util.ArrayList;
import java.util.List;

public class AdminCommentAdapter extends RecyclerView.Adapter<AdminCommentAdapter.ViewHolder> {
    public Context context;
    public List<AdminCommentBeans> adminCommentBeans;

    public AdminCommentAdapter(Context context, List<AdminCommentBeans> adminCommentBeans) {
        this.context = context;
        this.adminCommentBeans = adminCommentBeans;
    }

    @NonNull
    @Override
    public AdminCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.comment_card,parent,false);
        AdminCommentAdapter.ViewHolder viewHolder=new AdminCommentAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCommentAdapter.ViewHolder holder, int position) {

        holder.cmt_card_description.setText(adminCommentBeans.get(position).getComment());
        holder.cmt_card_date.setText(adminCommentBeans.get(position).getDate());

        String sdes1=adminCommentBeans.get(position).getComment();
        if(sdes1.isEmpty()){
            holder.cmt_card_description.setHint(Constant.nodata);
        }
        holder.cmt_card_description.setMaxLines(2);
        holder.cmt_card_description.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = holder.cmt_card_description.getLineCount();
                if(lineCount<2){

                    holder.Admin_cmt_readmore.setVisibility(View.GONE);
                }else {
                    holder.Admin_cmt_readmore.setVisibility(View.VISIBLE);
                }

            }
        });
        holder.Admin_cmt_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.Admin_cmt_readmore.setVisibility(View.GONE);
                holder.Admin_cmt_readless.setVisibility(View.VISIBLE);
                holder.cmt_card_description.setMaxLines(Integer.MAX_VALUE);


            }
        });
        holder.Admin_cmt_readless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.Admin_cmt_readless.setVisibility(View.GONE);
                holder.Admin_cmt_readmore.setVisibility(View.VISIBLE);
                holder.cmt_card_description.setMaxLines(2);


            }
        });

    }

    @Override
    public int getItemCount() {
        return adminCommentBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cmt_card_date,cmt_card_description,Admin_cmt_readless,Admin_cmt_readmore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cmt_card_date=itemView.findViewById(R.id.cmt_card_date);
            cmt_card_description=itemView.findViewById(R.id.cmt_card_description);
            Admin_cmt_readless=itemView.findViewById(R.id.Admin_cmt_readless);
            Admin_cmt_readmore=itemView.findViewById(R.id.Admin_cmt_readmore);
        }
    }
}
