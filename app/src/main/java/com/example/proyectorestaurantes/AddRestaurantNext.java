package com.example.proyectorestaurantes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AddRestaurantNext extends AppCompatActivity implements OnSuccessListener<UploadTask.TaskSnapshot>, OnFailureListener {



    private int GALLERY_REQUEST = 100;
    private int LOGO_REQUEST = 105;
    private StorageReference mStorageRef;
    private StorageReference ref;
    private String nameRest;
    private String imageURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant_next);
        this.imageURL = "";
        this.nameRest = "";
        Intent intent = getIntent();
        this.nameRest = intent.getStringExtra("name");
        //FirebaseApp.initializeApp(this);
        this.mStorageRef = FirebaseStorage.getInstance().getReference();
    }





    public void OnClickButtonAgregarUbicacion(View view){

        Intent intent = new Intent(this,MenuMapsActivity.class);
        intent.putExtra("action", "addLocation");
        intent.putExtra("name",this.nameRest);
        startActivity(intent);

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



                    // Se sube la imagen a fireBase

                    if(selectedImage != null){

                        this.ref = this.mStorageRef.child(selectedImage.toString());
                        ref.putFile(selectedImage)
                                .addOnSuccessListener(this)
                                .addOnFailureListener(this);
                    }

                    //this.UploadImageToFireBase(selectedImage.getPath(),bitmap);


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

    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

       // Toast toast = Toast.makeText(getApplicationContext(), "Se subio a storage", Toast.LENGTH_LONG);
       // toast.show();

        this.ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // getting image uri and converting into string
                Uri downloadUrl = uri;
                imageURL = downloadUrl.toString();

                // Post a la tabla de fotos

                JSONObject params = new JSONObject();
                String tipo = "GET";

                nameRest.replace(" ", "%20");
                String dir = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22" + nameRest + "%22";

                Conexion conexion;
                conexion = new Conexion();

                try {
                    String resultado = conexion.execute(dir,tipo,params.toString()).get();
                    JSONArray registros= new JSONArray(resultado);

                    for(int i = 0 ; i < registros.length();i++){
                        String valor = registros.getString(i);
                        JSONObject registro = new JSONObject(valor);
                        int idRest = registro.getInt("id");


                        // Post tabla de fotos

                        JSONObject params2 = new JSONObject();
                        String tipo2 = "POST";
                        String dir2 = "https://proyecto1moviles.herokuapp.com/photo_restaurants.json";

                        params2.put("image_url", imageURL);
                        params2.put("restaurant_id", idRest);

                        Conexion conexion2;
                        conexion2 = new Conexion();
                        String resultado2 = conexion2.execute(dir2,tipo2,params2.toString()).get();
                        if(resultado2.equals("Created")){
                            Toast.makeText(getApplicationContext(),"Foto Agregada",Toast.LENGTH_LONG).show();


                        }



                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }
}
