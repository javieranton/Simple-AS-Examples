package Classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier on 01/02/2016.
 */
public class DBContentHelper extends SQLiteOpenHelper {

    //Declaracion global de la version de la base de datos
    public static final int DATABASE_VERSION = 12;
    //Declaracion del nombre de la base de datos
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

    //Declaro el nombre de la segunda tabla
    public static final String LOGIN_TABLE = "Loged";
    public static final String LOGIN_COLUMN_NAME = "last_logued";
    public static final String LOGIN_COLUMN_STATUS = "status";
    //Sentencia global de la creación de la segunda tabla
    public static final String LOGIN_TABLE_CREATE = "CREATE TABLE " +LOGIN_TABLE+" ("
            +LOGIN_COLUMN_NAME+" vchar(255) primary key, " +
            LOGIN_COLUMN_STATUS+" INTEGER); ";

    //Declaramos la tercera tabla, la de el RANKING
    public static final String RANKING_TABLE = "ranking";
    public static final  String RANKING_COLUMN_ID = "id";
    public static final String RANKING_COLUMN_NAME = "nick";
    public static final String RANKING_COLUMN_POINS = "points";
    public static final String RANKING_TABLE_CREATE = "CREATE TABLE "+ RANKING_TABLE+" ("
            +RANKING_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RANKING_COLUMN_NAME+" TEXT, "
            +RANKING_COLUMN_POINS+" INTEGER); ";





    public DBContentHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(LOGIN_TABLE_CREATE);
        db.execSQL(RANKING_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+RANKING_TABLE);
        onCreate(db);

    }

    //Obtenter la lista de Ranking
    public List<Puntuation> getPuntuations(){
        ArrayList<Puntuation> ranking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT id _id, "+RANKING_COLUMN_NAME+", "+RANKING_COLUMN_POINS+" FROM ranking ORDER BY "+RANKING_COLUMN_POINS+" ASC";
        Cursor c = db.rawQuery(query, null);
        String[] columns = {RANKING_COLUMN_NAME, RANKING_COLUMN_POINS};

        while (c.moveToNext()){
            int i1 = c.getColumnIndex((columns[0]));
            int i2 = c.getColumnIndex((columns[1]));
            ranking.add(new Puntuation(c.getString(i1), c.getString(i2)));
        }
        return ranking;
    }

    //Cogemos el usuario logueado actualmente
    public String getLogedName(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns= {LOGIN_COLUMN_NAME};
        Cursor c = db.query(
                LOGIN_TABLE,          // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order
        );
        if (c.moveToNext()){
            int index = c.getColumnIndex(columns[0]);
            return c.getString(index);
        }
        return null;
    }
}
