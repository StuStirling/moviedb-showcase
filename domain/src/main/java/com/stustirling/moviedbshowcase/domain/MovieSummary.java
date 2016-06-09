package com.stustirling.moviedbshowcase.domain;

import java.util.Date;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieSummary {

    private long id;
    private String title;
    private String overview;
    private float rating;
    private Date releaseDate;
    private String posterPath;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---- MovieSummary ----\n");
        builder.append("id:"+this.getId()+"\n");
        builder.append("title:"+this.getTitle()+"\n");
        builder.append("overview:"+this.getOverview()+"\n");
        builder.append("poster path:"+this.getPosterPath()+"\n");
        builder.append("rating:"+this.getRating()+"\n");
        builder.append("release date:"+this.getReleaseDate()+"\n");
        builder.append("----xxxx----\n");
        return builder.toString();
    }
}
