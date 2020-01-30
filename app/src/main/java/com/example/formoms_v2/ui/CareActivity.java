package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.RecyclerItemClickListener;
import com.example.formoms_v2.adapter.pojo.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CareActivity extends AppCompatActivity {

    public static final String CARE_ID = "CARE_ID", CARE_TITLE = "CARE_TITLE", CARE_CONTENT = "CARE_CONTENT", CARE_AUTHOR = "CARE_AUTHOR";

    private ArrayList<Care> dataListTips;
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerViewCareTips;
    private CareAdapter adapterCare;

    private DatabaseReference refCare;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        // Get view by id
        recyclerViewCareTips = (RecyclerView) findViewById(R.id.rvCare);
        btnAdd = (FloatingActionButton) findViewById(R.id.fab_add);

        dataListTips = new ArrayList<>();

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
                adapterCare = new CareAdapter(dataListTips);
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
}
