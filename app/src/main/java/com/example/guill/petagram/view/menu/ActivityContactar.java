package com.example.guill.petagram.view.menu;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.guill.petagram.R;
import com.example.guill.petagram.javamail.SendMail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class ActivityContactar extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText etNombre, etEmail, etMensaje;
    private Button btnComentario;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = (TextInputEditText) findViewById(R.id.tietName);
        etEmail = (TextInputEditText) findViewById(R.id.tietEmail);
        etMensaje = (TextInputEditText) findViewById(R.id.tietMessage);
        btnComentario=(Button) findViewById(R.id.btnEnviar);


        btnComentario.setOnClickListener(this);
    }

    private void sendEmail() {
        String asunto = etNombre.getText().toString().trim();
        String to = etEmail.getText().toString().trim();
        String mensaje = etMensaje.getText().toString().trim();

        SendMail sm = new SendMail(this, to, asunto, mensaje);
        sm.execute();
    }


    @Override
    public void onClick(View v) {
        sendEmail();
        etNombre.setText("");
        etEmail.setText("");
        etMensaje.setText("");

    }

}

