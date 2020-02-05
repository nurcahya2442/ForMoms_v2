package com.example.formoms_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Care;

import java.util.ArrayList;

public class CareTipsAdapter extends RecyclerView.Adapter<CareTipsAdapter.ViewHolder> {
    private ArrayList<Care> dataList;
    private Context context;

    public CareTipsAdapter(Context context, ArrayList<Care> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_care_tips, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CareTipsAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getPicTips()).into(holder.ivTips);
        holder.tvJudulTips.setText(dataList.get(position).getTitleTips());
//        holder.ivPeople.setImageResource(dataList.get(position).getPhotoPeople());
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
