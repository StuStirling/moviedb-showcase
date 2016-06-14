package com.stustirling.moviedbshowcase.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.BaseActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.internal.di.components.DaggerMovieDetailsComponent;
import com.stustirling.moviedbshowcase.internal.di.components.MovieDetailsComponent;
import com.stustirling.moviedbshowcase.internal.di.modules.MovieDetailsModule;
import com.stustirling.moviedbshowcase.model.MovieDetailsModel;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsPresenter.MovieDetailsView {

    public static final String MOVIE_SUMMARY_MODEL = "com.stustirling.moviedbshowcase.movie_summary_model";

    private MovieSummaryModel movie;

    @BindView(R.id.tb_mda_toolbar) Toolbar toolbar;
    @BindView(R.id.tv_mda_overview) TextView overview;
    @BindView(R.id.tv_mda_rating) TextView rating;
    @BindView(R.id.iv_mda_backdrop) ImageView backdrop;
    @BindView(R.id.iv_mda_poster) ImageView poster;

    @BindView(R.id.tv_mda_tagline) TextView tagline;
    @BindView(R.id.tv_mda_revenue_value) TextView revenue;
    @BindView(R.id.tv_mda_budget_value) TextView budget;
    @BindView(R.id.tv_mda_vote_count) TextView voteCount;
    @BindView(R.id.btn_mda_homepage) Button homepage;
    @BindView(R.id.btn_mda_imdb) Button imdb;
    @BindView(R.id.tv_mda_runtime_value) TextView runtime;
    @BindView(R.id.tv_mda_released_value) TextView released;

    @Inject MovieDetailsPresenter presenter;
    private MovieDetailsComponent movieDetailsComponent;

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

        setupUI();
    }

    private void setupUI() {
        setSupportActionBar(toolbar);

        rating.setText(String.format(Locale.getDefault(),"%.1f/10",movie.getRating()));
        overview.setText(movie.getOverview());

        Log.d("PMP","Released: "+movie.getReleaseDate().toString());

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(movie.getReleaseDate());
//
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int month = cal.get(Calendar.MONTH)+1;
//        int year = cal.get(Calendar.YEAR);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = "%02d/%02d/%04d";
//        released.setText(String.format(Locale.getDefault(),dateFormat,day,month,year));
        released.setText(format.format(movie.getReleaseDate()));

        if ( movie.getPosterPath() != null ) {
            Picasso.with(this)
                    .load(Uri.parse(movie.getPosterPath()))
                    .into(poster);
        }

        getSupportActionBar().setTitle(movie.getTitle());

        presenter.init(this);
    }

    @Override
    protected void initInjector() {
        this.movieDetailsComponent = DaggerMovieDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .movieDetailsModule(new MovieDetailsModule(movie.getId()))
                .build();
        this.movieDetailsComponent.inject(this);
    }

    @Override
    protected void initActivity(Bundle savedInstanceState) {
        MovieSummaryModel model = getIntent().getParcelableExtra(MOVIE_SUMMARY_MODEL);
        if ( model == null )
            throw new IllegalArgumentException("Must pass a valid MovieSummaryModel object with intent.");
        this.movie = model;

        initInjector();
    }


    @Override
    public void loading(boolean loading) {

    }

    @Override
    public void displayMovieDetails(final MovieDetailsModel movieDetails) {
        if ( movieDetails == null )
            return;

        if ( movieDetails.getRevenue() > 0 )
            revenue.setText(String.format(
                    "$%s", NumberFormat.getIntegerInstance().format(movieDetails.getRevenue())));
        if ( movieDetails.getBudget() > 0 )
            budget.setText(String.format(
                    "$%s", NumberFormat.getIntegerInstance().format(movieDetails.getBudget())));

        voteCount.setText(String.format(getString(R.string.vote_count_format),movieDetails.getVoteCount()));

        if ( movieDetails.getTagline() != null ) {
            tagline.setText(movieDetails.getTagline());
            tagline.setVisibility(View.VISIBLE);
        }

        if ( movieDetails.getBackdropPath() != null ) {
            Picasso.with(this)
                    .load(Uri.parse(movieDetails.getBackdropPath()))
                    .into(backdrop);
        }

        if ( movieDetails.getHomepage() != null ) {
            homepage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(movieDetails.getHomepage()));
                    startActivity(intent);
                }
            });
            homepage.setVisibility(View.VISIBLE);
        }

        if ( movieDetails.getImdbId() != null ) {
            imdb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.imdb.com/title/"+movieDetails.getImdbId()));
                    startActivity(intent);
                }
            });
            imdb.setVisibility(View.VISIBLE);
        }

        runtime.setText(String.format(getString(R.string.runtime_format),movieDetails.getRuntime()));
    }
}
