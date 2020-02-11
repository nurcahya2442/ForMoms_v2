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
import com.example.formoms_v2.adapter.MemoriesAdapter;
import com.example.formoms_v2.adapter.pojo.Album;
import com.example.formoms_v2.adapter.RecyclerItemClickListener;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MemoriesActivity extends AppCompatActivity {

    private ArrayList<Album> listMemories;
    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;

    private DatabaseReference refMemories;
    private FirebaseDatabase database;

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

        listMemories = new ArrayList<>();

        initComponent();
        eventListener();

        // Get a reference to our posts
        database = FirebaseDatabase.getInstance();
        refMemories = database.getReference("Memories/Album");
    }

    @Override
    protected void onStart() {
        super.onStart();

        refMemories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listMemories.clear();
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    Album album = value.getValue(Album.class);
                    listMemories.add(album);
                }
                adapter = new MemoriesAdapter(listMemories);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    private void initComponent() {
        recyclerView = (RecyclerView)findViewById(R.id.rvMemories);
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
    }
}
