package com.example.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextInputLayout lNombre,lFecha,lTelefono,lEmail,lDescripcion;
    TextInputEditText eNombre,mFechaNacimiento,eTelefono,eEmail,eDescripcion;
    Button btn_Sig;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        btn_Sig = (Button) findViewById(R.id.btn_Siguiente);

       mFechaNacimiento = findViewById(R.id.edtFecha);

        eNombre= findViewById(R.id.edtNombre);
        eTelefono= findViewById(R.id.edtTelefono);
        eEmail= findViewById(R.id.edtEmail);
        eDescripcion= findViewById(R.id.edtDescripcion);

        //Bundle parametros = getIntent().getExtras();


       Calendar calendar = Calendar.getInstance();
       final int year = calendar.get(Calendar.YEAR);
       final int month = calendar.get(Calendar.MONTH);
       final int day = calendar.get(Calendar.DAY_OF_MONTH);

       mFechaNacimiento.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatePickerDialog datePickerDialog = new DatePickerDialog(
                       MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                       onDateSetListener,year,month,day);
               datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               datePickerDialog.show();
           }
       });
       onDateSetListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
               month = month +1;
               String date = dayofMonth+"/"+month+"/"+year;
               mFechaNacimiento.setText(date);
           }
       };

       btn_Sig.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              // startActivityForResult(new Intent(getApplicationContext()),);

               Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
               intent.putExtra("Nombre", eNombre.getText().toString());
               intent.putExtra("Fecha", mFechaNacimiento.getText().toString());
               intent.putExtra("Telefono", eTelefono.getText().toString());
               intent.putExtra("Email", eEmail.getText().toString());
               intent.putExtra("Descripcion", eDescripcion.getText().toString());


               startActivityForResult(intent,1);
           }
       });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK){
                String ma_nombre = data.getStringExtra("ed_Nombre");
                String ma_fecha = data.getStringExtra("ed_Fecha");
                String ma_telefono = data.getStringExtra("ed_Telefono");
                String ma_email = data.getStringExtra("ed_Email");
                String ma_descripcion = data.getStringExtra("ed_Descripcion");

                eNombre.setText(ma_nombre);
                mFechaNacimiento.setText(ma_fecha);
                eTelefono.setText(ma_telefono);
                eEmail.setText(ma_email);
                eDescripcion.setText(ma_descripcion);
            }
        }
    }
}