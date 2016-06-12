package com.stustirling.moviedbshowcase.model.mapper;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */

public class MovieSummaryModelMapper {

    @Inject
    public MovieSummaryModelMapper() {
    }

    public MovieSummaryModel transform(MovieSummary movieSummary) {
        MovieSummaryModel model = null;
        if ( movieSummary != null ) {
            model = new MovieSummaryModel();
            model.setId(movieSummary.getId());
            model.setTitle(movieSummary.getTitle());
            model.setOverview(movieSummary.getOverview());
            model.setRating(movieSummary.getRating());
            model.setReleaseDate(movieSummary.getReleaseDate());
            if ( movieSummary.getPosterPath() != null )
                model.setPosterPath(MovieDBApi.BASE_IMG_PATH+"w342"+movieSummary.getPosterPath());
        }
        return model;
    }

    public List<MovieSummaryModel> transform( List<MovieSummary> summaries ) {
        List<MovieSummaryModel> movieSummaryModels = new ArrayList<>();
        MovieSummaryModel model;
        for ( MovieSummary summary : summaries ) {
            model = transform(summary);
            if ( model != null )
                movieSummaryModels.add(model);
        }
        return movieSummaryModels;
    }
}
