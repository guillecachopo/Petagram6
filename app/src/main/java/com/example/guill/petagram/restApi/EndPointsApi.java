package com.example.guill.petagram.restApi;

import com.example.guill.petagram.restApi.model.UsuarioResponse;
import com.example.guill.petagram.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by guill on 18/08/2017.
 */
public interface EndPointsApi {
    @GET(ConstantesRestApi.URL_GET_USER_DATA)
    Call<UsuarioResponse> getData(@Query("q") String user);

    @GET(ConstantesRestApi.URL_GET_USER_MEDIA_RECENT)
    Call<MascotaResponse> getRecentMediaByUser(@Path("user") String user);
}
