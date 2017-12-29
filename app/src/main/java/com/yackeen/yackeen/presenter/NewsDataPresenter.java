package com.yackeen.yackeen.presenter;

import android.location.Location;

import com.yackeen.base.presenter.BasePresenter;
import com.yackeen.yackeen.view.NewsDataView;


public abstract class NewsDataPresenter extends BasePresenter<NewsDataView> {

    public abstract void getData(Location currentLocation);
}
