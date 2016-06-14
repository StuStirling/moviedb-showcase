package com.stustirling.moviedbshowcase.data.entity.tvshows;

import java.util.Date;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class TVShowEntity {

    int id;
    String name;
    String overview;
    float vote_average;
    int vote_count;
    int[] genre_ids;
    float popularity;
    Date first_air_date;
    String poster_path;
    String backdrop_path;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public int[] getGenreIds() {
        return genre_ids;
    }

    public float getPopularity() {
        return popularity;
    }

    public Date getFirstAirDate() {
        return first_air_date;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }
}
