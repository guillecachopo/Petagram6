package com.example.guill.petagram.view.favourite;

import android.content.Context;

import com.example.guill.petagram.async.FavoritoAsyncTask;

/**
 * Created by guill on 18/08/2017.
 */
public class FavoritoInteractor implements IFavoritoInteractor {
    private Context context;
    private IFavoritoView favoritoView;

    public FavoritoInteractor(Context context, IFavoritoView favouriteView) {
        this.context = context;
        this.favoritoView= favoritoView;
    }

    @Override
    public void obtenerMascotas(OnFavoritoFinishedListener listener) {
        new FavoritoAsyncTask(context, favoritoView, listener).execute();
    }
}
