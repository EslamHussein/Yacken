package com.yackeen.yackeen.model.db.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.yackeen.yackeen.model.dto.MainTemp;
import com.yackeen.yackeen.model.dto.WeatherItem;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWeatherItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeatherItem = new EntityInsertionAdapter<WeatherItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ITEM`(`id`,`dt`,`dt_txt`,`tempMin`,`tempMax`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WeatherItem value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getDt());
        if (value.getDtTxt() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDtTxt());
        }
        final MainTemp _tmpMain = value.getMain();
        if(_tmpMain != null) {
          stmt.bindDouble(4, _tmpMain.getTempMin());
          stmt.bindDouble(5, _tmpMain.getTempMax());
        } else {
          stmt.bindNull(4);
          stmt.bindNull(5);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ITEM";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(List<WeatherItem> weatherItems) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfWeatherItem.insert(weatherItems);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<WeatherItem> getAll() {
    final String _sql = "SELECT * FROM ITEM";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfDt = _cursor.getColumnIndexOrThrow("dt");
      final int _cursorIndexOfDtTxt = _cursor.getColumnIndexOrThrow("dt_txt");
      final int _cursorIndexOfTempMin = _cursor.getColumnIndexOrThrow("tempMin");
      final int _cursorIndexOfTempMax = _cursor.getColumnIndexOrThrow("tempMax");
      final List<WeatherItem> _result = new ArrayList<WeatherItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final WeatherItem _item;
        final MainTemp _tmpMain;
        if (! (_cursor.isNull(_cursorIndexOfTempMin) && _cursor.isNull(_cursorIndexOfTempMax))) {
          _tmpMain = new MainTemp();
          final double _tmpTempMin;
          _tmpTempMin = _cursor.getDouble(_cursorIndexOfTempMin);
          _tmpMain.setTempMin(_tmpTempMin);
          final double _tmpTempMax;
          _tmpTempMax = _cursor.getDouble(_cursorIndexOfTempMax);
          _tmpMain.setTempMax(_tmpTempMax);
        }  else  {
          _tmpMain = null;
        }
        _item = new WeatherItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpDt;
        _tmpDt = _cursor.getLong(_cursorIndexOfDt);
        _item.setDt(_tmpDt);
        final String _tmpDtTxt;
        _tmpDtTxt = _cursor.getString(_cursorIndexOfDtTxt);
        _item.setDtTxt(_tmpDtTxt);
        _item.setMain(_tmpMain);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
