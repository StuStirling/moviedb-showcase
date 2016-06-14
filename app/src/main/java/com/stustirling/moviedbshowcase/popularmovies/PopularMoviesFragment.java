package com.stustirling.moviedbshowcase.popularmovies;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stustirling.moviedbshowcase.BaseFragment;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.moviedetails.MovieDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends BaseFragment implements PopularMoviesPresenter.PopularMoviesView,PopularMoviesAdapter.MovieSummaryClickListener {

    private Unbinder unbinder;
    @BindView(R.id.rv_pmf_movies) RecyclerView recyclerView;
    @BindView(R.id.srl_pmf_refresh) SwipeRefreshLayout refreshLayout;

    private PopularMoviesAdapter adapter;

    @Inject
    PopularMoviesPresenter presenter;
    @Inject Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this,v);

        setupUI();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        if ( refreshLayout.isRefreshing() )
            animateSwipeRefresh(false);
    }

    private void setupUI() {
        refreshLayout.setEnabled(false);
        adapter = new PopularMoviesAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void animateSwipeRefresh(final boolean animate) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(animate);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        getComponent(MovieDBComponent.class).inject(this);
        presenter.init(this);
    }

    @Override
    public void refreshMovieSummaries(List<MovieSummaryModel> movieSummaries) {
        adapter.updatePopularMovies(movieSummaries);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loading(boolean loading) {
        animateSwipeRefresh(loading);
    }

    @Override
    public void movieSummarySelected(MovieSummaryModel movieSummaryModel, ImageView poster, TextView overview,TextView rating) {
        Intent intent = MovieDetailsActivity.launchIntent(getContext(),movieSummaryModel);

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Pair<View,String> pair1 = Pair.create((View)poster,poster.getTransitionName());
            Pair<View,String> pair2 = Pair.create((View)overview,overview.getTransitionName());
//            Pair<View,String> pair3 = Pair.create((View)rating,rating.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair1,pair2);
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
    }


}
