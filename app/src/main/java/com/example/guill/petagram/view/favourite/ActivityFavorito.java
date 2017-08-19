package com.example.guill.petagram.view.favourite;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.guill.petagram.R;
import com.example.guill.petagram.adapter.MascotaAdaptador;
import com.example.guill.petagram.pojo.Mascota;

import java.util.List;

public class ActivityFavorito extends AppCompatActivity implements IFavoritoView {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager layoutManager;

    private Toolbar toolbar;
    private ProgressBar pbProgressBar;
    private View view;
    private FavoritoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        view = getCurrentFocus();
        presenter = new FavoritoPresenter(getApplicationContext(), this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        pbProgressBar = (ProgressBar) findViewById(R.id.pbProgress);
        createToolbar();
        createRecyclerView();
    }

    @Override
    public void createToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tbMainToolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void createRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvMainRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        presenter.obtenerMascotas();
    }

    @Override
    public void setPets(List<Mascota> mascotas) {
        adaptador = new MascotaAdaptador(mascotas, false, getApplicationContext());
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        pbProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
