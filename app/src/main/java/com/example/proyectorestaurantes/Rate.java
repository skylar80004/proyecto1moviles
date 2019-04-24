package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Rate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    private int rate;
    private int idRest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rate = -1;
        Intent intent = getIntent();
        this.idRest = intent.getIntExtra("id",4);
        InitSpinner();
    }



    private void InitSpinner(){

        Spinner spinner  = findViewById(R.id.spinnerRate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rateItems,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    public void OnClickButtonApplyRate(View view) throws JSONException, ExecutionException, InterruptedException {

        if(rate == -1){
            Toast toast = Toast.makeText(this,"Debe seleccionar un puntaje para poder calificar el restaurante",Toast.LENGTH_LONG);
            toast.show();
        }
        else{

            // rate ya tiene el valor de calificacion escogido por el usuario

            JSONObject params = new JSONObject();
            String tipo = "POST";
            String dir = "https://proyecto1moviles.herokuapp.com/stars.json";

            params.put("value", rate);
            params.put("user_id",1);
            params.put("restaurant_id",this.idRest);


            Conexion conexion = new Conexion();

            String resultado = conexion.execute(dir,tipo,params.toString()).get();

            Toast toast = Toast.makeText(this, "Se ha calificado", Toast.LENGTH_LONG);
            toast.show();



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
