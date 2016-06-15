package com.stustirling.moviedbshowcase.popular.popularpeople;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.popular.PopularAdapter;
import com.stustirling.moviedbshowcase.popular.PopularFragment;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularPeopleFragment extends PopularFragment {

    @Inject PopularPeoplePresenter presenter;
    @Inject Activity activity;

    protected void injectWithComponent(MovieDBComponent component) {
        component.inject(this);
    }

    @Override
    protected PopularAdapter getAdapter() {
        if ( adapter == null )
            adapter = new PopularPeopleAdapter(this);
        return adapter;
    }

    @Override
    protected PopularPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void launchModelDetailsActivity(PopularModel modelItem,ImageView poster) {

    }
}
