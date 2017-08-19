package com.example.guill.petagram.view.menu;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.guill.petagram.MainActivity;
import com.example.guill.petagram.R;
import com.example.guill.petagram.fragment.mascota.MascotaFragment;

public class ConfigurarCuenta extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tietAccount;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        res = getResources();

        tietAccount = (TextInputEditText) findViewById(R.id.etCuenta);
        btnSave = (Button) findViewById(R.id.btnAcceder);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = tietAccount.getText().toString();
                if(account.isEmpty()) {
                    tietAccount.setError(res.getString(R.string.mensaje_vacio));
                } else {
                    String key = res.getString(R.string.shared_preferences_usuario);
                    String defaultAccount = res.getString(R.string.cuenta_por_defecto);
                    String fileName = res.getString(R.string.shared_preferences_nombre_archivo);

                    if(MascotaFragment.CUENTAS_SANDBOX.contains(account) || account.equals(defaultAccount)) {
                        MainActivity.guardarPreferencias(ConfigurarCuenta.this, fileName, key, account);
                        Intent intent = new Intent(ConfigurarCuenta.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Snackbar.make(v, res.getString(R.string.mensaje_usuario_no_valido), Snackbar.LENGTH_LONG)
                                .show();
                    }
                }
            }
        });
    }

}
