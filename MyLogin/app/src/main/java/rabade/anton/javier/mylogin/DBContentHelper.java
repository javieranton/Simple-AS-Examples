package rabade.anton.javier.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javier on 01/02/2016.
 */
public class DBContentHelper extends SQLiteOpenHelper {

    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 4;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "users";

    //Declaracion del nombre de la tabla
    public static final String USER_TABLE ="Users";

    public static final String USER_COLUMN_NAME = "user";
    public static final String USER_COLUMN_PASSWORD = "password";

    //sentencia global de cracion de la base de datos
    public static final String USER_TABLE_CREATE = "CREATE TABLE "  + USER_TABLE +" ("
            + USER_COLUMN_NAME +" varchar(255) primary key, "
            + USER_COLUMN_PASSWORD +" password varchar(255)); ";

            /* +
            "nombre varchar(255), " +
            "apellidos varchar(255)," +
            "email varchar(255))";  */


    public DBContentHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      onCreate(db);
    }
}
