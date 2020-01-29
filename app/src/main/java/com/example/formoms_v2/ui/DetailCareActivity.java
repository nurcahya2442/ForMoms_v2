package com.example.formoms_v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formoms_v2.R;

public class DetailCareActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtDesc;
    private TextView txtAuthor;

    private String id, title, content, author;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail_care);

         Intent intent = getIntent();
         id = intent.getStringExtra(CareActivity.CARE_ID);
         title = intent.getStringExtra(CareActivity.CARE_TITLE);
         content = intent.getStringExtra(CareActivity.CARE_CONTENT);
         author = intent.getStringExtra(CareActivity.CARE_AUTHOR);

         initComponent();
    }

    private void initComponent() {
        txtTitle = (TextView) findViewById(R.id.tvTitle);
        txtDesc = (TextView) findViewById(R.id.tvContent);
        txtAuthor = (TextView) findViewById(R.id.tvAuthor);

        txtTitle.setText(title);
        txtDesc.setText(content);
        txtAuthor.setText(author);
    }
}
