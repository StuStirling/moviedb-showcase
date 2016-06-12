package com.stustirling.moviedbshowcase.domain.interactor;

import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class GetMovieDetails extends UseCase {

    private final MovieDBRepository movieRepo;
    private final int movieId;

    @Inject
    public GetMovieDetails(MovieDBRepository repo,
                           @Named("threadExecutor") Scheduler threadExecutor,
                           @Named("postExecutionThread") Scheduler postExecutionThread,
                           @Named("movieId") int movieId ) {
        super(threadExecutor,postExecutionThread);
        this.movieRepo = repo;
        this.movieId = movieId;
    }

    @Override
    public Observable<MovieDetails> buildUseCaseObservable() {
        return movieRepo.getMovieDetails(movieId);
    }


}
