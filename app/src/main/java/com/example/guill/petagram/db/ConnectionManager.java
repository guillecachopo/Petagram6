package com.example.guill.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */
public class ConnectionManager extends SQLiteOpenHelper implements IConnectionManager {

    Context context;

    public ConnectionManager(Context context) {
        super(context, BaseDatos.DB_NAME, null, BaseDatos.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTablePet = "CREATE TABLE " + BaseDatos.TABLE_PET + "("
                + BaseDatos.TABLE_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BaseDatos.TABLE_PET_NAME + " TEXT,"
                + BaseDatos.TABLE_PET_PHOTO + " INTEGER"
                +")";

        String createTableLikesPet = "CREATE TABLE " + BaseDatos.TABLE_LIKES_PET + "("
                + BaseDatos.TABLE_LIKES_PET_ID + " INTEGER,"
                + BaseDatos.TABLE_LIKES_PET_LIKES + " INTEGER,"
                + "FOREIGN KEY (" + BaseDatos.TABLE_LIKES_PET_ID + ") "
                + "REFERENCES " + BaseDatos.TABLE_PET + "(" + BaseDatos.TABLE_PET_ID + ")"
                + ")";

        db.execSQL(createTablePet);
        db.execSQL(createTableLikesPet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + BaseDatos.TABLE_PET);
        db.execSQL("DROP TABLE IF EXIST " + BaseDatos.TABLE_LIKES_PET);
        onCreate(db);
    }


    @Override
    public List<List<String>> list(String sql) {
        List<List<String>> list = null;
        List<String> subList = null;
        int count = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor != null) {
            list = new ArrayList<>();
            while (cursor.moveToNext()) {
                subList = new ArrayList<>();
                count = cursor.getColumnCount();

                for(int i = 0; i < count; i++) {
                    subList.add(cursor.getString(i));
                }

                list.add(subList);
            }
        }
        db.close();
        return list;
    }

    @Override
    public long insert(String table, ContentValues values) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        id = db.insert(table, null, values);
        db.close();
        return id;
    }

    @Override
    public int update(String table, ContentValues values, String where, String[] whereArgs) {
        int affectedRows = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        affectedRows = db.update(table, values, where, whereArgs);
        db.close();
        return affectedRows;
    }
}
