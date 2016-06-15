package com.stustirling.moviedbshowcase;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stustirling.moviedbshowcase.model.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public abstract class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>  {

    private final ModelSelectedListener modelSelectedListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public @BindView(R.id.tv_cvpm_title) TextView title;
        public @BindView(R.id.tv_cvpm_overview) TextView overview;
        public @BindView(R.id.tv_cvpm_rating) TextView rating;
        public @BindView(R.id.tv_cvpm_year) TextView year;
        public @BindView(R.id.iv_cvpm_poster) ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modelSelectedListener.modelItemSelected(modelObjects.get(getAdapterPosition()),poster);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setTransitionNames(itemView.getContext(),this);
            }
        }

        public ImageView getPoster() {
            return poster;
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setOverview(String overview) {
            this.overview.setText(overview);
        }

        public void setRating( float rating ) {
            this.rating.setText(String.format(Locale.getDefault(),"%.1f",rating));
        }

        public void setYear(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            this.year.setText(String.format(Locale.getDefault(),"%d",cal.get(Calendar.YEAR)));
        }

    }

    protected List<? extends Model> modelObjects;

    public PopularAdapter(ModelSelectedListener modelSelectedListener ) {
        this.modelObjects = new ArrayList<>();
        this.modelSelectedListener = modelSelectedListener;
    }

    protected abstract void setTransitionNames(Context context, ViewHolder holder);
    protected abstract void displayModelInView(ViewHolder holder,int position);

    public void updateModel(List<? extends Model> modelObjects) {
        this.modelObjects = modelObjects;
        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return modelObjects.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return modelObjects.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_popular_media,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        displayModelInView(holder,position);
    }

    public interface ModelSelectedListener {
        void modelItemSelected(Model modelItem, ImageView poster);
    }
}
