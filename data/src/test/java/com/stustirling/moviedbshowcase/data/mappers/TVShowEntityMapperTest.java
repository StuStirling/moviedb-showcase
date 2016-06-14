package com.stustirling.moviedbshowcase.data.mappers;

import com.stustirling.moviedbshowcase.data.entity.mapper.TVShowEntityMapper;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
import com.stustirling.moviedbshowcase.domain.TVShow;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class TVShowEntityMapperTest {


    @Test
    public void shouldMapTVShowEntityToTVShow() {
        TVShowEntity gotEntity = mock(TVShowEntity.class);
        int id = 1339;
        String name = "Game of Thrones";
        String overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.";
        float voteAvg = 9.1f;
        int voteCount = 273;
        int[] genres = new int[]{10765,18};
        float popularity = 36.072708f;
        Date firstAirDate = new Date();
        String poster = "/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg";
        String backdrop = "/aKz3lXU71wqdslC1IYRC3yHD6yw.jpg";

        when(gotEntity.getId()).thenReturn(id);
        when(gotEntity.getName()).thenReturn(name);
        when(gotEntity.getOverview()).thenReturn(overview);
        when(gotEntity.getVoteAverage()).thenReturn(voteAvg);
        when(gotEntity.getVoteCount()).thenReturn(voteCount);
        when(gotEntity.getGenreIds()).thenReturn(genres);
        when(gotEntity.getPopularity()).thenReturn(popularity);
        when(gotEntity.getFirstAirDate()).thenReturn(firstAirDate);
        when(gotEntity.getPosterPath()).thenReturn(poster);
        when(gotEntity.getBackdropPath()).thenReturn(backdrop);

        TVShow gameOfThrones = TVShowEntityMapper.transform(gotEntity);
        assertEquals(id,gameOfThrones.getId());
        assertEquals(name,gameOfThrones.getName());
        assertEquals(overview,gameOfThrones.getOverview());
        assertEquals(voteAvg,gameOfThrones.getVoteAvg(),0.01f);
        assertEquals(voteCount,gameOfThrones.getVoteCount());
        assertEquals(genres,gameOfThrones.getGenreIds());
        assertEquals(popularity,gameOfThrones.getPopularity(),0.000001f);
        assertEquals(firstAirDate,gameOfThrones.getFirstAirDate());
        assertEquals(poster,gameOfThrones.getPosterPath());
        assertEquals(backdrop,gameOfThrones.getBackdropPath());
    }
}
