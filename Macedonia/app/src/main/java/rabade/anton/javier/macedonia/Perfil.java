package rabade.anton.javier.macedonia;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import Classes.DBContentHelper;

public class Perfil extends NavDrawer{

    private ImageView image;
    private Button twitterB, gallerryB;
    private TextView name, gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        image = (ImageView) findViewById(R.id.iv_perfil);
        twitterB = (Button) findViewById(R.id.button_image_twitter);
        gallerryB = (Button) findViewById(R.id.button_image_gallery);
        name = (TextView) findViewById(R.id.tv_name);
        gps = (TextView) findViewById(R.id.tv_address);

        DBContentHelper sql = new DBContentHelper(getApplicationContext());
        name.setText(sql.getLogedName());

        setTitle("Perfil");
    }

    public void onClickGalleryButton(View v){
        Intent imageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
        imageAsContent.setType("image/*");

        startActivityForResult(imageAsContent, 1);
        }

    public void onClickTwitterButton(View v){
        Twitter.getApiClient().getAccountService().verifyCredentials(true, false, new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                User user = userResult.data;
                String profileImage = user.profileImageUrl.replace("_normal", "_bigger");
                downloadImage(profileImage);
            }

            @Override
            public void failure(TwitterException e) {
            }
        });
    }

    private Handler mhHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 100 && msg.obj != null) {
                image.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public void downloadImage(final String url) {
        new Thread(new Runnable() {
            private Bitmap loadImageFromNetwork(String url) {
                try {
                    Bitmap bitmap = BitmapFactory
                            .decodeStream((InputStream) new URL(url)
                                    .getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public void run() {
                final Bitmap bitmap = loadImageFromNetwork(url);
                Message msg = new Message();
                msg.what = 100;
                msg.obj = bitmap;
                mhHandler.sendMessage(msg);
            }
        }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    Uri selectedImage = data.getData();
                    Log.v("PICK", "Selected image uri" + selectedImage);
                    try {
                        image.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    image.setImageBitmap(photo);
                    break;

            }
        }else{
            Log.v("Result","Something happened");
        }
    }


    public void onClickCameraButton(View view) {
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start the image capture Intent
        startActivityForResult(iCamera,2);
    }

    public void onClickLogOut(View view) {
        logout();
    }

    private void logout() {
        DBContentHelper dH = new DBContentHelper(getApplicationContext());
        SQLiteDatabase db = dH.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DBContentHelper.LOGIN_TABLE);
        db.execSQL(DBContentHelper.LOGIN_TABLE_CREATE);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

