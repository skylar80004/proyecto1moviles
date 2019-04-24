package com.example.proyectorestaurantes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONObject;

public class AdvancedSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        //this.InitSpinnerCantidadEstrellas();
        this.InitSpinnerTipoPrecio();





    }


    private void InitSpinnerTipoPrecio(){

        Spinner spinner  = findViewById(R.id.spinnerBusquedaTipoPrecio);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerBusquedaTipoPrecio,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void OnClickButtonBusquedaTipoComida(View view){

        EditText editTextTipoComida =findViewById(R.id.editTextBusquedaTipoComida);
        String tipoComidaIngresada =  editTextTipoComida.getText().toString();

        Intent intent = new Intent(this, ResultAdvancedSearch.class);
        intent.putExtra("accion", "tipoComida");
        intent.putExtra("param", tipoComidaIngresada);
        startActivity(intent);


    }

    public void OnClickButtonBusquedaTipoPrecio(View view){

        Spinner spinner = findViewById(R.id.spinnerBusquedaTipoPrecio);
        String tipoPrecioSeleccionado = (String)spinner.getSelectedItem();

        Intent intent = new Intent(this,ResultAdvancedSearch.class);
        intent.putExtra("accion", "tipoPrecio");
        intent.putExtra("param", tipoPrecioSeleccionado);
        startActivity(intent);




    }
    public void OnClickButtonBusquedaCantidadEstrellas(View view){

        EditText editText = findViewById(R.id.editTextCantidadEstrellas);
        String cantidadEstrellasSeleccionado = (String)editText.getText().toString();

        Intent intent = new Intent(this,ResultAdvancedSearch.class);
        intent.putExtra("accion", "estrellas");
        intent.putExtra("param", cantidadEstrellasSeleccionado);
        startActivity(intent);


    }
    public void OnClickButtonBusquedaKilometros(View view){

        EditText editTextKilometros = findViewById(R.id.editTextBusquedaKilometros);
        String kilometros = editTextKilometros.getText().toString();

    }

    // Listener al seleccionar un item de un spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = (String)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
