package com.example.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Comments extends AppCompatActivity {



    private int idRest = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Intent intent = getIntent();
        this.idRest = intent.getIntExtra("id",0);
        try {
            this.ConsultarComentarios();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Prueba
/*
        this.AddComenttoScrollView("Zikero", "Excelente Servicio");
        this.AddComenttoScrollView("Kira", "Pesimo Servicio");
        */
    }



    public void AddComenttoScrollView(String user, String comment){

        TextView textViewUser = new TextView(this);
        textViewUser.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewUser.setText("Usuario: " + user);

        TextView textViewComment = new TextView(this);
        textViewComment.setTextColor(getResources().getColor(R.color.colorPrimary));
        textViewComment.setText(comment);

        LinearLayout ln = findViewById(R.id.linearLayoutComments);
        ln.addView(textViewUser);
        ln.addView(textViewComment);

        TextView textViewEspacio = new TextView(this);
        textViewEspacio.setText("\n");
        ln.addView(textViewEspacio);


    }
    public void ConsultarComentarios() throws JSONException, ExecutionException, InterruptedException {

        JSONObject params = new JSONObject();
        String tipo = "GET";
        String dir = "https://proyecto1moviles.herokuapp.com/comments.json";

        params.put("restaurant_id", this.idRest);
        Conexion conexion ;
        conexion = new Conexion();

        String resultado = conexion.execute(dir,tipo,params.toString()).get();

        JSONArray registros = new JSONArray(resultado);
        for(int i = 0 ; i < registros.length();i++){
            String valor = registros.getString(i);
            JSONObject registro = new JSONObject(valor);

            String comment = registro.getString("body");
            this.AddComenttoScrollView(comment, "josedavidvm04@hotmail.com");
        }

    }
    public void OnClickCommentButton(View view) throws JSONException, ExecutionException, InterruptedException {

        EditText editTextUserComment =  findViewById(R.id.editTextUserComment);
        String userComment = editTextUserComment.getText().toString();

        JSONObject params = new JSONObject();
        String tipo = "POST";
        String dir = "https://proyecto1moviles.herokuapp.com/comments.json";

        params.put("body",userComment);
        params.put("user_id",1);
        params.put("restaurant_id",this.idRest);

        Conexion conexion = new Conexion();

        String resultado = conexion.execute(dir,tipo,params.toString()).get();
        this.AddComenttoScrollView(userComment, "josedavidvm04@hotmail.com");

        Toast toast = Toast.makeText(this, userComment,Toast.LENGTH_LONG);
        toast.show();
    }
}
