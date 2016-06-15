package com.stustirling.moviedbshowcase.popular.tvshows;

import com.stustirling.moviedbshowcase.ConnectionTester;
import com.stustirling.moviedbshowcase.domain.TVShow;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.model.mapper.TVShowModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class PopularTVShowsPresenter extends PopularPresenter{

    private final TVShowModelMapper mapper;

    @Inject
    public PopularTVShowsPresenter(@Named("top20PopularTVShows") UseCase useCase, ConnectionTester connectionTester, TVShowModelMapper mapper ) {
        super(useCase,connectionTester);
        this.mapper = mapper;
    }

    private final Subscriber<List<TVShow>> subscriber = new Subscriber<List<TVShow>>() {
        @Override
        public void onCompleted() {
            onCompletedCalled();
        }

        @Override
        public void onError(Throwable e) {
            onErrorCalled(e);
        }

        @Override
        public void onNext(List<TVShow> tvShows) {
            modelItems.addAll(mapper.transform(tvShows));
        }
    };

    @Override
    protected String getQueryableString(PopularModel model) {
        return ((TVShowModel)model).getName();
    }

    @Override
    protected Subscriber getSubscriber() {
        return subscriber;
    }

}
