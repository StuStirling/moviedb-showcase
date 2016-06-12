package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBService {

    Observable<List<MovieSummaryEntity>> getPopularMovies();

    Observable<MovieDetailsEntity> getMovieDetails(int id);
}
