package com.stustirling.moviedbshowcase;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stustirling.moviedbshowcase.internal.di.components.ApplicationComponent;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

        initActivity(savedInstanceState);
    }


    protected abstract void initInjector();
    protected abstract void initActivity(Bundle savedInstanceState);

    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
