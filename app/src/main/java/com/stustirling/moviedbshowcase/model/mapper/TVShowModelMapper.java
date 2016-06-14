package com.stustirling.moviedbshowcase.model.mapper;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.TVShow;
import com.stustirling.moviedbshowcase.model.TVShowModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */

public class TVShowModelMapper {

    @Inject
    public TVShowModelMapper() {
    }

    public TVShowModel transform(TVShow tvShow) {
        TVShowModel model = null;
        if ( tvShow != null ) {
            model = new TVShowModel();
            model.setId(tvShow.getId());
            model.setName(tvShow.getName());
            model.setOverview(tvShow.getOverview());
            model.setVoteAvg(tvShow.getVoteAvg());
            model.setVoteCount(tvShow.getVoteCount());
            model.setGenreIds(tvShow.getGenreIds());
            model.setPopularity(tvShow.getPopularity());
            model.setFirstAirDate(tvShow.getFirstAirDate());
            if ( tvShow.getPosterPath() != null )
                model.setPosterPath(MovieDBApi.BASE_IMG_PATH+"w342"+tvShow.getPosterPath());
            if ( tvShow.getBackdropPath() != null )
                model.setBackdropPath(MovieDBApi.BASE_IMG_PATH+"w780"+tvShow.getBackdropPath());
        }
        return model;
    }

    public List<TVShowModel> transform( List<TVShow> tvShows ) {
        List<TVShowModel> tvShowModels = new ArrayList<>();
        TVShowModel model;
        for ( TVShow summary : tvShows ) {
            model = transform(summary);
            if ( model != null )
                tvShowModels.add(model);
        }
        return tvShowModels;
    }
}
