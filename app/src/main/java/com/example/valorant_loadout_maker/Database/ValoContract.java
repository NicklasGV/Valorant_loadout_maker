package com.example.valorant_loadout_maker.Database;

import android.provider.BaseColumns;

public class ValoContract {
    private ValoContract() {}

    public static class ValoEntry implements BaseColumns {
        public static final String TABLE_NAME = "valo";
        public static final String COLUMN_NAME_TITLE = "title";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ValoEntry.TABLE_NAME + " (" +
                    ValoEntry._ID + " INTEGER PRIMARY KEY," +
                    ValoEntry.COLUMN_NAME_TITLE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ValoEntry.TABLE_NAME;
}
