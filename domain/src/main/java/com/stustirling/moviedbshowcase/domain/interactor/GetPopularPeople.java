package com.stustirling.moviedbshowcase.domain.interactor;

import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class GetPopularPeople extends UseCase {

    private final MovieDBRepository movieRepo;

    @Inject
    public GetPopularPeople(MovieDBRepository repo,
                            @Named("threadExecutor") Scheduler threadExecutor,
                            @Named("postExecutionThread") Scheduler postExecutionThread ) {
        super(threadExecutor,postExecutionThread);
        this.movieRepo = repo;

    }

    @Override
    public Observable<List<Person>> buildUseCaseObservable() {
        return movieRepo.getPopularPeople();
    }


}
