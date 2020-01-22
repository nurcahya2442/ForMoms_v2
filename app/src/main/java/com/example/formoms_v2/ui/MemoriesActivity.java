package com.example.formoms_v2.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.MemoriesAdapter;
import com.example.formoms_v2.adapter.pojo.Memories;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MemoriesActivity extends AppCompatActivity {

    private ArrayList<Memories> listMemories;
    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;

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

    private String[] albumName = new String[]{
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite",
            "My Favorite"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);

        recyclerView = (RecyclerView)findViewById(R.id.rvMemories);
        listMemories = addList();
        adapter = new MemoriesAdapter(listMemories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    private ArrayList<Memories> addList(){
        ArrayList<Memories> list = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();

        for(int i = 0; i<7; i++){
            Memories memories = new Memories();
            memories.setPic1(fotoBayi[i]);
            memories.setPic2(fotoBayi[i+1]);
            memories.setPic3(fotoBayi[i+2]);
            memories.setPic4(fotoBayi[i+3]);
            memories.setAlbumName(albumName[i]);
            memories.setCreatedAt(Integer.parseInt(formatter.format(date)));
            list.add(memories);
        }
        return list;
    }
}
