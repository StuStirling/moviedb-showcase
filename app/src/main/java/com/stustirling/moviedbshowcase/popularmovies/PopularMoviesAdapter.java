package com.stustirling.moviedbshowcase.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieSummary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cvpm_title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private List<MovieSummary> popularMovies;

    public PopularMoviesAdapter() {
        super();
        this.popularMovies = new ArrayList<>();
    }

    public void setPopularMovies(List<MovieSummary> popularMovies ) {
        this.popularMovies = popularMovies;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieSummary movie = popularMovies.get(position);
        holder.title.setText(movie.getTitle());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_popular_movie,parent,false);
        return new ViewHolder(itemView);
    }

}
