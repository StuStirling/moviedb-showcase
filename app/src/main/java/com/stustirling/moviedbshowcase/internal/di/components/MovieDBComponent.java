package com.stustirling.moviedbshowcase.internal.di.components;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;
import com.stustirling.moviedbshowcase.internal.di.modules.PopularMoviesModule;
import com.stustirling.moviedbshowcase.popularmovies.PopularMoviesFragment;

import dagger.Component;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PopularMoviesModule.class})
public interface MovieDBComponent extends ActivityComponent{
    void inject(PopularMoviesFragment fragment);
}
