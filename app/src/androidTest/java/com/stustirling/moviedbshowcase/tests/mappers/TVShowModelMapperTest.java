package com.stustirling.moviedbshowcase.tests.mappers;

import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.TVShow;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.model.mapper.TVShowModelMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stu Stirling on 14/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class TVShowModelMapperTest {

    @Test
    public void shouldMapToTVShowModel() {
        TVShow bbt = new TVShow();
        bbt.setId(1418);
        bbt.setName("The Big Bang Theory");
        bbt.setOverview("The Big Bang Theory is centered on five characters living in Pasadena, California: roommates Leonard Hofstadter and Sheldon Cooper; Penny, a waitress and aspiring actress who lives across the hall; and Leonard and Sheldon's equally geeky and socially awkward friends and co-workers, mechanical engineer Howard Wolowitz and astrophysicist Raj Koothrappali. The geekiness and intellect of the four guys is contrasted for comic effect with Penny's social skills and common sense.");
        bbt.setVoteAvg(8f);
        bbt.setVoteCount(181);
        bbt.setPopularity(21.903248f);
        bbt.setGenreIds(new int[]{35});
        bbt.setFirstAirDate( new Date());
        bbt.setPosterPath("/8SUIoe1ENMHWLZ0fe2sMqMP3eZD.jpg");
        bbt.setBackdropPath("/nGsNruW3W27V6r4gkyc3iiEGsKR.jpg");

        TVShowModelMapper mapper = new TVShowModelMapper();
        TVShowModel model = mapper.transform(bbt);
        assertEquals(bbt.getId(),model.getId());
        assertEquals(bbt.getName(),model.getName());
        assertEquals(bbt.getOverview(),model.getOverview());
        assertEquals(bbt.getVoteAvg(),model.getVoteAvg(),0.01f);
        assertEquals(bbt.getVoteCount(),model.getVoteCount());
        assertEquals(bbt.getPopularity(),model.getPopularity(),0.000001f);
        assertEquals(bbt.getGenreIds(),model.getGenreIds());
        assertEquals(bbt.getFirstAirDate(),model.getFirstAirDate());
        assertEquals(MovieDBApi.BASE_IMG_PATH + "w342" + bbt.getPosterPath(),model.getPosterPath());
        assertEquals(MovieDBApi.BASE_IMG_PATH + "w780" +bbt.getBackdropPath(),model.getBackdropPath());
    }

}
