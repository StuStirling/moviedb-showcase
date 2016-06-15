package com.stustirling.moviedbshowcase.data.entity.person;

import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;

import java.util.List;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PersonEntity {

    int id;
    List<MovieSummaryEntity> known_for;
    String name;
    float popularity;
    String profile_path;

    public int getId() {
        return id;
    }

    public List<MovieSummaryEntity> getKnownFor() {
        return known_for;
    }

    public String getName() {
        return name;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profile_path;
    }
}
