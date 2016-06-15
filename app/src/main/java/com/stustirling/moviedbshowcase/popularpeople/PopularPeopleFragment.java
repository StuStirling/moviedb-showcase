package com.stustirling.moviedbshowcase.popularpeople;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stustirling.moviedbshowcase.BaseFragment;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.MovieDBComponent;
import com.stustirling.moviedbshowcase.model.PersonModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularPeopleFragment extends BaseFragment implements PopularPeoplePresenter.PopularPeopleView,PopularPeopleAdapter.PersonClickListener {

    private Unbinder unbinder;
    @BindView(R.id.rv_ppf_people) RecyclerView recyclerView;
    @BindView(R.id.srl_ppf_refresh) SwipeRefreshLayout refreshLayout;

    private PopularPeopleAdapter adapter;

    @Inject
    PopularPeoplePresenter presenter;
    @Inject Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_people, container, false);
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
        adapter = new PopularPeopleAdapter(this);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refreshPopularPeople(List<PersonModel> people) {
        adapter.updatePopularPeople(people);
    }

    @Override
    public void loading(boolean loading) {
        animateSwipeRefresh(loading);
    }

    @Override
    public void personSelected(PersonModel person) {

    }


}
