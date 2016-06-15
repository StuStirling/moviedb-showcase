package com.stustirling.moviedbshowcase.popularpeople;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.PopularAdapter;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.PersonModel;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularPeopleAdapter extends PopularAdapter {

    public PopularPeopleAdapter(ModelSelectedListener modelSelectedListener) {
        super(modelSelectedListener);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setTransitionNames(Context context, PopularAdapter.ViewHolder holder) {

    }

    @Override
    protected void displayModelInView(PopularAdapter.ViewHolder holder, int position) {
        PersonModel person = (PersonModel) modelObjects.get(position);
        holder.bottomContainer.setVisibility(View.GONE);
        holder.setTitle(person.getName());

        if ( person.getKnownFor().size() > 0 ) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < person.getKnownFor().size(); i++ ) {
                MovieSummaryModel movie = person.getKnownFor().get(i);
                builder.append(movie.getTitle());
                if ( i < person.getKnownFor().size() - 1 )
                    builder.append(", ");
            }
            holder.setOverview(builder.toString());
        }

        if ( person.getProfilePath() != null ) {
            Picasso.with(holder.getPoster().getContext())
                    .load(Uri.parse(person.getProfilePath()))
                    .into(holder.getPoster());
        }
    }
}
