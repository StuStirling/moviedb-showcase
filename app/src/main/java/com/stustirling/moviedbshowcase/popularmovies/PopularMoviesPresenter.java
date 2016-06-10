package com.stustirling.moviedbshowcase.popularmovies;

import android.util.Log;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesPresenter {

    private final GetTop20PopularMovies getPopularMoviesUseCase;
    private PopularMoviesView popularMoviesView;

    @Inject
    public PopularMoviesPresenter(GetTop20PopularMovies useCase) {
        super();
        this.getPopularMoviesUseCase = useCase;
    }

    public void init( PopularMoviesView popularMoviesView ) {
        this.popularMoviesView = popularMoviesView;
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
            Log.e("PMP",e.getMessage());
        }

        @Override
        public void onNext(List<MovieSummary> movieSummaries) {
            popularMoviesView.refreshMovieSummaries(movieSummaries);
            Log.d("PMP","Received list of summaries: "+movieSummaries);
        }
    };

}
