package com.stustirling.moviedbshowcase.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PersonModel implements Parcelable,PopularModel {

    int id;
    List<MovieSummaryModel> knownFor;
    String name;
    float popularity;
    String profilePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieSummaryModel> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<MovieSummaryModel> knownFor) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedList(this.knownFor);
        dest.writeString(this.name);
        dest.writeFloat(this.popularity);
        dest.writeString(this.profilePath);
    }

    public PersonModel() {
    }

    protected PersonModel(Parcel in) {
        this.id = in.readInt();
        this.knownFor = in.createTypedArrayList(MovieSummaryModel.CREATOR);
        this.name = in.readString();
        this.popularity = in.readFloat();
        this.profilePath = in.readString();
    }

    public static final Parcelable.Creator<PersonModel> CREATOR = new Parcelable.Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel source) {
            return new PersonModel(source);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };
}
