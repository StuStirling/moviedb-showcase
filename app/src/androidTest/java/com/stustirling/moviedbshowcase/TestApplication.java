package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.components.DaggerTestComponent;
import com.stustirling.moviedbshowcase.internal.di.components.ApplicationComponent;

/**
 * Created by Stu Stirling on 11/06/16.
 */
public class TestApplication extends AndroidApplication {

    @Override
    protected ApplicationComponent createComponent() {
        return DaggerTestComponent.builder()
                .mockApplicationModule(new MockApplicationModule())
                .build();
    }
}
