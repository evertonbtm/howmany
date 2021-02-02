package br.com.btguth.howmany.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.btguth.howmany.model.Counter;
import br.com.btguth.howmany.sqlite.DatabaseHandler;

public class CounterDAO {
    private Context context;

    public CounterDAO(Context context) {
        this.context = context;
    }

    private static final String TABLE_COUNTER = "counter";

    // code to add the new contact
    void addContact(Counter counter) {
        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("counterName", counter.getCounterName());
        values.put("measureUnityName", counter.getMeasureUnityName());
        values.put("measureUnityAlias", counter.getMeasureUnityAlias());
        values.put("multiplier", counter.getMultiplier());
        values.put("counterColor", counter.getCounterColor());
        values.put("clickAction", counter.getClickAction());
        values.put("counterValue", counter.getCounterValue());

        // Inserting Row
        db.insert(TABLE_COUNTER, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Counter getCounter(int id) {
        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();

        Cursor cursor = db.query(TABLE_COUNTER,
                                    new String[]
                                    {
                                            "idCounter",
                                            "counterName",
                                            "measureUnityName",
                                            "measureUnityAlias",
                                            "multiplier",
                                            "counterColor",
                                            "clickAction",
                                            "counterValue"
                                    },
                            "idCounter" + "=?",
        new String[] { String.valueOf(id) }, null, null, null, null);

        Counter counter = new Counter();
        if (cursor != null) {
            cursor.moveToFirst();
            counter.setIdCounter(Integer.parseInt(cursor.getString(0)));
            counter.setCounterName(cursor.getString(1));
            counter.setMeasureUnityName(cursor.getString(2));
            counter.setMeasureUnityAlias(cursor.getString(3));
            counter.setMultiplier(cursor.getString(4));
            counter.setCounterColor(cursor.getInt(5));
            counter.setClickAction(cursor.getString(6));
            counter.setClickAction(cursor.getString(7));
        }

        // return contact
        return counter;
    }

    // code to get all contacts in a list view
    public List<Counter> getAllContacts() {
        List<Counter> contactList = new ArrayList<Counter>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COUNTER;

        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Counter counter = new Counter();
                counter.setIdCounter(Integer.parseInt(cursor.getString(0)));
                counter.setCounterName(cursor.getString(1));
                counter.setMeasureUnityName(cursor.getString(2));
                counter.setMeasureUnityAlias(cursor.getString(3));
                counter.setMultiplier(cursor.getString(4));
                counter.setCounterColor(cursor.getInt(5));
                counter.setClickAction(cursor.getString(6));
                counter.setClickAction(cursor.getString(7));

                // Adding contact to list
                contactList.add(counter);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Counter counter) {
        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("counterName", counter.getCounterName());
        values.put("measureUnityName", counter.getMeasureUnityName());
        values.put("measureUnityAlias", counter.getMeasureUnityAlias());
        values.put("multiplier", counter.getMultiplier());
        values.put("counterColor", counter.getCounterColor());
        values.put("clickAction", counter.getClickAction());
        values.put("counterValue", counter.getCounterValue());

        // updating row
        return db.update(TABLE_COUNTER, values, "idCounter" + " = ?",
                new String[] { String.valueOf(counter.getIdCounter()) });
    }

    // Deleting single contact
    public void deleteContact(Counter counter) {
        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();

        db.delete(TABLE_COUNTER, "idCounter" + " = ?",
                new String[] { String.valueOf(counter.getIdCounter()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_COUNTER;

        DatabaseHandler handler = new DatabaseHandler(this.context);
        SQLiteDatabase db = handler.getWritableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
