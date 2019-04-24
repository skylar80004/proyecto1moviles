package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CheckSchedule extends AppCompatActivity {


    private int idRest = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_schedule);
        Intent intent = getIntent();
        idRest = intent.getIntExtra("id", 1);

        Log.i("CHES", String.valueOf(idRest));

        try {

            this.ConsultarHorario();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void ConsultarHorario() throws JSONException, ExecutionException, InterruptedException {

        JSONObject params = new JSONObject();
        String tipo = "GET";
        String dir = "https://proyecto1moviles.herokuapp.com/schedules.json";
        params.put("restaurant_id", this.idRest);

        Conexion conexion;
        conexion = new Conexion();

        String resultado = conexion.execute(dir,tipo,params.toString()).get();
        JSONArray registros = new JSONArray(resultado);


        for(int i = 0 ; i < registros.length();i++){

            String valor = registros.getString(i);
            JSONObject registro = new JSONObject(valor);

            String dia = registro.getString("day");
            String abre = registro.getString("start");
            String cierra = registro.getString("end");

            Log.i("CHES",dia);
            Log.i("CHES",abre);
            Log.i("CHES",cierra);


            if(dia.equals("Lunes")){


                EditText editTextAbre = findViewById(R.id.editTextLunesAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextLunesCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);

            }
            else if (dia.equals("Martes")){


                EditText editTextAbre = findViewById(R.id.editTextMartesAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextMartesCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);

            }
            else if(dia.equals("Miercoles")){

                EditText editTextAbre = findViewById(R.id.editTextMiercolesbre2);
                EditText editTextCierra  = findViewById(R.id.editTextMiercolesCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);

            }
            else if(dia.equals("Jueves")){

                EditText editTextAbre = findViewById(R.id.editTextJuevesAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextJuevesCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);

            }
            else if(dia.equals("Viernes")){

                EditText editTextAbre = findViewById(R.id.editTextViernesAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextViernesCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);


            }
            else if(dia.equals("Sabado")){

                EditText editTextAbre = findViewById(R.id.editTextSabadoAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextSabadoCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);


            }
            else if(dia.equals("Domingo")){

                EditText editTextAbre = findViewById(R.id.editTextDomingoAbre2);
                EditText editTextCierra  = findViewById(R.id.editTextDomingoCierra2);
                editTextAbre.setText(abre);
                editTextCierra.setText(cierra);


            }


        }

    }
}
