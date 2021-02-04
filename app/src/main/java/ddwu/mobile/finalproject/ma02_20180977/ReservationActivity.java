package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {
    final static String TAG = "ReservationActivity 입니다";

    AlarmDBHelper helper;
    SQLiteDatabase sqLiteDatabase;
    HospitalDTO hospital;
    Cursor cursor;
    String putDate;
//    String mon1 = "없음";
//    String tue1 = "없음";
//    String wed1 = "없음";
//    String thr1 = "없음";
//    String fri1 = "없음";
//    String sat1 = "없음";
//    String sun1 = "없음";
//    String hol1 = "없음";
//    String mon2 = "없음";
//    String tue2 = "없음";
//    String wed2 = "없음";
//    String thr2 = "없음";
//    String fri2 = "없음";
//    String sat2 = "없음";
//    String sun2 = "없음";
//    String hol2 = "없음";
//    TextView tvTime;

    private TimePicker timePicker;
    private AlarmManager alarmManager;
    private Calendar cal = Calendar.getInstance();

    int nowYear;
    int nowMonth;
    int nowDay;
    private int hour, minute;

    Button btnRervCal;
    RadioGroup rg;
    //    RadioButton cbSun, cbMon, cbTue, cbWed, cbThu, cbFri, cbSat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        createNotificationChannel();

        helper = new AlarmDBHelper(ReservationActivity.this);
        hospital = (HospitalDTO) getIntent().getSerializableExtra("hospital");

        nowYear = cal.get(Calendar.YEAR);
        nowMonth = cal.get(Calendar.MONTH) + 1;
        nowDay = cal.get(Calendar.DAY_OF_MONTH);
        btnRervCal = (Button)findViewById(R.id.btnRervCal);

//        tvTime = findViewById(R.id.tvTime);

//        rg = findViewById(R.id.rgDay);
        btnRervCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        timePicker=findViewById(R.id.tp_timepicker);
        alarmManager= (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

//        cbSun=findViewById(R.id.cb_sun);
//        cbMon=findViewById(R.id.cb_mon);
//        cbTue=findViewById(R.id.cb_thu);
//        cbWed=findViewById(R.id.cb_wed);
//        cbThu=findViewById(R.id.cb_thu);
//        cbFri=findViewById(R.id.cb_fri);
//        cbSat=findViewById(R.id.cb_sat);
    }


    void showDialog() {
        DatePickerDialog dialog = new DatePickerDialog(ReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(ReservationActivity.this,year + "년 " + (month + 1) + "월 " + dayOfMonth + "일 선택", Toast.LENGTH_SHORT).show();
                putDate = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                nowYear=year;
                nowMonth=month;
                nowDay=dayOfMonth;
//                cal.set(Calendar.YEAR, year);
//                cal.set(Calendar.MONTH, month + 1);
//                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        }, nowYear, nowMonth - 1, nowDay);

        dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        dialog.show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnTime:
//                if(hospital.getDutyTime1c()!=null)
//                    mon1=hospital.getDutyTime1c();
//
//                if(hospital.getDutyTime2c()!=null)
//                    tue1=hospital.getDutyTime2c();
//
//                if(hospital.getDutyTime3c()!=null)
//                    wed1=hospital.getDutyTime3c();
//
//                if(hospital.getDutyTime4c()!=null)
//                    thr1=hospital.getDutyTime4c();
//
//                if(hospital.getDutyTime5c()==null)
//                    fri1=hospital.getDutyTime5c();
//
//                if(hospital.getDutyTime6c()==null)
//                    fri1=hospital.getDutyTime5c();
//
//                if(hospital.getDutyTime7c()==null)
//                    fri1=hospital.getDutyTime5c();
//                break;
            case R.id.btnAlarmOn:

                Intent intent = new Intent(ReservationActivity.this, MyBroadCastReceiverAlarm.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(ReservationActivity.this, 0, intent, 0); //PendingIntent.FLAG_UPDATE_CURRENT
//                rg.getCheckedRadioButtonId();
//                week = {false, cbSun.isChecked(), cbMon.isChecked(), cbTue.isChecked(), cbWed.isChecked(),
//                        cbThu.isChecked(), cbFri.isChecked(), cbSat.isChecked()}; // cbSun을 1번부터 사용하기 위해 배열 0번은 false로 고정

//                if (!cbSun.isChecked() && !cbMon.isChecked() && !cbTue.isChecked() && !cbWed.isChecked() && !cbThu.isChecked() && !cbFri.isChecked() && !cbSat.isChecked()) {
//                    Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    Toast.makeText(this, "버전을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

//                intent.putExtra("weekday", week);

                cal.set(Calendar.YEAR, nowYear);
                cal.set(Calendar.MONTH, nowMonth);
                cal.set(Calendar.DAY_OF_MONTH, nowDay);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                Date today = new Date();
//                long intervalDay = 24 * 60 * 60 * 1000;// 24시간

                long selectTime = cal.getTimeInMillis();
//                long currenTime = System.currentTimeMillis();

                //만약 설정한 시간이, 현재 시간보다 작다면 알람이 부정확하게 울리기 때문에 다음날 울리게 설정
//                if (currenTime > selectTime) {
//                    selectTime += intervalDay;
//                }

                Log.e(TAG, "등록 버튼 누른 시간 : " + today + "  설정한 시간 : " + cal.getTime());

                Log.d(TAG, "calendar.getTimeInMillis()  : " + cal.getTimeInMillis());

                // 지정한 시간에 알림
//                alarmManager.set(AlarmManager.RTC_WAKEUP, selectTime, pIntent);

                alarmManager.set(AlarmManager.RTC, selectTime, pIntent);

                sqLiteDatabase = helper.getWritableDatabase();

                ContentValues row = new ContentValues();
                row.put(AlarmDBHelper.COL_DATE, putDate + hour + "시" + minute + "분");
                row.put(AlarmDBHelper.COL_DUTYNAME, hospital.getDutyName());

                long count = sqLiteDatabase.insert(AlarmDBHelper.TABLE_NAME, null, row);

                String result;
                if (count > 0)
                    result = "true";
                else
                    result = " false";
                if (result.equals("true")) {    // 정상수행에 따른 처리
                    Toast.makeText(ReservationActivity.this, "알람이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(ReservationActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);


                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    int notificationId = 0;

                    cursor = sqLiteDatabase.rawQuery("SELECT _id FROM " + AlarmDBHelper.TABLE_NAME + " WHERE "
                            + helper.COL_DUTYNAME + "= '" + hospital.getDutyName() + "' and " + helper.COL_DATE + "= '" + putDate + "' ", null);

//                    String[] colums = {"_id"};
//                    String selection = "name=? and date=?";
//                    String[] selectArgs = new String[] {hospital.getDutyName(), putDate};
//                    cursor = sqLiteDatabase.rawquery(AlarmDBHelper.TABLE_NAME, colums, selection, selectArgs, null, null,null, null);

                    if(cursor.moveToNext()) {
                        notificationId = cursor.getColumnIndex(AlarmDBHelper.COL_ID);
                    }
//                    intent.putExtra("id", notificationId);
                    setResult(RESULT_OK);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(ReservationActivity.this, getString(R.string.CHANNEL_ID2))
                            .setSmallIcon(R.mipmap.somsom) //알람 아이콘
                            .setContentTitle("아프지마솜")
                            .setContentText("알람이 등록되었습니다.")
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT); //알람 중요도

                    notificationManager.notify(200, builder.build()); //알람 생성
                    finish();
//                Notification noti = builder.build; 로 해도 됨
                } else {        // 이상에 따른 처리
                    Toast.makeText(ReservationActivity.this, "알람 등록에 실패했습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my alarm";       // strings.xml 에 채널명 기록
            String description = "알람이 설정됐을 때";       // strings.xml에 채널 설명 기록
            int importance = NotificationManager.IMPORTANCE_DEFAULT;    // 알림의 우선순위 지정
            NotificationChannel channel = new NotificationChannel(getString(R.string.CHANNEL_ID), name, importance);    // CHANNEL_ID 지정
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);  // 채널 생성
            notificationManager.createNotificationChannel(channel);
        }
    }
}