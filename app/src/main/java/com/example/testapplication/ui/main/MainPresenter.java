package com.example.testapplication.ui.main;

/**
 * Created by fobo66 on 16.09.2016.
 */
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import icepick.State;
import com.example.testapplication.network.ServerAPI;
import com.example.testapplication.pojo.Offer;
import com.example.testapplication.pojo.Offers;
import com.example.testapplication.ui.base.BasePresenter;
import com.example.testapplication.util.PageBundle;

import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static java.util.Arrays.asList;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class MainPresenter extends BasePresenter<MainFragment> {
    private static final int REQUEST_ITEMS = 1;

    @Inject ServerAPI api;
    @Inject SharedPreferences pref;

    @State int id;

    private PublishSubject<Integer> pageRequests = PublishSubject.create();

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableReplay(REQUEST_ITEMS,
                () -> pageRequests.startWith(0)
                        .concatMap(page ->
                                api.getItems("ukAXxeJYZN")
                                        .subscribeOn(Schedulers.io())
                                        //.map(Offers::getOffers)
                                        //.filter(offers -> offers.removeIf(y -> y.getCategoryId() != id))
                                        .map(this::offersWithID)
                                        .map(data -> new PageBundle<>(page, data))
                                        .observeOn(mainThread())),
                (frag, page) -> frag.onItems(page, id),
                MainFragment::onNetworkError);
    }

    private List<Offer> offersWithID(Offers data) {
        List<Offer> tempData = data.getOffers();

        Iterator<Offer> iter = tempData.listIterator();

        while (iter.hasNext()) {
            if (iter.next().getCategoryId() != id)
                iter.remove();
        }

        return tempData;
    }

    void request(int id) {
        this.id = id;
        start(REQUEST_ITEMS);
    }

    void requestNext(int page) {
        pageRequests.onNext(page);
    }
}