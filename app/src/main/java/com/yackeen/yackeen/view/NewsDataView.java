package com.yackeen.yackeen.view;

import com.yackeen.base.view.MvpView;

import java.util.List;

/**
 * Created by EslamHussein on 12/28/17.
 */

public interface NewsDataView extends MvpView {

    void showPhotos(List<Object> objects);

    void showLoading();

    void hideLoading();

    void showError(String error);

}
