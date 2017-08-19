package com.example.guill.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.guill.petagram.restApi.ConstantesRestApi;
import com.example.guill.petagram.restApi.EndPointsApi;
import com.example.guill.petagram.restApi.deserialize.UsuarioDeserializador;
import com.example.guill.petagram.restApi.deserialize.MascotaDeserializador;
import com.example.guill.petagram.restApi.model.UsuarioResponse;
import com.example.guill.petagram.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by guill on 18/08/2017.
 */
public class RestApiAdapter {

    public EndPointsApi establecerConexionRestApiInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointsApi.class);
    }

    public Gson construyeGsonDeserializadorMascota() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUsuario() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioDeserializador());

        return gsonBuilder.create();
    }
}
