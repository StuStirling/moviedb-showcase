package com.stustirling.moviedbshowcase.popularmovies;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesController {

    private final GetTop20PopularMovies getPopularMoviesUseCase;
    private final PopularMoviesView popularMoviesView;

    public PopularMoviesController(PopularMoviesView popularMoviesView, GetTop20PopularMovies useCase) {
        super();
        this.popularMoviesView = popularMoviesView;
        this.getPopularMoviesUseCase = useCase;
    }

    public void init() {
        popularMoviesView.loading(true);
        getPopularMoviesUseCase.execute(subscriber);
    }

    Subscriber<List<MovieSummary>> subscriber = new Subscriber<List<MovieSummary>>() {
        @Override
        public void onCompleted() {
            popularMoviesView.loading(false);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<MovieSummary> movieSummaries) {
            popularMoviesView.refreshMovieSummaries(movieSummaries);
        }
    };

}
