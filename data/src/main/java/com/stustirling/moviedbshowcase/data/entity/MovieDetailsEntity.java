package com.stustirling.moviedbshowcase.data.entity;

import java.util.Date;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class MovieDetailsEntity {

    int id;
    String imdb_id;
    String original_title;
    String overview;
    String tagline;
    String homepage;
    Date release_date;
    String backdrop_path;
    int runtime;
    float vote_average;
    int vote_count;
    int budget;
    int revenue;

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public String getTitle() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getTagline() {
        return tagline;
    }

    public String getHomepage() {
        return homepage;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public int getRuntime() {
        return runtime;
    }

    public float getVoteAvg() {
        return vote_average;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public int getBudget() {
        return budget;
    }

    public int getRevenue() {
        return revenue;
    }

}
