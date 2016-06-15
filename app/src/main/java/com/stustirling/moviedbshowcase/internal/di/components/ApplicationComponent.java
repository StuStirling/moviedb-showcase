package com.stustirling.moviedbshowcase.internal.di.components;

import com.stustirling.moviedbshowcase.BaseActivity;
import com.stustirling.moviedbshowcase.ConnectionTester;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

/**
 * Created by Stu Stirling on 12/06/16.
 */
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    MovieDBRepository movieDBRepository();
    ConnectionTester connectionTester();
}
