package com.example.formoms_v2.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.formoms_v2.R;
import com.example.formoms_v2.auth.LoginActivity;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new onBoarding1());
        addSlide(new onBoarding2());
        addSlide(new onBoarding3());

        showStatusBar(false);

        showSkipButton(true);
        showDoneButton(true);

        setSlideOverAnimation();



    }

    @Override
    public void onSkipPressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

        finish();
    }
}
