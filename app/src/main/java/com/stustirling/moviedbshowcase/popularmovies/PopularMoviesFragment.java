package com.stustirling.moviedbshowcase.popularmovies;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stustirling.moviedbshowcase.BaseFragment;
import com.stustirling.moviedbshowcase.PopularAdapter;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.PopularModel;
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
public class PopularMoviesFragment extends BaseFragment implements PopularMoviesPresenter.PopularMoviesView,PopularAdapter.ModelSelectedListener, SearchView.OnQueryTextListener {

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

        setHasOptionsMenu(true);

        setupUI();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        if ( refreshLayout.isRefreshing() )
            animateSwipeRefresh(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
    }

    private void setupUI() {
        refreshLayout.setEnabled(false);
        adapter = new PopularMoviesAdapter(this);
        adapter.setHasStableIds(true);
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
        adapter.updateModel(movieSummaries);
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
    public void modelItemSelected(PopularModel popularModel, ImageView poster) {
        Intent intent = MovieDetailsActivity.launchIntent(getContext(), (MovieSummaryModel) popularModel);

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            Pair<View,String> pair1 = Pair.create((View)poster,poster.getTransitionName());
//            Pair<View,String> pair2 = Pair.create((View)overview,overview.getTransitionName());
//            Pair<View,String> pair3 = Pair.create((View)rating,rating.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair1);
            startActivity(intent,options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showFilteredMovies(List<MovieSummaryModel> filteredMovies) {
        adapter.updateModel(filteredMovies);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        presenter.filterMovies(newText);
        return true;
    }


}
