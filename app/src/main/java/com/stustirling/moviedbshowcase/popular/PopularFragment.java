package com.stustirling.moviedbshowcase.popular;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.PopularModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public abstract class PopularFragment extends BaseFragment implements PopularPresenter.PopularView,PopularAdapter.ModelSelectedListener, SearchView.OnQueryTextListener {

    private Unbinder unbinder;
    @BindView(R.id.rv_pmif_items) RecyclerView recyclerView;
    @BindView(R.id.srl_pmif_refresh) SwipeRefreshLayout refreshLayout;
    protected PopularAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popular_items, container, false);
        unbinder = ButterKnife.bind(this,v);

        setHasOptionsMenu(true);

        setupUI();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if ( refreshLayout.isRefreshing() )
            animateSwipeRefresh(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ( getPresenter() != null )
            getPresenter().filterItems("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void init() {
        injectWithComponent(getComponent(MovieDBComponent.class));
        getPresenter().init(this);
    }

    protected abstract void injectWithComponent(MovieDBComponent component);

    private void setupUI() {
        refreshLayout.setEnabled(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(getAdapter());
    }

    protected void animateSwipeRefresh(final boolean animate) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(animate);
            }
        });
    }

    protected abstract PopularPresenter getPresenter();
    protected abstract PopularAdapter getAdapter();

    @Override
    public void showFilteredModelItems(List<? extends PopularModel> filteredMovies) {
        getAdapter().updateModel(filteredMovies);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void refreshModelItems(List<? extends PopularModel> items) {
        getAdapter().updateModel(items);
    }

    @Override
    public void loading(boolean loading) {
        animateSwipeRefresh(loading);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getPresenter().filterItems(newText);
        return true;
    }

    @Override
    public void modelItemSelected(PopularModel popularModel, ImageView poster) {
        launchModelDetailsActivity(popularModel,poster);
    }

    protected abstract void launchModelDetailsActivity(PopularModel modelItem,ImageView poster);

}
