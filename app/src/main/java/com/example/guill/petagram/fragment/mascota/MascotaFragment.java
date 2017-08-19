package com.example.guill.petagram.fragment.mascota;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.guill.petagram.R;
import com.example.guill.petagram.adapter.MascotaAdaptador;
import com.example.guill.petagram.pojo.Mascota;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MascotaFragment extends Fragment implements IMascotaFragmentView {

    public static final List<String> CUENTAS_SANDBOX = Arrays.asList("perritocollie", "mascotas.coursera");
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MascotaFragmentPresenter presenter;
    private LinearLayout linearLayout;
    private Context context;
    private View view;
    private ProgressBar pbProgress;
    private ProgressDialog progressDialog;

    public MascotaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mascotas, container, false);
        context = getContext();
        presenter = new MascotaFragmentPresenter(context, this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llMain);
        pbProgress = (ProgressBar) view.findViewById(R.id.pbProgress);
        presenter.obtenerTimeLine(CUENTAS_SANDBOX);
        return view;
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreateRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.rvMainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void setMascotas(List<Mascota> mascotas) {
        adapter = new MascotaAdaptador(mascotas, false, context);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
