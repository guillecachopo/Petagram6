package com.example.guill.petagram.view.favourite;

import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 18/08/2017.
 */
public interface IFavoritoInteractor {

    interface OnFavoritoFinishedListener {
        void onAlways();

        void onError();

        void onEmpty();

        void onSuccess(List<Mascota> mascotas);

    }

    void obtenerMascotas(OnFavoritoFinishedListener listener);

}
