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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.example.formoms_v2.adapter.pojo.Care;

import java.util.List;

public class AlbumPhotoAdapter extends RecyclerView.Adapter<AlbumPhotoAdapter.ViewHolder> {
    private List<AlbumPhoto> dataList;
    private Context context;

    public AlbumPhotoAdapter(Context context, List<AlbumPhoto> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_album_photo, parent, false);
        AlbumPhotoAdapter.ViewHolder holder = new AlbumPhotoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getPhotoUrl()).into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.ivRecentView);
        }
    }
}
