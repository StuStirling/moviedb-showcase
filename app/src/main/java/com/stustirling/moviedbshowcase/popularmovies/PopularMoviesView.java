package com.stustirling.moviedbshowcase.popularmovies;

import com.stustirling.moviedbshowcase.domain.MovieSummary;

import java.util.List;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public interface PopularMoviesView {
    void loading(boolean loading);
    void refreshMovieSummaries(List<MovieSummary> movieSummaries);
}
