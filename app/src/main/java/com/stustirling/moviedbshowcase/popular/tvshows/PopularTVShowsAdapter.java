package com.stustirling.moviedbshowcase.popular.tvshows;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.MovieGenre;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.TVShowGenre;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.popular.PopularAdapter;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularTVShowsAdapter extends PopularAdapter {

    public PopularTVShowsAdapter(ModelSelectedListener modelSelectedListener) {
        super(modelSelectedListener);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setTransitionNames(Context context,PopularAdapter.ViewHolder holder) {
        holder.getPoster().setTransitionName(context.getString(R.string.transition_tvshow_poster));
    }

    @Override
    protected void displayModelInView(PopularAdapter.ViewHolder holder, int position) {
        TVShowModel tvShow = (TVShowModel) modelObjects.get(position);
        holder.setTitle(tvShow.getName());
        holder.setOverview(tvShow.getOverview());
        holder.setRating(tvShow.getVoteAvg());
        holder.setYear(tvShow.getFirstAirDate());

        int[] genres = tvShow.getGenreIds();
        if ( genres != null && genres.length > 0 ) {
            StringBuilder builder = new StringBuilder();
            for ( int i = 0; i < genres.length; i++ ) {
                String genreName = TVShowGenre.getName(genres[i]);
                if ( genreName == null )
                    genreName = MovieGenre.getName(genres[i]);
                builder.append(genreName);
                if ( i < genres.length - 1 )
                    builder.append(", ");
            }
            holder.genres.setText(builder.toString());
        }

        if ( tvShow.getPosterPath() != null ) {
            Picasso.with(holder.getPoster().getContext())
                    .load(Uri.parse(tvShow.getPosterPath()))
                    .into(holder.getPoster());
        }
    }

}
