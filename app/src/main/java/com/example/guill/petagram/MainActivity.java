package com.example.guill.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.guill.petagram.adapter.PageAdapter;
import com.example.guill.petagram.fragment.mascota.MascotaFragment;
import com.example.guill.petagram.fragment.perfil.PerfilFragment;
import com.example.guill.petagram.view.menu.ActividadAcerca;
import com.example.guill.petagram.view.menu.ActivityContactar;
import com.example.guill.petagram.view.favourite.ActivityFavorito;
import com.example.guill.petagram.view.main.IMainView;
import com.example.guill.petagram.view.menu.ConfigurarCuenta;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private PageAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreateToolbar();
        onCreateFragments();
        onCreatePageAdapter();
        onCreateViewPager();
        onCreateTabLayout();
        onCreateTabs();

        getCurrentAccount(this, getResources().getString(R.string.shared_preferences_nombre_archivo));
        ImageView imgvFavorites = (ImageView) findViewById(R.id.imgvFavourite);
        imgvFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityFavorito.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_contacto:
                startActivity(new Intent(MainActivity.this, ActivityContactar.class));
                return true;
            case R.id.action_acerca_de:
                startActivity(new Intent(MainActivity.this, ActividadAcerca.class));
                return true;
            case R.id.action_configurar_cuenta:
                startActivity(new Intent(MainActivity.this, ConfigurarCuenta.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToFavorites() {
        startActivity(new Intent(MainActivity.this, ActivityFavorito.class));
    }

    @Override
    public void onCreateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tbMainToolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateViewPager() {
        if(adapter != null) {
            viewPager = (ViewPager) findViewById(R.id.vpMainViewPager);
            viewPager.setAdapter(adapter);
        }
    }

    @Override
    public void onCreateTabLayout() {
        if(viewPager != null) {
            tabLayout = (TabLayout) findViewById(R.id.tlMainTabLayout);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onCreateTabs() {
        if(tabLayout != null) {
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_inicio);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
        }
    }

    @Override
    public void onCreatePageAdapter() {
        if(fragments != null) {
            adapter = new PageAdapter(getSupportFragmentManager(), fragments);
        }
    }

    @Override
    public void onCreateFragments() {
        fragments = Arrays.asList(
                new MascotaFragment(),
                new PerfilFragment()
        );
    }

    public static String getCurrentAccount(Context context, String nombreArchivo) {
        Resources resources = context.getResources();
        String usuarioPorDefecto = resources.getString(R.string.cuenta_por_defecto);
        String usuario = resources.getString(R.string.shared_preferences_usuario);
        String usuarioActual = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreArchivo, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(usuario, "").isEmpty()) {
            guardarPreferencias(context, nombreArchivo, usuario, usuarioPorDefecto);
        }

        usuarioActual = sharedPreferences.getString(usuario, usuarioPorDefecto);

        return usuarioActual;
    }

    public  static void guardarPreferencias(Context context, String nombreArchivo, String key, String usuario) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(nombreArchivo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, usuario);
        editor.commit();
    }
}
