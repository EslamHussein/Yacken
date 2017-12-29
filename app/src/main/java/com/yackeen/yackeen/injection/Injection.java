package com.yackeen.yackeen.injection;

import android.arch.persistence.room.Room;

import com.yackeen.App;
import com.yackeen.base.db.YSolutionDataBase;
import com.yackeen.yackeen.model.db.DBConstant;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by EslamHussein on 12/28/17.
 */

public class Injection {


    public static YSolutionDataBase provideReservationDataBase() {
        return Room.databaseBuilder(App.get().getBaseContext(), YSolutionDataBase.class, DBConstant.DB_NAME).build();
    }

    public static Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();

    }
}
