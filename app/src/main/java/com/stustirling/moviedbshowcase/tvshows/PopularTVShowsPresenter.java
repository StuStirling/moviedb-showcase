package com.stustirling.moviedbshowcase.tvshows;

import com.stustirling.moviedbshowcase.domain.TVShow;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.model.mapper.TVShowModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class PopularTVShowsPresenter {

    private final UseCase getTop20PopularTVShows;
    private final TVShowModelMapper mapper;
    private PopularTVShowsView view;
    private ArrayList<TVShow> tvShows;

    @Inject
    public PopularTVShowsPresenter(@Named("top20PopularTVShows") UseCase getTop20PopularTVShows, TVShowModelMapper mapper ) {
        this.getTop20PopularTVShows = getTop20PopularTVShows;
        this.mapper = mapper;
        this.tvShows = new ArrayList<>();
    }

    public void init(PopularTVShowsView view ) {
        this.view = view;
        this.view.loading(true);
        this.getTop20PopularTVShows.execute(subscriber);
    }


    private Subscriber<List<TVShow>> subscriber = new Subscriber<List<TVShow>>() {
        @Override
        public void onCompleted() {
            view.refreshTVShows(mapper.transform(tvShows));
            view.loading(false);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<TVShow> tvShows) {
            PopularTVShowsPresenter.this.tvShows.addAll(tvShows);
        }
    };



    public interface PopularTVShowsView {
        void loading(boolean loading);
        void refreshTVShows(List<TVShowModel> tvShows);
    }

}
