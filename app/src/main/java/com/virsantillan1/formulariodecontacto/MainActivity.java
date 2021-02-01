package com.virsantillan1.formulariodecontacto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity   {

    EditText nombre;
    DatePicker fecha;
    EditText telefono;
    EditText email;
    EditText descripcion;
    int sesion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.inputNombre);
        fecha =  findViewById(R.id.inputFecha);
        telefono =  findViewById(R.id.inputTelefono);
        email = findViewById(R.id.inputEmail);
        descripcion =  findViewById(R.id.inputDescripcion);

        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        int valorRestaurado = preferencias.getInt(String.valueOf(sesion),0);
        if(valorRestaurado == 1){
            restaurarDatos();
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putInt("sesion",0);
            editor.apply();

        }

    }

    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this,ConfirmarDatos.class);
        intent.putExtra(getResources().getString(R.string.nombre),nombre.getText().toString());
        intent.putExtra("anio",String.valueOf(fecha.getYear()));
        intent.putExtra("mes",String.valueOf(fecha.getMonth()));
        intent.putExtra("dia",String.valueOf(fecha.getDayOfMonth()));
        intent.putExtra(getResources().getString(R.string.telefono),telefono.getText().toString());
        intent.putExtra(getResources().getString(R.string.email),email.getText().toString());
        intent.putExtra(getResources().getString(R.string.descripcion),descripcion.getText().toString());

        startActivity(intent);
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("sesion",1);
        editor.apply();

        finish();
    }



    public void restaurarDatos(){
        Bundle parametros = getIntent().getExtras();
        Bundle bundleConfirmarDatos = parametros.getBundle("bundle");

        String nombre1 = bundleConfirmarDatos.getString(getResources().getString(R.string.nombre));
        String anio1 = bundleConfirmarDatos.getString("anio");
        String mes1 = bundleConfirmarDatos.getString("mes");
        String dia1 = bundleConfirmarDatos.getString("dia");
        String telefono1 =bundleConfirmarDatos.getString(getResources().getString(R.string.telefono));
        String email1 = bundleConfirmarDatos.getString(getResources().getString(R.string.email));
        String descripcion1 = bundleConfirmarDatos.getString(getResources().getString(R.string.descripcion));

        nombre.setText(nombre1);
        fecha.updateDate(Integer.parseInt(anio1),Integer.parseInt(mes1),Integer.parseInt(dia1));
        telefono.setText(telefono1);
        email.setText(email1);
        descripcion.setText(descripcion1);

    }

}