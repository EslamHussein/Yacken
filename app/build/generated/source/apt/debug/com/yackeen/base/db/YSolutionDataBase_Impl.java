package com.yackeen.base.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.yackeen.yackeen.model.db.dao.NewsItemDao;
import com.yackeen.yackeen.model.db.dao.NewsItemDao_Impl;
import com.yackeen.yackeen.model.db.dao.WeatherDao;
import com.yackeen.yackeen.model.db.dao.WeatherDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class YSolutionDataBase_Impl extends YSolutionDataBase {
  private volatile WeatherDao _weatherDao;

  private volatile NewsItemDao _newsItemDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ITEM` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dt` INTEGER NOT NULL, `dt_txt` TEXT, `tempMin` REAL, `tempMax` REAL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NEWS_ITEM` (`NEWS_ITEM_ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `NEWS_ITEM_TITLE` TEXT, `PUBLISHED_DATE` TEXT, `url` TEXT, `height` INTEGER, `width` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"8b69315fa7094e923d89e665118de68b\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ITEM`");
        _db.execSQL("DROP TABLE IF EXISTS `NEWS_ITEM`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsITEM = new HashMap<String, TableInfo.Column>(5);
        _columnsITEM.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsITEM.put("dt", new TableInfo.Column("dt", "INTEGER", true, 0));
        _columnsITEM.put("dt_txt", new TableInfo.Column("dt_txt", "TEXT", false, 0));
        _columnsITEM.put("tempMin", new TableInfo.Column("tempMin", "REAL", false, 0));
        _columnsITEM.put("tempMax", new TableInfo.Column("tempMax", "REAL", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysITEM = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesITEM = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoITEM = new TableInfo("ITEM", _columnsITEM, _foreignKeysITEM, _indicesITEM);
        final TableInfo _existingITEM = TableInfo.read(_db, "ITEM");
        if (! _infoITEM.equals(_existingITEM)) {
          throw new IllegalStateException("Migration didn't properly handle ITEM(com.yackeen.yackeen.model.dto.WeatherItem).\n"
                  + " Expected:\n" + _infoITEM + "\n"
                  + " Found:\n" + _existingITEM);
        }
        final HashMap<String, TableInfo.Column> _columnsNEWSITEM = new HashMap<String, TableInfo.Column>(6);
        _columnsNEWSITEM.put("NEWS_ITEM_ID", new TableInfo.Column("NEWS_ITEM_ID", "INTEGER", true, 1));
        _columnsNEWSITEM.put("NEWS_ITEM_TITLE", new TableInfo.Column("NEWS_ITEM_TITLE", "TEXT", false, 0));
        _columnsNEWSITEM.put("PUBLISHED_DATE", new TableInfo.Column("PUBLISHED_DATE", "TEXT", false, 0));
        _columnsNEWSITEM.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsNEWSITEM.put("height", new TableInfo.Column("height", "INTEGER", false, 0));
        _columnsNEWSITEM.put("width", new TableInfo.Column("width", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNEWSITEM = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNEWSITEM = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNEWSITEM = new TableInfo("NEWS_ITEM", _columnsNEWSITEM, _foreignKeysNEWSITEM, _indicesNEWSITEM);
        final TableInfo _existingNEWSITEM = TableInfo.read(_db, "NEWS_ITEM");
        if (! _infoNEWSITEM.equals(_existingNEWSITEM)) {
          throw new IllegalStateException("Migration didn't properly handle NEWS_ITEM(com.yackeen.yackeen.model.dto.NewsItem).\n"
                  + " Expected:\n" + _infoNEWSITEM + "\n"
                  + " Found:\n" + _existingNEWSITEM);
        }
      }
    }, "8b69315fa7094e923d89e665118de68b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "ITEM","NEWS_ITEM");
  }

  @Override
  public WeatherDao weatherDao() {
    if (_weatherDao != null) {
      return _weatherDao;
    } else {
      synchronized(this) {
        if(_weatherDao == null) {
          _weatherDao = new WeatherDao_Impl(this);
        }
        return _weatherDao;
      }
    }
  }

  @Override
  public NewsItemDao newsItemDao() {
    if (_newsItemDao != null) {
      return _newsItemDao;
    } else {
      synchronized(this) {
        if(_newsItemDao == null) {
          _newsItemDao = new NewsItemDao_Impl(this);
        }
        return _newsItemDao;
      }
    }
  }
}
