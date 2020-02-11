package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.AlbumPhotoAdapter;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailMemoriesActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView rvPhoto;
    TextView tvAlbumName;

    ArrayList<AlbumPhoto> photoList;

    //Firebase Database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference refPhoto;
    DatabaseReference refAlbum;

    private String idAlbum, namaAlbum;
    private AlbumPhotoAdapter adapterPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memories);

        initComponent();

        photoList = new ArrayList<>();

        Intent intent = getIntent();
        idAlbum = intent.getStringExtra(MemoriesActivity.MEMORIES_ID);
        namaAlbum = intent.getStringExtra(MemoriesActivity.MEMORIES_NAME);

        tvAlbumName.setText(namaAlbum);

        //Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        refPhoto = firebaseDatabase.getReference("Memories").child("Photo");
        refAlbum = firebaseDatabase.getReference("Memories/Album").child(idAlbum);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, idAlbum, Toast.LENGTH_SHORT).show();
        refPhoto.orderByChild("albumId").equalTo(idAlbum).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot photoSnap: dataSnapshot.getChildren()) {
                    AlbumPhoto photo = photoSnap.getValue(AlbumPhoto.class);
                    photoList.add(photo);
                }
                adapterPhoto = new AlbumPhotoAdapter(DetailMemoriesActivity.this, photoList);
                rvPhoto.setAdapter(adapterPhoto);
                rvPhoto.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                adapterPhoto.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initComponent() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add_photo);
        rvPhoto = (RecyclerView) findViewById(R.id.rvPhoto);
        tvAlbumName = (TextView) findViewById(R.id.tvDetailAlbumName);
        rvPhoto = (RecyclerView) findViewById(R.id.rvPhoto);

        //Event
        eventListener();
    }

    private void eventListener() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMemoriesActivity.this, PreviewUploadActivity.class);
                startActivity(intent);
            }
        });
    }
}
