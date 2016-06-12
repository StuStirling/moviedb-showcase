package com.stustirling.moviedbshowcase.internal.di.modules;

import android.app.Application;
import android.content.Context;

import com.stustirling.moviedbshowcase.data.MovieDBDataRepository;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    MovieDBRepository providesMovieDBRepository(MovieDBDataRepository movideDBDataRepository) {
        return movideDBDataRepository;
    }
}
