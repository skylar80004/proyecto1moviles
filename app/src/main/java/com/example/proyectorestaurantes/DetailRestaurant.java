package com.example.proyectorestaurantes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.io.IOException;

public class DetailRestaurant extends AppCompatActivity {


    int LOGO_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent = getIntent();
        String nombreRestaurante = intent.getStringExtra("nombreRestaurante");
        TextView textViewNombreRestaurante = findViewById(R.id.textViewName);
        textViewNombreRestaurante.setText(nombreRestaurante);


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == this.LOGO_REQUEST) {

                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);


                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(250, 250));
                  //  imageView.setMaxHeight(50);
                  //  imageView.setMaxWidth(50);
                    imageView.setImageBitmap(bitmap);

                    LinearLayout linearLayout = findViewById(R.id.linearLayoutDetailPhotos);
                    linearLayout.addView(imageView);


                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }

            }
        }
    }





    public void OnClickButtonLocation(View view){

        Intent intent = new Intent(this, MenuMapsActivity.class);
        startActivity(intent);

        // Se le debe pasar la informacion de la ubicacion del restaurante actual para mostrarla

    }
    public void OnClickButtonAddPhoto(View view){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,this.LOGO_REQUEST);

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
