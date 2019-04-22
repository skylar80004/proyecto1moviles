package com.example.proyectorestaurantes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        this.TestListView();

        // Set Listener to ListView
        ListView listView  = findViewById(R.id.listViewRestaurantes);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.menuItemAgregar) {

            Intent intent = new Intent(this, AddRestaurant.class);
            startActivity(intent);

        }
        else if(id == R.id.menuItemBusqueda){

            Intent intent = new Intent(this,AdvancedSearch.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    // Prueba de como llenar el list View
    public void TestListView(){

        ListView listView = findViewById(R.id.listViewRestaurantes);
        final ArrayList<String> elements = new ArrayList<String>();
        elements.add("McDonalds");
        elements.add("Il Volpino");
        elements.add("Dominos Pizza");
        elements.add("Monster Pizza");
        elements.add("The Draft");
        elements.add("Burger King");
        elements.add("Chante Bike Club");
        elements.add("Ches Toronjas");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
                        ,elements);
        listView.setAdapter(itemsAdapter);
    }

    // Buttons
    public void OnClickButtonSeeMap(View view){

        Intent intent = new Intent(this, MenuMapsActivity.class);
        startActivity(intent);

    }

    public void OnClickButtonSearch(View view){

        EditText editTextSearch = findViewById(R.id.editTextSearch);
        String nombreRestauranteBuscado = editTextSearch.getText().toString();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String nombreRestaurante = (String)parent.getItemAtPosition(position);
        Intent intent = new Intent(this,DetailRestaurant.class);
        intent.putExtra("nombreRestaurante",nombreRestaurante);
        startActivity(intent);

    }
}
