package com.stustirling.moviedbshowcase.popular;

import android.accounts.NetworkErrorException;

import com.stustirling.moviedbshowcase.ConnectionTester;
import com.stustirling.moviedbshowcase.domain.interactor.UseCase;
import com.stustirling.moviedbshowcase.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public abstract class PopularPresenter {

    private final UseCase useCase;
    private final ConnectionTester connectionTester;
    protected PopularView view;
    protected ArrayList<PopularModel> modelItems;

    public PopularPresenter(UseCase useCase, ConnectionTester connectionTester) {
        this.useCase = useCase;
        this.connectionTester = connectionTester;
    }

    public void init(PopularView popularView) {
        this.view = popularView;
        this.modelItems = new ArrayList<>();
        fetchItems();
    }

    protected void fetchItems() {
        if ( connectionTester.isThereAnInternetConnection() ) {
            this.view.loading(true);
            this.useCase.execute(getSubscriber());
        } else
            onErrorCalled(new NetworkErrorException("No internet connection"));
    }

    protected void onCompletedCalled() {
        view.refreshModelItems(modelItems);
        view.loading(false);
    }

    protected void onErrorCalled(Throwable e) {
        view.displayError(e);
    }

    protected abstract Subscriber getSubscriber();
    protected abstract String getQueryableString(PopularModel model);


    public void filterItems(String query) {
        final List<PopularModel> filteredModelItems = new ArrayList<>();
        if (query.length() == 0 )
            filteredModelItems.addAll(modelItems);
        else {
            for (PopularModel modelItem : modelItems) {
                final String lowerCaseTitle = getQueryableString(modelItem).toLowerCase();
                if (lowerCaseTitle.contains(query.toLowerCase()))
                    filteredModelItems.add(modelItem);
            }
        }
        view.showFilteredModelItems(filteredModelItems);
    }

    public void resetFullItemList() {
        view.refreshModelItems(modelItems);
    }

    public interface PopularView {
        void loading(boolean loading);
        void refreshModelItems(List<? extends PopularModel> items);
        void showFilteredModelItems(List<? extends PopularModel> filteredItems);
        void displayError( Throwable e );
    }
}
