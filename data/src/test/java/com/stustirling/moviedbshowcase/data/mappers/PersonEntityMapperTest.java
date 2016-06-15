package com.stustirling.moviedbshowcase.data.mappers;

import com.stustirling.moviedbshowcase.data.entity.mapper.PersonEntityMapper;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.domain.Person;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class PersonEntityMapperTest {


    @Test
    public void shouldMapPersonEntityToPerson() {
        PersonEntity personEntity = mock(PersonEntity.class);
        int id = 28782;
        String name = "Monica Bellucci";

        List<MovieSummaryEntity> knownFor = new ArrayList<>();
        MovieSummaryEntity test = mock(MovieSummaryEntity.class);
        when(test.getId()).thenReturn(231);
        knownFor.add(test);

        float popularity = 5.32423f;
        String profilePath = "/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg";

        when(personEntity.getId()).thenReturn(id);
        when(personEntity.getName()).thenReturn(name);
        when(personEntity.getKnownFor()).thenReturn(knownFor);
        when(personEntity.getPopularity()).thenReturn(popularity);
        when(personEntity.getProfilePath()).thenReturn(profilePath);

        Person person = PersonEntityMapper.transform(personEntity);
        assertEquals(id,person.getId());
        assertEquals(name,person.getName());
        assertEquals(popularity,person.getPopularity(),0.1f);
        assertEquals(profilePath,person.getProfilePath());
        assertEquals(231,person.getKnownFor().get(0).getId());
    }
}
