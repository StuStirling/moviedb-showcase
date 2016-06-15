package com.stustirling.moviedbshowcase.internal.di.modules;

import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;
import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularTVShows;
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
public class MovieDBModule {

    @Provides @PerActivity @Named("threadExecutor")
    Scheduler provideThreadExecutor() {
        return Schedulers.newThread();
    }

    @Provides @PerActivity @Named("postExecutionThread")
    Scheduler providePostExecutionThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides @PerActivity @Named("top20PopularMovies")
    UseCase provideGetTop20PopularMoviesUseCase(GetTop20PopularMovies getTop20PopularMovies) {
        return getTop20PopularMovies;
    }

    @Provides @PerActivity @Named("top20PopularTVShows")
    UseCase provideGetTop20PopularTVShowsUseCase(GetTop20PopularTVShows getTop20PopularTVShows) {
        return getTop20PopularTVShows;
    }

}
