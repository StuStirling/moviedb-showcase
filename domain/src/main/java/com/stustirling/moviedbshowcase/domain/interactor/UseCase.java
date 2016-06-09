package com.stustirling.moviedbshowcase.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public abstract class UseCase {

    private final Scheduler threadExecutor;
    private final Scheduler postExecutionThread;

    public UseCase( Scheduler threadExecutor,
                    Scheduler postExecutionThread) {
        super();
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable buildUseCaseObservable();

    @SuppressWarnings("unchecked")
    public void execute( Subscriber useCaseSubscriber ) {
        buildUseCaseObservable()
                .subscribeOn(threadExecutor)
                .observeOn(postExecutionThread)
                .subscribe(useCaseSubscriber);
    }

}
