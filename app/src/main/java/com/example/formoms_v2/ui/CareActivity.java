package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.CareAdapter;
import com.example.formoms_v2.adapter.pojo.Care;
import com.example.formoms_v2.adapter.pojo.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CareActivity extends AppCompatActivity {

    public static final String CARE_ID = "CARE_ID";
    public static final String CARE_TITLE = "CARE_TITLE";
    public static final String CARE_CONTENT = "CARE_CONTENT";
    public static final String CARE_AUTHOR = "CARE_AUTHOR";

    private ArrayList<Care> dataListTips;
    private FloatingActionButton btnAdd;
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

        // Get view by id
        recyclerViewCareTips = (RecyclerView) findViewById(R.id.rvCare);
        btnAdd = (FloatingActionButton) findViewById(R.id.fab_add);

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
