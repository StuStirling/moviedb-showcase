package com.stustirling.moviedbshowcase.popularmovies;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesPresenter {

    private final GetTop20PopularMovies getPopularMoviesUseCase;
    private PopularMoviesView popularMoviesView;
    private List<MovieSummary> movieSummaries;

    @Inject
    public PopularMoviesPresenter(GetTop20PopularMovies useCase) {
        super();
        this.getPopularMoviesUseCase = useCase;
    }

    public void init( PopularMoviesView popularMoviesView ) {
        this.popularMoviesView = popularMoviesView;
        popularMoviesView.loading(true);
        this.movieSummaries = new ArrayList<>();
        getPopularMoviesUseCase.execute(subscriber);
    }

    Subscriber<List<MovieSummary>> subscriber = new Subscriber<List<MovieSummary>>() {
        @Override
        public void onCompleted() {
            popularMoviesView.refreshMovieSummaries(movieSummaries);
            popularMoviesView.loading(false);

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<MovieSummary> movieSummaries) {
            PopularMoviesPresenter.this.movieSummaries.addAll(movieSummaries);
        }
    };

}
