package com.example.formoms_v2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ProfileActivity extends AppCompatActivity {

    private TextView btnEditProfile;
    FirebaseAuth mAuth;
    TextView tvNama,tvTanggal,tvTempat,tvBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnEditProfile = (TextView)findViewById(R.id.tvBtnEditProfile);
        tvNama = (TextView)findViewById(R.id.tvNameProfile);
        tvTanggal= (TextView)findViewById(R.id.tvTanggalLahir);
        tvTempat = (TextView)findViewById(R.id.tvTempatLahir);
        tvBio=(TextView)findViewById(R.id.tvStatusBayi);
        initUpdate();
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, DetailProfileActivity.class);
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
                tvNama.setText(a.getNama());
                tvTanggal.setText(a.getTglLahir());
                tvTempat.setText(a.gettLahir());
                tvBio.setText(a.getDepskripsi());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }
}
