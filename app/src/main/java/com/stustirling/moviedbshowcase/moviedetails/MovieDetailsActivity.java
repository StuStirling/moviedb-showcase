package com.stustirling.moviedbshowcase.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_SUMMARY_MODEL = "com.stustirling.moviedbshowcase.movie_summary_model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }

    public static Intent launchIntent(Context context, MovieSummaryModel movieSummaryModel) {
        Intent intent = new Intent( context, MovieDetailsActivity.class );
        intent.putExtra(MOVIE_SUMMARY_MODEL, movieSummaryModel);
        return intent;
    }
}
