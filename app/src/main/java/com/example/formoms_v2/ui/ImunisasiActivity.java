package com.example.formoms_v2.ui;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.ImunisasiAdapter;
import com.example.formoms_v2.adapter.pojo.Imunisasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ImunisasiActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Imunisasi> nlist;

    DatabaseReference db;

    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nlist.clear();
                for(DataSnapshot notess: dataSnapshot.getChildren()){
                    Imunisasi note = notess.getValue(Imunisasi.class);
                    nlist.add(note);
                }

                ArrayAdapter adapter = new ImunisasiAdapter(ImunisasiActivity.this,nlist);
                lv = findViewById(R.id.list);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imunisasi);

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(ImunisasiActivity.this, ManageImunisasiActivity.class);
                pindah.putExtra("action","Tambah");
                pindah.putExtra("id","");
                startActivity(pindah);
            }
        });

        db = FirebaseDatabase.getInstance().getReference("Imunisasi");

        lv = findViewById(R.id.list);
        nlist = new ArrayList<Imunisasi>();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imunisasi bear = nlist.get(position);
                showUpdateDialog(bear.getId());
            }
        });

    }

    private void showUpdateDialog(final String id){
        AlertDialog.Builder dialogb = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogv = inflater.inflate(R.layout.dialog_imunisasi,null);

        dialogb.setView(dialogv);

        final Button btn_edit = dialogv.findViewById(R.id.Edit);
        final Button btn_delete = dialogv.findViewById(R.id.Delete);
        final Button btn_lihat = dialogv.findViewById(R.id.Lihat);
        final AlertDialog dialog = dialogb.create();
        dialog.show();
        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUpdate(id);
                dialog.dismiss();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(id);
                dialog.dismiss();
            }
        });

    }
    private void initUpdate(final String id){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Imunisasi").child(id);

        db.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Imunisasi note = dataSnapshot.getValue(Imunisasi.class);
                String judul = note.getJudul();
                String deskripsi = "Sudah";
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Imunisasi").child(id);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String tanggal = sdf.format(new Date());
                Imunisasi notea = new Imunisasi(id,judul,deskripsi,tanggal);

                db.setValue(notea);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void Delete(String id){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Imunisasi").child(id);
        db.removeValue();
        Toast.makeText(this,"Data berhasil dihapus",Toast.LENGTH_LONG).show();
    }


}
