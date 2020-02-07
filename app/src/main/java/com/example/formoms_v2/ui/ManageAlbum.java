package com.example.formoms_v2.ui;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Album;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ManageAlbum extends AppCompatActivity {
    TextView act;
    EditText jdl,desc;
    Button confirm,delete,back;
    String id = "";

    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_album);

        db = FirebaseDatabase.getInstance().getReference("Memories");
        act = findViewById(R.id.action_txt);
        jdl = findViewById(R.id.edt_judul);
        desc = findViewById(R.id.edt_detail);
        confirm = findViewById(R.id.btn_confirm);
        delete = findViewById(R.id.btn_delete);
        back = findViewById(R.id.btn_back);

        Intent pindah = getIntent();
        String action = pindah.getStringExtra("action");
        id = pindah.getStringExtra("id");
        act.setText(action);

        if(!id.isEmpty()){
            initUpdate(id);
            delete.setEnabled(true);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Delete(id);
                }
            });
        }
        initbtn(confirm, MemoriesActivity.class,action);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ManageAlbum.this, MemoriesActivity.class);
                startActivity(pindah);
            }
        });

    }

    private void initbtn(Button btn,final Class tanclass,final String act){
        confirm.setText(act);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(act.equals("Tambah")){
                    Insert();
                }else if(act.equals("Ubah")){
                    Update(id);
                }
                Intent pindah = new Intent(ManageAlbum.this,tanclass);
                startActivity(pindah);
            }
        });
    }

    private void initUpdate(final String id){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Memories").child(id);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Album note = dataSnapshot.getValue(Album.class);
                jdl.setText(note.getAlbumName());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Insert(){
        String judul = jdl.getText().toString();
        String deskripsi = desc.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss", Locale.getDefault());
        String tanggal = sdf.format(new Date());

        if(!TextUtils.isEmpty(judul)){

            String id = db.push().getKey();

            Album newnotes = new Album(id,judul,tanggal);

            db.child(id).setValue(newnotes);

            Toast.makeText(this,"Album berhasil di masukan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Album gagal di masukan",Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean Update(String id){
        String judul = jdl.getText().toString();
        String deskripsi = desc.getText().toString();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Memories").child(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss", Locale.getDefault());
        String tanggal = sdf.format(new Date());
        Album note = new Album(id,judul,tanggal);

        db.setValue(note);

        return true;
    }

    private void Delete(String id){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Memories").child(id);
        db.removeValue();
        Toast.makeText(this,"Berhasil dihapus",Toast.LENGTH_LONG).show();
    }
}