package com.virsantillan1.formulariodecontacto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmarDatos extends AppCompatActivity {

    TextView nombre;
    TextView fecha;
    TextView telefono;
    TextView email;
    TextView descripcion;
    Bundle parametros;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        parametros = getIntent().getExtras();
        String nombre1 = parametros.getString(getResources().getString(R.string.nombre));
        String dia1 = parametros.getString("dia");
        String mes1 = parametros.getString("mes");
        String anio1 = parametros.getString("anio");
        String telefono1 =parametros.getString(getResources().getString(R.string.telefono));
        String email1 = parametros.getString(getResources().getString(R.string.email));
        String descripcion1 = parametros.getString(getResources().getString(R.string.descripcion));

        nombre =  findViewById(R.id.confirmarNombre);
        fecha =  findViewById(R.id.confirmarFecha);
        telefono =  findViewById(R.id.confirmarTelefono);
        email =  findViewById(R.id.confirmarEmail);
        descripcion =  findViewById(R.id.confirmarDescripcion);

        nombre.setText(nombre1);
        fecha.setText(dia1 + "/" + mes1 + "/" + anio1);
        telefono.setText(telefono1);
        email.setText(email1);
        descripcion.setText(descripcion1);

    }

    public void onClick(View v){

        Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
        intent.putExtra("bundle", parametros);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sesion", 1);
    }
}
