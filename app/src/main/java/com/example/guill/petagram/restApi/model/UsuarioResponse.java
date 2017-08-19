package com.example.guill.petagram.restApi.model;

import com.example.guill.petagram.pojo.Usuario;

/**
 * Created by guill on 18/08/2017.
 */
public class UsuarioResponse {
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private Usuario usuario;
}
