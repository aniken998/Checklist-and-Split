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
    static String BIG_TABLE_NAME = "big_list_table";
    static String DUTY_TABLE_NAME = "duty_list_table";
    Context ctx;
    static float VERSION = 1;
    SQLiteDatabase db;
    public Mydb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BIG_TABLE_NAME + "(ID INTEGER PRIMARY KEY, TITLE STRING, DATE STRING, TIME STRING)");
        db.execSQL("CREATE TABLE " + DUTY_TABLE_NAME + "(ID INTEGER PRIMARY KEY, NAME STRING, EXECUTOR STRING, isCHECK BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion == VERSION) {
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + BIG_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DUTY_TABLE_NAME);
            onCreate(db);
            VERSION = newVersion;
        }
    }

    public void big_list_insert(String title, String date, String time) {
        ContentValues cv = new ContentValues();
        //cv.put("ISCHECK", b);
        cv.put("TITLE", title);
        cv.put("DATE", date);
        cv.put("TIME", time);
        db = getWritableDatabase();
        db.insert(BIG_TABLE_NAME, null, cv);
        Toast.makeText(ctx, "Added " + title + " " + date + " " + time, Toast.LENGTH_LONG).show();

    }


    public void duty_list_insert(String title, String executor, boolean isCHECK) {
        ContentValues cv = new ContentValues();
        cv.put("NAME", title);
        cv.put("EXECUTOR", executor);
        cv.put("isCHECK", isCHECK);
        db = getWritableDatabase();
        db.insert(DUTY_TABLE_NAME, null, cv);
        Toast.makeText(ctx, "Added " + title + " " + title + " " + executor, Toast.LENGTH_LONG).show();
    }
    public void big_list_delete(String title, String date) {
        db = getWritableDatabase();
        db.delete(BIG_TABLE_NAME, "TITLE = ? AND DATE = ?", new String[]{title,date});
    }
    public void duty_list_delete(String name, String executor) {
        db = getWritableDatabase();
        db.delete(BIG_TABLE_NAME, "NAME = ? AND EXECUTOR = ?", new String[]{name,executor});
    }

    public ArrayList<BigList> biglist() {
        ArrayList<BigList> bigList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + BIG_TABLE_NAME + ";", null);
        while(c.moveToNext()) {
            Toast.makeText(ctx, "Added " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3), Toast.LENGTH_LONG).show();
            bigList.add(new BigList(c.getString(1), c.getString(2), c.getString(3)));
        }
        return bigList;
    }

    public ArrayList<Duty>  dutylist() {
        ArrayList<Duty> dutyList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DUTY_TABLE_NAME + ";", null);
        while(c.moveToNext()) {
            dutyList.add(new Duty(c.getInt(3) == 1, c.getString(1), c.getString(2)));
        }
        return dutyList;
    }
    public int count() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + BIG_TABLE_NAME + ";", null);
        Toast.makeText(ctx, "" + c.getCount(), Toast.LENGTH_LONG).show();
        return c.getCount();

    }
}
