package br.com.btguth.howmany.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CounterDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "HowMany";

    private static final String TABLE_COUNTER = "counter";


    public CounterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTER_TABLE =
                "CREATE TABLE counter " +
                    "("+
                        "idCounter INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "counterName TEXT,"+
                        "measureUnityName TEXT,"+
                        "measureUnityAlias TEXT,"+
                        "multiplier INTEGER,"+
                        "counterColor INTEGER,"+
                        "clickAction TEXT,"+
                        "counterValue INTEGER"+
                    ")";

        db.execSQL(CREATE_COUNTER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTER);

        // Create tables again
        onCreate(db);
    }

}
