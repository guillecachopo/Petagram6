package com.example.guill.petagram.fragment.mascota;

import java.util.List;

/**
 * Created by guill on 16/08/2017.
 */
public interface IMascotaFragmentPresenter {
    void obtenerMascotas();

    void obtenerMediaRecienteUsuario(String user);

    void obtenerTimeLine(List<String> accounts);

    void onDestroy();
}
