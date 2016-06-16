package com.stustirling.moviedbshowcase.internal.di.components;

import com.stustirling.moviedbshowcase.internal.di.PerActivity;
import com.stustirling.moviedbshowcase.internal.di.modules.ActivityModule;
import com.stustirling.moviedbshowcase.internal.di.modules.TVShowDetailsModule;
import com.stustirling.moviedbshowcase.details.tvshow.TVShowDetailsActivity;

import dagger.Component;

/**
 * Created by Stu Stirling on 13/06/16.
 */
@PerActivity
@Component(dependencies=ApplicationComponent.class,modules={ActivityModule.class,TVShowDetailsModule.class})
public interface TVShowDetailsComponent {
    void inject(TVShowDetailsActivity tvShowDetailsActivity);
}
