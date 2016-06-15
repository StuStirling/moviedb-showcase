package com.stustirling.moviedbshowcase.data.entity.mapper;

import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class PersonEntityMapper {

    public static Person transform(PersonEntity entity) {
        Person person = null;
        if ( entity != null ) {
            person = new Person();
            person.setId(entity.getId());
            person.setName(entity.getName());
            person.setPopularity(entity.getPopularity());
            person.setProfilePath(entity.getProfilePath());
            if ( entity.getKnownFor() != null )
                person.setKnownFor( MovieSummaryEntityDataMapper.transform(entity.getKnownFor() ));
            else
                person.setKnownFor( new ArrayList<MovieSummary>());
        }
        return person;
    }

    public static List<Person> transform(List<PersonEntity> entities ) {
        List<Person> persons = new ArrayList<>();
        Person person;
        for ( PersonEntity entity : entities ) {
            person = transform(entity);
            if ( person != null )
                persons.add(person);
        }
        return persons;
    }


}
