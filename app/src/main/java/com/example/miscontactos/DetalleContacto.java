package com.example.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DetalleContacto extends AppCompatActivity {

    Button btn_Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        btn_Edit = (Button) findViewById(R.id.btn_Edit);

        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("Nombre");
        String fecha = parametros.getString("Fecha");
        String telefono = parametros.getString("Telefono");
        String email = parametros.getString("Email");
        String descripcion = parametros.getString("Descripcion");

        TextView ecNombre = (TextView) findViewById(R.id.tvNombre);
        TextView ecFecha = (TextView) findViewById(R.id.tvFecha);
        TextView ecTelefono = (TextView) findViewById(R.id.tvTelefono);
        TextView ecEmail = (TextView) findViewById(R.id.tvemail);
        TextView ecDescripcion = (TextView) findViewById(R.id.tvdescripcion);

        ecNombre.setText(nombre);
        ecFecha.setText(fecha);
        ecTelefono.setText(telefono);
        ecEmail.setText(email);
        ecDescripcion.setText(descripcion);


        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(DetalleContacto.this, MainActivity.class);
                resultIntent.putExtra("ed_Nombre", ecNombre.getText().toString());
                resultIntent.putExtra("ed_Fecha", ecFecha.getText().toString());
                resultIntent.putExtra("ed_Telefono", ecTelefono.getText().toString());
                resultIntent.putExtra("ed_Email", ecEmail.getText().toString());
                resultIntent.putExtra("ed_Descripcion", ecDescripcion.getText().toString());
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });

    }
}