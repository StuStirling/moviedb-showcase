package com.stustirling.moviedbshowcase.tests.mappers;

import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class MovieSummaryMapperTest {

    @Test
    public void shouldMapToMovieSummaryModel() {
        MovieSummary movieSummary = new MovieSummary();
        movieSummary.setId(4321);
        movieSummary.setTitle("Test movie summary");
        movieSummary.setOverview("Using it to test the mapping");
        movieSummary.setRating(1.4f);
        movieSummary.setReleaseDate(new Date());
        movieSummary.setPosterPath("/alkdjfpasdlaksd.jpg");
        movieSummary.setGenres(new int[]{23,54});

        MovieSummaryModelMapper mapper = new MovieSummaryModelMapper();
        MovieSummaryModel model = mapper.transform(movieSummary);
        assertEquals(movieSummary.getId(), model.getId());
        assertEquals(movieSummary.getTitle(), model.getTitle());
        assertEquals(movieSummary.getOverview(), model.getOverview());
        assertEquals(movieSummary.getRating(), model.getRating(), 0.1f);
        assertEquals(movieSummary.getReleaseDate(), model.getReleaseDate());
        assertEquals(MovieDBApi.BASE_IMG_PATH + "w342" + movieSummary.getPosterPath(), model.getPosterPath());
        assertEquals(movieSummary.getGenres(),model.getGenres());
    }

}
