package com.stustirling.moviedbshowcase.internal.di.components;

import com.stustirling.moviedbshowcase.domain.interactor.GetMovieDetails;
import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;
import com.stustirling.moviedbshowcase.internal.di.modules.MovieDetailsModule;
import com.stustirling.moviedbshowcase.details.movie.MovieDetailsActivity;

import dagger.Component;

/**
 * Created by Stu Stirling on 13/06/16.
 */
@PerActivity
@Component(dependencies=ApplicationComponent.class,modules={ActivityModule.class,MovieDetailsModule.class})
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity movieDetailsActivity);

    GetMovieDetails getMovieDetails();
}
