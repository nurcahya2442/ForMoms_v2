package com.example.formoms_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.RecentMemories;

import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    public LayoutInflater inflater;
    private ArrayList<RecentMemories> imageArrayListRecent;

    public RecentAdapter(Context ctx, ArrayList<RecentMemories> imageArrayListRecent){
         inflater = LayoutInflater.from(ctx);
         this.imageArrayListRecent = imageArrayListRecent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recent_memories, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAdapter.ViewHolder holder, int position) {
        holder.fotoBayi.setImageResource(imageArrayListRecent.get(position).getBabyPhoto());

    }

    @Override
    public int getItemCount() {
        return imageArrayListRecent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoBayi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoBayi = itemView.findViewById(R.id.ivRecentView);
        }
    }
}
