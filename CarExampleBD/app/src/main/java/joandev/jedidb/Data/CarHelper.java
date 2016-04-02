package joandev.jedidb.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joanbarroso on 31/1/15.
 */
public class CarHelper extends SQLiteOpenHelper {


    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "car";

    //Declaracion del nombre de la tabla
    public static final String CAR_TABLE ="Car";

    //sentencia global de cracion de la base de datos
    public static final String CAR_TABLE_CREATE = "CREATE TABLE " + CAR_TABLE + " (name TEXT PRIMARY KEY UNIQUE, maxSpeed INTEGER);";



    public CarHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CAR_TABLE_CREATE);

    }


    //obtener una lista de coches
    public List<Car> getAllCars() {
        ArrayList <Car>  cars= new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"name"};
        Cursor c = db.query(
                CAR_TABLE,          // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order
        );
        while (c.moveToNext()) {
            int index = c.getColumnIndex((columns[0]));
            cars.add(new Car(c.getString(index)));
        }

        return cars;
    }


    public Cursor getCarSpeedByName(String carName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"maxSpeed"};
        String[] where = {carName};
        Cursor c = db.query(
                CAR_TABLE,  // The table to query
                columns,         // The columns to return
                "name=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        return c;
    }


    public void createCar (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
