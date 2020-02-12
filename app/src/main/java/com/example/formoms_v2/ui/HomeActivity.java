package com.example.formoms_v2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareTipsAdapter;
import com.example.formoms_v2.adapter.HomeMemoriesAdapter;
import com.example.formoms_v2.adapter.RecentAdapter;
import com.example.formoms_v2.adapter.RecyclerItemClickListener;
import com.example.formoms_v2.adapter.pojo.Album;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.RecentMemories;
import com.example.formoms_v2.auth.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CARE_ID = "CARE_ID", CARE_TITLE = "CARE_TITLE", CARE_CONTENT = "CARE_CONTENT", CARE_AUTHOR = "CARE_AUTHOR";

    private ArrayList<AlbumPhoto> photoList;

    private ArrayList<RecentMemories> dataListPhoto;
    private RecyclerView recyclerView;
    private RecentAdapter adapter;

    private ArrayList<Care> dataListTips;
    private RecyclerView recyclerViewCareTips;
    private CareTipsAdapter adapterCareTips;

    private ArrayList<Album> dataMemories;
    private RecyclerView recyclerViewMemories;
    private HomeMemoriesAdapter adapterMemories;

    private TextView tvLihatDetailCare;
    private TextView tvLihatDetailMemories;
    private ImageView ivMenuBars;
    private NavigationView navigationSidebar;

    HomeActivity context;
    private FirebaseAuth mAuth;
    private DatabaseReference ref, refMemories, refRecent, refPhoto;
    public FirebaseDatabase database;

    private String[] name = new String[] {"Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson","Angelica Witson", "Pretty Jonson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;

        tvLihatDetailCare = (TextView) findViewById(R.id.tvLihatDetailCare);
        tvLihatDetailMemories = (TextView) findViewById(R.id.tvLihatDetailMemories);
        ivMenuBars = (ImageView) findViewById(R.id.ivMenuBars);

        navigationSidebar = (NavigationView)findViewById(R.id.navigationBar);
        navigationSidebar.setNavigationItemSelectedListener(this);


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


        //Manggil Recycler View Recent
        recyclerView = (RecyclerView) findViewById(R.id.recyclerRecentMemories);
        dataListPhoto = new ArrayList<>();

        //Manggil Recycler View Care Tips
        recyclerViewCareTips = (RecyclerView)findViewById(R.id.recyclerCareTips);
        dataListTips = new ArrayList<>();

        //Manggil Recycler View Memories
        recyclerViewMemories = (RecyclerView) findViewById(R.id.recyclerMemories);
        dataMemories = new ArrayList<>();

        photoList = new ArrayList<>();

        eventListener();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Care");
        refMemories = database.getReference("Memories/Album");
        refRecent = database.getReference("Memories/Photo");
        refPhoto = database.getReference("Memories").child("Photo");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataListTips.clear();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    Care care = value.getValue(Care.class);
                    dataListTips.add(care);
                }
                adapterCareTips = new CareTipsAdapter(HomeActivity.this, dataListTips);
                recyclerViewCareTips.setAdapter(adapterCareTips);
                recyclerViewCareTips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                adapterCareTips.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        refMemories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataMemories.clear();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    Album album = value.getValue(Album.class);
                    dataMemories.add(album);
                    refPhoto.orderByChild("albumId").equalTo(album.getAlbumId()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            photoList.clear();
                            for (DataSnapshot photoValue : dataSnapshot.getChildren()){
                                AlbumPhoto albumPhoto = photoValue.getValue(AlbumPhoto.class);
                                photoList.add(albumPhoto);
                            }
                            adapterMemories = new HomeMemoriesAdapter(HomeActivity.this, dataMemories, photoList);
                            recyclerViewMemories.setAdapter(adapterMemories);
                            recyclerViewMemories.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                            adapterMemories.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        refRecent.limitToFirst(5).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataListPhoto.clear();
                for (DataSnapshot photoSnap: dataSnapshot.getChildren()) {
                    RecentMemories photo = photoSnap.getValue(RecentMemories.class);
                    dataListPhoto.add(photo);
                }
                adapter = new RecentAdapter(dataListPhoto,HomeActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                Care care = dataListTips.get(position);
                Intent intent = new Intent(HomeActivity.this, DetailCareActivity.class);
                intent.putExtra(CARE_ID, care.getIdCare());
                intent.putExtra(CARE_TITLE, care.getTitleTips());
                intent.putExtra(CARE_CONTENT, care.getContent());
                intent.putExtra(CARE_AUTHOR, care.getNamePeople());
                startActivity(intent);
            }
        }));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            // Handle the camera action
            mAuth = FirebaseAuth.getInstance();

            startActivity(new Intent(context, ProfileActivity.class));
        } else if (id == R.id.menu_memories) {
            startActivity(new Intent(context, MemoriesActivity.class));
        } else if (id == R.id.menu_care) {
            startActivity(new Intent(context, CareActivity.class));
        } else if (id == R.id.menu_chcekup) {
            startActivity(new Intent(context, ImunisasiActivity.class));
        } else if (id == R.id.menu_Logout) {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            finish();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}