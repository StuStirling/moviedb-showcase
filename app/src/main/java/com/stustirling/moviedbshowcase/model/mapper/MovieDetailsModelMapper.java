package com.stustirling.moviedbshowcase.model.mapper;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.model.MovieDetailsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */

public class MovieDetailsModelMapper implements ModelMapper {

    @Inject
    public MovieDetailsModelMapper() {
    }

    public MovieDetailsModel transform(MovieDetails movieDetails) {
        MovieDetailsModel model = null;
        if ( movieDetails != null ) {
            model = new MovieDetailsModel();
            model.setId(movieDetails.getId());
            model.setTitle(movieDetails.getTitle());
            model.setOverview(movieDetails.getOverview());
            if ( movieDetails.getBackdropPath() != null )
                model.setBackdropPath(MovieDBApi.BASE_IMG_PATH+"w780"+movieDetails.getBackdropPath());
            model.setReleaseDate(movieDetails.getReleaseDate());
            model.setTagline(movieDetails.getTagline());
            model.setHomepage(movieDetails.getHomepage());
            model.setRuntime(movieDetails.getRuntime());
            model.setVoteAvg(movieDetails.getVoteAvg());
            model.setVoteCount(movieDetails.getVoteCount());
            model.setBudget(movieDetails.getBudget());
            model.setRevenue(movieDetails.getRevenue());
            model.setImdbId(movieDetails.getImdbId());
        }
        return model;
    }

    public List<MovieDetailsModel> transform( List<MovieDetails> summaries ) {
        List<MovieDetailsModel> movieDetailsModels = new ArrayList<>();
        MovieDetailsModel model;
        for ( MovieDetails movieDetails : summaries ) {
            model = transform(movieDetails);
            if ( model != null )
                movieDetailsModels.add(model);
        }
        return movieDetailsModels;
    }
}
