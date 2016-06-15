package com.stustirling.moviedbshowcase.popularmovies;

import android.util.Log;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesPresenter {

    private final UseCase getPopularMoviesUseCase;
    private final MovieSummaryModelMapper movieSummaryMapper;
    private PopularMoviesView popularMoviesView;
    private List<MovieSummary> movieSummaries;

    @Inject
    public PopularMoviesPresenter(@Named("top20PopularMovies") UseCase useCase, MovieSummaryModelMapper mapper ) {
        super();
        this.getPopularMoviesUseCase = useCase;
        this.movieSummaryMapper = mapper;
    }

    public void init( PopularMoviesView popularMoviesView ) {
        this.popularMoviesView = popularMoviesView;
        this.popularMoviesView.loading(true);
        this.movieSummaries = new ArrayList<>();
        this.getPopularMoviesUseCase.execute(subscriber);
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

    public List<MovieSummary> getRetrievedPopularMovies() {
        return movieSummaries;
    }

    public void filterMovies(String query) {
        final List<MovieSummaryModel> filteredMovies = new ArrayList<>();
        for ( MovieSummary movie : getRetrievedPopularMovies() ) {
            final String lowerCaseTitle = movie.getTitle().toLowerCase();
            if ( lowerCaseTitle.contains(query.toLowerCase()))
                filteredMovies.add(movieSummaryMapper.transform(movie));
        }
        popularMoviesView.showFilteredMovies(filteredMovies);
    }

    public interface PopularMoviesView {
        void loading(boolean loading);
        void refreshMovieSummaries(List<MovieSummaryModel> movieSummaries);

        void showFilteredMovies(List<MovieSummaryModel> filteredMovies);
    }

}
