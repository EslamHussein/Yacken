package com.yackeen.yackeen.model.db.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.yackeen.yackeen.model.dto.MultiMediaItem;
import com.yackeen.yackeen.model.dto.NewsItem;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class NewsItemDao_Impl implements NewsItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfNewsItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public NewsItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNewsItem = new EntityInsertionAdapter<NewsItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `NEWS_ITEM`(`NEWS_ITEM_ID`,`NEWS_ITEM_TITLE`,`PUBLISHED_DATE`,`url`,`height`,`width`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NewsItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getPublishedDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPublishedDate());
        }
        final MultiMediaItem _tmpMultiMediaItem = value.getMultiMediaItem();
        if(_tmpMultiMediaItem != null) {
          if (_tmpMultiMediaItem.getUrl() == null) {
            stmt.bindNull(4);
          } else {
            stmt.bindString(4, _tmpMultiMediaItem.getUrl());
          }
          stmt.bindLong(5, _tmpMultiMediaItem.getHeight());
          stmt.bindLong(6, _tmpMultiMediaItem.getWidth());
        } else {
          stmt.bindNull(4);
          stmt.bindNull(5);
          stmt.bindNull(6);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM NEWS_ITEM";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(List<NewsItem> items) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfNewsItem.insert(items);
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
  public List<NewsItem> getAll() {
    final String _sql = "SELECT * FROM NEWS_ITEM";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("NEWS_ITEM_ID");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("NEWS_ITEM_TITLE");
      final int _cursorIndexOfPublishedDate = _cursor.getColumnIndexOrThrow("PUBLISHED_DATE");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfHeight = _cursor.getColumnIndexOrThrow("height");
      final int _cursorIndexOfWidth = _cursor.getColumnIndexOrThrow("width");
      final List<NewsItem> _result = new ArrayList<NewsItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NewsItem _item;
        final MultiMediaItem _tmpMultiMediaItem;
        if (! (_cursor.isNull(_cursorIndexOfUrl) && _cursor.isNull(_cursorIndexOfHeight) && _cursor.isNull(_cursorIndexOfWidth))) {
          _tmpMultiMediaItem = new MultiMediaItem();
          final String _tmpUrl;
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
          _tmpMultiMediaItem.setUrl(_tmpUrl);
          final int _tmpHeight;
          _tmpHeight = _cursor.getInt(_cursorIndexOfHeight);
          _tmpMultiMediaItem.setHeight(_tmpHeight);
          final int _tmpWidth;
          _tmpWidth = _cursor.getInt(_cursorIndexOfWidth);
          _tmpMultiMediaItem.setWidth(_tmpWidth);
        }  else  {
          _tmpMultiMediaItem = null;
        }
        _item = new NewsItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpPublishedDate;
        _tmpPublishedDate = _cursor.getString(_cursorIndexOfPublishedDate);
        _item.setPublishedDate(_tmpPublishedDate);
        _item.setMultiMediaItem(_tmpMultiMediaItem);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
