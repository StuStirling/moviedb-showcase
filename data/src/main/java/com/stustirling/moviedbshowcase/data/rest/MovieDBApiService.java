package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.PopularMoviesResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieDBApiService implements MovieDBService {

    MovieDBApi movieDBApi;

    @Inject
    public MovieDBApiService(MovieDBApi movieDBApi ) {
        this.movieDBApi = movieDBApi;
    }

    @Override
    public Observable<List<MovieSummaryEntity>> getPopularMovies() {
        return movieDBApi.getPopularMovies(1)
                .map(new Func1<PopularMoviesResponse, List<MovieSummaryEntity>>() {
                    @Override
                    public List<MovieSummaryEntity> call(PopularMoviesResponse popularMoviesResponse) {
                        return popularMoviesResponse.results;
                    }
                });
    }
}
