package com.stustirling.moviedbshowcase.internal.di.modules;

import android.app.Activity;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule( Activity activity ) {
        this.activity = activity;
    }

    @Provides @PerActivity Activity provideActivity() {
        return this.activity;
    }
}
