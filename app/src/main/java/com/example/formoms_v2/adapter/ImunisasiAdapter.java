package com.example.formoms_v2.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Imunisasi;

import java.util.ArrayList;

public class ImunisasiAdapter extends RecyclerView.Adapter<ImunisasiAdapter.ViewHolder> {



    private ArrayList<Imunisasi> dataListImun;

//    public String[] mColors = {
//            "#fff",
//            "#0ABDE3"
//    };

    public ImunisasiAdapter(ArrayList<Imunisasi> dataListImun){
        this.dataListImun = dataListImun;

    }

    @Override
    public ImunisasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_imunisasi, parent, false);
        ImunisasiAdapter.ViewHolder holder = new ImunisasiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImunisasiAdapter.ViewHolder holder, int position) {
        holder.tvNamaImun.setText(dataListImun.get(position).getNamaImun());
        holder.tvUmurBayi.setText(dataListImun.get(position).getUmurImun());
//        holder.itemView.setBackgroundColor(Color.parseColor(mColors[position % 2]));

    }

    @Override
    public int getItemCount() {
        return dataListImun.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaImun;
        TextView tvUmurBayi;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaImun = itemView.findViewById(R.id.tvNamaImun);
            tvUmurBayi = itemView.findViewById(R.id.tvUmurBayi);
        }
    }
}
