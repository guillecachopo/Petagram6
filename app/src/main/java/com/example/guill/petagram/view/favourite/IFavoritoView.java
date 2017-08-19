package com.example.guill.petagram.view.favourite;

import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

/**
 * Created by guill on 18/08/2017.
 */
public interface IFavoritoView {

    void showMessage(String message);

    void showProgress();

    void hideProgress();

    void createToolbar();

    void createRecyclerView();

    void setPets(List<Mascota> mascotas);
}
