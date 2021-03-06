package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.AlbumPhotoAdapter;
import com.example.formoms_v2.adapter.MemoriesAdapter;
import com.example.formoms_v2.adapter.pojo.Album;
import com.example.formoms_v2.adapter.RecyclerItemClickListener;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.example.formoms_v2.auth.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemoriesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private ArrayList<Album> listMemories;
    private ArrayList<AlbumPhoto> photoList;

    private RecyclerView recyclerView;
    private ImageView btnAdd;
    private MemoriesAdapter adapter;

    private DatabaseReference refMemories;
    private DatabaseReference refPhoto;
    private FirebaseDatabase database;
    private ImageView ivMenuBars;
    private NavigationView navigationSidebar;

    MemoriesActivity context;
    private FirebaseAuth mAuth;

    private String idAlbum;

    public static final String MEMORIES_ID = "MEMORIES_ID", MEMORIES_NAME = "MEMORIES_NAME", MEMORIES_CREATEDAT = "MEMORIES_CREATEDAT";

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        context = this;
        listMemories = new ArrayList<>();
        photoList = new ArrayList<>();
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


        initComponent();
        eventListener();

        // Get a reference to our posts
        database = FirebaseDatabase.getInstance();
        refMemories = database.getReference("Memories/Album");
        refPhoto = database.getReference("Memories").child("Photo");
    }

    @Override
    protected void onStart() {
        super.onStart();

        refMemories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMemories.clear();
                for (DataSnapshot albumValue : dataSnapshot.getChildren()) {
                    Album album = albumValue.getValue(Album.class);
                    listMemories.add(album);
                    refPhoto.orderByChild("albumId").equalTo(album.getAlbumId()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            photoList.clear();
                            for (DataSnapshot photoValue : dataSnapshot.getChildren()){
                                AlbumPhoto albumPhoto = photoValue.getValue(AlbumPhoto.class);
                                photoList.add(albumPhoto);
                            }
                            adapter = new MemoriesAdapter(listMemories, photoList,MemoriesActivity.this);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                            adapter.notifyDataSetChanged();
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
    }

    private void initComponent() {
        recyclerView = (RecyclerView)findViewById(R.id.rvMemories);
        btnAdd = (ImageView) findViewById(R.id.ivTambahMemories);
    }

    private void eventListener() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Album album = listMemories.get(position);

                Intent intent = new Intent(MemoriesActivity.this, DetailMemoriesActivity.class);
                intent.putExtra(MEMORIES_ID, album.getAlbumId());
                intent.putExtra(MEMORIES_NAME, album.getAlbumName());
                intent.putExtra(MEMORIES_CREATEDAT, album.getCreatedAt());
                startActivity(intent);
            }
        }));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoriesActivity.this, ManageAlbum.class);
                startActivity(intent);
            }
        });
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
            startActivity(new Intent(MemoriesActivity.this, LoginActivity.class));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
