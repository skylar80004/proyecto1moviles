package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AddRestaurant extends AppCompatActivity {



    public void OnClickButtonSiguiente(View view){


        String inputs[] = new String[10];

        EditText editTextNombre = findViewById(R.id.editTextNombreRest);
        EditText editTextTipoComida = findViewById(R.id.editTextTipoComida);
        EditText editTextSitioWeb = findViewById(R.id.editTextSitioWeb);
        EditText editTextTipoPrecio = findViewById(R.id.editTextTipoPrecio);
        EditText editTextTelefono = findViewById(R.id.editTextTelefono);

        String nombreRest = editTextNombre.getText().toString();
        String tipoComida = editTextTipoComida.getText().toString();
        String sitioWeb = editTextSitioWeb.getText().toString();
        String tipoPrecio = editTextTipoPrecio.getText().toString();
        String telefono = editTextTelefono.getText().toString();


        // Horario
        ArrayList<ArrayList> horarioArray = new ArrayList<>();

        ArrayList<String> horarioLunes = new ArrayList<>();
        ArrayList<String> horarioMartes = new ArrayList<>();
        ArrayList<String> horarioMiercoles = new ArrayList<>();
        ArrayList<String> horarioJueves = new ArrayList<>();
        ArrayList<String> horarioViernes = new ArrayList<>();
        ArrayList<String> horarioSabado = new ArrayList<>();
        ArrayList<String> horarioDomingo = new ArrayList<>();

        String dia,abre,cierra;
        TextView textViewDia;
        EditText editTextabreCierra;

        GridLayout gridLayoutHorario = findViewById(R.id.gridLayoutHorario);



        //Lunes

        textViewDia = (TextView) gridLayoutHorario.getChildAt(3);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(4);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(5);
        cierra = editTextabreCierra.getText().toString();

        horarioLunes.add(dia);
        horarioLunes.add(abre);
        horarioLunes.add(cierra);

        horarioArray.add(horarioLunes);



        // Martes

        textViewDia = (TextView)gridLayoutHorario.getChildAt(6);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(7);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(8);
        cierra = editTextabreCierra.getText().toString();

        horarioMartes.add(dia);
        horarioMartes.add(abre);
        horarioMartes.add(cierra);

        horarioArray.add(horarioMartes);

        // Miercoles

        textViewDia = (TextView)gridLayoutHorario.getChildAt(9);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(10);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(11);
        cierra = editTextabreCierra.getText().toString();

        horarioMiercoles.add(dia);
        horarioMiercoles.add(abre);
        horarioMiercoles.add(cierra);

        horarioArray.add(horarioMiercoles);

        // Jueves

        textViewDia = (TextView)gridLayoutHorario.getChildAt(12);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(13);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(14);
        cierra = editTextabreCierra.getText().toString();

        horarioJueves.add(dia);
        horarioJueves.add(abre);
        horarioJueves.add(cierra);

        horarioArray.add(horarioJueves);

        // Viernes

        textViewDia = (TextView)gridLayoutHorario.getChildAt(15);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(16);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(17);
        cierra = editTextabreCierra.getText().toString();

        horarioViernes.add(dia);
        horarioViernes.add(abre);
        horarioViernes.add(cierra);

        horarioArray.add(horarioViernes);

        // Sabado

        textViewDia = (TextView)gridLayoutHorario.getChildAt(18);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(19);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(20);
        cierra = editTextabreCierra.getText().toString();

        horarioSabado.add(dia);
        horarioSabado.add(abre);
        horarioSabado.add(cierra);

        horarioArray.add(horarioSabado);

        // Doming

        textViewDia = (TextView)gridLayoutHorario.getChildAt(21);
        dia = textViewDia.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(22);
        abre =  editTextabreCierra.getText().toString();
        editTextabreCierra = (EditText)gridLayoutHorario.getChildAt(23);
        cierra = editTextabreCierra.getText().toString();

        horarioDomingo.add(dia);
        horarioDomingo.add(abre);
        horarioDomingo.add(cierra);

        horarioArray.add(horarioDomingo);

        Log.i("GRID",horarioArray.toString());
        Intent intent = new Intent(this,AddRestaurantNext.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
    }
}
