package com.example.guill.petagram.fragment.mascota;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.guill.petagram.R;
import com.example.guill.petagram.pojo.Mascota;
import com.example.guill.petagram.restApi.EndPointsApi;
import com.example.guill.petagram.restApi.adapter.RestApiAdapter;
import com.example.guill.petagram.restApi.model.UsuarioResponse;
import com.example.guill.petagram.restApi.model.MascotaResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by guill on 15/08/2017.
 */
public class MascotaFragmentPresenter implements IMascotaFragmentPresenter, IMascotaFragmentInteractor.OnMascotaFragmentFinishedListener {

    private IMascotaFragmentView mascotaFragmentView;
    private IMascotaFragmentInteractor mascotaFragmentInterator;
    private Context context;
    private ArrayList<Mascota> mascotas;
    private int contador = 0;

    public MascotaFragmentPresenter(Context context, IMascotaFragmentView mascotaFragmentView) {
        this.context = context;
        this.mascotaFragmentView = mascotaFragmentView;
        this.mascotaFragmentInterator = new MascotaFragmentInteractor(context, mascotaFragmentView);
        this.mascotas = new ArrayList<>();
    }

    @Override
    public void obtenerMascotas() {
        mascotaFragmentInterator.obtenerMascotas(this);
    }

    @Override
    public void obtenerMediaRecienteUsuario(String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(restApiAdapter.construyeGsonDeserializadorMascota());
        Call<MascotaResponse> petResponseCall = endPointsApi.getRecentMediaByUser(user);
        petResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas.addAll(mascotaResponse.getMascotas());
                validaContador();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, context.getResources().getString(R.string.mensaje_api_error), Toast.LENGTH_LONG).show();
                Log.e("FALLO LA COENXION", t.toString());
                validaContador();
            }
        });
    }

    @Override
    public void obtenerTimeLine(List<String> usuarios) {
        contador = usuarios.size();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(restApiAdapter.construyeGsonDeserializadorUsuario());

        for(String usuario : usuarios) {
            Call<UsuarioResponse> accountResponseCall = endPointsApi.getData(usuario);
            accountResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse = response.body();
                    obtenerMediaRecienteUsuario(usuarioResponse.getUsuario().getId());
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    Toast.makeText(context, context.getResources().getString(R.string.mensaje_api_error), Toast.LENGTH_LONG).show();
                    Log.e("FALLO LA CONEXION", t.toString());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        mascotaFragmentView = null;
    }

    @Override
    public void onAlways() {
        mascotaFragmentView.hideProgress();
    }

    @Override
    public void onError() {
        mascotaFragmentView.showMessage(context.getResources().getString(R.string.mensaje_error_mascota));
    }

    @Override
    public void onEmpty() {
        mascotaFragmentView.showMessage(context.getResources().getString(R.string.mensaje_mascota_vacia));
    }

    @Override
    public void onSuccess(List<Mascota> mascotas) {
        mascotaFragmentView.onCreateRecyclerView();
        mascotaFragmentView.setMascotas(mascotas);
    }

    private void validaContador() {
        contador--;

        if(contador == 0) {
            onSuccess(mascotas);
        }
    }
}
