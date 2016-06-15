package com.stustirling.moviedbshowcase.popularpeople;

import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.mapper.PersonModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PopularPeoplePresenter {

    private final UseCase useCase;
    private final PersonModelMapper mapper;
    private PopularPeopleView view;
    private ArrayList<Person> personsList;

    public PopularPeoplePresenter(@Named("getPopularPeople")UseCase useCase, PersonModelMapper mapper ) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    public void init( PopularPeopleView view ) {
        this.view = view;
        this.view.loading(true);
        this.useCase.execute(subscriber);
    }

    private Subscriber<List<Person>> subscriber = new Subscriber<List<Person>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Person> persons) {
            PopularPeoplePresenter.this.personsList.addAll(persons);
        }
    };

    public interface PopularPeopleView {
        void loading(boolean loading);
    }


}
