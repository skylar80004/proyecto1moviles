package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MapPopUp extends AppCompatActivity {

    String nombreRestaurante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_pop_up);
        Intent intent = getIntent();
        this.nombreRestaurante = intent.getStringExtra("nombreRestaurante");

        TextView textView = findViewById(R.id.textViewNameMapPopUp);
        textView.setText(this.nombreRestaurante);
    }

    public void OnClickButtonVerDetalle(View view){

        Intent intent = new Intent(this,DetailRestaurant.class);
        intent.putExtra("nombreRestaurante",nombreRestaurante);
        startActivity(intent);
    }
}
