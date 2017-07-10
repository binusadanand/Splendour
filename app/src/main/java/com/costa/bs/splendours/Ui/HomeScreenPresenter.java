package com.costa.bs.splendours.Ui;

import com.costa.bs.splendours.ApiServices.Api;
import com.costa.bs.splendours.ApiServices.Provider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binusadanand on 09/07/2017.
 */

public class HomeScreenPresenter {
    private Api mApiService;
    private final HomeScreenView mView;
    private CompositeSubscription mSubscriptions;

    public HomeScreenPresenter(HomeScreenView aView) {
        mView = aView;
        mSubscriptions = new CompositeSubscription();
        mApiService = Provider.getApiService();

    }

    public void getWeatherDetails() {

        mView.showProgress();

        Subscription event = mApiService.searchVenue("london,uk", "json", EndPointConstants.APP_ID_VALUE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherObj>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissProgress();
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(WeatherObj aWeatherObj) {
                        mView.dismissProgress();
                        mView.showDetails(aWeatherObj);
                    }
                });


    }

}
