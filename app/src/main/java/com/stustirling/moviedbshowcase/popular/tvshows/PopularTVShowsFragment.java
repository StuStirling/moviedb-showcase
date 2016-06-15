package com.stustirling.moviedbshowcase.popular.tvshows;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.popular.PopularAdapter;
import com.stustirling.moviedbshowcase.popular.PopularFragment;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;
import com.stustirling.moviedbshowcase.tvshowdetails.TVShowDetailsActivity;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularTVShowsFragment extends PopularFragment {

    @Inject PopularTVShowsPresenter presenter;
    @Inject Activity activity;


    protected void injectWithComponent(MovieDBComponent component) {
        component.inject(this);
    }

    @Override
    protected PopularAdapter getAdapter() {
        if ( adapter == null ) {
            adapter = new PopularTVShowsAdapter(this);
        }
        return adapter;
    }

    @Override
    protected PopularPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void launchModelDetailsActivity(PopularModel popularModel, ImageView poster ) {
        Intent intent = TVShowDetailsActivity.launchIntent(getContext(), (TVShowModel) popularModel);
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Pair<View,String> pair1 = Pair.create((View)poster,poster.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair1);
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
    }




}
