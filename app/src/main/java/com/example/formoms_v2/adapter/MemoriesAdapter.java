package com.example.formoms_v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Album;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.ViewHolder> {

    private ArrayList<Album> dataList;
    private ArrayList<AlbumPhoto> dataPhoto;

    Context context;

    public MemoriesAdapter(ArrayList<Album> dataList, ArrayList<AlbumPhoto> dataPhoto, Context context){
        this.dataList = dataList;
        this.dataPhoto = dataPhoto;
        this.context = context;
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
        holder.tvAlbumName.setText(dataList.get(position).getAlbumName());
        holder.tvCreatedAt.setText(calculateDate(position));
        if(dataPhoto.size() >= 1) {
            Glide.with(context).load(dataPhoto.get(position).getPhotoUrl()).into(holder.ivPic1);
        }
        if(dataPhoto.size() >= 2) {
            Glide.with(context).load(dataPhoto.get(position+1).getPhotoUrl()).into(holder.ivPic2);
        }
        if(dataPhoto.size() >= 3) {
            Glide.with(context).load(dataPhoto.get(position+2).getPhotoUrl()).into(holder.ivPic3);
        }
        if(dataPhoto.size() >= 4) {
            Glide.with(context).load(dataPhoto.get(position+3).getPhotoUrl()).into(holder.ivPic4);
        }
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

    private String calculateDate(int position) {
        // Get Created At
        String sampleString = dataList.get(position).getCreatedAt();

        if(sampleString != null){
            String[] stringArray = sampleString.split("-");
            int[] intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                String numberAsString = stringArray[i];
                intArray[i] = Integer.parseInt(numberAsString);
            }

            // Get date now
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String createdAt = sdf.format(new Date());
            String[] stringArrayNow = createdAt.split("-");
            int[] intArrayNow = new int[stringArrayNow.length];
            for (int i = 0; i < stringArrayNow.length; i++) {
                String numberAsString = stringArrayNow[i];
                intArrayNow[i] = Integer.parseInt(numberAsString);
            }

            // Calculate
            int year = intArrayNow[0] - intArray[0];
            int month = intArrayNow[1] - intArray[1];
            int day = intArrayNow[2] - intArray[2];

            String tes = year +" - "+month+" - "+day;


            // Logic
            String createdDate = "";
            if(year <= 0){
                if(month <= 0){
                    if(day <= 0){
                        createdDate = "Hari ini";
                    }else if(day > 0){
                        createdDate = "Dibuat "+day+" hari yang lalu";
                    }
                }else if(month > 0){
                    createdDate = "Dibuat "+month+" bulan yang lalu";
                }
            }else if(year > 0){
                createdDate = "Dibuat "+year+" tahun yang lalu";
            }

            return createdDate;
        }
        return null;
    }
}
