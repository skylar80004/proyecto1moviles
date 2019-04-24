package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ResultAdvancedSearch extends AppCompatActivity {


    String accion = "";
    String param = "";

    ArrayList<String> elements;
    ArrayList<Integer> cantidadRestaurantes;
    ArrayList<String> nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_advanced_search);
        Intent intent = getIntent();
        this.elements = new ArrayList<>();
        this.cantidadRestaurantes = new ArrayList<>();
        this.nombres = new ArrayList<>();

        this.InitArrayCantidadRest();

        this.accion = intent.getStringExtra("accion");
        this.param = intent.getStringExtra("param");



        try {
            this.Consultar();

            ListView listViewResults = findViewById(R.id.listViewResultAdvanced);
            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
                            ,elements);
            listViewResults.setAdapter(itemsAdapter);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void InitArrayCantidadRest(){

        for(int i = 0 ; i< 120; i++){

            this.cantidadRestaurantes.add(0);
            this.nombres.add("hola");
        }
    }

    public void Consultar() throws ExecutionException, InterruptedException, JSONException {

        System.out.println("holaaaa");
        if(this.accion.equals("tipoComida") ||  this.accion.equals("tipoPrecio")){

            JSONObject params = new JSONObject();
            String tipo = "GET";

            this.param.replace(" ", "%20");
            String dir2 = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22" + param + "%22";

            Conexion conexion;
            conexion = new Conexion();

            String resultado = conexion.execute(dir2,tipo,params.toString()).get();

            JSONArray registros = new JSONArray(resultado);

            for(int i = 0; i<registros.length();i++){

                String valor = registros.getString(i);
                JSONObject registro = new JSONObject(valor);

                String nombre = registro.getString("name");
                this.elements.add(nombre);
            }

        }
        else if(this.accion.equals("estrellas")){


            JSONObject params = new JSONObject();
            String tipo = "GET";
            String dir = "https://proyecto1moviles.herokuapp.com/stars.json";


            Conexion conexion;
            conexion = new Conexion();

            String resultado = conexion.execute(dir,tipo,params.toString()).get();
            JSONArray registros = new JSONArray(resultado);

            int restID = 0 ;
            int puntaje = 0 ;
            int actual = 0;

            for(int i = 0 ; i< registros.length();i++){

                String valor = registros.getString(i);
                JSONObject registro = new JSONObject(valor);


                System.out.println("holaaaa");
                System.out.println(restID + " CHES");
                System.out.println(puntaje + " CHES");
                System.out.println(actual + " CHES");

                restID = registro.getInt("restaurant_id");
                puntaje = registro.getInt("value");
                actual = this.cantidadRestaurantes.get(restID);
                this.cantidadRestaurantes.set(restID,actual + puntaje);
            }


            System.out.println("holaaaa");
            System.out.println(this.cantidadRestaurantes.toString());

            int cantidadEstrellas = Integer.parseInt(this.param);
            for(int i = 0; i< this.cantidadRestaurantes.size();i++){

                if(cantidadEstrellas == cantidadRestaurantes.get(i)){

                    JSONObject params2 = new JSONObject();
                    String tipo2 = "GET";

                    String dir2 = "https://proyecto1moviles.herokuapp.com/restaurants.json";
                    params2.put("id", i);

                    Conexion conexion1;
                    conexion1 = new Conexion();

                    String resultado2 = conexion1.execute(dir,tipo,params2.toString()).get();

                    JSONArray registros2 = new JSONArray(resultado2);
                    String valor = registros2.getString(0);
                    JSONObject registro2 = new JSONObject(valor);

                    String nombre = registro2.getString("name");
                    this.elements.add(nombre);


                }
            }




        }


    }
}
