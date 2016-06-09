package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

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
        return movieDBApi.getPopularMovies();
    }
}
