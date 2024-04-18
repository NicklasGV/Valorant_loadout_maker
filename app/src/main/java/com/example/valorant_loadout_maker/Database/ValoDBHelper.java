package com.example.valorant_loadout_maker.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ValoDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "valo.db"
    private SQLiteDatabase db;

    public ValoDBHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ValoContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop the older tables
        db.execSQL(ValoContract.SQL_DELETE_ENTRIES);

        // Create tables again
        onCreate(db);
    }

    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    // CRUD

    // Create
    public void createLoadout(LoadoutModel loadout){
        ContentValues cv = new ContentValues();
        cv.put(ValoContract.ValoEntry.COLUMN_NAME_TITLE, loadout.loadout_name);
        db.insert(ValoContract.ValoEntry.TABLE_NAME, null, cv);
    }

    // Read
    @SuppressLint("Range")
    public List<LoadoutModel> getAllLoadouts(){
        List<LoadoutModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(ValoContract.ValoEntry.TABLE_NAME, null, null,  null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        LoadoutModel task = new LoadoutModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ValoContract.ValoEntry._ID)));
                        task.setLoadout_name(cur.getString(cur.getColumnIndex(ValoContract.ValoEntry.COLUMN_NAME_TITLE)));
                        taskList.add(task);
                    } while (cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cur.close();
        }
        return taskList;
    }

    // Update
    public long editLoadout(int todoId, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ValoContract.ValoEntry.COLUMN_NAME_TITLE, title);

        long rowsAffected = db.update(
                ValoContract.ValoEntry.TABLE_NAME,
                values,
                ValoContract.ValoEntry._ID + " = ?",
                new String[]{String.valueOf(todoId)}
        );

        db.close();
        return rowsAffected;
    }

    // Delete
    public long deleteLoadout(int loadoutId) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(ValoContract.ValoEntry.TABLE_NAME,
                    ValoContract.ValoEntry._ID + " = ?",
                    new String[]{String.valueOf(loadoutId)});
        } finally {
            db.close();
        }
        return 0;
    }
}
