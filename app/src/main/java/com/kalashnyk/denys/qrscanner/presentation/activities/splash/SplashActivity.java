package com.kalashnyk.denys.qrscanner.presentation.activities.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.kalashnyk.denys.qrscanner.R;
import com.kalashnyk.denys.qrscanner.di.component.ViewModelComponent;
import com.kalashnyk.denys.qrscanner.presentation.activities.main.MainActivity;
import com.kalashnyk.denys.qrscanner.presentation.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private long SPLASH_TIMER = 3000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            startActivity(MainActivity.newInstance(this));
            finish();
        }, SPLASH_TIMER);
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        //method override for all children of BaseActivity for dependence injection
    }
}
