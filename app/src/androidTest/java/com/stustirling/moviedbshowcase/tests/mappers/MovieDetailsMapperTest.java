package com.stustirling.moviedbshowcase.tests.mappers;

import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.model.MovieDetailsModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieDetailsModelMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class MovieDetailsMapperTest {

    @Test
    public void shouldMapToMovieSummaryModel() {
        MovieDetails testMovieDetails = new MovieDetails();
        testMovieDetails.setId(18023);
        testMovieDetails.setTitle("Test Movie Details");
        testMovieDetails.setRevenue(212314123);
        testMovieDetails.setBudget(807123123);
        testMovieDetails.setHomepage("http://test.com");
        testMovieDetails.setImdbId("alkjd[0dkddw");
        testMovieDetails.setRuntime(120);
        testMovieDetails.setVoteCount(32413);
        testMovieDetails.setTagline("The best movie in the world");
        testMovieDetails.setBackdropPath("/asdkjnpashdaksd.jpg");
        testMovieDetails.setVoteAvg(3.4f);
        testMovieDetails.setReleaseDate(new Date());

        MovieDetailsModelMapper mapper = new MovieDetailsModelMapper();
        MovieDetailsModel model = mapper.transform(testMovieDetails);
        assertEquals(testMovieDetails.getId(), model.getId());
        assertEquals(testMovieDetails.getTitle(), model.getTitle());
        assertEquals(testMovieDetails.getOverview(), model.getOverview());
        assertEquals(testMovieDetails.getVoteAvg(), model.getVoteAvg(), 0.1f);
        assertEquals(testMovieDetails.getReleaseDate(), model.getReleaseDate());
        assertEquals(testMovieDetails.getRevenue(),model.getRevenue());
        assertEquals(testMovieDetails.getBudget(),model.getBudget());
        assertEquals(testMovieDetails.getHomepage(),model.getHomepage());
        assertEquals(testMovieDetails.getImdbId(),model.getImdbId());
        assertEquals(testMovieDetails.getVoteCount(),model.getVoteCount());
        assertEquals(testMovieDetails.getRuntime(),model.getRuntime());
        assertEquals(testMovieDetails.getTagline(),model.getTagline());
        assertEquals(MovieDBApi.BASE_IMG_PATH + "w780" + testMovieDetails.getBackdropPath(), model.getBackdropPath());
    }

}
