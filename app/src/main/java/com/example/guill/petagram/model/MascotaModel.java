package com.example.guill.petagram.model;

import android.content.ContentValues;
import android.content.Context;

import com.example.guill.petagram.db.BaseDatos;
import com.example.guill.petagram.db.ConnectionManager;
import com.example.guill.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */
public class MascotaModel {

    Context context;
    List<Mascota> mascotas;
    ConnectionManager connectionManager;

    public MascotaModel(Context context) {
        this.context = context;
        mascotas = new ArrayList<>();
        connectionManager = new ConnectionManager(context);
    }

    public List<Mascota> getAll() {
        String sql = "SELECT " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_ID
                    + ", " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_NAME
                    + ", " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_PHOTO
                    + ", " + BaseDatos.TABLE_LIKES_PET_ALIAS + "." + BaseDatos.TABLE_LIKES_PET_LIKES
                    + " FROM " + BaseDatos.TABLE_PET + " AS " + BaseDatos.TABLE_PET_ALIAS
                    + " INNER JOIN " + BaseDatos.TABLE_LIKES_PET + " AS " + BaseDatos.TABLE_LIKES_PET_ALIAS
                    + " ON " + BaseDatos.TABLE_LIKES_PET_ALIAS + "." + BaseDatos.TABLE_LIKES_PET_ID
                    + " = " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_ID
                    + " ORDER BY " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_ID;

        List<List<String>> list = connectionManager.list(sql);

        for (List<String> item : list) {
            mascotas.add(Mascota.crear(item));
        }

        return mascotas;
    }

    public List<Mascota> getFavourite(int limit) {
        String sql = "SELECT " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_ID
                + ", " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_NAME
                + ", " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_PHOTO
                + ", " + BaseDatos.TABLE_LIKES_PET_ALIAS + "." + BaseDatos.TABLE_LIKES_PET_LIKES
                + " FROM " + BaseDatos.TABLE_PET + " AS " + BaseDatos.TABLE_PET_ALIAS
                + " INNER JOIN " + BaseDatos.TABLE_LIKES_PET + " AS " + BaseDatos.TABLE_LIKES_PET_ALIAS
                + " ON " + BaseDatos.TABLE_LIKES_PET_ALIAS + "." + BaseDatos.TABLE_LIKES_PET_ID
                + " = " + BaseDatos.TABLE_PET_ALIAS + "." + BaseDatos.TABLE_PET_ID
                + " ORDER BY " + BaseDatos.TABLE_LIKES_PET_ALIAS + "." + BaseDatos.TABLE_LIKES_PET_LIKES + " DESC"
                + " LIMIT " + limit;

        List<List<String>> list = connectionManager.list(sql);

        for (List<String> item : list) {
            mascotas.add(Mascota.crear(item));
        }

        return mascotas;
    }

    public int addPet(Mascota mascota) {
        int id = -1;
        ContentValues values = new ContentValues();
        values.put(BaseDatos.TABLE_PET_NAME, mascota.getNombre());
        values.put(BaseDatos.TABLE_PET_PHOTO, mascota.getFoto());

        id = (int) connectionManager.insert(BaseDatos.TABLE_PET, values);
        mascota.setId(id);

        if(id != -1) {
            values = new ContentValues();
            values.put(BaseDatos.TABLE_LIKES_PET_ID, mascota.getId());
            values.put(BaseDatos.TABLE_LIKES_PET_LIKES, mascota.getLikes());
            connectionManager.insert(BaseDatos.TABLE_LIKES_PET, values);
        }

        return id;
    }

    public int updatePet(Mascota mascota) {
        int affectedRows = -1;
        String where = BaseDatos.TABLE_PET_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(mascota.getId())};

        ContentValues values = new ContentValues();
        values.put(BaseDatos.TABLE_PET_NAME, mascota.getNombre());
        values.put(BaseDatos.TABLE_PET_PHOTO, mascota.getFoto());

        affectedRows = connectionManager.update(BaseDatos.TABLE_PET, values, where, whereArgs);

        return affectedRows;
    }

    public int setLikes(Mascota mascota) {
        int affectedRows = -1;
        String where = BaseDatos.TABLE_LIKES_PET_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(mascota.getId())};

        ContentValues values = new ContentValues();
        values.put(BaseDatos.TABLE_LIKES_PET_LIKES, mascota.getLikes());

        affectedRows = connectionManager.update(BaseDatos.TABLE_LIKES_PET, values, where, whereArgs);

        return affectedRows;
    }
}
