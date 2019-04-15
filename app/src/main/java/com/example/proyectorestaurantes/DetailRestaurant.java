package com.example.proyectorestaurantes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Comment;

public class DetailRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);


        ImageView imageViewUbication = findViewById(R.id.imageViewLocation);
       //mageViewUbication.setImageResource(R.drawable.ic_ubi2);
    }




    public void setActive(boolean isActive){

        TextView text = findViewById(R.id.textViewName);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewRateTitle);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewRatePoints);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewPhone);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewTipoPrecio);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewTipoComida);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewWeb);
        text.setActivated(isActive);

        text = findViewById(R.id.textViewRateTitle);
        text.setActivated(isActive);

        // Image View

        ImageView imageView = findViewById(R.id.imageViewLogo);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewLocation);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewPhone);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewWeb);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewTipoComida);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewTipoPrecio);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewComments);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewRate);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewSchedule);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewAddPhoto);
        imageView.setActivated(isActive);

        imageView = findViewById(R.id.imageViewDataBackground);
        imageView.setActivated(isActive);

        // Buttons

        Button button = findViewById(R.id.buttonUbicacion);
        button.setActivated(isActive);

        button = findViewById(R.id.buttonComments);
        button.setActivated(isActive);

        button = findViewById(R.id.buttonRate);
        button.setActivated(isActive);

        button = findViewById(R.id.buttonAddPhoto);
        button.setActivated(isActive);

        button = findViewById(R.id.buttonSchedule);
        button.setActivated(isActive);
    }

    public void OnClickButtonSchedule(View view){

        Intent intent  = new Intent(this,CheckSchedule.class);
        startActivity(intent);

    }

    public void OnClickButtonRate(View view){
        Intent intent = new Intent(this, Rate.class);
        startActivity(intent);
    }

    public void OnClickButtonComments(View view){

        Intent intent = new Intent(this, Comments.class);
        startActivity(intent);
    }
}
