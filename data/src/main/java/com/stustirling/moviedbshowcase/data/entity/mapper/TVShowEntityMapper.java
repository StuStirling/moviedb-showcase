package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
import com.stustirling.moviedbshowcase.domain.TVShow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class TVShowEntityMapper {

    public static TVShow transform(TVShowEntity entity) {
        TVShow tvShow = null;
        if ( entity != null ) {
            tvShow = new TVShow();
            tvShow.setId(entity.getId());
            tvShow.setName(entity.getName());
            tvShow.setOverview(entity.getOverview());
            tvShow.setVoteAvg(entity.getVoteAverage());
            tvShow.setVoteCount(entity.getVoteCount());
            tvShow.setGenreIds(entity.getGenreIds());
            tvShow.setPopularity(entity.getPopularity());
            tvShow.setFirstAirDate(entity.getFirstAirDate());
            tvShow.setPosterPath(entity.getPosterPath());
            tvShow.setBackdropPath(entity.getBackdropPath());
        }
        return tvShow;
    }

    public static List<TVShow> transform(List<TVShowEntity> entities ) {
        List<TVShow> tvShows = new ArrayList<>();
        TVShow tvShow;
        for ( TVShowEntity entity : entities ) {
            tvShow = transform(entity);
            if ( tvShow != null )
                tvShows.add(tvShow);
        }
        return tvShows;
    }


}
