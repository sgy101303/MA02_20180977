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

public class ReviewActivity extends AppCompatActivity {

    final static String TAG = "Review list";
    final int PERMISSION_REQ_CODE = 100;    // Permission 요청 코드

    final int UPDATE_CODE = 100;
    ListView listView = null;
    HospitalReviewDBHelper helper;
    Cursor cursor;
    MyReviewAdapter adapter;
    Boolean onResume = TRUE;
    String review;
    String reviewDate;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        listView = (ListView)findViewById(R.id.lvReview);

        helper = new HospitalReviewDBHelper(ReviewActivity.this);

        adapter = new MyReviewAdapter(this, R.layout.listview_review, null);

        listView.setAdapter(adapter);

//		리스트 뷰 클릭 처리
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////				"delete from contact_table where _id =" + id"
//                cursor.moveToPosition(position);
//                adapter.changeCursor(cursor);
//                Intent intent = new Intent(ReviewActivity.this, ReviewOpenActivity.class);
//
//                SQLiteDatabase db = helper.getReadableDatabase();
//                cursor = db.rawQuery("select * from " + HospitalReviewDBHelper.TABLE_NAME + " where " + helper.COL_ID + " = " + id, null);
//
//                HospitalDTO hospital = new HospitalDTO();
//
//                if(cursor.moveToNext()) {
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYADDR))!=null)
//                        hospital.setDutyAddr(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYADDR)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYINF))!=null)
//                        hospital.setDutyInf(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYETC)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYETC))!=null)
//                        hospital.setDutyEtc(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYETC)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYDIVNAM))!=null)
//                        hospital.setDutyDivNam(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYDIVNAM)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYNAME))!=null)
//                        hospital.setDutyName(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYNAME)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTEL1))!=null)
//                        hospital.setDutyTel1(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTEL1)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTEL3))!=null)
//                        hospital.setDutyTel3(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTEL3)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME1C))!=null)
//                        hospital.setDutyTime1c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME1C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME2C))!=null)
//                        hospital.setDutyTime2c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME2C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME3C))!=null)
//                        hospital.setDutyTime3c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME3C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME4C))!=null)
//                        hospital.setDutyTime4c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME4C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME5C))!=null)
//                        hospital.setDutyTime5c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME5C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME6C))!=null)
//                        hospital.setDutyTime6c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME6C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME7C))!=null)
//                        hospital.setDutyTime7c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME7C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME8C))!=null)
//                        hospital.setDutyTime8c(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME8C)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME1S))!=null)
//                        hospital.setDutyTime1s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME1S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME2S))!=null)
//                        hospital.setDutyTime2s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME2S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME3S))!=null)
//                        hospital.setDutyTime3s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME3S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME4S))!=null)
//                        hospital.setDutyTime4s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME4S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME5S))!=null)
//                        hospital.setDutyTime5s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME5S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME6S))!=null)
//                        hospital.setDutyTime6s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME6S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME7S))!=null)
//                        hospital.setDutyTime7s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME7S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME8S))!=null)
//                        hospital.setDutyTime8s(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYTIME8S)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYMAPIMG))!=null)
//                        hospital.setDutyMapimg(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYMAPIMG)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_WGS84LAT))!=null)
//                        hospital.setWgs84Lat(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_WGS84LAT)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_WGS84LON))!=null)
//                        hospital.setWgs84Lon(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_WGS84LON)));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DATE))!=null)
//                        reviewDate = cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DATE));
//
//                    if (cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_REVIEW))!=null)
//                        review = cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_REVIEW));
//
//                        imgPath = cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG));
//
//                    Log.d(TAG, cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DATE)) + "\n\n\n");
//                    Log.d(TAG, cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG)) + "\n\n\n");
//                    Log.d(TAG, cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_REVIEW)) + "\n\n\n");
//                }
//                intent.putExtra("hospital", hospital);
//                intent.putExtra("reviewDate", reviewDate);
//                intent.putExtra("review", review);
//                intent.putExtra("imgPath", imgPath);
//                startActivityForResult(intent, UPDATE_CODE);
//            }
//        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                cursor.moveToPosition(position);
                adapter.changeCursor(cursor);
                AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                builder.setTitle("해당 리뷰 삭제")
                        .setMessage(cursor.getString(cursor.getColumnIndex(helper.COL_DATE)) + "에 쓴 리뷰를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                                String whereClause = helper.COL_ID + "=?";
                                String[] whereArgs = new String[] { String.valueOf(id) };
                                if ((sqLiteDatabase.delete(helper.TABLE_NAME, whereClause,whereArgs)) > 0) {
                                    Toast.makeText(ReviewActivity.this, "리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    dbRead();
                                } else {
                                    Toast.makeText(ReviewActivity.this, "리뷰 삭제에 실패하였습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
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
        cursor = db.rawQuery("select * from " + HospitalReviewDBHelper.TABLE_NAME, null);

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