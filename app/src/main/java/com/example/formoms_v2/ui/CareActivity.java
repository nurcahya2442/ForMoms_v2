package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CareActivity extends AppCompatActivity {

    private ArrayList<Care> dataListTips;
    private RecyclerView recyclerViewCareTips;
    private CareAdapter adapterCare;

    DatabaseReference ref;
    public FirebaseDatabase database;

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
        dataListTips = new ArrayList<>();

        eventListener();


        // Get a reference to our posts
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
                startActivity(new Intent(CareActivity.this, DetailCareActivity.class));
            }
        }));
    }
}
