package com.example.guill.petagram.fragment.mascota;

import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 16/08/2017.
 */
public interface IMascotaFragmentView {
    void showProgress();

    void hideProgress();

    void onCreateRecyclerView();

    void setMascotas(List<Mascota> mascotas);

    void showMessage(String message);
}
