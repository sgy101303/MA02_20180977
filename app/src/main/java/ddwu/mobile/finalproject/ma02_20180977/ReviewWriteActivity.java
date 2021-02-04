package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReviewWriteActivity extends AppCompatActivity {
    static final String TAG = "ReviewWriteActivity";

    private final static int REQUEST_TAKE_THUMBNAIL = 100;
    private static final int REQUEST_TAKE_PHOTO = 200;
    //    private Calendar myCalendar = Calendar.getInstance();
    private Calendar cal = Calendar.getInstance();

    HospitalReviewDBHelper helper;
    SQLiteDatabase sqLiteDatabase;
    String whereClause;
    String[] whereArgs;

    TextView tvYear;
    TextView tvMonth;
    TextView tvDay;
    EditText etReview;
    int nowYear;
    int nowMonth;
    int nowDay;
    Button btnCal;
    HospitalDTO hospital;
    String imgPutCol;

    private ImageView imgCapture;
    private String mCurrentPhotoPath;

//    private DatePickerDialog.OnDateSetListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        createNotificationChannel();

        helper = new HospitalReviewDBHelper(ReviewWriteActivity.this);
        hospital = (HospitalDTO) getIntent().getSerializableExtra("hospital");
//
        nowYear = cal.get(Calendar.YEAR);
        nowMonth = cal.get(Calendar.MONTH) + 1;
        nowDay = cal.get(Calendar.DAY_OF_MONTH);

        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        etReview = findViewById(R.id.etReview);


        tvYear.setText(String.format(String.valueOf(nowYear)));
        tvMonth.setText(String.format(String.valueOf(nowMonth)));
        tvDay.setText(String.format(String.valueOf(nowDay)));

        imgCapture = findViewById(R.id.imgCapture);
        btnCal = (Button)findViewById(R.id.btnCal);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    void showDialog() {
        DatePickerDialog dialog = new DatePickerDialog(ReviewWriteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvYear.setText(String.format(String.valueOf(year)));
                tvMonth.setText(String.format(String.valueOf(month + 1)));
                tvDay.setText(String.format(String.valueOf(dayOfMonth)));
            }
        }, nowYear, nowMonth - 1, nowDay);

        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        dialog.show();

    }

    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnCal :
//
//                DialogFragment newFragment = new DatePickerFragment();
//                newFragment.setMaxDate(System.currentTimeMillis());
//                newFragment.show(getSupportFragmentManager(),"datePicker");

//                final Calendar currentDate = Calendar.getInstance();
//                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
//                SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
//                SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

//                int year = Integer.parseInt(yearFormat.format(currentDate));
//                int month = Integer.parseInt(monthFormat.format(currentDate)) - 1;
//                int day = Integer.parseInt(dayFormat.format(currentDate));
//                break;

            case R.id.btnCapture:
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivityForResult(takePictureIntent, REQUEST_TAKE_THUMBNAIL);
                dispatchTakePictureIntent();
//                }
                break;
//            case R.id.btnImgSelect:
//                galleryAddPic();
//                break;
            case R.id.btnSave:
                sqLiteDatabase = helper.getWritableDatabase();

                ContentValues row = new ContentValues();

//                    if(hospital.getDutyAddr()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYADDR, hospital.getDutyAddr());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYADDR, hospital.getDutyAddr());

//                    if(hospital.getDutyEtc()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYETC, hospital.getDutyEtc());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYETC, "");

//                    if(hospital.getDutyInf()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYINF, hospital.getDutyInf());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYINF, "");

//                    if(hospital.getDutyDivNam()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYDIVNAM, hospital.getDutyDivNam());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYDIVNAM, "");

//                    if(hospital.getDutyName()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYNAME, hospital.getDutyName());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYADDR, "");

//                    if(hospital.getDutyTel1()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTEL1, hospital.getDutyTel1());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTEL1, "");

//                    if(hospital.getDutyTel3()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTEL3, hospital.getDutyTel3());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTEL3, "");

//                    if(hospital.getDutyTime1c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME1C, hospital.getDutyTime1c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1C, "");

//                    if(hospital.getDutyTime2c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME2C, hospital.getDutyTime2c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2C, "");

//                    if(hospital.getDutyTime3c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME3C, hospital.getDutyTime3c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3C, "");

//                    if(hospital.getDutyTime4c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME4C, hospital.getDutyTime4c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4C, "");

//                    if(hospital.getDutyTime5c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME5C, hospital.getDutyTime5c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5C, "");

//                    if(hospital.getDutyTime6c()!=null)
                row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6C, hospital.getDutyTime6c());
