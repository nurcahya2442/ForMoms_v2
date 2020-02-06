package com.example.formoms_v2.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.AlbumPhoto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailMemoriesActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView rvPhoto;
    TextView tvAlbumName;
    ImageView ivTest;

    //Firebase Storage
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageTask uploadTask;

    //Firebase Database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memories);

        initComponent();

        Intent intent = getIntent();
        String idAlbum = intent.getStringExtra(MemoriesActivity.MEMORIES_ID);
        String namaAlbum = intent.getStringExtra(MemoriesActivity.MEMORIES_NAME);

        tvAlbumName.setText(namaAlbum);

        //Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Memories/"+idAlbum+"/albumPhoto");

        //Firebase Storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    private void initComponent() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add_photo);
        rvPhoto = (RecyclerView) findViewById(R.id.rvPhoto);
        tvAlbumName = (TextView) findViewById(R.id.tvDetailAlbumName);
        ivTest = (ImageView) findViewById(R.id.iv_test);

        //Event
        eventListener();
    }

    private void eventListener() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        ivTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Insert(String url){
        // Get value to string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String createdAt = sdf.format(new Date());

        if(!createdAt.isEmpty()){
            // get key id
            String id = ref.push().getKey();

            AlbumPhoto photoData = new AlbumPhoto(id,url,createdAt);

            // Add data to firebase
            ref.child(id).setValue(photoData);

            Toast.makeText(this,"Post berhasil di masukan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Post gagal di masukan",Toast.LENGTH_LONG).show();
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivTest.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Sedang Upload...");
            progressDialog.show();

            final StorageReference storageRef = storageReference.child(System.currentTimeMillis()+".jpg");
            uploadTask = storageRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Insert(uri.toString());
                                }
                            });
                            progressDialog.dismiss();
                            Toast.makeText(DetailMemoriesActivity.this, "Data Berhasil Terupload", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(DetailMemoriesActivity.this, "Upload gagal "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Sedang Upload "+(int)progress+"%");
                        }
                    });
        }
    }

}
