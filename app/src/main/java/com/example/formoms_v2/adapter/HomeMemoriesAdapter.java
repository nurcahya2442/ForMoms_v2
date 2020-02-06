package com.example.formoms_v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Album;

import java.util.ArrayList;

public class HomeMemoriesAdapter extends RecyclerView.Adapter<HomeMemoriesAdapter.ViewHolder> {
    private ArrayList<Album> dataList;

    public HomeMemoriesAdapter(ArrayList<Album> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HomeMemoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home_memories, parent, false);
        HomeMemoriesAdapter.ViewHolder holder = new HomeMemoriesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMemoriesAdapter.ViewHolder holder, int position) {
        holder.tvAlbumName.setText(dataList.get(position).getAlbumName());
        holder.tvCreatedAt.setText("Dibuat pada "+dataList.get(position).getCreatedAt());
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
