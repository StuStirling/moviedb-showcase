package com.stustirling.moviedbshowcase.domain;

import java.util.List;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class Person implements DomainEntity {

    int id;
    List<MovieSummary> knownFor;
    String name;
    float popularity;
    String profilePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieSummary> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<MovieSummary> knownFor) {
        this.knownFor = knownFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
