package com.stustirling.moviedbshowcase.model.mapper;

import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */

public class PersonModelMapper {

    private final MovieSummaryModelMapper movieSummaryMapper;

    @Inject
    public PersonModelMapper(MovieSummaryModelMapper movieSummaryModelMapper) {
        this.movieSummaryMapper = movieSummaryModelMapper;
    }

    public PersonModel transform(Person person) {
        PersonModel model = null;
        if ( person != null ) {
            model = new PersonModel();
            model.setId(person.getId());
            model.setName(person.getName());
            model.setPopularity(person.getPopularity());
            if ( person.getProfilePath() != null )
                model.setProfilePath(MovieDBApi.BASE_IMG_PATH+"w342"+person.getProfilePath());

            if ( person.getKnownFor() != null )
                model.setKnownFor(movieSummaryMapper.transform(person.getKnownFor()));
            else
                model.setKnownFor(new ArrayList<MovieSummaryModel>());
        }
        return model;
    }

    public List<PersonModel> transform( List<Person> persons ) {
        List<PersonModel> personModels = new ArrayList<>();
        PersonModel model;
        for ( Person summary : persons ) {
            model = transform(summary);
            if ( model != null )
                personModels.add(model);
        }
        return personModels;
    }
}
