package com.example.proyectorestaurantes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class DetailRestaurant extends AppCompatActivity implements OnSuccessListener<UploadTask.TaskSnapshot>, OnFailureListener {


    int LOGO_REQUEST = 100;
    String nombreRest = "";
    Double latitude = 0.0;
    Double longitude = 0.0;
    String tipoComida = "";
    String telefono = "";
    String website = "";
    String tipoPrecio = "";

    int idRest = 2;
    private StorageReference mStorageRef;
    private StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        this.mStorageRef = FirebaseStorage.getInstance().getReference();

        Intent intent = getIntent();
        String nombreRestaurante = intent.getStringExtra("nombreRestaurante");
        TextView textViewNombreRestaurante = findViewById(R.id.textViewName);
        textViewNombreRestaurante.setText(nombreRestaurante);


        ImageView imageViewUbication = findViewById(R.id.imageViewLocation);
       //mageViewUbication.setImageResource(R.drawable.ic_ubi2);

        try {
            this.ConsultarDatosRest(nombreRestaurante);
            this.MostrarConsulta();


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void MostrarConsulta(){

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText(this.nombreRest);


        TextView textViewTipoComida = findViewById(R.id.textViewTipoComida);
        textViewTipoComida.setText(this.tipoComida);

        TextView textViewTipoPrecio = findViewById(R.id.textViewTipoPrecio);
        textViewTipoPrecio.setText(this.tipoPrecio);

        TextView textViewWeb = findViewById(R.id.textViewWeb);
        textViewWeb.setText(this.website);

        TextView textViewTelefono = findViewById(R.id.textViewPhone);
        textViewTelefono.setText(this.telefono);



    }


    public void ConsultarFotos(int idRest){

        JSONObject params = new JSONObject();
        String tipo = "GET";
        String dir = "https://proyecto1moviles.herokuapp.com/photo_restaurants.json";

        Conexion conexion;
        conexion = new Conexion();

        try {
            String resultado = conexion.execute(dir,tipo,params.toString()).get();
            JSONArray registros = new JSONArray(resultado);
            for(int i = 0; i < registros.length();i++){
                String valor = registros.getString(i);
                JSONObject registro = new JSONObject(valor);

                int idRestConsultado = registro.getInt("restaurant_id");

                if(idRestConsultado == idRest){

                    String imageURL = registro.getString("image_url");

                    ImageDownloader imageDownloader = new ImageDownloader();
                    Bitmap bitmap = imageDownloader.execute(imageURL).get();
                    ImageView imageView =  new ImageView(this);
                    imageView.setImageBitmap(bitmap);

                    LinearLayout ln = findViewById(R.id.linearLayoutDetailPhotos);
                    ln.addView(imageView);

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
    public void ConsultarDatosRest(String nameRest) throws ExecutionException, InterruptedException, JSONException {

        JSONObject params = new JSONObject();
        String tipo = "GET";

        nameRest.replace(" ", "%20");
        String dir2 = "https://proyecto1moviles.herokuapp.com/restaurants.json?search=%22" + nameRest + "%22";


        Conexion conexion = new Conexion();
        conexion = new Conexion();

        String resultado = conexion.execute(dir2, tipo,params.toString()).get();
        JSONArray registros = new JSONArray(resultado);

        String valor = registros.getString(0);
        JSONObject registro = new JSONObject(valor);

        this.nombreRest = registro.getString("name");
        this.latitude = registro.getDouble("latitude");
        this.longitude = registro.getDouble("longitude");
        this.tipoComida = registro.getString("kind_food");
        this.telefono = registro.getString("phone");
        this.website = registro.getString("website");
        this.tipoPrecio = registro.getString("price_type");
        this.idRest = registro.getInt("id");


        this.ConsultarFotos(idRest);


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


                    // Subir foto

                    if(selectedImage != null){

                        this.ref = this.mStorageRef.child(selectedImage.toString());
                        ref.putFile(selectedImage)
                                .addOnSuccessListener(this)
                                .addOnFailureListener(this);
                    }


                } catch (IOException e) {
                    Log.i("TAG", "Some exception " + e);
                }

            }
        }
    }





    public void OnClickButtonLocation(View view){

        Intent intent = new Intent(this, MenuMapsActivity.class);
        intent.putExtra("name",this.nombreRest);
        intent.putExtra("action", "singleLocation");
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
        intent.putExtra("id", this.idRest);
        startActivity(intent);

    }

    public void OnClickButtonRate(View view){
        Intent intent = new Intent(this, Rate.class);
        intent.putExtra("id", this.idRest);
        startActivity(intent);
    }

    public void OnClickButtonComments(View view){

        Intent intent = new Intent(this, Comments.class);
        intent.putExtra("id", this.idRest);
        startActivity(intent);
    }

    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

        this.ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // getting image uri and converting into string
                Uri downloadUrl = uri;
                String imageURL = downloadUrl.toString();

                JSONObject params2 = new JSONObject();
                String tipo2 = "POST";
                String dir2 = "https://proyecto1moviles.herokuapp.com/photo_restaurants.json";

                try {
                    params2.put("image_url", imageURL);
                    params2.put("restaurant_id", idRest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Conexion conexion2;
                conexion2 = new Conexion();
                try {
                    String resultado2 = conexion2.execute(dir2, tipo2, params2.toString()).get();
                    if (resultado2.equals("Created")) {
                        Toast.makeText(getApplicationContext(), "Foto Agregada", Toast.LENGTH_LONG).show();

                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


    }
}
