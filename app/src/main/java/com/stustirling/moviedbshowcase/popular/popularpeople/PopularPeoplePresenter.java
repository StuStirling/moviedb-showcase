package com.stustirling.moviedbshowcase.popular.popularpeople;

import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.PersonModel;
import com.stustirling.moviedbshowcase.model.PopularModel;
import com.stustirling.moviedbshowcase.model.mapper.PersonModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PopularPeoplePresenter extends PopularPresenter{
    private final PersonModelMapper mapper;

    @Inject
    public PopularPeoplePresenter(@Named("popularPeople") UseCase useCase, PersonModelMapper mapper ) {
        super(useCase);
        this.mapper = mapper;
    }

    private final Subscriber<List<Person>> subscriber = new Subscriber<List<Person>>() {
        @Override
        public void onCompleted() {
            onCompletedCalled();
        }

        @Override
        public void onError(Throwable e) {
            onErrorCalled(e);
        }

        @Override
        public void onNext(List<Person> people) {

            modelItems.addAll(mapper.transform(people));
        }
    };

    @Override
    protected String getQueryableString(PopularModel model) {
        return ((PersonModel)model).getName();
    }

    @Override
    protected Subscriber getSubscriber() {
        return subscriber;
    }


}
