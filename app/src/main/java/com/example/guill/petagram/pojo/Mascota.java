package com.example.guill.petagram.pojo;

import android.util.Log;

import java.util.List;

/**
 * Created by guill on 14/08/2017.
 */
public class Mascota {

    private int id;
    private String fotoId;
    private int foto;
    private String fotoUrl;
    private String nombre;
    private int likes = 0;

    public Mascota(int id, String nombre, int foto, int likes) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota(String nombre, int photo, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota(String fotoId, String nombre, String fotoUrl, int likes) {
        this.setFotoId(fotoId);
        this.fotoUrl = fotoUrl;
        this.nombre = nombre;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoId() {
        return fotoId;
    }

    public void setFotoId(String photoId) {
        this.fotoId = photoId;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int photo) {
        this.foto = foto;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public static Mascota crear(List<String> item) {
        Mascota mascota = null;

        try {
            int id = Integer.parseInt(item.get(0));
            String nombre = item.get(1);
            int foto = Integer.parseInt(item.get(2));
            int likes = Integer.parseInt(item.get(3));

            mascota = new Mascota(id, nombre, foto, likes);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Log.d("EXCEPTION", ex.getMessage());
        } catch (NumberFormatException ex) {
            Log.d("EXCEPTION", ex.getMessage());
        } catch (Exception ex) {
            Log.d("EXCEPTION", ex.getMessage());
        }

        return mascota;
    }
}
