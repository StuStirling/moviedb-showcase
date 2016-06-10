package com.stustirling.moviedbshowcase.internal.di.components;

import android.content.Context;

import com.stustirling.moviedbshowcase.BaseActivity;
import com.stustirling.moviedbshowcase.data.rest.MovieDBServiceModule;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;
import com.stustirling.moviedbshowcase.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@Singleton
@Component( modules = {ApplicationModule.class,MovieDBServiceModule.class} )
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    MovieDBRepository movieDBRepository();
}
