package com.stustirling.moviedbshowcase.internal.di.components;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;
import com.stustirling.moviedbshowcase.internal.di.modules.MovieDBModule;
import com.stustirling.moviedbshowcase.popularmovies.PopularMoviesFragment;
import com.stustirling.moviedbshowcase.tvshows.PopularTVShowsFragment;

import dagger.Component;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MovieDBModule.class })
public interface MovieDBComponent extends ActivityComponent{
    void inject(PopularMoviesFragment fragment);
    void inject(PopularTVShowsFragment fragment);

//    UseCase getTop20PopularMoviesUseCase();
//    UseCase getTop20PopularTVShowsUseCase();
}
