package com.yackeen.yackeen.presenter;

import android.location.Location;
import android.util.Log;

import com.yackeen.base.error.AppException;
import com.yackeen.utils.TextUtils;
import com.yackeen.yackeen.R;
import com.yackeen.yackeen.model.NewsDataRepImpl;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class NewsDataPresenterImpl extends NewsDataPresenter {

    private NewsDataRepImpl newsDataRep;
    private CompositeDisposable disposables;

    private Scheduler scheduler;


    public NewsDataPresenterImpl(NewsDataRepImpl newsDataRep, CompositeDisposable disposables, Scheduler scheduler) {
        this.newsDataRep = newsDataRep;
        this.disposables = disposables;
        this.scheduler = scheduler;
    }

    @Override
    public void getData(Location currentLocation) {

        if (!isViewAttached())
            return;

        getView().showLoading();

        disposables.add(newsDataRep.getData(currentLocation).observeOn(scheduler).
                subscribeOn(Schedulers.io()).subscribeWith(new DisposableObserver<List<Object>>() {
            @Override
            public void onNext(List<Object> objects) {
                if (!isViewAttached())
                    return;

                getView().showPhotos(objects);
                Log.d("Tag", "onNext size" + String.valueOf(objects.size()));
            }

            @Override
            public void onError(Throwable e) {
                if (!isViewAttached())
                    return;
                Log.d("Tag", "onError");

                if (e instanceof AppException) {


                    getView().showError(((AppException) e).getErrorMessage());

                } else {
                    getView().showError(TextUtils.getString(R.string.something_went_wrong));
                }


            }

            @Override
            public void onComplete() {
                if (!isViewAttached())
                    return;
                Log.d("Tag", "onComplete");
                getView().hideLoading();


            }
        }));


    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposables.dispose();

    }
}
