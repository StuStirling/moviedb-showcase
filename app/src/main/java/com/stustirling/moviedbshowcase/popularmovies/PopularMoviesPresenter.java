package com.stustirling.moviedbshowcase.popularmovies;

import android.util.Log;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesPresenter {

    private final GetTop20PopularMovies getPopularMoviesUseCase;
    private final MovieSummaryModelMapper movieSummaryMapper;
    private PopularMoviesView popularMoviesView;
    private List<MovieSummary> movieSummaries;

    @Inject
    public PopularMoviesPresenter(GetTop20PopularMovies useCase, MovieSummaryModelMapper mapper ) {
        super();
        this.getPopularMoviesUseCase = useCase;
        this.movieSummaryMapper = mapper;
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
            popularMoviesView.refreshMovieSummaries(movieSummaryMapper.transform(movieSummaries));
            popularMoviesView.loading(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("PMP",e.getMessage());
        }

        @Override
        public void onNext(List<MovieSummary> movieSummaries) {
            PopularMoviesPresenter.this.movieSummaries.addAll(movieSummaries);
        }
    };

    public interface PopularMoviesView {
        void loading(boolean loading);
        void refreshMovieSummaries(List<MovieSummaryModel> movieSummaries);
    }

}
