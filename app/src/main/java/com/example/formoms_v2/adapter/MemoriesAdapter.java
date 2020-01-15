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
import com.example.formoms_v2.adapter.pojo.Memories;

import java.util.ArrayList;

public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.ViewHolder> {
    public LayoutInflater inflater;
    private ArrayList<Memories> dataList;

    public MemoriesAdapter(Context ctx, ArrayList<Memories> dataList){
        inflater = LayoutInflater.from(ctx);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MemoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_memories, parent, false);
        MemoriesAdapter.ViewHolder holder = new MemoriesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoriesAdapter.ViewHolder holder, int position) {
        holder.ivPic1.setImageResource(dataList.get(position).getPic1());
        holder.ivPic2.setImageResource(dataList.get(position).getPic2());
        holder.ivPic3.setImageResource(dataList.get(position).getPic3());
        holder.ivPic4.setImageResource(dataList.get(position).getPic4());
        holder.tvAlbumName.setText(dataList.get(position).getAlbumName());
        holder.tvCreatedAt.setText("Dibuat pada tanggal"+dataList.get(position).getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic1;
        ImageView ivPic2;
        ImageView ivPic3;
        ImageView ivPic4;
        TextView tvAlbumName;
        TextView tvCreatedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPic1 = itemView.findViewById(R.id.ivPic1);
            ivPic2 = itemView.findViewById(R.id.ivPic2);
            ivPic3 = itemView.findViewById(R.id.ivPic3);
            ivPic4 = itemView.findViewById(R.id.ivPic4);
            tvAlbumName = itemView.findViewById(R.id.tvAlbumName);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
        }
    }
}
