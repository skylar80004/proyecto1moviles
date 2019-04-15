package com.example.proyectorestaurantes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

public class AddRestaurantNext extends AppCompatActivity {



    private int GALLERY_REQUEST = 100;
    private int LOGO_REQUEST = 105;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant_next);
    }

    public void OnClickButtonAgregarImagen(View view){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

    }


    public void OnClickButtonAgregarLogo(View view){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,this.LOGO_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){


            if(requestCode == this.GALLERY_REQUEST){

                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);


                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(50,50));
                    imageView.setMaxHeight(50);
                    imageView.setMaxWidth(50);
                    imageView.setImageBitmap(bitmap);

                    LinearLayout linearLayout = findViewById(R.id.layoutHorizontalScroll);
                    linearLayout.addView(imageView);


                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }

            }
            else if(requestCode == this.LOGO_REQUEST){

                Uri selectedImage = data.getData();
                try {

                    // Set resized bit map
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    ImageView imageView = findViewById(R.id.imageViewLogo);
                    imageView.setImageBitmap(bitmap);
                    //Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap,50,50,false);
                    //ImageView imageViewLogo = findViewById(R.id.imageViewLogo);
                    //imageViewLogo.setImageBitmap(bitmap);



                } catch (IOException e) {
                    Log.i("CHES", "Some exception " + e);
                }



            }
        }

    }
}
