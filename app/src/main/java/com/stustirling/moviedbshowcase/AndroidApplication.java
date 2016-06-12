package com.stustirling.moviedbshowcase;

import android.app.Application;

import com.stustirling.moviedbshowcase.data.rest.MovieDBServiceModule;
import com.stustirling.moviedbshowcase.internal.di.components.ApplicationComponent;
import com.stustirling.moviedbshowcase.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Stu Stirling on 10/06/16.
 */
public class AndroidApplication extends Application {

    @Singleton
    @Component(modules={ApplicationModule.class,MovieDBServiceModule.class})
    public interface AndroidApplicationComponent extends ApplicationComponent {

    }

    protected final ApplicationComponent applicationComponent = createComponent();

    protected ApplicationComponent createComponent() {
        return DaggerAndroidApplication_AndroidApplicationComponent.builder()
                .applicationModule( new ApplicationModule(this) )
                .movieDBServiceModule( new MovieDBServiceModule() )
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    protected void init() {

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
