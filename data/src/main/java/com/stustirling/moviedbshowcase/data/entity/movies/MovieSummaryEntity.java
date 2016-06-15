package com.stustirling.moviedbshowcase.data.entity.movies;

import java.util.Date;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieSummaryEntity {

    public int id;
    public String title;
    String overview;
    float vote_average;
    Date release_date;
    String poster_path;

    int[] genre_ids;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public float getVoteAverage(){
        return vote_average;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public int[] getGenres() {
        return genre_ids;
    }
}
