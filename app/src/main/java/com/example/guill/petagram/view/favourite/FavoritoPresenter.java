package com.example.guill.petagram.view.favourite;

import android.content.Context;

import com.example.guill.petagram.R;
import com.example.guill.petagram.async.FavoritoAsyncTask;
import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 18/08/2017.
 */
public class FavoritoPresenter implements IFavoritoPresenter, IFavoritoInteractor.OnFavoritoFinishedListener {

    private IFavoritoView favoritoView;
    private Context context;
    private IFavoritoInteractor favoritoInteractor;

    public FavoritoPresenter(Context context, IFavoritoView favoritoView) {
        this.context = context;
        this.favoritoView = favoritoView;
        this.favoritoInteractor = new FavoritoInteractor(context, favoritoView);
    }

    @Override
    public void obtenerMascotas() {
        new FavoritoAsyncTask(context, favoritoView, this).execute();
    }

    @Override
    public void onDestroy() {
        favoritoView = null;
    }

    @Override
    public void onAlways() {
        favoritoView.hideProgress();
    }

    @Override
    public void onError() {
        favoritoView.showMessage(context.getResources().getString(R.string.mensaje_error_mascota));
    }

    @Override
    public void onEmpty() {
        favoritoView.showMessage(context.getResources().getString(R.string.mensaje_mascota_vacia));
    }

    @Override
    public void onSuccess(List<Mascota> mascotas) {
        favoritoView.setPets(mascotas);
    }
}
