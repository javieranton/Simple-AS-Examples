package rabade.anton.javier.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBContentHelper dH = new DBContentHelper(getApplicationContext());
        db = dH.getWritableDatabase();

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
    }


    public void onClickNewActivity(View view) {
        String user = et_username.getText().toString();
        String password = et_password.getText().toString();
        Cursor c = db.rawQuery("select * from " + DBContentHelper.USER_TABLE +" where " + DBContentHelper.USER_COLUMN_NAME +"='"+user+"' and " + DBContentHelper.USER_COLUMN_PASSWORD+ "='"+password+"'",null);
        if (c.getCount()==1){
            //Instanciamos el Shared Preferences
            SharedPreferences settings = getSharedPreferences("usersXML", Context.MODE_PRIVATE);
            //Obtenemos el editor
            SharedPreferences.Editor editor = settings.edit();
            //Editamos
            editor.putString("user", user);
            //Guardamos los cambios
            editor.apply();


            Intent i = new Intent(getApplicationContext(),Main2Activity.class);
            //i.putExtra("Nombre", "Valor String");
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Cuenta o contraseña incorrectas",Toast.LENGTH_SHORT).show();
        }
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

    public void verifyUser(String user, String password){
        Cursor c = db.rawQuery("select * from " + DBContentHelper.USER_TABLE +"· where " + DBContentHelper.USER_COLUMN_NAME +"='"+user+"' and " + DBContentHelper.USER_COLUMN_PASSWORD+ "='"+password+"'",null);
        if (c.getCount()==1){
            Intent i = new Intent(getApplicationContext(),Main2Activity.class);
            //i.putExtra("Nombre", "Valor String");
            startActivity(i);
        }
    }
}


