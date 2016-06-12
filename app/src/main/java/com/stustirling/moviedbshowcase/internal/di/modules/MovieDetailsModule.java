package com.stustirling.moviedbshowcase.internal.di.modules;

import com.stustirling.moviedbshowcase.domain.interactor.GetMovieDetails;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Module
public class MovieDetailsModule {

    private final int movieId;

    public MovieDetailsModule(int movieId ) {
        this.movieId = movieId;
    }

    @Provides @PerActivity @Named("threadExecutor")
    Scheduler provideThreadExecutor() {
        return Schedulers.newThread();
    }

    @Provides @PerActivity @Named("postExecutionThread")
    Scheduler providePostExecutionThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides @PerActivity @Named("movieId")
    int provideMovieId() {
        return movieId;
    }

    @Provides @PerActivity @Named("getMovieDetails")
    UseCase provideGetMovieDetailsUseCase(GetMovieDetails getMovieDetails) {
        return getMovieDetails;
    }

}
