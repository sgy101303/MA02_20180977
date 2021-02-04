package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReviewOpenActivity extends AppCompatActivity {

    final static String TAG = "Review 왜안돼";

    HospitalDTO hospital;

    String reviewDate;
    String review;
    String imgPath;

    TextView tvOpenDate;
    TextView tvOpenReview;

    private ImageView imgOpenCapture;

    //    private String mCurrentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_open);
        Log.d(TAG,"열렸다");

        hospital = (HospitalDTO) getIntent().getSerializableExtra("hospital");
        reviewDate = getIntent().getStringExtra("reviewDate");
        review = getIntent().getStringExtra("review");
        imgPath = getIntent().getStringExtra("imgPath");
        Log.d(TAG,review + "+++++++++" + reviewDate + "+++++++++++" + imgPath);

        tvOpenDate=findViewById(R.id.tvDate);
        tvOpenReview=findViewById(R.id.tvOpenReview);
        imgOpenCapture=findViewById(R.id.imgOpenCapture);

        if(imgPath!=null || imgPath.equals("null")==false)
            imgOpenCapture.setImageURI(Uri.parse(imgPath));

        tvOpenReview.setText(review);
        tvOpenDate.setText(reviewDate);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenReviewToInfo:
                Intent intent1 = new Intent(ReviewOpenActivity.this, MoreInfoActivity.class);
                intent1.putExtra("hospital", hospital);
                startActivity(intent1);
                break;
            case R.id.btnReviewOpenBack:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}