package com.example.formoms_v2.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formoms_v2.MainActivity;
import com.example.formoms_v2.R;
import com.example.formoms_v2.ui.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mloginBtn,mregisterBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mEmailField = findViewById(R.id.email_login);
        mPasswordField = findViewById(R.id.password_login);
        mloginBtn = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        mregisterBtn =(Button) findViewById(R.id.btn_regist);
        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        };

        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                startSignIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() {
                @Override

                public void onComplete(@NonNull Task task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Problem", Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }
}

