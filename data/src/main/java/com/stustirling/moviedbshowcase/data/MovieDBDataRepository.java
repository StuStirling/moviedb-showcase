package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.mapper.EntityDataMapper;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;
import com.stustirling.moviedbshowcase.data.rest.MovieDBService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Stu Stirling on 07/06/16.
 */
@Singleton
public class MovieDBDataRepository implements MovieDBRepository {

    private final EntityDataMapper entityMapper;
    private MovieDBService movieDBService;

    @Inject
    public MovieDBDataRepository(MovieDBService service, EntityDataMapper entityDataMapper) {
        movieDBService = service;
        entityMapper = entityDataMapper;
    }

    @Override
    public Observable<List<MovieSummary>> getPopularMovies(int amount) {
        return movieDBService.getPopularMovies()
                .take(amount)
                .map(new Func1<List<MovieSummaryEntity>, List<MovieSummary>>() {
                    @Override
                    public List<MovieSummary> call(List<MovieSummaryEntity> movieSummaryEntities) {
                        return entityMapper.transform(movieSummaryEntities);
                    }
                });
    }
}