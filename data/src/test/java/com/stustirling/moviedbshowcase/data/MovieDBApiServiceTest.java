package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.data.rest.MovieDBApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieDBApiServiceTest {

    @Mock MovieDBApi mockApi;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPopularMoviesRetrieval() {
        MovieDBApiService apiService = new MovieDBApiService(mockApi);
        apiService.getPopularMovies();
        verify(mockApi,times(1)).getPopularMovies();
    }

}
