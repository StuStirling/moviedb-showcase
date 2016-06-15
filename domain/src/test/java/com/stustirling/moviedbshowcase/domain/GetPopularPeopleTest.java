package com.stustirling.moviedbshowcase.domain;

import com.stustirling.moviedbshowcase.domain.interactor.GetPopularPeople;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class GetPopularPeopleTest {

    private GetPopularPeople getPopularPeople;
    @Mock private MovieDBRepository mockRepo;
    @Mock private Scheduler mockExector;
    @Mock private Scheduler mockPostExecution;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getPopularPeople= new GetPopularPeople(mockRepo,mockExector,mockPostExecution);
    }

    @Test
    public void testGetPopularPeopleUseCaseHappyCase() {
        getPopularPeople.buildUseCaseObservable();

        verify(mockRepo,times(1)).getPopularPeople();
        verifyNoMoreInteractions(mockRepo);
    }

}
