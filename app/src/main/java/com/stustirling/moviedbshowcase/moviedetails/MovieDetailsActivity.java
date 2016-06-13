package com.stustirling.moviedbshowcase.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.BaseActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity {

    public static final String MOVIE_SUMMARY_MODEL = "com.stustirling.moviedbshowcase.movie_summary_model";

    private MovieSummaryModel movie;

    @BindView(R.id.tb_mda_toolbar) Toolbar toolbar;
    @BindView(R.id.tv_mda_title) TextView title;
    @BindView(R.id.tv_mda_overview) TextView overview;
    @BindView(R.id.tv_mda_rating) TextView rating;
    @BindView(R.id.iv_mda_backdrop) ImageView backdrop;

    public static Intent launchIntent(Context context, MovieSummaryModel movieSummaryModel) {
        if ( movieSummaryModel == null )
            throw new IllegalArgumentException("MovieSummaryModel must not be null");

        Intent intent = new Intent( context, MovieDetailsActivity.class );
        intent.putExtra(MOVIE_SUMMARY_MODEL, movieSummaryModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        MovieSummaryModel model = getIntent().getParcelableExtra(MOVIE_SUMMARY_MODEL);
        if ( model == null )
            throw new IllegalArgumentException("Must pass a valid MovieSummaryModel object with intent.");
        this.movie = model;
        setupUI();
    }

    private void setupUI() {
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        rating.setText(String.format(Locale.getDefault(),"%.1f",movie.getRating()));

        getSupportActionBar().setTitle(movie.getTitle());

        Picasso.with(this)
                .load(Uri.parse("http://image.tmdb.org/t/p//w780/hNFMawyNDWZKKHU4GYCBz1krsRM.jpg"))
                .into(backdrop);

    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initActivity(Bundle savedInstanceState) {

    }
}
