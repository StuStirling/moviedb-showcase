package com.stustirling.moviedbshowcase.moviedetails;

import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.interactor.GetMovieDetails;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class MovieDetailsPresenter {

    private final GetMovieDetails getMovieDetails;
    private MovieDetailsView movieDetailsView;
    private MovieDetails movieDetails;

    @Inject
    public MovieDetailsPresenter( GetMovieDetails getMovieDetails ) {
        this.getMovieDetails = getMovieDetails;
    }

    public void init( MovieDetailsView movieDetailsView ) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsView.loading( true);
        this.getMovieDetails.execute(subscriber);
    }

    Subscriber<MovieDetails> subscriber = new Subscriber<MovieDetails>() {


        @Override
        public void onCompleted() {
            movieDetailsView.displayMovieDetails(movieDetails);
            movieDetailsView.loading(false);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(MovieDetails movieDetails) {
            MovieDetailsPresenter.this.movieDetails = movieDetails;
        }
    };

    public interface MovieDetailsView {
        void loading( boolean loading );
        void displayMovieDetails(MovieDetails movieDetails);
    }
}
