package com.example.formoms_v2.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareTipsAdapter;
import com.example.formoms_v2.adapter.RecentAdapter;
import com.example.formoms_v2.adapter.pojo.CareTips;
import com.example.formoms_v2.adapter.pojo.RecentMemories;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<RecentMemories> listPhoto;
    private RecyclerView recyclerView;
    private RecentAdapter adapter;

    private ArrayList<CareTips> dataListTips;
    private RecyclerView recyclerViewCareTips;
    private CareTipsAdapter adapterCareTips;

    private TextView tvLihatDetailCare;
    private TextView tvLihatDetailMemories;
    private ImageView ivMenuBars;


    //Data Recycler View Dummy Recent View


    private int[] fotoBayi = new int[]{
            R.drawable.bayi_1,
            R.drawable.bayi_2,
            R.drawable.bayi_3,
            R.drawable.bayi_4,
            R.drawable.bayi_1,
            R.drawable.bayi_2,
            R.drawable.bayi_3,
            R.drawable.bayi_4,
            R.drawable.bayi_1,
            R.drawable.bayi_2


    };

    //Data Recycler View Dummy Care Tips
    private int[] foodPic = new int[]{

            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food
    };

    private String[] judulTips = new String[] {"Baby Food Recommendation","How To Take Care A Baby","Baby Food Recommendation", "How To Take Care A Baby","Baby Food Recommendation", "How To Take Care A Baby","Baby Food Recommendation", "How To Take Care A Baby","Baby Food Recommendation", "How To Take Care A Baby"};

    private int[] peoplePhoto = new int[]{
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people,
            R.drawable.people
    };

    private String[] name = new String[] {"Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvLihatDetailCare = (TextView) findViewById(R.id.tvLihatDetailCare);
        tvLihatDetailMemories = (TextView) findViewById(R.id.tvLihatDetailMemories);
        ivMenuBars = (ImageView) findViewById(R.id.ivMenuBars);

        //Manggil Recycler View Recent View

        recyclerView = (RecyclerView)findViewById(R.id.recyclerRecentMemories);
        listPhoto = addList();
        adapter = new RecentAdapter(this,listPhoto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        //Manggil Recycler View Care Tips

        recyclerViewCareTips = (RecyclerView)findViewById(R.id.recyclerCareTips);
        dataListTips = dataTips();
        adapterCareTips = new CareTipsAdapter(this,dataListTips);
        recyclerViewCareTips.setAdapter(adapterCareTips);
        recyclerViewCareTips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        eventListener();
    }

    //Data RecentView

    private ArrayList<RecentMemories> addList(){
        ArrayList<RecentMemories> list = new ArrayList<>();

        for(int i = 0; i< 10; i++){
            RecentMemories recentMemories = new RecentMemories();
            recentMemories.setBabyPhoto(fotoBayi[i]);
            list.add(recentMemories);
        }
        return list;
    }

    //Data Care Tips

    private ArrayList<CareTips> dataTips(){

        ArrayList<CareTips> listTips = new ArrayList<>();

        for(int i = 0; i< 10; i++){
            CareTips careTips = new CareTips();
            careTips.setPicTips(foodPic[i]);
            careTips.setTitleTips(judulTips[i]);
            careTips.setPhotoPeople(peoplePhoto[i]);
            careTips.setNamePeople(name[i]);
            listTips.add(careTips);
        }
        return listTips;
    }

    private void eventListener() {
        tvLihatDetailCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CareActivity.class));
            }
        });
        tvLihatDetailMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MemoriesActivity.class));
            }
        });
        tvLihatDetailCare.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    tvLihatDetailCare.setTextColor(Color.YELLOW);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    System.out.println("UP");
                }
                if(event.getAction() == MotionEvent.ACTION_CANCEL){
                    System.out.println("cancel");
                }
                return false;
            }
        });
        ivMenuBars.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SidebarActivity.class));
            }
        });
    }

}