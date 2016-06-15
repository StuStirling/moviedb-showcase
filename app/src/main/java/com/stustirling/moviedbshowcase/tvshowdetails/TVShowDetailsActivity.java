package com.stustirling.moviedbshowcase.tvshowdetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.BaseActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.DaggerTVShowDetailsComponent;
import com.stustirling.moviedbshowcase.internal.di.components.TVShowDetailsComponent;
import com.stustirling.moviedbshowcase.internal.di.modules.TVShowDetailsModule;
import com.stustirling.moviedbshowcase.model.TVShowModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVShowDetailsActivity extends BaseActivity implements TVShowDetailsPresenter.TVShowDetailsView{

    public static final String TV_SHOW_MODEL = "com.stustirling.moviedbshowcase.tv_show_model";

    private TVShowModel tvShow;

    @BindView(R.id.tb_tsda_toolbar) Toolbar toolbar;
    @BindView(R.id.tv_tsda_overview) TextView overview;
    @BindView(R.id.tv_tsda_rating) TextView rating;
    @BindView(R.id.iv_tsda_backdrop) ImageView backdrop;
    @BindView(R.id.iv_tsda_poster) ImageView poster;
    @BindView(R.id.tv_tsda_vote_count) TextView voteCount;
    @BindView(R.id.tv_tsda_first_aired_value) TextView firstAired;


    @Inject
    TVShowDetailsPresenter presenter;
    private TVShowDetailsComponent tvShowDetailsComponent;

    public static Intent launchIntent(Context context, @NonNull TVShowModel tvShow) {
        Intent intent = new Intent( context, TVShowDetailsActivity.class );
        intent.putExtra(TV_SHOW_MODEL, tvShow);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_details);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI() {
        setSupportActionBar(toolbar);

        if ( tvShow.getPosterPath() != null ) {
            Picasso.with(this)
                    .load(Uri.parse(tvShow.getPosterPath()))
                    .into(poster);
        }

        if ( tvShow.getBackdropPath() != null ) {
            Picasso.with(this)
                    .load(Uri.parse(tvShow.getBackdropPath()))
                    .into(backdrop);
        }

        presenter.init();
    }

    @Override
    protected void initInjector() {
        this.tvShowDetailsComponent = DaggerTVShowDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .tVShowDetailsModule( new TVShowDetailsModule(tvShow,this))
                .build();
        this.tvShowDetailsComponent.inject(this);
    }

    @Override
    protected void initActivity(Bundle savedInstanceState) {
        TVShowModel model = getIntent().getParcelableExtra(TV_SHOW_MODEL);
        if ( model == null )
            throw new IllegalArgumentException("Must pass a valid TVShowModel object with intent.");
        this.tvShow = model;

        initInjector();
    }


    @Override
    public void displayTVShow(final TVShowModel tvShow ) {
        if ( tvShow == null )
            return;

        getSupportActionBar().setTitle(tvShow.getName());

        rating.setText(String.format(Locale.getDefault(),"%.1f/10", tvShow.getVoteAvg()));
        overview.setText(tvShow.getOverview());
        voteCount.setText(String.format(getString(R.string.vote_count_format),tvShow.getVoteCount()));

        if ( tvShow.getFirstAirDate() != null ) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            firstAired.setText(format.format(tvShow.getFirstAirDate()));
        }

    }
}
