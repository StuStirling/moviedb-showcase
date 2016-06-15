package com.stustirling.moviedbshowcase.tvshows;

import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.TVShowModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularTVShowsAdapter extends RecyclerView.Adapter<PopularTVShowsAdapter.ViewHolder> {

    private TVShowClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cvpm_title) TextView title;
        @BindView(R.id.tv_cvpm_overview) TextView overview;
        @BindView(R.id.tv_cvpm_rating) TextView rating;
        @BindView(R.id.tv_cvpm_year) TextView year;
        @BindView(R.id.iv_cvpm_poster) ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.tvShowSelected( popularTVShows.get(getAdapterPosition()),poster,overview,rating);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                poster.setTransitionName(itemView.getContext().getString(R.string.transition_tvshow_poster));
//                overview.setTransitionName(itemView.getContext().getString(R.string.transition_tvshow_overview));
            }
        }
    }

    private List<TVShowModel> popularTVShows;

    public PopularTVShowsAdapter(TVShowClickListener clickListener ) {
        super();
        this.popularTVShows = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public void updatePopularTVShows(List<TVShowModel> popularTVShows) {
        this.popularTVShows = popularTVShows;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return popularTVShows.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TVShowModel tvShow = popularTVShows.get(position);
        holder.title.setText(tvShow.getName());
        holder.overview.setText(tvShow.getOverview());
        holder.rating.setText(String.format(Locale.getDefault(),"%.1f",tvShow.getVoteAvg()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(tvShow.getFirstAirDate());
        holder.year.setText(String.format(Locale.getDefault(),"%d",cal.get(Calendar.YEAR)));

        if ( tvShow.getPosterPath() != null ) {
            Picasso.with(holder.poster.getContext())
                    .load(Uri.parse(tvShow.getPosterPath()))
                    .into(holder.poster);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_popular_media,parent,false);
        return new ViewHolder(itemView);
    }

    interface TVShowClickListener {
        void tvShowSelected(TVShowModel model, ImageView poster, TextView overview, TextView rating);
    }

}
