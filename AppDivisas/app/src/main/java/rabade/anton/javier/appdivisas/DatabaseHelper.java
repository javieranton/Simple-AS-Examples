package rabade.anton.javier.appdivisas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jantonra7.alumnes on 18/02/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    //Nombre global de la base de datos
    public static final String DATABASE_NAME = "MyDBase";
    //Versión de la base de datos
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "Badges";
    public static final String COLUMN_TABLE_ID = "id";
    public static final String COLUMN_TABLE_CURRENCY = "currency";
    public static final String COLUMN_TABLE_COMMISSION = "commission";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_TABLE_ID + " FLOAT PRIMARY KEY ," + COLUMN_TABLE_CURRENCY + " FLOAT ," + COLUMN_TABLE_COMMISSION + " FLOAT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Gets the actual Currency
    public String getCurrency(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COLUMN_TABLE_CURRENCY};
        Cursor c = db.query(
                TABLE_NAME,          // The table to query
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

    // Gets the actual Commission
    public String getCommission(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COLUMN_TABLE_COMMISSION};
        Cursor c = db.query(
                TABLE_NAME,          // The table to query
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
