package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieSummaryEntityDataMapper {

    public static MovieSummary transform( MovieSummaryEntity entity ) {
        MovieSummary movieSummary = null;
        if ( entity != null ) {
            movieSummary = new MovieSummary();
            movieSummary.setId(entity.getId());
            movieSummary.setTitle(entity.getTitle());
            movieSummary.setOverview(entity.getOverview());
            movieSummary.setPosterPath(entity.getPosterPath());
            movieSummary.setRating(entity.getVoteAverage());
            movieSummary.setReleaseDate(entity.getReleaseDate());
        }
        return movieSummary;
    }

    public static List<MovieSummary> transform(List<MovieSummaryEntity> entities) {
        List<MovieSummary> movies = new ArrayList<>();
        MovieSummary movieSummary;
        for ( MovieSummaryEntity entity : entities ) {
            movieSummary = transform(entity);
            if ( movieSummary != null )
                movies.add(movieSummary);
        }
        return movies;
    }
}
