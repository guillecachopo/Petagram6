package com.example.guill.petagram.restApi.deserialize;

import com.example.guill.petagram.pojo.Mascota;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.example.guill.petagram.restApi.JsonKeys;
import com.example.guill.petagram.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by guill on 18/08/2017.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.KEY_DATA);
        mascotaResponse.setMascotas(deserializarMascotaDeJson(jsonArray));

        return mascotaResponse;
    }

    protected static ArrayList<Mascota> deserializarMascotaDeJson(JsonArray jsonArray) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        Mascota mascota;
        String photoId = "";
        String name = "";
        String photoUrl = "";
        int likes = 0;

        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            JsonObject jsonUser = jsonObject.get(JsonKeys.KEY_USER).getAsJsonObject();
            JsonObject jsonLikes = jsonObject.get(JsonKeys.KEY_LIKES).getAsJsonObject();
            JsonObject jsonImages = jsonObject.get(JsonKeys.KEY_IMAGES).getAsJsonObject();
            JsonObject jsonStandardResolution= jsonImages.get(JsonKeys.KEY_STANDARD_RESOLUTION).getAsJsonObject();

            photoId = jsonObject.get(JsonKeys.KEY_ID).getAsString();
            photoUrl = jsonStandardResolution.get(JsonKeys.KEY_URL).getAsString();
            likes = jsonLikes.get(JsonKeys.KEY_LIKES_COUNT).getAsInt();
            name = jsonUser.get(JsonKeys.KEY_USER_NAME).getAsString();

            mascota = new Mascota(photoId, name, photoUrl, likes);
            mascotas.add(mascota);
        }

        return mascotas;
    }
}
