package com.stustirling.moviedbshowcase.popularpeople;

import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.PersonModel;
import com.stustirling.moviedbshowcase.model.mapper.PersonModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
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

    @Inject
    public PopularPeoplePresenter(@Named("popularPeople")UseCase useCase, PersonModelMapper mapper ) {
        this.useCase = useCase;
        this.mapper = mapper;
        this.personsList = new ArrayList<>();
    }

    public void init( PopularPeopleView view ) {
        this.view = view;
        this.view.loading(true);
        this.useCase.execute(subscriber);
    }

    private Subscriber<List<Person>> subscriber = new Subscriber<List<Person>>() {
        @Override
        public void onCompleted() {
            view.refreshPopularPeople(mapper.transform(personsList));
            view.loading(false);
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
        void refreshPopularPeople(List<PersonModel> people);
    }


}
