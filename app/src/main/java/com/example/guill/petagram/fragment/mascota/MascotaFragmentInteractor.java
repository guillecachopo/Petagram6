package com.example.guill.petagram.fragment.mascota;

import android.content.Context;

import com.example.guill.petagram.async.MascotaAsyncTask;

/**
 * Created by guill on 15/08/2017.
 */
public class MascotaFragmentInteractor implements IMascotaFragmentInteractor {
    private Context context;
    private IMascotaFragmentView mascotaFragmentView;

    public MascotaFragmentInteractor(Context context, IMascotaFragmentView petFragmentView) {
        this.context = context;
        this.mascotaFragmentView = petFragmentView;
    }

    @Override
    public void obtenerMascotas(OnMascotaFragmentFinishedListener listener) {
        new MascotaAsyncTask(context, mascotaFragmentView, listener).execute();
    }
}
