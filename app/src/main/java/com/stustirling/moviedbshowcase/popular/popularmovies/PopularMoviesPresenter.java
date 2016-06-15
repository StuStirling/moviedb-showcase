package com.stustirling.moviedbshowcase.popular.popularmovies;

import com.stustirling.moviedbshowcase.ConnectionTester;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 06/06/16.
 */
public class PopularMoviesPresenter extends PopularPresenter {

    private final MovieSummaryModelMapper movieSummaryMapper;

    @Inject
    public PopularMoviesPresenter(@Named("top20PopularMovies") UseCase useCase, ConnectionTester connectionTester, MovieSummaryModelMapper mapper ) {
        super(useCase,connectionTester);
        this.movieSummaryMapper = mapper;
    }

    private final Subscriber<List<MovieSummary>> subscriber = new Subscriber<List<MovieSummary>>() {
        @Override
        public void onCompleted() {
            onCompletedCalled();
        }

        @Override
        public void onError(Throwable e) {
            onErrorCalled(e);
        }

        @Override
        public void onNext(List<MovieSummary> movieSummaries) {
            PopularMoviesPresenter.this.modelItems.addAll(movieSummaryMapper.transform(movieSummaries));
        }
    };

    @Override
    protected String getQueryableString(PopularModel model) {
        return ((MovieSummaryModel)model).getTitle();
    }

    @Override
    protected Subscriber getSubscriber() {
        return subscriber;
    }

}
