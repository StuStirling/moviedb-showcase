package com.stustirling.moviedbshowcase.popular.popularmovies;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.popular.PopularAdapter;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularMoviesAdapter extends PopularAdapter {

    public PopularMoviesAdapter(ModelSelectedListener modelSelectedListener) {
        super(modelSelectedListener);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setTransitionNames(Context context, PopularAdapter.ViewHolder holder) {
        holder.poster.setTransitionName(context.getString(R.string.transition_movie_poster));
    }

    @Override
    protected void displayModelInView(PopularAdapter.ViewHolder holder, int position) {
        MovieSummaryModel movie = (MovieSummaryModel) modelObjects.get(position);
        holder.setTitle(movie.getTitle());
        holder.setOverview(movie.getOverview());
        holder.setRating(movie.getRating());
        holder.setYear(movie.getReleaseDate());

        if ( movie.getPosterPath() != null ) {
            Picasso.with(holder.getPoster().getContext())
                    .load(Uri.parse(movie.getPosterPath()))
                    .into(holder.getPoster());
        }
    }

}
