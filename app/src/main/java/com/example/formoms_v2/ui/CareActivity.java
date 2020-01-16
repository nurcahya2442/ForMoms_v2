package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.RecyclerItemClickListener;

import java.util.ArrayList;

public class CareActivity extends AppCompatActivity {
    private ArrayList<Care> dataListTips;
    private RecyclerView recyclerViewCareTips;
    private CareAdapter adapterCare;

    //Data Recycler View Dummy Care Tips
    private int[] foodPic = new int[]{
            R.drawable.food,
            R.drawable.food
    };

    private String[] judulTips = new String[]{"Baby Food Recommendation", "How To Take Care A Baby"};

    private int[] peoplePhoto = new int[]{
            R.drawable.people,
            R.drawable.people
    };

    private String[] name = new String[]{"Angelica Witson", "Pretty Jonson"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        //Manggil Recycler View Care

        recyclerViewCareTips = (RecyclerView) findViewById(R.id.rvCare);
        dataListTips = dataTips();
        adapterCare = new CareAdapter(this,dataListTips);
        recyclerViewCareTips.setAdapter(adapterCare);
        recyclerViewCareTips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        eventListener();
    }

    private ArrayList<Care> dataTips(){

        ArrayList<Care> listTips = new ArrayList<>();

        for(int i = 0; i<2; i++){
            Care care = new Care();
            care.setPicTips(foodPic[i]);
            care.setTitleTips(judulTips[i]);
            care.setPhotoPeople(peoplePhoto[i]);
            care.setNamePeople(name[i]);
            listTips.add(care);
        }
        return listTips;
    }

    private void eventListener() {
        recyclerViewCareTips.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(CareActivity.this, DetailCareActivity.class));
            }
        }));
    }
}
