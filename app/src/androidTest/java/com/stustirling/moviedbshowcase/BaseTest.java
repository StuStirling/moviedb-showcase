package com.stustirling.moviedbshowcase;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.stustirling.moviedbshowcase.components.TestComponent;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import org.junit.Before;
import org.mockito.Mockito;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public class BaseTest {

    @Inject
    protected MovieDBRepository mockRepo;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AndroidApplication application = (AndroidApplication) instrumentation.getTargetContext().getApplicationContext();
        TestComponent component = (TestComponent) application.getApplicationComponent();
        component.inject(this);

        Mockito.reset(mockRepo);
    }

}
