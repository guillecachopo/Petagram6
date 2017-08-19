package com.example.guill.petagram.restApi.deserialize;

import com.example.guill.petagram.pojo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.example.guill.petagram.restApi.JsonKeys;
import com.example.guill.petagram.restApi.model.UsuarioResponse;

import java.lang.reflect.Type;

/**
 * Created by guill on 18/08/2017.
 */
public class UsuarioDeserializador implements JsonDeserializer<UsuarioResponse> {
    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json, UsuarioResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.KEY_DATA);
        usuarioResponse.setUsuario(deserializeAccountFromJson(jsonArray, false));

        return usuarioResponse;
    }

    protected static Usuario deserializeAccountFromJson(JsonArray jsonArray, boolean profile) {
        Usuario usuario = null;

        if(jsonArray.size() > 0) {
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            if(profile) {
                jsonObject = jsonObject.get(JsonKeys.KEY_USER).getAsJsonObject();
            }

            String id = jsonObject.get(JsonKeys.KEY_USER_ID).getAsString();
            String nombre = jsonObject.get(JsonKeys.KEY_USER_NAME).getAsString();
            String nombreCompleto = jsonObject.get(JsonKeys.KEY_USER_FULL_NAME).getAsString();
            String fotoPerfilUrl = jsonObject.get(JsonKeys.KEY_USER_PROFILE_PICTURE_URL).getAsString();

            usuario = new Usuario(id, nombre, nombreCompleto, fotoPerfilUrl);
        }

        return usuario;
    }
}
