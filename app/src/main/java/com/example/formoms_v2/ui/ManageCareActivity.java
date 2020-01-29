package com.example.formoms_v2.ui;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Care;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Locale;

public class ManageCareActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    EditText txtJudul;
    EditText txtDesc;
    Button btnEnter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_care);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Care");

        initComponent();
        initEvent();
    }

    private void initComponent() {
        txtJudul = (EditText) findViewById(R.id.txt_title);
        txtDesc = (EditText) findViewById(R.id.txt_desc);
        btnEnter = (Button) findViewById(R.id.btn_simpan);
    }

    private void initEvent() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Insert();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Insert(){
        // Get value to string
        String title = txtJudul.getText().toString();
        String desc = txtDesc.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  hh:mm:ss", Locale.getDefault());
        String createdAt = sdf.format(new Date());
        String author = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        if(!TextUtils.isEmpty(title)){
            // get key id
            String id = ref.push().getKey();
            Care careData = new Care(id,0,title,0,author,desc,createdAt);

            // Add data to firebase
            ref.child(id).setValue(careData);

            Toast.makeText(this,"Post berhasil di masukan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Post gagal di masukan",Toast.LENGTH_LONG).show();
        }
    }
}
