package com.example.guill.petagram.db;

/**
 * Created by guill on 17/08/2017.
 */
public class BaseDatos {

    public static final String DB_NAME = "petagram";
    public static final int DB_VERSION = 1;

    public static final String TABLE_PET = "pet";
    public static final String TABLE_PET_ALIAS = "pt";
    public static final String TABLE_PET_ID = "id";
    public static final String TABLE_PET_NAME = "name";
    public static final String TABLE_PET_PHOTO = "photo";

    public static final String TABLE_LIKES_PET = "likes_pet";
    public static final String TABLE_LIKES_PET_ALIAS = "lkpt";
    public static final String TABLE_LIKES_PET_ID = "id_pet";
    public static final String TABLE_LIKES_PET_LIKES = "likes";
}
