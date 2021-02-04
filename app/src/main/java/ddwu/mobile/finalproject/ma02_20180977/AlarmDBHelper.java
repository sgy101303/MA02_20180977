package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class AlarmDBHelper  extends SQLiteOpenHelper {

    final static String DB_NAME = "alarm.db";
    public final static String TABLE_NAME = "alarm_table";

    public final static String COL_ID = "_id";
    public final static String COL_DATE = "date"; //주소
    public final static String COL_DUTYNAME = "dutyName"; //기관명


    public AlarmDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_DATE + " TEXT, "
                + COL_DUTYNAME + " TEXT)";

//        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
//                COL_NUM + " TEXT, " + COL_GENRE + " TEXT, " + COL_MOVIE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
