package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Stu Stirling on 11/06/16.
 */
@Module
public class MockMovieDBServiceModule  {

    @Provides
    @Singleton
    protected MovieDBApi providesMockMovieDBApi() {
        return mock(MovieDBApi.class);
    }
}
