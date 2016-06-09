package com.stustirling.moviedbshowcase.popularmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieSummary;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularMoviesFragment extends Fragment implements PopularMoviesView {

    private Unbinder unbinder;
    @BindView(R.id.rv_mf_content)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject PopularMoviesController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this,v);

        return v;
    }

    private void init() {

    }

    @Override
    public void refreshMovieSummaries(List<MovieSummary> movieSummaries) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loading(boolean loading) {

    }


}
