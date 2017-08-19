package com.example.guill.petagram.pojo;

/**
 * Created by guill on 17/08/2017.
 */
public class Usuario {
    private String id;
    private String nombre;
    private String nombreCompleto;
    private String fotoPerfilUrl;

    public Usuario(String id, String nombre, String fullName, String fotoPerfilUrl) {
        this.setId(id);
        this.setNombre(nombre);
        this.setNombreCompleto(nombreCompleto);
        this.setFotoPerfilUrl(fotoPerfilUrl);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public void setFotoPerfilUrl(String fotoPerfilUrl) {
        this.fotoPerfilUrl = fotoPerfilUrl;
    }
}
