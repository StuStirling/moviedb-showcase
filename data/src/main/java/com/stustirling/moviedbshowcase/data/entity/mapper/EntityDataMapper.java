package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;

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

    public List<MovieSummary> transform(List<MovieSummaryEntity> movieSummaryEntities) {
        return MovieSummaryEntityDataMapper.transform(movieSummaryEntities);
    }
}
