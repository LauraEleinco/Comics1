package com.sive.comics1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

        private EditText Password;
        private Button Entrar;
        private Button CrearCuenta;
        private Button BackDoor;
        private EditText Email;
        private TextView OlvideClave;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            Email = (EditText) findViewById(R.id.input_email);
            Password = (EditText) findViewById(R.id.input_password);

            Entrar = (Button) findViewById(R.id.btn_entrar);
            Entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Entrar();
                }
            });

            CrearCuenta = (Button) findViewById(R.id.btn_creacuenta);
            CrearCuenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreaCuenta();
                }
            });


        BackDoor = (Button) findViewById(R.id.btn_backdoor);
        BackDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackDoor();
            }
        });


            OlvideClave = (TextView) findViewById(R.id.olvidarcuenta);
            OlvideClave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    olvide();
                }
            });

            SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String pref_email = preferences.getString("pref_Email", "");
            String pref_pass = preferences.getString("pref_Pass", "");

            if ( !pref_email.equals("") && !pref_pass.equals("") ){
                //Recuperamos la información pasada en el intent
                Email.setText(pref_email);
                Password.setText(pref_pass);
               // opWebSer v = new opWebSer();
                //v.ConsultarUsuario(pref_email, pref_pass, this, true, false);
            }else{
                // Estos son los datos que se traen desde Registro
                Intent bundle = getIntent();

                if (bundle != null) {
                    //Construimos el mensaje a mostrar
                    Email.setText(bundle.getStringExtra("Email"));
                    Password.setText(bundle.getStringExtra("clave"));
                }
            }
        }

        public void BackDoor(){
            String s_nombre = "Prueba Usuario Eleinc";
            String s_celular = "3205186807";
            String s_correo = "app@gmail.com";
            String s_contrasena = "1234";

            SharedPreferences preferences = getSharedPreferences("credenciales",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("pref_Email", s_correo);
            editor.putString("pref_Pass", s_contrasena );
            editor.putString("pref_Nombre", s_nombre);
            editor.putString("pref_Celular", s_celular);
            editor.putBoolean("pref_hasLogin", true);
            editor.commit();

            Intent intent =new Intent(LoginActivity.this, MainActivity.class);
            //Iniciamos la nueva actividad
            startActivity(intent);
            finish();
        }

        public void Entrar() {
            String contrasena = Password.getText().toString();
            String s_email = Email.getText().toString();

            if (contrasena.isEmpty() || s_email.isEmpty()) {
                Password.setError(getResources().getString(R.string.campoobligatorio));
                Email.setError(getResources().getString(R.string.campoobligatorio));
                Snackbar snackbar = Snackbar.make(findViewById(R.id.login_xml), getResources().getString(R.string.todosloscampos), Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                snackbar.show();
            }else if( contrasena.length()<4 ){
                Password.setError("@strings/contraseñacorta");
                Snackbar snackbar = Snackbar.make(findViewById(R.id.login_xml), getResources().getString(R.string.contrasenacorta), Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                snackbar.show();
            } else if (s_email.length() > 0 && (
                    s_email.toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]") ||
                            s_email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]") ||
                            s_email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.co") ||
                            s_email.matches("[a-zA-Z0-9._-]+@[a-z]+.co") ||
                            s_email.matches("[a-zA-Z0-9._-]+@[a-z]+.es"))
                    ) {
                //opWebSer conexionSW = new opWebSer();
               // conexionSW.ConsultarUsuario(s_email, contrasena, this, false, false);
            }
        }


        //oldive mi cuenta
        public void olvide() {
            String s_email = Email.getText().toString();
            if(s_email.isEmpty()){
                Email.setError(getResources().getString(R.string.ingresecorreo));
            }else {
                // en caso de que este vacio, sacarla alerta de error
                if(s_email.equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.login_xml), getResources().getString(R.string.todosloscampos), Snackbar.LENGTH_LONG);
                    snackbar.getView().setBackgroundColor(getResources().getColor(R.color.redMaterialDesign));
                    snackbar.show();
                }

             //   opWebSer conexionSW = new opWebSer();
               // conexionSW.recuperarPass(s_email, this);
            }
        }


        //metodo que redirecciona a clase registro
        public void CreaCuenta() {
            Intent i = new Intent(LoginActivity.this, Registro.class);
            startActivity(i);
            finish();
        }

    }





