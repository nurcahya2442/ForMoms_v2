package com.example.formoms_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Care;

import java.util.ArrayList;

public class CareAdapter extends RecyclerView.Adapter<CareAdapter.ViewHolder> {
    public LayoutInflater inflater;
    private ArrayList<Care> dataList;

    public CareAdapter(Context ctx, ArrayList<Care> dataList){
        inflater = LayoutInflater.from(ctx);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_care, parent, false);
        CareAdapter.ViewHolder holder = new CareAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivTips.setImageResource(dataList.get(position).getPicTips());
        holder.tvJudulTips.setText(dataList.get(position).getTitleTips());
        holder.ivPeople.setImageResource(dataList.get(position).getPhotoPeople());
        holder.tvName.setText(dataList.get(position).getNamePeople());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTips;
        TextView tvJudulTips;
        ImageView ivPeople;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTips = itemView.findViewById(R.id.ivListFood);
            tvJudulTips = itemView.findViewById(R.id.tvJudulListCare);
            ivPeople = itemView.findViewById(R.id.ivPeopleImage);
            tvName = itemView.findViewById(R.id.tvPopleName);
        }
    }
}
