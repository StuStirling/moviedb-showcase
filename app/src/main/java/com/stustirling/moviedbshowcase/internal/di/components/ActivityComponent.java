package com.stustirling.moviedbshowcase.internal.di.components;

import android.app.Activity;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class )
public interface ActivityComponent {

    Activity activity();
}
