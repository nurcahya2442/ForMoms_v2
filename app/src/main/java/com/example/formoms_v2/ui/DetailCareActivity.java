package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.formoms_v2.R;

public class DetailCareActivity extends AppCompatActivity {

    private TextView txtTitle,txtDesc,txtAuthor, txtSubtitle;
    private String id, title, content, author, pics;
    ImageView ivMenuBack, ivDetailCare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail_care);

         Intent intent = getIntent();
         id = (intent.getStringExtra(HomeActivity.CARE_ID) != null) ? intent.getStringExtra(HomeActivity.CARE_ID) : intent.getStringExtra(CareActivity.CARE_ID);
         pics = (intent.getStringExtra(HomeActivity.CARE_PIC) != null) ? intent.getStringExtra(HomeActivity.CARE_PIC) : intent.getStringExtra(CareActivity.CARE_PIC);
         title = (intent.getStringExtra(HomeActivity.CARE_TITLE) != null) ? intent.getStringExtra(HomeActivity.CARE_TITLE) : intent.getStringExtra(CareActivity.CARE_TITLE);
         content = (intent.getStringExtra(HomeActivity.CARE_CONTENT) != null) ? intent.getStringExtra(HomeActivity.CARE_CONTENT) : intent.getStringExtra(CareActivity.CARE_CONTENT);
         author = (intent.getStringExtra(HomeActivity.CARE_AUTHOR) != null) ? intent.getStringExtra(HomeActivity.CARE_AUTHOR) : intent.getStringExtra(CareActivity.CARE_AUTHOR);
         ivMenuBack = findViewById(R.id.ivMenuBack);
         ivMenuBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent n = new Intent(DetailCareActivity.this,CareActivity.class);
                 startActivity(n);

             }
         });
         initComponent();
    }

    private void initComponent() {
        txtTitle = (TextView) findViewById(R.id.tvTitle);
        txtDesc = (TextView) findViewById(R.id.tvContent);
        txtAuthor = (TextView) findViewById(R.id.tvAuthor);
        txtSubtitle = (TextView) findViewById(R.id.tvSubtitle);
        ivDetailCare = (ImageView) findViewById(R.id.ivDetailCare);

        txtTitle.setText(title);
        txtDesc.setText(content);
        txtAuthor.setText(author);
        txtSubtitle.setText(title);
        Glide.with(DetailCareActivity.this).load(pics).into(ivDetailCare);
    }
}
