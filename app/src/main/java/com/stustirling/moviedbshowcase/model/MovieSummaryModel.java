package com.stustirling.moviedbshowcase.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class MovieSummaryModel implements Parcelable,Model {

    private int id;
    private String title;
    private String posterPath;
    private String overview;
    private float rating;
    private Date releaseDate;

    public MovieSummaryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        builder.append("---- MovieSummaryModel ----\n");
        builder.append("id:"+this.getId()+"\n");
        builder.append("title:"+this.getTitle()+"\n");
        builder.append("overview:"+this.getOverview()+"\n");
        builder.append("poster path:"+this.getPosterPath()+"\n");
        builder.append("rating:"+this.getRating()+"\n");
        builder.append("release date:"+this.getReleaseDate()+"\n");
        builder.append("----xxxx----\n");
        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeFloat(this.rating);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
    }

    protected MovieSummaryModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.rating = in.readFloat();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
    }

    public static final Parcelable.Creator<MovieSummaryModel> CREATOR = new Parcelable.Creator<MovieSummaryModel>() {
        @Override
        public MovieSummaryModel createFromParcel(Parcel source) {
            return new MovieSummaryModel(source);
        }

        @Override
        public MovieSummaryModel[] newArray(int size) {
            return new MovieSummaryModel[size];
        }
    };
}
