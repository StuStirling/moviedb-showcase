package com.stustirling.moviedbshowcase.popularmovies;

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
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

    private MovieSummaryClickListener clickListener;

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
                    clickListener.movieSummarySelected( popularMovies.get(getAdapterPosition()),poster,overview,rating);
                }
            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                poster.setTransitionName(itemView.getContext().getString(R.string.transition_movie_poster));
//                overview.setTransitionName(itemView.getContext().getString(R.string.transition_movie_overview));
            }
        }

    }

    private List<MovieSummaryModel> popularMovies;

    public PopularMoviesAdapter(MovieSummaryClickListener clickListener ) {
        super();
        this.popularMovies = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public void updatePopularMovies(List<MovieSummaryModel> popularMovies ) {
        this.popularMovies = popularMovies;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieSummaryModel movie = popularMovies.get(position);
        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        holder.rating.setText(String.format(Locale.getDefault(),"%.1f",movie.getRating()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(movie.getReleaseDate());
        holder.year.setText(String.format(Locale.getDefault(),"%d",cal.get(Calendar.YEAR)));

        if ( movie.getPosterPath() != null ) {
            Picasso.with(holder.poster.getContext())
                    .load(Uri.parse(movie.getPosterPath()))
                    .into(holder.poster);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_popular_media,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public long getItemId(int position) {
        return popularMovies.get(position).getId();
    }

    /*public void animateTo(List<MovieSummaryModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<MovieSummaryModel> newModels) {
        for (int i = popularMovies.size() - 1; i >= 0; i--) {
            final MovieSummaryModel model = popularMovies.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<MovieSummaryModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final MovieSummaryModel model = newModels.get(i);
            if (!popularMovies.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<MovieSummaryModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final MovieSummaryModel model = newModels.get(toPosition);
            final int fromPosition = popularMovies.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public MovieSummaryModel removeItem(int position) {
        final MovieSummaryModel  model = popularMovies.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, MovieSummaryModel model) {
        popularMovies.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final MovieSummaryModel model = popularMovies.remove(fromPosition);
        popularMovies.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }*/

    interface MovieSummaryClickListener {
        void movieSummarySelected(MovieSummaryModel movieSummaryModel,ImageView poster,TextView overview,TextView rating);
    }

}
