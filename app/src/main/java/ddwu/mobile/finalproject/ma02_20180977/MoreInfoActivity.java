package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MoreInfoActivity extends AppCompatActivity {

    final int REVIEW_CODE = 100;
    final int ALARM_CODE = 200;
    //    db
    HospitalBookmarkDBHelper helper;
    SQLiteDatabase sqLiteDatabase;
    String whereClause;
    String[] whereArgs;
    Cursor cursor;

    HospitalDTO hospital;
//    ImageButton imgbtnStarOffM;

    Button btnCallDetail;
    Button btnReserv;
    Button btnReview;
    Button btnSearchHospitalOnMap;

    EditText etDutyName;
    EditText etDutyDivNam;
    EditText etDutyEtc;
    EditText etDutyInf;
    EditText etDutyAddr;
    EditText etDutyTel1;
    EditText etDutyTel3;
    EditText etDutyMapimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        helper = new HospitalBookmarkDBHelper(MoreInfoActivity.this);

        hospital = (HospitalDTO) getIntent().getSerializableExtra("hospital");


        btnCallDetail = findViewById(R.id.btnCallD);
//        btnReserv = findViewById(R.id.btnReservD);
        btnReview = findViewById(R.id.btnReviewD);
        btnSearchHospitalOnMap = findViewById(R.id.btnSearchHospitalOnMap);

        etDutyName = findViewById(R.id.etDutyName);
        etDutyDivNam = findViewById(R.id.etDutyDivNam);
        etDutyEtc = findViewById(R.id.etDutyEtc);
        etDutyInf = findViewById(R.id.etDutyInf);
        etDutyAddr = findViewById(R.id.etDutyAddr);
        etDutyTel1 = findViewById(R.id.etDutyTel1);
        etDutyTel3 = findViewById(R.id.etDutyTel3);
        etDutyMapimg = findViewById(R.id.etDutyMapimg);

        if(hospital.getDutyName()!=null)
            etDutyName.setText(hospital.getDutyName());
        if(hospital.getDutyDivNam()!=null)
            etDutyDivNam.setText(hospital.getDutyDivNam());
        if(hospital.getDutyEtc()!=null)
            etDutyEtc.setText(hospital.getDutyEtc());
        if(hospital.getDutyInf()!=null)
            etDutyInf.setText(hospital.getDutyInf());
        if(hospital.getDutyAddr()!=null)
            etDutyAddr.setText(hospital.getDutyAddr());
        if(hospital.getDutyTel1()!=null)
            etDutyTel1.setText(hospital.getDutyTel1());
        if(hospital.getDutyTel3()!=null)
            etDutyTel3.setText(hospital.getDutyTel3());
        if(hospital.getDutyMapimg()!=null)
            etDutyMapimg.setText(hospital.getDutyMapimg());

        sqLiteDatabase = helper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + HospitalBookmarkDBHelper.TABLE_NAME + " WHERE "
                + helper.COL_DUTYNAME + "= '" + hospital.getDutyName() + "'", null);

        final ToggleButton btnBookmark = (ToggleButton)this.findViewById(R.id.btnBookmark);

        if(cursor.moveToNext()) {
            Toast.makeText(MoreInfoActivity.this, "즐겨찾기한 병원입니다.", Toast.LENGTH_SHORT).show();
            btnBookmark.setBackgroundDrawable(getResources().getDrawable(R.drawable.staron));
            btnBookmark.setChecked(true);
        }

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnBookmark.isChecked()) {

                    sqLiteDatabase = helper.getWritableDatabase();

                    ContentValues row = new ContentValues();

//                    if(hospital.getDutyAddr()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYADDR, hospital.getDutyAddr());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYADDR, hospital.getDutyAddr());

//                    if(hospital.getDutyEtc()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYETC, hospital.getDutyEtc());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYETC, "");

//                    if(hospital.getDutyInf()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYINF, hospital.getDutyInf());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYINF, "");

//                    if(hospital.getDutyDivNam()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYDIVNAM, hospital.getDutyDivNam());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYDIVNAM, "");

//                    if(hospital.getDutyName()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYNAME, hospital.getDutyName());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYADDR, "");

//                    if(hospital.getDutyTel1()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTEL1, hospital.getDutyTel1());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTEL1, "");

//                    if(hospital.getDutyTel3()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTEL3, hospital.getDutyTel3());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTEL3, "");

//                    if(hospital.getDutyTime1c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1C, hospital.getDutyTime1c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1C, "");

//                    if(hospital.getDutyTime2c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2C, hospital.getDutyTime2c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2C, "");

//                    if(hospital.getDutyTime3c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3C, hospital.getDutyTime3c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3C, "");

//                    if(hospital.getDutyTime4c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4C, hospital.getDutyTime4c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4C, "");

//                    if(hospital.getDutyTime5c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5C, hospital.getDutyTime5c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5C, "");

//                    if(hospital.getDutyTime6c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6C, hospital.getDutyTime6c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6C, "");
//
//                    if(hospital.getDutyTime7c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7C, hospital.getDutyTime7c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7C, "");

//                    if(hospital.getDutyTime8c()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8C, hospital.getDutyTime8c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8C, "");

//                    if(hospital.getDutyTime1s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1S, hospital.getDutyTime1s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1S, "");

//                    if(hospital.getDutyTime2s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2S, hospital.getDutyTime2s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2S, "");

//                    if(hospital.getDutyTime3s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3S, hospital.getDutyTime3s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3S, "");

//                    if(hospital.getDutyTime4s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4S, hospital.getDutyTime4s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4S, "");

//                    if(hospital.getDutyTime5s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5S, hospital.getDutyTime5s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5S, "");

//                    if(hospital.getDutyTime6s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6S, hospital.getDutyTime6s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6S, "");

//                    if(hospital.getDutyTime7s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7S, hospital.getDutyTime7s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7S, "");

//                    if(hospital.getDutyTime8s()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8S, hospital.getDutyTime8s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8S, "");

//                    if(hospital.getDutyMapimg()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_DUTYMAPIMG, hospital.getDutyMapimg());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYMAPIMG, "");

//                    if(hospital.getWgs84Lat()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_WGS84LAT, hospital.getWgs84Lat());
//                    if(hospital.getWgs84Lon()!=null)
                    row.put(HospitalBookmarkDBHelper.COL_WGS84LON, hospital.getWgs84Lon());

                    long count = sqLiteDatabase.insert(HospitalBookmarkDBHelper.TABLE_NAME, null, row);
                    String result;
                    if (count > 0)
                        result = "true";
                    else
                        result = " false";
                    if (result.equals("true")) {    // 정상수행에 따른 처리
                        Toast.makeText(MoreInfoActivity.this, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                        btnBookmark.setBackgroundDrawable(getResources().getDrawable(R.drawable.staron));
                    } else {        // 이상에 따른 처리
                        Toast.makeText(MoreInfoActivity.this, "즐겨찾기 추가에 실패하였습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    sqLiteDatabase = helper.getWritableDatabase();
                    whereClause = helper.COL_DUTYNAME + "=?";
                    whereArgs = new String[] {hospital.getDutyName()};
                    if ((sqLiteDatabase.delete(helper.TABLE_NAME, whereClause,whereArgs)) > 0) {
                        Toast.makeText(MoreInfoActivity.this, "즐겨찾기에서 제거되었습니다.", Toast.LENGTH_SHORT).show();
                        btnBookmark.setBackgroundDrawable(getResources().getDrawable(R.drawable.staroff));
                    } else {
                        Toast.makeText(MoreInfoActivity.this, "즐겨찾기 제거에 실패하였습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReservM:
                Intent intentRersv = new Intent(MoreInfoActivity.this, ReservationActivity.class);
                intentRersv.putExtra("hospital", hospital);
                startActivity(intentRersv);
                break;
            case R.id.btnCallM:
                if(hospital.getDutyTel1()!=null) {
                    Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + hospital.getDutyTel1()));
                    startActivity(intentCall);
                }
                else
                    Toast.makeText(MoreInfoActivity.this, "전화번호가 제공되지 않습니다.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnReviewM:
                Intent intent = new Intent(MoreInfoActivity.this, ReviewWriteActivity.class);
                intent.putExtra("hospital", hospital);
                startActivityForResult(intent, REVIEW_CODE);
                break;
            case R.id.btnSearchHospitalOnMap:
                Intent intentMap = new Intent(MoreInfoActivity.this, MapMIAActivity.class);
                intentMap.putExtra("name", hospital.getDutyName());
                intentMap.putExtra("address", hospital.getDutyAddr());
                intentMap.putExtra("lat", hospital.getWgs84Lat());
                intentMap.putExtra("lon", hospital.getWgs84Lon());
                startActivity(intentMap);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REVIEW_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "일지 작성을 취소했습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        if (requestCode == ALARM_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "알람 설정을 취소했습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(cursor != null)
//            cursor.close();
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        helper.close();
//    }
}