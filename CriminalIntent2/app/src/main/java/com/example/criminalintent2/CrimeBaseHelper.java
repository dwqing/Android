package com.example.criminalintent2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.criminalintent2.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;//version
    private static final String DATABASE_NAME = "crimeBase.db"; //database

    //自带
    public CrimeBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    //onCreate(SQLiteDatabase)方法负责创建初始数据库；
    // onUpgrade(SQLiteDatabase, int, int)方法负责与升级相关的工作。
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + CrimeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CrimeTable.Cols.UUID + ", " +
                CrimeTable.Cols.TITLE + ", " +
                CrimeTable.Cols.DATE + ", " +
                CrimeTable.Cols.SOLVED +","+
                CrimeTable.Cols.SUSPECT +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
