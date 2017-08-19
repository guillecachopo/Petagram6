package com.example.guill.petagram.fragment.perfil;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guill.petagram.R;
import com.example.guill.petagram.adapter.MascotaAdaptador;
import com.example.guill.petagram.pojo.Mascota;
import com.example.guill.petagram.pojo.Usuario;
import com.example.guill.petagram.restApi.EndPointsApi;
import com.example.guill.petagram.restApi.adapter.RestApiAdapter;
import com.example.guill.petagram.restApi.model.UsuarioResponse;
import com.example.guill.petagram.restApi.model.MascotaResponse;
import com.example.guill.petagram.MainActivity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Resources res;
    private View view;
    private Context context;
    private ArrayList<Mascota> mascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        res = getResources();
        context = getActivity();
        mascotas = new ArrayList<>();
        String usuarioActual = MainActivity.getCurrentAccount(getActivity(), res.getString(R.string.shared_preferences_nombre_archivo));
        obtenerDatosUsuario(usuarioActual);
        return view;
    }


    private void createProfile() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rvMainRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MascotaAdaptador(mascotas, true, getContext());
        recyclerView.setAdapter(adapter);
    }

    public void obtenerDatosUsuario(String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(restApiAdapter.construyeGsonDeserializadorUsuario());

        Call<UsuarioResponse> usuarioResponseCall = endPointsApi.getData(user);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Usuario usuario = usuarioResponse.getUsuario();

                if(usuario != null) {
                    TextView tvPetName = (TextView) view.findViewById(R.id.tvPetName);
                    tvPetName.setText(usuario.getNombre());

                    CircularImageView civPerfil = (CircularImageView) view.findViewById(R.id.civPerfil);
                    Picasso.with(context)
                            .load(usuario.getFotoPerfilUrl())
                            .placeholder(R.drawable.loader)
                            .error(R.drawable.default_pet)
                            .resizeDimen(R.dimen.timeline_image_width, R.dimen.timeline_image_height)
                            .into(civPerfil);

                    obtenerMediaRecienteUsuario(usuario.getId());
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(context, context.getResources().getString(R.string.mensaje_api_error), Toast.LENGTH_LONG).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }

    private void obtenerMediaRecienteUsuario(String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(restApiAdapter.construyeGsonDeserializadorMascota());
        Call<MascotaResponse> petResponseCall = endPointsApi.getRecentMediaByUser(user);
        petResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                createProfile();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, context.getResources().getString(R.string.mensaje_api_error), Toast.LENGTH_LONG).show();
                Log.e("Falló la conexión", t.toString());
            }
        });
    }
}