package com.example.checklistandsplit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.checklistandsplit.BigList;

import java.util.ArrayList;

public class Mydb extends SQLiteOpenHelper {
    static String TABLE_NAME = "big_list_table";
    Context ctx;
    static float VERSION = 1;
    SQLiteDatabase db;
    public Mydb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, TITLE STRING, DATE STRING, TIME STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion == VERSION) {
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
            VERSION = newVersion;
        }
    }

    public void insert(String title, String date, String time) {
        ContentValues cv = new ContentValues();
        //cv.put("ISCHECK", b);
        cv.put("TITLE", title);
        cv.put("DATE", date);
        cv.put("TIME", time);
        db = getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
        Toast.makeText(ctx, "Added " + title + " " + date + " " + time, Toast.LENGTH_LONG).show();
    }

    public void delete(String title, String date) {
        db = getWritableDatabase();
        db.delete(TABLE_NAME, "TITLE = ? AND DATE = ?", new String[]{title,date});
    }

    public ArrayList<BigList> list() {
        ArrayList<BigList> bigList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        while(c.moveToNext()) {
            Toast.makeText(ctx, "Added " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3), Toast.LENGTH_LONG).show();
            bigList.add(new BigList(c.getString(1), c.getString(2), c.getString(3)));
        }
        return bigList;
    }

    public int count() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        Toast.makeText(ctx, "" + c.getCount(), Toast.LENGTH_LONG).show();
        return c.getCount();

    }
}
