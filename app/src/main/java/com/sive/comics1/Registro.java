package com.sive.comics1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Registro extends Activity {

    EditText etv_nombre;
    EditText etv_celular;
    EditText etv_email;
    EditText etv_pass;
    EditText etv_confirmacion;

    String nombre;
    String celular;
    String email;
    String pass;
    String confirmacion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etv_nombre = (EditText) findViewById(R.id.et_nombre);
        etv_celular = (EditText) findViewById(R.id.et_celular);
        etv_email = (EditText) findViewById(R.id.et_email);
        etv_pass = (EditText) findViewById(R.id.et_clave);
        etv_confirmacion = (EditText) findViewById(R.id.et_confirmacion);

        Button registrar = (Button) findViewById(R.id.btn_Registro);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntrarRegistro();
            }
        });
        TextView entrar = (TextView) findViewById(R.id.link_entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrar();
            }
        });
    }

    public void EntrarRegistro() {
        //metodo para registrarse e ingresar a la pagina
        nombre = etv_nombre.getText().toString();
        celular = etv_celular.getText().toString();
        email = etv_email.getText().toString();
        pass = etv_pass.getText().toString();
        confirmacion = etv_confirmacion.getText().toString();

        if (!nombre.isEmpty() && !celular.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !confirmacion.isEmpty()) {
            if (celular.trim().length() == 10) {
                if (email.length() > 0 && (
                        email.toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]") ||
                                email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]") ||
                                email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.co") ||
                                email.matches("[a-zA-Z0-9._-]+@[a-z]+.co") ||
                                email.matches("[a-zA-Z0-9._-]+@[a-z]+.es"))
                        ) {
                    if( pass.length() >= 4 ) {
                        if (pass.equals(confirmacion)) { // La contraseña se compara con la confirmacion aqui

                            //opWebSer conexionSW = new opWebSer();
                            //conexionSW.registrarUsuario(celular, nombre, email, pass, this);

                        } else {
                            etv_pass.setError(getResources().getString(R.string.contraseñasnocoinciden));
                            etv_confirmacion.setError(getResources().getString(R.string.contraseñasnocoinciden));
                            etv_pass.setText("");
                            etv_confirmacion.setText("");
                            Snackbar snackbar = Snackbar.make(findViewById(R.id.registro_layout), getResources().getString(R.string.contraeñasiguales), Snackbar.LENGTH_LONG);
                            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                            snackbar.show();
                        }
                    }else{
                        etv_pass.setError("Contraseña demasiado corta");
                        Snackbar snackbar = Snackbar.make(findViewById(R.id.registro_layout), "Contraseña demasiado corta", Snackbar.LENGTH_LONG);
                        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                        snackbar.show();
                    }
                } else {
                    etv_email.setError("Formato de correo inválido");
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.registro_layout), getResources().getString(R.string.validarcorreo), Snackbar.LENGTH_LONG);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                    snackbar.show();
                }
            } else {
                etv_celular.setError("Corrija la longitud del número");
                Snackbar snackbar = Snackbar.make(findViewById(R.id.registro_layout), getResources().getString(R.string.validarcelular), Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                snackbar.show();
            }
        } else {
            if (nombre.isEmpty()) {
                etv_nombre.setError(getResources().getString(R.string.campoobligatorio));
            }
            if (celular.isEmpty()) {
                etv_celular.setError(getResources().getString(R.string.campoobligatorio));
            }
            if (email.isEmpty()) {
                etv_email.setError(getResources().getString(R.string.campoobligatorio));
            }
            if (pass.isEmpty()) {
                etv_pass.setError(getResources().getString(R.string.campoobligatorio));
            }
            if (confirmacion.isEmpty()) {
                etv_confirmacion.setError(getResources().getString(R.string.campoobligatorio));
            }
        }
    }


    public void entrar() {
        //ya tienes una cuenta lleva a login
        Intent Ativity = new Intent(Registro.this, LoginActivity.class);
        startActivity(Ativity);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent nuevaAtivity = new Intent(Registro.this, LoginActivity.class);
            startActivity(nuevaAtivity);
            finish();
        }
        return true;
    }
}



