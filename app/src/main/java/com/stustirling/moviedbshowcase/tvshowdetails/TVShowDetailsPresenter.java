package com.stustirling.moviedbshowcase.tvshowdetails;

import com.stustirling.moviedbshowcase.model.TVShowModel;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class TVShowDetailsPresenter {

    private final TVShowModel tvShow;
    private TVShowDetailsView view;

    @Inject
    public TVShowDetailsPresenter( TVShowModel tvShowModel, TVShowDetailsView view ) {
        this.tvShow = tvShowModel;
        this.view = view;
    }

    public void init() {
        this.view.displayTVShow(tvShow);
    }

    public interface TVShowDetailsView {
        void displayTVShow(TVShowModel tvShow);
    }
}
