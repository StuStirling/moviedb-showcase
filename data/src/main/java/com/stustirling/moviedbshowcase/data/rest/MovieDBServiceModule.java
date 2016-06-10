package com.stustirling.moviedbshowcase.data.rest;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Module
public class MovieDBServiceModule {

    @Provides RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides @Singleton Retrofit providesRetrofit(RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", MovieDBApi.apiKey)
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(MovieDBApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(httpClient.build())
                .build();
    }

    @Provides @Singleton MovieDBApi providesMovieDBApi( Retrofit retrofit ) {



        return retrofit.create( MovieDBApi.class );
    }

    @Provides @Singleton MovieDBService provideMovieDBService( MovieDBApiService movieDBApiService ) {
        return movieDBApiService;
    }
}
