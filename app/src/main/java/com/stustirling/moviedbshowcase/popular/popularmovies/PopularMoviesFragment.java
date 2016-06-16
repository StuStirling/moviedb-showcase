package com.stustirling.moviedbshowcase.popular.popularmovies;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.details.movie.MovieDetailsActivity;
import com.stustirling.moviedbshowcase.popular.PopularAdapter;
import com.stustirling.moviedbshowcase.popular.PopularFragment;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends PopularFragment {

    @Inject Activity activity;
    @Inject PopularMoviesPresenter presenter;

    protected void injectWithComponent(MovieDBComponent component) {
        component.inject(this);
    }

    @Override
    protected PopularAdapter getAdapter() {
        if ( adapter == null ) {
            adapter = new PopularMoviesAdapter(this);
        }
        return adapter;
    }

    @Override
    protected PopularPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void launchModelDetailsActivity(PopularModel modelItem,ImageView poster) {
        Intent intent = MovieDetailsActivity.launchIntent(getContext(), (MovieSummaryModel) modelItem);
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Pair<View,String> pair1 = Pair.create((View)poster,poster.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair1);
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
    }

}
