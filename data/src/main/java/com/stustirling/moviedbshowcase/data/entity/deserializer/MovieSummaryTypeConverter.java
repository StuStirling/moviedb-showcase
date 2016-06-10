package com.stustirling.moviedbshowcase.data.entity.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.stustirling.moviedbshowcase.domain.MovieSummary;

import java.lang.reflect.Type;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class MovieSummaryTypeConverter implements JsonDeserializer<MovieSummary> {

    @Override
    public MovieSummary deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
