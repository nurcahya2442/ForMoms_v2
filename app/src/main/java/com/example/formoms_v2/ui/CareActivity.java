package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.RecyclerItemClickListener;
import com.example.formoms_v2.auth.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CareActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    public static final String CARE_ID = "CARE_ID", CARE_PIC = "CARE_PIC", CARE_TITLE = "CARE_TITLE", CARE_CONTENT = "CARE_CONTENT", CARE_AUTHOR = "CARE_AUTHOR";

    private ArrayList<Care> dataListTips;
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerViewCareTips;
    private CareAdapter adapterCare;

    private DatabaseReference refCare;
    private FirebaseDatabase database;

    private ImageView ivMenuBars;
    private NavigationView navigationSidebar;
    CareActivity context;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);
        context=this;
        // Get view by id
        recyclerViewCareTips = (RecyclerView) findViewById(R.id.rvCare);
        btnAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        dataListTips = new ArrayList<>();

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


        eventListener();

        // Get a reference to our posts
        database = FirebaseDatabase.getInstance();
        refCare = database.getReference("Care");
    }

    @Override
    protected void onStart() {
        super.onStart();
        refCare.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataListTips.clear();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    Care care = value.getValue(Care.class);
                    dataListTips.add(care);
                }
                adapterCare = new CareAdapter(CareActivity.this, dataListTips);
                recyclerViewCareTips.setAdapter(adapterCare);
                recyclerViewCareTips.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapterCare.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void eventListener() {
        recyclerViewCareTips.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Care care = dataListTips.get(position);
                Intent intent = new Intent(CareActivity.this, DetailCareActivity.class);
                intent.putExtra(CARE_ID, care.getIdCare());
                intent.putExtra(CARE_PIC, care.getPicTips());
                intent.putExtra(CARE_TITLE, care.getTitleTips());
                intent.putExtra(CARE_CONTENT, care.getContent());
                intent.putExtra(CARE_AUTHOR, care.getNamePeople());
                startActivity(intent);
            }
        }));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareActivity.this, ManageCareActivity.class);
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
            startActivity(new Intent(CareActivity.this, LoginActivity.class));


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
