package com.example.criminalintent2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    //private List<Crime> mCrimes;有数据库后list数据就删除
    //数据库需要的，然后赋值给构造器
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;



    /**
     * 写入数据
     * 将crime转化为contextValue类储存起来
     * 在addCrime中使用
     * ContentValues。它是一个键值存储类，
     * ContentValues只能用于处理SQLite数据
     * @param crime
     * @return
     */
    public static ContentValues  getContentValues(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.DATE,crime.getDate().getTime());
      //  contentValues.put(CrimeDbSchema.CrimeTable.Cols.SOLVED,crime.isSolved() ? 1:0);
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.TITLE,crime.getTitle());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.UUID,crime.getId().toString());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT,crime.getSuspect());

        return contentValues;
    }
    //更新记录，
    // 在CrimeFragment中使用
    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mSQLiteDatabase.update(CrimeDbSchema.CrimeTable.NAME,values,
                CrimeDbSchema.CrimeTable.Cols.UUID+
                "= ?",new String[]{uuidString});
    }
    //查询crime记录
    //读取数据
    private CrimeCursorWrapper queryCrimes(String whereClause,String[] whereArgs){
        Cursor cursor = mSQLiteDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static CrimeLab sCrimeLab;
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    //构造器
    private CrimeLab(Context context) {

        //数据的初始
        mContext = context;
        mSQLiteDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();

       // mCrimes = new ArrayList<>();
    }
    public void addCrime(Crime crime){

        ContentValues values = getContentValues(crime);

        //将数据加入数据库
        mSQLiteDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);
        //mCrimes.add(crime);
    }
    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }
    public Crime getCrime(UUID id) {
      //  for (Crime crime : mCrimes) {
      //      if (crime.getId().equals(id)) {
      //          return crime;
        //    }
      //  }
       CrimeCursorWrapper crimeCursorWrapper =
               queryCrimes(
                       CrimeDbSchema.CrimeTable.Cols.UUID +"= ?",
                       new String[]{id.toString()}
               );
       try {
           if (crimeCursorWrapper.getCount() == 0){
               return null;
           }
           crimeCursorWrapper.moveToFirst();
           return crimeCursorWrapper.getCrime();
       }finally {
           crimeCursorWrapper.close();
       }
    }//获得特殊id的Crime实列
    public File getPhotoFile(Crime crime){
        File file = mContext.getFilesDir();
        return new File(file,crime.getPhotoFilename());
    }
}
