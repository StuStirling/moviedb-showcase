package com.stustirling.moviedbshowcase.components;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.MockApplicationModule;
import com.stustirling.moviedbshowcase.internal.di.components.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@Singleton
@Component(modules={MockApplicationModule.class})
public interface TestComponent extends ApplicationComponent {
    void inject(BaseTest test);
}
