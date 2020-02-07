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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
        holder.tvCreatedAt.setText(calculateDate(position));
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
}
