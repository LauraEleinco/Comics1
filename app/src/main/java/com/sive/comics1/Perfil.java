package com.sive.comics1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {

    private String pref_email;
    private String pref_pass;
    private String pref_nombre;
    private String pref_celular;
    final Context tempContext = this;

    TextView et_nombre, et_celular, et_email;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        pref_email = preferences.getString("pref_Email", "");
        pref_pass = preferences.getString("pref_Pass", "");
        pref_nombre = preferences.getString("pref_Nombre", "");
        pref_celular = preferences.getString("pref_Celular", "");

        et_nombre = (TextView) findViewById(R.id.tv_nombrePerfil);
        et_celular = (TextView) findViewById(R.id.tv_celularPerfil);
        et_email = (TextView) findViewById(R.id.tv_emailPerfil);

        if ( !pref_email.equals("") && !pref_pass.equals("") && !pref_nombre.equals("") && !pref_celular.equals("")) {
            //Recuperamos la información pasada en el intent
            et_email.setText(pref_email);
            et_celular.setText(pref_celular);
            et_nombre.setText(pref_nombre);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarperfil);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevaAtivity = new Intent(tempContext, MainActivity.class);
                startActivity(nuevaAtivity);
                finish();
            }
        });

    }


    //boton atras
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(Perfil.this, MainActivity.class);
            startActivity(nuevaAtivity);
            finish();
        }
        return true;
    }

}
