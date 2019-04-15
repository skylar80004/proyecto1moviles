package com.example.proyectorestaurantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Rate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private int rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rate = -1;
        InitSpinner();
    }



    private void InitSpinner(){

        Spinner spinner  = findViewById(R.id.spinnerRate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rateItems,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    public void OnClickButtonApplyRate() {

        if(rate == -1){
            Toast toast = Toast.makeText(this,"Debe seleccionar un puntaje para poder calificar el restaurante",Toast.LENGTH_LONG);
            toast.show();
        }
        else{

            // rate ya tiene el valor de calificacion escogido por el usuario
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = (String)parent.getItemAtPosition(position);
        this.rate = Integer.parseInt(item);
        Log.i("Ches",item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
