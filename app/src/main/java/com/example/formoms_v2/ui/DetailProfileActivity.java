package com.example.formoms_v2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Locale;

public class DetailProfileActivity extends AppCompatActivity {
    EditText edtNama,edtTanggal,edtTempat,edtBio;
    TextView btn_simpan;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        edtNama =(EditText)findViewById(R.id.edtName_profile);
        edtTanggal=(EditText)findViewById(R.id.edtTanggal_profile);
        edtTempat = (EditText)findViewById(R.id.edtCountry_profile);
        edtBio = (EditText)findViewById(R.id.edtBio_profile);
        initUpdate();
        btn_simpan= (TextView)findViewById(R.id.btnSimpanProfile);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Update();
                Intent i= new Intent(DetailProfileActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });
    }
    private void initUpdate(){
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser usera = mAuth.getCurrentUser();
        String id = usera.getUid();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("User").child(id);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User a  = dataSnapshot.getValue(User.class);
                edtNama.setText(a.getNama());
                edtTanggal.setText(a.getTglLahir());
                edtTempat.setText(a.gettLahir());
                edtBio.setText(a.getDepskripsi());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean Update(){
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser usera = mAuth.getCurrentUser();
        String id = usera.getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("User").child(id);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User a  = dataSnapshot.getValue(User.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        String email = usera.getEmail();
        String nama = edtNama.getText().toString();
        String tanggal = edtTanggal.getText().toString();
        String tempat = edtTempat.getText().toString();
        String bio = edtBio.getText().toString();
        User u = new User(id,nama,email,null,null,tanggal,tempat,bio);

        db.setValue(u);

        return true;
    }

}