//                    else
//                row.put(HospitalReviewDBHelper.COL_DUTYTIME6C, "");
//
//                    if(hospital.getDutyTime7c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME7C, hospital.getDutyTime7c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7C, "");

//                    if(hospital.getDutyTime8c()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME8C, hospital.getDutyTime8c());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8C, "");

//                    if(hospital.getDutyTime1s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME1S, hospital.getDutyTime1s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME1S, "");

//                    if(hospital.getDutyTime2s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME2S, hospital.getDutyTime2s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME2S, "");

//                    if(hospital.getDutyTime3s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME3S, hospital.getDutyTime3s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME3S, "");

//                    if(hospital.getDutyTime4s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME4S, hospital.getDutyTime4s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME4S, "");

//                    if(hospital.getDutyTime5s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME5S, hospital.getDutyTime5s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME5S, "");

//                    if(hospital.getDutyTime6s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME6S, hospital.getDutyTime6s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME6S, "");

//                    if(hospital.getDutyTime7s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME7S, hospital.getDutyTime7s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME7S, "");

//                    if(hospital.getDutyTime8s()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYTIME8S, hospital.getDutyTime8s());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYTIME8S, "");

//                    if(hospital.getDutyMapimg()!=null)
                row.put(HospitalReviewDBHelper.COL_DUTYMAPIMG, hospital.getDutyMapimg());
//                    else
//                        row.put(HospitalBookmarkDBHelper.COL_DUTYMAPIMG, "");

//                    if(hospital.getWgs84Lat()!=null)
                row.put(HospitalReviewDBHelper.COL_WGS84LAT, hospital.getWgs84Lat());
//                    if(hospital.getWgs84Lon()!=null)
                row.put(HospitalBookmarkDBHelper.COL_WGS84LON, hospital.getWgs84Lon());
                row.put(HospitalReviewDBHelper.COL_DATE, tvYear.getText().toString()+"년"+tvMonth.getText().toString()+"월"+tvDay.getText().toString()+"일");
                row.put(HospitalReviewDBHelper.COL_REVIEW, etReview.getText().toString());
                row.put(HospitalReviewDBHelper.COL_IMG, mCurrentPhotoPath);
                long count = sqLiteDatabase.insert(HospitalReviewDBHelper.TABLE_NAME, null, row);
                String result;
                if (count > 0)
                    result = "true";
                else
                    result = " false";
                if (result.equals("true")) {    // 정상수행에 따른 처리
                    Toast.makeText(ReviewWriteActivity.this, "일지가 기록되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ReviewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

                    NotificationCompat.Builder builder
                            = new NotificationCompat.Builder(this, getString(R.string.CHANNEL_ID))
                            .setSmallIcon(R.mipmap.somsom)
                            .setContentTitle("아프지마솜")
                            .setContentText("일지 작성이 정상적으로 등록되었습니다.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                    int notificationId = 100;
                    notificationManager.notify(notificationId, builder.build());
//                Notification noti = builder.build; 로 해도 됨
                } else {        // 이상에 따른 처리
                    Toast.makeText(ReviewWriteActivity.this, "일지 기록에 실패했습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

//    private void listener() {
//        listener = new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
////            myCalendar.set(Calendar.YEAR, year);
////            myCalendar.set(Calendar.MONTH, monthOfYear);
////            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                tvYear.setText(year);
//                tvMonth.setText(monthOfYear);
//                tvDay.setText(dayOfMonth);
//            }
//        };
//    }

    /*원본 사진 파일 저장*/
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(ReviewWriteActivity.this,
                        "ddwu.mobile.finalproject.ma02_20180977.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

//    public void processDatePickerResult(int year, int month, int day){
//        String month_string = Integer.toString(month+1);
//        String day_string = Integer.toString(day);
//        String year_string = Integer.toString(year);
//        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
//
//        tvYear.setText(year);
//        tvMonth.setText(month);
//        tvDay.setText(day);
//        Toast.makeText(this,"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
//    }


    /*사진의 크기를 ImageView에서 표시할 수 있는 크기로 변경*/
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imgCapture.getWidth();
        int targetH = imgCapture.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        imgCapture.setImageBitmap(bitmap);
    }


    /*현재 시간 정보를 사용하여 파일 정보 생성*/
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_THUMBNAIL && resultCode == RESULT_OK) {
            Bundle extra = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extra.get("data");
            imgCapture.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my seq";       // strings.xml 에 채널명 기록
            String description = "리뷰가 정상적으로 실행되었을 때 noti";       // strings.xml에 채널 설명 기록
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