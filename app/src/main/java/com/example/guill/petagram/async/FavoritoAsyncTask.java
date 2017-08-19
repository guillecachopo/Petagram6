package com.example.guill.petagram.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.guill.petagram.model.MascotaModel;
import com.example.guill.petagram.pojo.Mascota;
import com.example.guill.petagram.view.favourite.IFavoritoInteractor;
import com.example.guill.petagram.view.favourite.IFavoritoView;

import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */


public class FavoritoAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    private Context context;
    private IFavoritoView favoritoView;
    private MascotaModel mascotaModel;
    private IFavoritoInteractor.OnFavoritoFinishedListener listener;
    private List<Mascota> mascotas;

    public FavoritoAsyncTask(Context context, IFavoritoView favoritoView, IFavoritoInteractor.OnFavoritoFinishedListener listener) {
        this.context = context;
        this.favoritoView = favoritoView;
        this.listener = listener;
        mascotaModel = new MascotaModel(context);
    }

    @Override
    protected void onPreExecute() {
        favoritoView.showProgress();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        mascotas = mascotaModel.getFavourite(5);
        return mascotas.size();
    }

    @Override
    protected void onPostExecute(Integer result) {
        listener.onAlways();

        if(mascotas != null) {
            if(mascotas.isEmpty() || mascotas.size() == 0) {
                listener.onEmpty();
            } else {
                listener.onSuccess(mascotas);
            }
        } else {
            listener.onError();
        }
    }
}
