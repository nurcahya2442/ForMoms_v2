package com.example.formoms_v2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.example.formoms_v2.ui.HomeActivity;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
////        Handler handler = new Handler();
////
////        handler.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                startActivity(new Intent(SplashScreen.this, HomeActivity.class));
////            }
////        },3000);
//
//    }

    @Override
    public void initSplash(ConfigSplash configSplash) {

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Background Animation

        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(3000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        //Logo

        configSplash.setLogoSplash(R.drawable.ic_formom_logo);
        configSplash.setAnimLogoSplashDuration(5000);
        configSplash.setAnimLogoSplashTechnique(Techniques.BounceIn);


        //Title
        configSplash.setTitleSplash(getString(R.string.title_splash));
        configSplash.setTitleTextColor(R.color.txtColor);
        configSplash.setTitleTextSize(40f);
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);

    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashScreen.this,HomeActivity.class));

    }
}
