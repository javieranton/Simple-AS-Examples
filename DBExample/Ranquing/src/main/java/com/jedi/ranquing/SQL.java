package com.jedi.ranquing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL extends SQLiteOpenHelper {

    private static int BD_VERSION = 1;
    private static String BD_NAME = "bd_projecte";
    private static String RANKING_TABLE = "ranking";


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RANKING_TABLE);
        sqLiteDatabase.execSQL("CREATE TABLE "+RANKING_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, punts INTEGER)");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+RANKING_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, punts INTEGER)");
        sqLiteDatabase.execSQL("INSERT INTO "+RANKING_TABLE+" (nick, punts) VALUES('jugador1','15'), ('jugador2', '30'), ('jugador3','8')");
    }

    public SQL(Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }
}
