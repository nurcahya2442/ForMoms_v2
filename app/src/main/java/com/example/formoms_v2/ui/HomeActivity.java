package com.example.formoms_v2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

            R.drawable.bayi_4,
            R.drawable.food,
            R.drawable.bayi_4,
            R.drawable.food,
            R.drawable.bayi_4,
            R.drawable.food,
            R.drawable.bayi_4,
            R.drawable.food,
            R.drawable.bayi_4,
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

}
