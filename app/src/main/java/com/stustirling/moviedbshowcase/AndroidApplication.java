package com.stustirling.moviedbshowcase;

import android.app.Application;

import com.stustirling.moviedbshowcase.internal.di.components.ApplicationComponent;import com.stustirling.moviedbshowcase.internal.di.components.DaggerApplicationComponent;
import com.stustirling.moviedbshowcase.internal.di.modules.ApplicationModule;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        initInjector();
    }

    private void initInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
