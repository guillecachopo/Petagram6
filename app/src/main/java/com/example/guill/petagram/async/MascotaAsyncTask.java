package com.example.guill.petagram.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.guill.petagram.fragment.mascota.IMascotaFragmentInteractor;
import com.example.guill.petagram.fragment.mascota.IMascotaFragmentView;
import com.example.guill.petagram.model.MascotaModel;
import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 17/08/2017.
 */


public class MascotaAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    private Context context;
    private IMascotaFragmentView mascotaFragmentView;
    private MascotaModel mascotaModel;
    private IMascotaFragmentInteractor.OnMascotaFragmentFinishedListener listener;
    private List<Mascota> mascotas;

    public MascotaAsyncTask(Context context, IMascotaFragmentView mascotaFragmentView, IMascotaFragmentInteractor.OnMascotaFragmentFinishedListener listener) {
        this.context = context;
        this.mascotaFragmentView = mascotaFragmentView;
        this.listener = listener;
        mascotaModel = new MascotaModel(context);
    }

    @Override
    protected void onPreExecute() {
        mascotaFragmentView.showProgress();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        mascotas = mascotaModel.getAll();

        if(mascotas.isEmpty()) {
            //insertDummy();
        }

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
