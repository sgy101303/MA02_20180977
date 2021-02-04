package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import static java.lang.Boolean.TRUE;


public class BookmarkActivity extends AppCompatActivity {

    final int UPDATE_CODE = 100;
    ListView listView = null;
    HospitalBookmarkDBHelper helper;
    Cursor cursor;
    MyBookmarkAdapter adapter;
    Boolean onResume = TRUE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        listView = (ListView)findViewById(R.id.lvBookmarkHospital);

        helper = new HospitalBookmarkDBHelper(BookmarkActivity.this);

        adapter = new MyBookmarkAdapter(BookmarkActivity.this, R.layout.listview_hospital, null);

        listView.setAdapter(adapter);

//		리스트 뷰 클릭 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                adapter.changeCursor(cursor);
                Intent intent = new Intent(BookmarkActivity.this, MoreInfoActivity.class);

                SQLiteDatabase db = helper.getReadableDatabase();
                cursor = db.rawQuery("select * from " + HospitalBookmarkDBHelper.TABLE_NAME + " where " + helper.COL_ID + " = " + id, null);

                HospitalDTO hospital = new HospitalDTO();

                if(cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYADDR))!=null)
                        hospital.setDutyAddr(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYADDR)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYINF))!=null)
                        hospital.setDutyInf(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYETC)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYETC))!=null)
                        hospital.setDutyEtc(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYETC)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYDIVNAM))!=null)
                        hospital.setDutyDivNam(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYDIVNAM)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYNAME))!=null)
                        hospital.setDutyName(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYNAME)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTEL1))!=null)
                        hospital.setDutyTel1(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTEL1)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTEL3))!=null)
                        hospital.setDutyTel3(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTEL3)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME1C))!=null)
                        hospital.setDutyTime1c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME1C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME2C))!=null)
                        hospital.setDutyTime2c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME2C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME3C))!=null)
                        hospital.setDutyTime3c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME3C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME4C))!=null)
                        hospital.setDutyTime4c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME4C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME5C))!=null)
                        hospital.setDutyTime5c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME5C)));


                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME6C))!=null)
                        hospital.setDutyTime6c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME6C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME7C))!=null)
                        hospital.setDutyTime7c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME7C)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME8C))!=null)
                        hospital.setDutyTime8c(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME8C)));


                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME1S))!=null)
                        hospital.setDutyTime1s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME1S)));


                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME2S))!=null)
                        hospital.setDutyTime2s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME2S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME3S))!=null)
                        hospital.setDutyTime3s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME3S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME4S))!=null)
                        hospital.setDutyTime4s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME4S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME5S))!=null)
                        hospital.setDutyTime5s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME5S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME6S))!=null)
                        hospital.setDutyTime6s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME6S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME7S))!=null)
                        hospital.setDutyTime7s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME7S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME8S))!=null)
                        hospital.setDutyTime8s(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYTIME8S)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYMAPIMG))!=null)
                        hospital.setDutyMapimg(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYMAPIMG)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_WGS84LAT))!=null)
                        hospital.setWgs84Lat(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_WGS84LAT)));

                    if (cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_WGS84LON))!=null)
                        hospital.setWgs84Lon(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_WGS84LON)));
                }
                intent.putExtra("hospital", hospital);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                cursor.moveToPosition(position);
                adapter.changeCursor(cursor);
                AlertDialog.Builder builder = new AlertDialog.Builder(BookmarkActivity.this);
                builder.setTitle("즐겨찾기 제거")
                        .setMessage(cursor.getString(cursor.getColumnIndex(helper.COL_DUTYNAME)) + "을(를) 지우시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                                String whereClause = helper.COL_ID + "=?";
                                String[] whereArgs = new String[] { String.valueOf(id) };
                                if ((sqLiteDatabase.delete(helper.TABLE_NAME, whereClause,whereArgs)) > 0) {
                                    Toast.makeText(BookmarkActivity.this, "즐겨찾기 제거 완료", Toast.LENGTH_SHORT).show();
                                    dbRead();
                                } else {
                                    Toast.makeText(BookmarkActivity.this, "즐겨찾기 제거 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_CANCELED:
                    onResume = TRUE;
                    Toast.makeText(this, "목록으로 돌아왔습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void dbRead() {
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + HospitalBookmarkDBHelper.TABLE_NAME, null);

        adapter.changeCursor(cursor);
        helper.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onResume) {
//        DB에서 데이터를 읽어와 Adapter에 설정
            dbRead();
        }
        adapter.changeCursor(cursor);
        helper.close();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        cursor 사용 종료
//        if (cursor != null) cursor.close();
//    }
}