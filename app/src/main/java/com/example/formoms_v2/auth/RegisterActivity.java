package com.example.formoms_v2.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    Button btn_regis;
    EditText email_regis,password_regis;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        btn_regis = (Button) findViewById(R.id.btn_regis);
        email_regis= (EditText) findViewById(R.id.email_regis);
        password_regis= (EditText) findViewById(R.id.password_regis);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = email_regis.getText().toString();
                final String password = password_regis.getText().toString();

                if (email.isEmpty()){
                    email_regis.setError("email kosong");
                    email_regis.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    password_regis.setError("password kosong");
                    password_regis.requestFocus();
                    return;
                }else if(password.length()<6){
                    password_regis.setError("Minimal 6 digit password");
                    password_regis.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // Get reference
                            ref = database.getReference("User");

                            // Get user id
                            String id= mAuth.getCurrentUser().getUid();

                            User user = new User( id,null,email,password,null,null,null,null);

                            // Add data to firebase
                            ref.child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RegisterActivity.this, "Data berhasil tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });

                            // Set display name user
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName("null").build();
                            currentUser.updateProfile(profileUpdates);

                            // Show toast
                            Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}

