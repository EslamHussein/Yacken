package com.yackeen.yackeen.view;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.yackeen.base.view.BaseActivity;
import com.yackeen.utils.TextUtils;
import com.yackeen.yackeen.R;
import com.yackeen.yackeen.injection.Injection;
import com.yackeen.yackeen.model.NewsDataRepImpl;
import com.yackeen.yackeen.model.cloud.NewsDataCloudRepoImpl;
import com.yackeen.yackeen.model.db.NewsDataDBRepoImpl;
import com.yackeen.yackeen.presenter.NewsDataPresenter;
import com.yackeen.yackeen.presenter.NewsDataPresenterImpl;
import com.yackeen.yackeen.view.adapter.MainAdapter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends BaseActivity<NewsDataPresenter> implements NewsDataView, LocationListener, MultiplePermissionsListener {


    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView errorTextView;
    private LocationManager locationManager;
    private Location currentLocation;


    @Override
    protected NewsDataPresenter createPresenter() {
        return new NewsDataPresenterImpl(new NewsDataRepImpl(new NewsDataCloudRepoImpl(),
                new NewsDataDBRepoImpl(Injection.provideReservationDataBase())),
                new CompositeDisposable(), Injection.provideMainThread());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        errorTextView = findViewById(R.id.text_view_error);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(TextUtils.getString(R.string.trying_to_get_current_location));
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        Dexter.withActivity(this)
                .withPermissions(PERMISSIONS).withListener(this).check();
        getPresenter().getData(null);


    }


    @SuppressWarnings("MissingPermission")
    private void getCurrentLocation(String provider) {
        locationManager.requestLocationUpdates(provider, 0, 0, this);
    }

    @Override
    public void showPhotos(List<Object> objects) {

        errorTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(objects, this));


    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showError(String error) {

        if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0) {
            Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show();


        } else {
            errorTextView.setText(error);

        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if (currentLocation == null) {
            currentLocation = location;
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(TextUtils.getString(R.string.loading));
            getPresenter().getData(location);

        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        Log.d("TAG", "onStatusChanged: ");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("TAG", "onProviderEnabled: ");
        if (currentLocation == null)
            getCurrentLocation(provider);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("TAG", "onProviderDisabled: ");
        Snackbar.make(findViewById(android.R.id.content), TextUtils.getString(R.string.enable_providers), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {

        if (report.areAllPermissionsGranted()) {
            showLoading();
            getCurrentLocation(LocationManager.NETWORK_PROVIDER);
        } else {
            showError(TextUtils.getString(R.string.permission_denied));
        }

    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

        showError(TextUtils.getString(R.string.permission_denied));
        token.continuePermissionRequest();

    }
}
