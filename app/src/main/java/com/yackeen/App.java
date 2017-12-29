package com.yackeen;

import android.app.Application;

import com.squareup.leakcanary.BuildConfig;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by Eslam Hussein on 10/27/17.
 */

public class App extends Application {

    public static final int REQUEST_CODE = 0;

    private static App instance;

    public static App get() {
        if (instance == null)
            throw new IllegalStateException("Something went horribly wrong!!, no application attached!");
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLeakCanary();
        initRoomBD();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG && LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initRoomBD() {


    }


}
