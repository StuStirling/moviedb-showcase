package com.stustirling.moviedbshowcase.domain.repository;

import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.TVShow;

import java.util.List;

import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBRepository {

    Observable<List<MovieSummary>> getPopularMovies();
    Observable<MovieDetails> getMovieDetails(int id);

    Observable<List<TVShow>> getPopularTVShows();
}
