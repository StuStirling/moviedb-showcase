package com.stustirling.moviedbshowcase.internal.di.modules;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.tvshowdetails.TVShowDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Module
public class TVShowDetailsModule {


    private final TVShowModel tvShow;
    private final TVShowDetailsPresenter.TVShowDetailsView view;

    public TVShowDetailsModule(TVShowModel tvShow, TVShowDetailsPresenter.TVShowDetailsView view ) {
        this.tvShow = tvShow;
        this.view = view;
    }

    @Provides @PerActivity
    TVShowModel provideTVShow() {
        return tvShow;
    }

    @Provides @PerActivity
    TVShowDetailsPresenter.TVShowDetailsView provideTVShowDetailsView() {
        return view;
    }

}
