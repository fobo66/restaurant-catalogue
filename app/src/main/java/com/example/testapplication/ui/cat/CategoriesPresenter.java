package com.example.testapplication.ui.cat;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.testapplication.network.ServerAPI;
import com.example.testapplication.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;


/**
 * Fetches info from API and take categories from it
 */
public class CategoriesPresenter extends BasePresenter<CategoriesFragment> {

    public static final int GET_CATEGORY_REQUEST = 22;

    @Inject
    ServerAPI api;

    @Inject
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableLatestCache(GET_CATEGORY_REQUEST,
                () -> api.getCategories("ukAXxeJYZN")
                        .subscribeOn(Schedulers.io())
                        .map(cat -> cat.categories)
                        .observeOn(mainThread()),
                CategoriesFragment::onItems,
                CategoriesFragment::onNetworkError);
    }

    void request() {
        start(GET_CATEGORY_REQUEST);
    }
}
