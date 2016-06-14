package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.domain.TVShow;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Stu Stirling on 07/06/16.
 */
@Singleton
public class EntityDataMapper {

    @Inject
    public EntityDataMapper() {
    }

    public List<MovieSummary> transformMovieSummaries(List<MovieSummaryEntity> movieSummaryEntities) {
        return MovieSummaryEntityDataMapper.transform(movieSummaryEntities);
    }

    public MovieDetails transformMovieDetails(MovieDetailsEntity movieDetailsEntity) {
        return MovieDetailsEntityDataMapper.transform(movieDetailsEntity);
    }

    public List<TVShow> transformTVShows(List<TVShowEntity> tvShowEntities) {
        return TVShowEntityMapper.transform(tvShowEntities);
    }
}
