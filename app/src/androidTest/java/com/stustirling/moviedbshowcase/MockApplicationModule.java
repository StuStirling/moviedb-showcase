package com.stustirling.moviedbshowcase;

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
    MovieDBRepository providesMovieDBRepository() {
        return mock(MovieDBRepository.class);
    }
}
