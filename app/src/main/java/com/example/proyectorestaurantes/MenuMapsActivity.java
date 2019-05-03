package com.example.proyectorestaurantes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MenuMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private double lat;
    private double longi;
    private boolean mLocationPermissionGranted;
    private int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
    private String action;
    private String nameRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_maps);

        this.action = "";
        this.nameRest = "";
        Intent intent = getIntent();
        this.action = intent.getStringExtra("action");
        if(this.action.equals("addLocation")){
            this.nameRest = intent.getStringExtra("name");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lat = 0;
        longi = 0;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void SetMarker(double lat,double longi,String title){

        LatLng location = new LatLng(lat, longi);
        this.mMap.addMarker(new MarkerOptions().position(location).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

    }

    public void GetRestaurants(){

        JSONObject jsonParam = new JSONObject();
        String tipo = "GET";
        String dir = "https://proyecto1moviles.herokuapp.com/restaurants.json";
        //String dir = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22Pizza%20hut%22";
        Conexion conexion;
        conexion = new Conexion();
        try {


            String resultado = conexion.execute(dir, tipo, jsonParam.toString()).get();
            JSONArray marcas = new JSONArray(resultado);

            for(int i = 0 ; i< marcas.length();i++){

                String valor = marcas.getString(i);
                JSONObject marca = new JSONObject(valor);

                String nombre = marca.getString("name");
               // this.elements.add(nombre);
                Log.i("CHES",nombre);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mMap.setOnMarkerClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        if(this.action.equals("all")){

            JSONObject params = new JSONObject();
            String tipo = "GET";
            String dir = "https://proyecto1moviles.herokuapp.com/restaurants.json";

            Conexion conexion = new Conexion();

            try {
                String resultado = conexion.execute(dir,tipo,params.toString()).get();
                JSONArray registros = new JSONArray(resultado);

                for(int i = 0; i < registros.length(); i++){

                    String valor = registros.getString(i);
                    JSONObject registro = new JSONObject(valor);
                    String latitute = registro.getString("latitude");
                    String longitude = registro.getString("longitude");
                    String name = registro.getString("name");

                    Double latDouble = Double.parseDouble(latitute);
                    Double longDouble = Double.parseDouble(longitude);
                    LatLng place = new LatLng(latDouble, longDouble);
                    mMap.addMarker(new MarkerOptions().position(place).title(name));

                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        /*
        // Add a marker in Sydney and move the camera
        LatLng costaRica = new LatLng(this.lat, this.longi);
        mMap.addMarker(new MarkerOptions().position(costaRica).title("Marker in Costa Rica"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(costaRica));
        */

        Toast toast = Toast.makeText(getApplicationContext(),"Mapas listos" , Toast.LENGTH_LONG);
        toast.show();
       // mMap.set
    }


    @Override
    public boolean onMarkerClick(Marker marker){

        String markerTitleNombreRestaurante = marker.getTitle();
        Intent intent = new Intent(this,MapPopUp.class);
        intent.putExtra("nombreRestaurante",markerTitleNombreRestaurante);
        startActivity(intent);
        return true;
    }



    public void UpdatePosition(String nameRest, double latitude, double longitude){


        Toast.makeText(getApplicationContext(),nameRest, Toast.LENGTH_LONG).show();


        // Primero se busca el restarante

        JSONObject params = new JSONObject();
        String tipo = "GET";

        nameRest.replace(" ", "%20");
        String dir2 = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22" + nameRest + "%22";
        Conexion conexion;
        conexion = new Conexion();
        int id = -100;

        try {
            String resultado = conexion.execute(dir2, tipo,params.toString()).get();
            JSONArray registros = new JSONArray(resultado);

            for(int i = 0 ; i < registros.length();i++){
                String valor = registros.getString(i);
                JSONObject registro = new JSONObject(valor);
                id = registro.getInt("id");
                Toast.makeText(getApplicationContext(),"ID DEL REST " + String.valueOf(id), Toast.LENGTH_LONG).show();

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(id != -100){

            JSONObject params2 = new JSONObject();
            String tipo2 = "PATCH";
            String dir23 = "https://proyecto1moviles.herokuapp.com/restaurants/" + String.valueOf(id) + ".json";

            try {
                params2.put("latitude",latitude);
                params2.put("longitude", longitude);

                Conexion conexion2;
                conexion2 = new Conexion();

                String resultado = conexion2.execute(dir23,tipo2,params2.toString()).get();
                Toast.makeText(getApplicationContext(),"POST HECHO", Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }



        }


    }
    @Override
    public void onMapLongClick(LatLng latLng){


        if(this.action.equals("addLocation")){
            mMap.addMarker(new MarkerOptions().position(latLng));
            this.UpdatePosition(this.nameRest, latLng.latitude, latLng.longitude);

            // Se actualiza el restaurante ya agregado


        }


    }

    public void AddPositionToRest(){


    }
}
