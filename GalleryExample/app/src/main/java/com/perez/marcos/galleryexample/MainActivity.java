package com.perez.marcos.galleryexample;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonContent, buttonPick, buttonChoose;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContent = (Button) findViewById(R.id.buttonActionGetContent);
        buttonContent.setOnClickListener(this);

        buttonPick = (Button) findViewById(R.id.buttonActionPick);
        buttonPick.setOnClickListener(this);

        buttonChoose = (Button) findViewById(R.id.buttonChooser);
        buttonChoose.setOnClickListener(this);

        image = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonActionGetContent:
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");

                startActivityForResult(getImageAsContent, 1);
                break;
            case R.id.buttonActionPick:

                /*Esto funciona, pero no es correcto según la guía de Google
                * ya que ACTION_PICK espera una URI del contenido.
                * */
                /*
                Intent pickAnImage = new Intent(Intent.ACTION_PICK, null);
                pickAnImage.setType("image/*");
                */

                /*Código correcto de action pick */
                Intent pickAnImage = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickAnImage.setType("image/*");

                startActivityForResult(pickAnImage, 2);
                break;

            case R.id.buttonChooser:
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                //Este Intent define para el ACTION_PICK, la URI de donde cogerá los datos
                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                //Usamos el Intent anterior para filtrar únicamente los que queremos que usen
                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 3);
                break;

            default:
                Log.v("OnClick", "Not implemented");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                data.getData();
                Uri selectedImage = data.getData();
                Log.v("PICK","Selected image uri" + selectedImage);
                try {
                    image.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else{
            Log.v("Result","Something happened");
        }
    }
}
