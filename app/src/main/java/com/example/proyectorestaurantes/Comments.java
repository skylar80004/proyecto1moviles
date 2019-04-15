package com.example.proyectorestaurantes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Comments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        // Prueba

        this.AddComenttoScrollView("Zikero", "Excelente Servicio");
        this.AddComenttoScrollView("Kira", "Pesimo Servicio");
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
    public void OnClickCommentButton(View view){

        EditText editTextUserComment =  findViewById(R.id.editTextUserComment);
        String userComment = editTextUserComment.getText().toString();
        Toast toast = Toast.makeText(this, userComment,Toast.LENGTH_LONG);
        toast.show();
    }
}
