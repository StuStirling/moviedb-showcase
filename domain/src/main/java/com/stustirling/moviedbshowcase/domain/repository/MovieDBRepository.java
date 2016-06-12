package com.stustirling.moviedbshowcase.domain.repository;

import com.stustirling.moviedbshowcase.domain.MovieSummary;

import java.util.List;

import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBRepository {

    Observable<List<MovieSummary>> getPopularMovies();
}
