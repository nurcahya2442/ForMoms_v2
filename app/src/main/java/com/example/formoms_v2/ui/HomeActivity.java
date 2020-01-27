package com.example.formoms_v2.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.CareTipsAdapter;
import com.example.formoms_v2.adapter.RecentAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.CareTips;
import com.example.formoms_v2.adapter.pojo.RecentMemories;
import com.example.formoms_v2.adapter.pojo.RecyclerItemClickListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private NavigationView navigationSidebar;



    DatabaseReference ref;
    public FirebaseDatabase database;

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

        navigationSidebar = (NavigationView)findViewById(R.id.navigationBar);
        final DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);

        ivMenuBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });


        //Manggil Recycler View Recent View

        recyclerView = (RecyclerView)findViewById(R.id.recyclerRecentMemories);
        listPhoto = addList();
        adapter = new RecentAdapter(this,listPhoto);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        //Manggil Recycler View Care Tips

        recyclerViewCareTips = (RecyclerView)findViewById(R.id.recyclerCareTips);
        dataListTips = new ArrayList<>();

        eventListener();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Care");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataListTips.clear();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    CareTips care = value.getValue(CareTips.class);
                    dataListTips.add(care);
                }
                adapterCareTips = new CareTipsAdapter(dataListTips);
                recyclerViewCareTips.setAdapter(adapterCareTips);
                recyclerViewCareTips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                adapterCareTips.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
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

    private void eventListener() {
        tvLihatDetailCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CareActivity.class));
            }
        });

        tvLihatDetailMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MemoriesActivity.class));
            }
        });



        recyclerViewCareTips.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(HomeActivity.this, DetailCareActivity.class));
            }
        }));
    }

}