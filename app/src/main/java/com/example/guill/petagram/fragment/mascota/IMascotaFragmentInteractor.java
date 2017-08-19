package com.example.guill.petagram.fragment.mascota;

import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 18/08/2017.
 */
public interface IMascotaFragmentInteractor {

    interface OnMascotaFragmentFinishedListener {
        void onAlways();

        void onError();

        void onEmpty();

        void onSuccess(List<Mascota> mascotas);
    }

    void obtenerMascotas(OnMascotaFragmentFinishedListener listener);
}
