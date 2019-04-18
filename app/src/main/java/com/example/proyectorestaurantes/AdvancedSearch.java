package com.example.proyectorestaurantes;

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

public class AdvancedSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        this.InitSpinnerCantidadEstrellas();
        this.InitSpinnerTipoPrecio();





    }


    private void InitSpinnerCantidadEstrellas(){

        Spinner spinner  = findViewById(R.id.spinnerBusquedaEstrellas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rateItems,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
    }

    public void OnClickButtonBusquedaTipoPrecio(View view){

        Spinner spinner = findViewById(R.id.spinnerBusquedaTipoPrecio);
        String tipoPrecioSeleccionado = (String)spinner.getSelectedItem();


    }
    public void OnClickButtonBusquedaCantidadEstrellas(View view){

        Spinner spinner = findViewById(R.id.spinnerBusquedaEstrellas);
        String cantidadEstrellasSeleccionado = (String)spinner.getSelectedItem();

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
