package com.example.guill.petagram.db;

import android.content.ContentValues;

import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */
public interface IConnectionManager {

    List<List<String>> list(String sql);

    long insert(String table, ContentValues values);

    int update(String table, ContentValues values, String where, String[] whereArgs);
}
