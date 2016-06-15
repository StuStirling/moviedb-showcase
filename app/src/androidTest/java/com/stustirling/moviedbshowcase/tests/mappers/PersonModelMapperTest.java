package com.stustirling.moviedbshowcase.tests.mappers;

import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.model.PersonModel;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;
import com.stustirling.moviedbshowcase.model.mapper.PersonModelMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stu Stirling on 14/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class PersonModelMapperTest {

    @Test
    public void shouldMapToPersonModel() {
        Person person = new Person();
        person.setId(1418);
        person.setName("The Big Bang Theory");
        person.setPopularity(21.903248f);
        person.setProfilePath("/8SUIoe1ENMHWLZ0fe2sMqMP3eZD.jpg");

        List<MovieSummary> movieSummaries = new ArrayList<>();
        MovieSummary testMovieSummary = new MovieSummary();
        testMovieSummary.setId(23);
        movieSummaries.add(testMovieSummary);
        person.setKnownFor(movieSummaries);

        PersonModelMapper mapper = new PersonModelMapper(new MovieSummaryModelMapper());
        PersonModel model = mapper.transform(person);
        assertEquals(person.getId(),model.getId());
        assertEquals(person.getName(),model.getName());
        assertEquals(person.getPopularity(),model.getPopularity(),0.000001f);
        assertEquals(MovieDBApi.BASE_IMG_PATH + "w342" + person.getProfilePath(),model.getProfilePath());

        assertEquals(23,model.getKnownFor().get(0).getId());
    }

}
