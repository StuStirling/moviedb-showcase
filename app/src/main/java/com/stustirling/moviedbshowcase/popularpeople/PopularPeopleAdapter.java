package com.stustirling.moviedbshowcase.popularpeople;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class PopularPeopleAdapter extends RecyclerView.Adapter<PopularPeopleAdapter.ViewHolder> {

    private PersonClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_cvpp_name) TextView name;
        @BindView(R.id.tv_cvpp_known_for) TextView knownFor;
        @BindView(R.id.iv_cvpp_profile) ImageView profile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.personSelected( popularPeople.get(getAdapterPosition()));
                }
            });
        }
    }

    private List<PersonModel> popularPeople;

    public PopularPeopleAdapter(PersonClickListener clickListener ) {
        super();
        this.popularPeople = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public void updatePopularPeople(List<PersonModel> popularPeople) {
        this.popularPeople = popularPeople;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return popularPeople.size();
    }

    @Override
    public long getItemId(int position) {
        return popularPeople.get(position).getId();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PersonModel person = popularPeople.get(position);
        holder.name.setText(person.getName());

        if ( person.getKnownFor().size() > 0 ) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < person.getKnownFor().size(); i++ ) {
                MovieSummaryModel movie = person.getKnownFor().get(i);
                builder.append(movie.getTitle());
                if ( i < person.getKnownFor().size() - 1 )
                    builder.append(", ");
            }
            holder.knownFor.setText(builder.toString());
        }

        if ( person.getProfilePath() != null ) {
            Picasso.with(holder.profile.getContext())
                    .load(Uri.parse(person.getProfilePath()))
                    .into(holder.profile);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_popular_people,parent,false);
        return new ViewHolder(itemView);
    }

    interface PersonClickListener {
        void personSelected(PersonModel model);
    }

}
