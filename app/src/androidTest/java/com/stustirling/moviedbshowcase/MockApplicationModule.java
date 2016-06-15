package com.stustirling.moviedbshowcase;

import android.content.Context;

import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@Module
public class MockApplicationModule {

    @Provides
    @Singleton
    Context provideContext() {
        return mock(Context.class);
    }

    @Provides
    @Singleton
    MovieDBRepository providesMovieDBRepository() {
        return mock(MovieDBRepository.class);
    }

    @Provides @Singleton
    ConnectionTester providesConnectionIndicator() {
        return mock(ConnectionTester.class);
    }
}
