package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBService {

    Observable<List<MovieSummaryEntity>> getPopularMovies();

    Observable<MovieDetailsEntity> getMovieDetails(int id);

    Observable<List<TVShowEntity>> getPopularTVShows();

    Observable<List<PersonEntity>> getPopularPeople();
}
