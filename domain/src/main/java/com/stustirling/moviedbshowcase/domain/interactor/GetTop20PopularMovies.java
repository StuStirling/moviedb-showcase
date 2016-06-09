package com.stustirling.moviedbshowcase.domain.interactor;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class GetTop20PopularMovies extends UseCase {

    private final MovieDBRepository movieRepo;

    @Inject
    public GetTop20PopularMovies(MovieDBRepository repo,
                                 Scheduler threadExecutor,
                                 Scheduler postExecutionThread ) {
        super(threadExecutor,postExecutionThread);
        this.movieRepo = repo;

    }

    @Override
    public Observable<List<MovieSummary>> buildUseCaseObservable() {
        return movieRepo.getPopularMovies(20);
    }


}
