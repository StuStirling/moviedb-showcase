package com.stustirling.moviedbshowcase.data.mappers;

import com.stustirling.moviedbshowcase.data.entity.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.mapper.MovieDetailsEntityDataMapper;
import com.stustirling.moviedbshowcase.domain.MovieDetails;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class MovieDetailsEntityDataMapperTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testMovieDetailsMapping() {
        MovieDetailsEntity entity = mock(MovieDetailsEntity.class);
        int budget = 63000000;
        String homepage = "";
        long id = 550;
        String imdb_id = "tt0137523";
        String title = "Fight Club";
        String overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.";
        Date releaseDate = new Date();
        String backdropPath = "/hNFMawyNDWZKKHU4GYCBz1krsRM.jpg";
        int revenue = 100853753;
        int runtime = 139;
        String tagline = "How much can you know about yourself if you've never been in a fight?";
        float vote_avg = 7.7f;
        int vote_count = 3185;
        when(entity.getId()).thenReturn(id);
        when(entity.getImdbId()).thenReturn(imdb_id);
        when(entity.getTitle()).thenReturn(title);
        when(entity.getOverview()).thenReturn(overview);
        when(entity.getTagline()).thenReturn(tagline);
        when(entity.getHomepage()).thenReturn(homepage);
        when(entity.getReleaseDate()).thenReturn(releaseDate);
        when(entity.getRuntime()).thenReturn(runtime);
        when(entity.getVoteAvg()).thenReturn(vote_avg);
        when(entity.getVoteCount()).thenReturn(vote_count);
        when(entity.getBudget()).thenReturn(budget);
        when(entity.getRevenue()).thenReturn(revenue);
        when(entity.getBackdropPath()).thenReturn(backdropPath);

        MovieDetails details = MovieDetailsEntityDataMapper.transform(entity);
        assertEquals(id,details.getId());
        assertEquals(imdb_id,details.getImdbId());
        assertEquals(title,details.getTitle());
        assertEquals(overview,details.getOverview());
        assertEquals(tagline,details.getTagline());
        assertEquals(homepage,details.getHomepage());
        assertEquals(releaseDate,details.getReleaseDate());
        assertEquals(runtime,details.getRuntime());
        assertEquals(vote_avg,details.getVoteAvg(),0.01f);
        assertEquals(vote_count,details.getVoteCount());
        assertEquals(budget,details.getBudget());
        assertEquals(revenue,details.getRevenue());
        assertEquals(backdropPath,details.getBackdropPath());
    }

    @Test
    public void testMultipleMovieDetailsMappings() {
        MovieDetailsEntity firstMock = mock(MovieDetailsEntity.class);
        long firstId = 154;
        when(firstMock.getId()).thenReturn(firstId);
        MovieDetailsEntity secondMock = mock(MovieDetailsEntity.class);
        long secondId = 1325;
        when(secondMock.getId()).thenReturn(secondId);

        List<MovieDetailsEntity> entities = new ArrayList<>();
        entities.add(firstMock);
        entities.add(secondMock);
        List<MovieDetails> details = MovieDetailsEntityDataMapper.transform(entities);
        assertEquals(firstId,details.get(0).getId());
        assertEquals(secondId,details.get(1).getId());
    }


}
