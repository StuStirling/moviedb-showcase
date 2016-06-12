package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.data.entity.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.domain.MovieDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class MovieDetailsEntityDataMapper {

    public static MovieDetails transform(MovieDetailsEntity entity) {
        MovieDetails movieDetails = null;
        if ( entity != null ) {
            movieDetails = new MovieDetails();
            movieDetails.setId(entity.getId());
            movieDetails.setImdbId(entity.getImdbId());
            movieDetails.setTitle(entity.getTitle());
            movieDetails.setOverview(entity.getOverview());
            movieDetails.setTagline(entity.getTagline());
            movieDetails.setHomepage(entity.getHomepage());
            movieDetails.setReleaseDate(entity.getReleaseDate());
            movieDetails.setRuntime(entity.getRuntime());
            movieDetails.setVoteAvg(entity.getVoteAvg());
            movieDetails.setVoteCount(entity.getVoteCount());
            movieDetails.setBudget(entity.getBudget());
            movieDetails.setRevenue(entity.getRevenue());
            movieDetails.setBackdropPath(entity.getBackdropPath());
        }
        return movieDetails;
    }

    public static List<MovieDetails> transform(List<MovieDetailsEntity> entities) {
        List<MovieDetails> movies = new ArrayList<>();
        MovieDetails movieDetails;
        for ( MovieDetailsEntity entity : entities ) {
            movieDetails = transform(entity);
            if ( movieDetails != null )
                movies.add(movieDetails);
        }
        return movies;
    }

}
