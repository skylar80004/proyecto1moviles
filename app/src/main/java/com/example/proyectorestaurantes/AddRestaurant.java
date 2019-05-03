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
        intent.putExtra("name", nombreRest);
        startActivity(intent);

    }



    public void UploadSchedule(String day, String start, String end,int id) throws JSONException, ExecutionException, InterruptedException {

        JSONObject params = new JSONObject();
        String tipo3 = "POST";
        String dir3 = "https://proyecto1moviles.herokuapp.com/schedules.json";

        params.put("day", day);
        params.put("start", start);
        params.put("end", end);
        params.put("restaurant_id", id);

        Conexion conexion2;
        conexion2 = new Conexion();
        String resultado = conexion2.execute(dir3, tipo3,params.toString()).get();

        if(resultado.equals("Created")){
            Toast.makeText(getApplicationContext(),"Dia " + day + " listo",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Dia " + day + " error",Toast.LENGTH_LONG).show();
        }

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

                this.UploadSchedule("Lunes",abre,cierra,id);

                //Martes

                EditText edMartesAbre = findViewById(R.id.editTextMartesAbre);
                EditText edMartesCierra = findViewById(R.id.editTextMartesCierra);
                String abreMartes = edMartesAbre.getText().toString();
                String cierraMartes = edMartesCierra.getText().toString();

                this.UploadSchedule("Martes",abreMartes,cierraMartes,id);

                //Miercoles

                EditText edMiercolesAbre = findViewById(R.id.editTextMiercolesbre);
                EditText edMiercolesCierra = findViewById(R.id.editTextMiercolesCierra);
                String abreMiercoles = edMiercolesAbre.getText().toString();
                String cierraMiercoles = edMiercolesCierra.getText().toString();

                this.UploadSchedule("Miercoles",abreMiercoles,cierraMiercoles,id);


                //Jueves

                EditText edJuevesAbre = findViewById(R.id.editTextJuevesAbre);
                EditText edJuevesCierra = findViewById(R.id.editTextJuevesCierra);
                String abreJueves = edJuevesAbre.getText().toString();
                String cierraJueves = edJuevesCierra.getText().toString();

                this.UploadSchedule("Jueves",abreJueves,cierraJueves,id);



                //Viernes

                EditText edViernesAbre = findViewById(R.id.editTextViernesAbre);
                EditText edViernesCierra = findViewById(R.id.editTextViernesCierra);
                String abreViernes = edViernesAbre.getText().toString();
                String cierraViernes = edViernesCierra.getText().toString();

                this.UploadSchedule("Viernes",abreViernes,cierraViernes,id);


                //Sabado

                EditText edSabadoAbre = findViewById(R.id.editTextSabadoAbre);
                EditText edSabadoCierra = findViewById(R.id.editTextSabadoCierra);
                String abreSabado = edSabadoAbre.getText().toString();
                String cierraSabado = edSabadoCierra.getText().toString();

                this.UploadSchedule("Sabado",abreSabado,cierraSabado,id);



                //Domingo

                EditText edDomingoAbre = findViewById(R.id.editTextDomingoAbre);
                EditText edDomingoCierra = findViewById(R.id.editTextDomingoCierra);
                String abreDomingo = edDomingoAbre.getText().toString();
                String cierraDomingo = edDomingoCierra.getText().toString();

                this.UploadSchedule("Domingo",abreDomingo,cierraDomingo,id);


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
