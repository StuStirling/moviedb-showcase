package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.PopularMoviesResponse;
import com.stustirling.moviedbshowcase.data.entity.person.PopularPeopleResponse;
import com.stustirling.moviedbshowcase.data.entity.tvshows.PopularTVShowsResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBApi {

    String apiKey = "0a08e38b874d0aa2d426ffc04357069d";
    String BASE_URL = "http://api.themoviedb.org/3/";
    String BASE_IMG_PATH = "http://image.tmdb.org/t/p/";

    @GET("movie/popular")
    Observable<PopularMoviesResponse> getPopularMovies(@Query("page") Integer page);

    @GET("movie/{id}")
    Observable<MovieDetailsEntity> getMovieDetails(@Path("id") int id);

    @GET("tv/popular")
    Observable<PopularTVShowsResponse> getPopularTVShows();

    @GET("person/popular")
    Observable<PopularPeopleResponse> getPopularPeople();
}
