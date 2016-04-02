package rabade.anton.javier.macedonia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import Classes.DBContentHelper;
import io.fabric.sdk.android.Fabric;

public class Login extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "NkAbWrdY9XdxCD5N05ubNy10o";
    private static final String TWITTER_SECRET = "5xgpFQbo2CJc7mua6xeudeL15u4Xbnj9aDPlqewh5V8X2o7rsD";

    private TwitterLoginButton loginButton;

    EditText et_username;
    EditText et_password;
    CheckBox checkBox;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);

        final DBContentHelper dH = new DBContentHelper(getApplicationContext());
        db = dH.getWritableDatabase();

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        getSupportActionBar().hide();

    }


    public void onClickLogin(View view) {
        String user = et_username.getText().toString();
        String password = et_password.getText().toString();
        Cursor c = db.rawQuery("select * from " + DBContentHelper.USER_TABLE +" where " + DBContentHelper.USER_COLUMN_NAME +"='"+user+"' and " + DBContentHelper.USER_COLUMN_PASSWORD+ "='"+password+"'",null);
        if (c.getCount()==1){
            db.execSQL("DROP TABLE IF EXISTS " + DBContentHelper.LOGIN_TABLE);
            db.execSQL(DBContentHelper.LOGIN_TABLE_CREATE);

            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put(DBContentHelper.LOGIN_COLUMN_NAME, String.valueOf(et_username.getText()));
            if(checkBox.isChecked()){
                valuesToStore.put(DBContentHelper.LOGIN_COLUMN_STATUS, String.valueOf(1));
            }else{
                valuesToStore.put(DBContentHelper.LOGIN_COLUMN_STATUS, String.valueOf(0));
            }
            db.insert(DBContentHelper.LOGIN_TABLE, null, valuesToStore);

            Intent i = new Intent(getApplicationContext(), NavDrawer.class);
            //i.putExtra("Nombre", "Valor String");
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Cuenta o contraseña incorrectas",Toast.LENGTH_SHORT).show();
        }
        finish();
    }


    public void onClickNewUser(View view) {

        ContentValues valuesToStore = new ContentValues();
        valuesToStore.put(DBContentHelper.USER_COLUMN_NAME, String.valueOf(et_username.getText()));
        valuesToStore.put(DBContentHelper.USER_COLUMN_PASSWORD, String.valueOf(et_password.getText()));

        long ret = db.insert(DBContentHelper.USER_TABLE, null, valuesToStore);
        if(ret==-1){
            Toast.makeText(getApplicationContext(), "ERROR DE REGISTRO", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        }

        //
        //db.execSQL("INSERT INTO USUARIOS VALUES ('" + usuario + "','" + pssw + "','" + nombre+"','"+apellidos+"','"+email+"')");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


}


