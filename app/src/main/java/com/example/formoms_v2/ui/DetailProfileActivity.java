package com.example.formoms_v2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView;
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
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

import java.util.Date;
import java.util.Locale;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class DetailProfileActivity extends AppCompatActivity {
    EditText edtNama,edtTanggal,edtTempat,edtBio;
    TextView btn_simpan;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    ImageView ivMenuBack;
    ImageView btnIvCamera;
    ImageView btnIvCameraRounded, img_profpict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        edtNama =(EditText)findViewById(R.id.edtName_profile);
        edtTanggal=(EditText)findViewById(R.id.edtTanggal_profile);
        edtTempat = (EditText)findViewById(R.id.edtCountry_profile);
        edtBio = (EditText)findViewById(R.id.edtBio_profile);
        btnIvCamera = (ImageView)findViewById(R.id.ivCamera);
        btnIvCameraRounded = (ImageView)findViewById(R.id.ivRoundedCamera);
        img_profpict = findViewById(R.id.ivImageProfile);

        btnIvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 1213);
            };




        });
        img_profpict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 1213);
            };



        });

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
        ivMenuBack = findViewById(R.id.ivMenuBack);
        ivMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(DetailProfileActivity.this,ProfileActivity.class);
                startActivity(n);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1213 && resultCode == Activity.RESULT_OK){
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            img_profpict.setImageBitmap(selectedImage);
        }
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
