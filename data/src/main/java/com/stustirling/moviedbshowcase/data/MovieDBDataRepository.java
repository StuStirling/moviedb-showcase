package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.mapper.EntityDataMapper;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
import com.stustirling.moviedbshowcase.data.rest.MovieDBService;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.TVShow;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

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
    public Observable<List<MovieSummary>> getPopularMovies() {
        return movieDBService.getPopularMovies()
                .map(new Func1<List<MovieSummaryEntity>, List<MovieSummary>>() {
                    @Override
                    public List<MovieSummary> call(List<MovieSummaryEntity> movieSummaryEntities) {
                        return entityMapper.transformMovieSummaries(movieSummaryEntities);
                    }
                });
    }

    @Override
    public Observable<MovieDetails> getMovieDetails(int id) {
        return movieDBService.getMovieDetails(id)
                .map(new Func1<MovieDetailsEntity, MovieDetails>() {
                    @Override
                    public MovieDetails call(MovieDetailsEntity movieDetailsEntity) {
                        return entityMapper.transformMovieDetails(
                                movieDetailsEntity);
                    }
                });
    }

    @Override
    public Observable<List<TVShow>> getPopularTVShows() {
        return movieDBService.getPopularTVShows()
                .map(new Func1<List<TVShowEntity>, List<TVShow>>() {
                    @Override
                    public List<TVShow> call(List<TVShowEntity> tvShowEntities) {
                        return entityMapper.transformTVShows(tvShowEntities);
                    }
                });
    }

    public Observable<List<Person>> getPopularPeople() {
        return movieDBService.getPopularPeople()
                .map(new Func1<List<PersonEntity>, List<Person>>() {
                    @Override
                    public List<Person> call(List<PersonEntity> personEntities) {
                        return entityMapper.transformPeople(personEntities);
                    }
                });
    }
}
