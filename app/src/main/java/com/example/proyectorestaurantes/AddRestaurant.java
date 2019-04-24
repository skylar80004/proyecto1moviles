package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AddRestaurant extends AppCompatActivity {



    public void OnClickButtonSiguiente(View view){


        String inputs[] = new String[10];

        EditText editTextNombre = findViewById(R.id.editTextNombreRest);
        EditText editTextTipoComida = findViewById(R.id.editTextBusquedaTipoComida);
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



        this.UploadRest(nombreRest, 100, 100, tipoComida,telefono,sitioWeb,tipoPrecio);
        Toast toast = Toast.makeText(this, "Se agrego el restaurante", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this,AddRestaurantNext.class);
        startActivity(intent);

    }



    public void UploadRest(String nameRest, double lat, double longi, String foodType, String phone, String webSite, String priceType) {

        JSONObject jsonParam = new JSONObject();
        String tipo = "POST";
        String dir = "https://proyecto1moviles.herokuapp.com/restaurants.json";

        Conexion conexion;
        conexion = new Conexion();


        try {

            jsonParam.put("name", nameRest);
            jsonParam.put("latitude", 10.0);
            jsonParam.put("longitude", 15.67);
            jsonParam.put("kind_food", foodType);
            jsonParam.put("phone", phone);
            jsonParam.put("website", webSite);
            jsonParam.put("price_type", priceType);

            String resultado = conexion.execute(dir,tipo, jsonParam.toString()).get();

            if(resultado.equals("Created")){
                // iterars sobre resultado ;

                JSONObject jsonBusqueda= new JSONObject();
                String tipo2 = "GET";
                String name  = nameRest;
                name.replace(" ", "%20");
                String dir2 = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22" + name + "%22";

                Conexion conexion1 = new Conexion();
                String resultado1 = conexion1.execute(dir2,tipo2, jsonBusqueda.toString()).get();

                JSONArray marcas = new JSONArray(resultado1);
                String valor  = marcas.getString(0);
                JSONObject marca = new JSONObject(valor);
                int id =  marca.getInt("id");


                // Añadir horario


                // LUNES

                EditText edLunesAbre = findViewById(R.id.editTextLunesAbre);
                EditText edLunesCierra = findViewById(R.id.editTextLunesCierra);
                String abre = edLunesAbre.getText().toString();
                String cierra = edLunesCierra.getText().toString();


                JSONObject params = new JSONObject();
                String tipo3 = "POST";
                String dir3 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Lunes");
                params.put("start", abre);
                params.put("end", cierra);
                params.put("restaurant_id", id);

                Conexion conexion2;
                conexion2 = new Conexion();
                conexion2.execute(dir3, tipo3,params.toString()).get();


                //Martes

                EditText edMartesAbre = findViewById(R.id.editTextMartesAbre);
                EditText edMartesCierra = findViewById(R.id.editTextMartesCierra);
                String abreMartes = edMartesAbre.getText().toString();
                String cierraMartes = edMartesCierra.getText().toString();

                JSONObject params4 = new JSONObject();
                String tipo4 = "POST";
                String dir4 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Martes");
                params.put("start", abreMartes);
                params.put("end", cierraMartes);
                params.put("restaurant_id", id);

                Conexion conexion3;
                conexion3 = new Conexion();
                conexion3.execute(dir4, tipo4,params4.toString()).get();

                //Miercoles

                EditText edMiercolesAbre = findViewById(R.id.editTextMiercolesbre);
                EditText edMiercolesCierra = findViewById(R.id.editTextMiercolesCierra);
                String abreMiercoles = edMiercolesAbre.getText().toString();
                String cierraMiercoles = edMiercolesCierra.getText().toString();

                JSONObject params5 = new JSONObject();
                String tipo5 = "POST";
                String dir5 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Miercoles");
                params.put("start", abreMiercoles);
                params.put("end", cierraMiercoles);
                params.put("restaurant_id", id);

                Conexion conexion5;
                conexion5= new Conexion();
                conexion5.execute(dir5, tipo5,params5.toString()).get();

                //Jueves

                EditText edJuevesAbre = findViewById(R.id.editTextJuevesAbre);
                EditText edJuevesCierra = findViewById(R.id.editTextJuevesCierra);
                String abreJueves = edJuevesAbre.getText().toString();
                String cierraJueves = edJuevesCierra.getText().toString();

                JSONObject params6 = new JSONObject();
                String tipo6 = "POST";
                String dir6 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Jueves");
                params.put("start", abreJueves);
                params.put("end", cierraJueves);
                params.put("restaurant_id", id);

                Conexion conexion6;
                conexion6= new Conexion();
                conexion6.execute(dir6, tipo6,params6.toString()).get();

                //Viernes

                EditText edViernesAbre = findViewById(R.id.editTextViernesAbre);
                EditText edViernesCierra = findViewById(R.id.editTextViernesCierra);
                String abreViernes = edViernesAbre.getText().toString();
                String cierraViernes = edViernesCierra.getText().toString();

                JSONObject params7 = new JSONObject();
                String tipo7 = "POST";
                String dir7 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Viernes");
                params.put("start", abreViernes);
                params.put("end", cierraViernes);
                params.put("restaurant_id", id);

                Conexion conexion7;
                conexion7= new Conexion();
                conexion7.execute(dir7, tipo7,params7.toString()).get();

                //Sabado

                EditText edSabadoAbre = findViewById(R.id.editTextSabadoAbre);
                EditText edSabadoCierra = findViewById(R.id.editTextSabadoCierra);
                String abreSabado = edSabadoAbre.getText().toString();
                String cierraSabado = edSabadoCierra.getText().toString();

                JSONObject params8 = new JSONObject();
                String tipo8 = "POST";
                String dir8 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Sabado");
                params.put("start", abreSabado);
                params.put("end", cierraSabado);
                params.put("restaurant_id", id);

                Conexion conexion8;
                conexion8= new Conexion();
                conexion8.execute(dir8, tipo8,params8.toString()).get();

                //Domingo

                EditText edDomingoAbre = findViewById(R.id.editTextDomingoAbre);
                EditText edDomingoCierra = findViewById(R.id.editTextDomingoCierra);
                String abreDomingo = edDomingoAbre.getText().toString();
                String cierraDomingo = edDomingoCierra.getText().toString();

                JSONObject params9 = new JSONObject();
                String tipo9 = "POST";
                String dir9 = "https://proyecto1moviles.herokuapp.com/schedules.json";

                params.put("day", "Domingo");
                params.put("start", abreDomingo);
                params.put("end", cierraDomingo);
                params.put("restaurant_id", id);

                Conexion conexion9;
                conexion9= new Conexion();
                conexion9.execute(dir9, tipo9,params9.toString()).get();














            }





        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        //this.UploadTest();
    }
}
