package ddwu.mobile.finalproject.ma02_20180977;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HospitalBookmarkDBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "bookmarkHospital.db";
    public final static String TABLE_NAME = "bookmark_hospital_table";

    public final static String COL_ID = "_id";
    public final static String COL_DUTYADDR = "dutyAddr"; //주소
    public final static String COL_DUTYETC = "dutyEtc"; //비고
    public final static String COL_DUTYINF = "dutyInf"; //기관 설명 상세
    public final static String COL_DUTYDIVNAM = "dutyDivNam"; // 병원분류명
    public final static String COL_DUTYNAME = "dutyName"; //기관명
    public final static String COL_DUTYTEL1 = "dutyTel1"; //대표전화
    public final static String COL_DUTYTEL3 = "dutyTel3"; //응급실 전화
    //c가 끝나는 시간
    public final static String COL_DUTYTIME1C = "dutyTime1c";
    public final static String COL_DUTYTIME2C = "dutyTime2c";
    public final static String COL_DUTYTIME3C = "dutyTime3c";
    public final static String COL_DUTYTIME4C = "dutyTime4c";
    public final static String COL_DUTYTIME5C = "dutyTime5c";
    public final static String COL_DUTYTIME6C = "dutyTime6c";
    public final static String COL_DUTYTIME7C = "dutyTime7c";
    public final static String COL_DUTYTIME8C = "dutyTime8c";
    //s가 시작하는 시간
    public final static String COL_DUTYTIME1S = "dutyTime1s";
    public final static String COL_DUTYTIME2S = "dutyTime2s";
    public final static String COL_DUTYTIME3S = "dutyTime3s";
    public final static String COL_DUTYTIME4S = "dutyTime4s";
    public final static String COL_DUTYTIME5S = "dutyTime5s";
    public final static String COL_DUTYTIME6S = "dutyTime6s";
    public final static String COL_DUTYTIME7S = "dutyTime7s";
    public final static String COL_DUTYTIME8S = "dutyTime8s";

    public final static  String COL_DUTYMAPIMG = "dutyMapimg"; //약도
    public final static String COL_WGS84LAT = "wgs84Lat"; //위도
    public final static String COL_WGS84LON = "wgs84Lon"; //경도


    public HospitalBookmarkDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_DUTYADDR + " TEXT, " + COL_DUTYETC + " TEXT, " + COL_DUTYINF + " TEXT, " + COL_DUTYDIVNAM + " TEXT, " + COL_DUTYNAME + " TEXT, "
                + COL_DUTYTEL1 + " TEXT, " + COL_DUTYTEL3 + " TEXT, " + COL_DUTYTIME1C + " TEXT, " + COL_DUTYTIME2C + " TEXT, " + COL_DUTYTIME3C + " TEXT, "
                + COL_DUTYTIME4C + " TEXT, " + COL_DUTYTIME5C + " TEXT, " + COL_DUTYTIME6C + " TEXT, " + COL_DUTYTIME7C + " TEXT, " + COL_DUTYTIME8C + " TEXT, "
                + COL_DUTYTIME1S + " TEXT, " + COL_DUTYTIME2S + " TEXT, " + COL_DUTYTIME3S + " TEXT, "
                + COL_DUTYTIME4S + " TEXT, " + COL_DUTYTIME5S + " TEXT, " + COL_DUTYTIME6S + " TEXT, " + COL_DUTYTIME7S + " TEXT, " + COL_DUTYTIME8S + " TEXT, "
                + COL_DUTYMAPIMG + " TEXT, " + COL_WGS84LAT + " TEXT, "+ COL_WGS84LON + " TEXT)";

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
