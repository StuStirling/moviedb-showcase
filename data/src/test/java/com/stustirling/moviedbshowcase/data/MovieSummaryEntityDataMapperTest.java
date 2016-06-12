package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.mapper.MovieSummaryEntityDataMapper;
import com.stustirling.moviedbshowcase.domain.MovieSummary;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieSummaryEntityDataMapperTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testMovieSummaryMapping() {
        MovieSummaryEntity mockEntity = mock(MovieSummaryEntity.class);
        long id = 213123;
        String title = "Test entity";
        String overview = "A nice test entity";
        float vote_avg = 9.9f;
        Date release_date = new Date();
        String poster_path = "/6iQ4CMtYorKFfAmXEpAQZMnA0Qe.jpg";
        when(mockEntity.getId()).thenReturn(id);
        when(mockEntity.getTitle()).thenReturn(title);
        when(mockEntity.getOverview()).thenReturn(overview);
        when(mockEntity.getVoteAverage()).thenReturn(vote_avg);
        when(mockEntity.getReleaseDate()).thenReturn(release_date);
        when(mockEntity.getPosterPath()).thenReturn(poster_path);

        MovieSummary movieSummary = MovieSummaryEntityDataMapper.transform(mockEntity);
        assertEquals(id,movieSummary.getId());
        assertEquals(title,movieSummary.getTitle());
        assertEquals(overview,movieSummary.getOverview());
        assertEquals(release_date,movieSummary.getReleaseDate());
        assertEquals(poster_path,movieSummary.getPosterPath());
        assertEquals(vote_avg,movieSummary.getRating(),0.01f);
        assertEquals(poster_path,movieSummary.getPosterPath());
    }

    @Test
    public void testMultipleMovieSummaryMappings() {
        MovieSummaryEntity firstMock = mock(MovieSummaryEntity.class);
        long firstID = 1232523;
        when(firstMock.getId()).thenReturn(firstID);
        MovieSummaryEntity secondMock = mock(MovieSummaryEntity.class);
        long secondID = 981732;
        when(secondMock.getId()).thenReturn(secondID);
        List<MovieSummaryEntity> entities = new ArrayList<>();
        entities.add(firstMock);
        entities.add(secondMock);
        List<MovieSummary> movieSummaries = MovieSummaryEntityDataMapper.transform(entities);
        assertEquals(firstID,movieSummaries.get(0).getId());
        assertEquals(secondID,movieSummaries.get(1).getId());
    }

}
