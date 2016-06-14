package com.stustirling.moviedbshowcase.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class TVShowModel implements Parcelable {

    int id;
    String name;
    String overview;
    float voteAvg;
    int voteCount;
    int[] genreIds;
    float popularity;
    Date firstAirDate;
    String posterPath;
    String backdropPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(float voteAvg) {
        this.voteAvg = voteAvg;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeFloat(this.voteAvg);
        dest.writeInt(this.voteCount);
        dest.writeIntArray(this.genreIds);
        dest.writeFloat(this.popularity);
        dest.writeLong(this.firstAirDate != null ? this.firstAirDate.getTime() : -1);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
    }

    public TVShowModel() {
    }

    protected TVShowModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.voteAvg = in.readFloat();
        this.voteCount = in.readInt();
        this.genreIds = in.createIntArray();
        this.popularity = in.readFloat();
        long tmpFirstAirDate = in.readLong();
        this.firstAirDate = tmpFirstAirDate == -1 ? null : new Date(tmpFirstAirDate);
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
    }

    public static final Parcelable.Creator<TVShowModel> CREATOR = new Parcelable.Creator<TVShowModel>() {
        @Override
        public TVShowModel createFromParcel(Parcel source) {
            return new TVShowModel(source);
        }

        @Override
        public TVShowModel[] newArray(int size) {
            return new TVShowModel[size];
        }
    };
}
