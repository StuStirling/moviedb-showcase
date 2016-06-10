package com.stustirling.moviedbshowcase.data.rest;

import com.stustirling.moviedbshowcase.data.entity.PopularMoviesResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public interface MovieDBApi {

    String apiKey = "0a08e38b874d0aa2d426ffc04357069d";
    String BASE_URL = "http://api.themoviedb.org/3/";

    @GET("movie/popular")
    Observable<PopularMoviesResponse> getPopularMovies();

}
