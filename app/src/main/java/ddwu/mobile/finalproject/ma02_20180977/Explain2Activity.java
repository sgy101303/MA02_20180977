package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//네이버 지식백과를 통해 각 병원의 설명을 기재하였습니다.

public class Explain2Activity extends AppCompatActivity {

    String category1;
    String category2;
    TextView tvCategory;
    TextView tvPutExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain2);

        tvCategory = findViewById(R.id.tvCategory);
        tvPutExplain = findViewById(R.id.tvPutExplain);

        Intent intent = getIntent();
        category1 = intent.getStringExtra("category1");
        if(category1.equals("소아과") || category1.equals("정신과") || category1.equals("기타")) {
            category2 = intent.getStringExtra("category2");
        }

        tvCategory.setText(category1);

        if(category1.equals("전체")) {
            tvPutExplain.setText(R.string.all);
        }

        else if(category1.equals("안과")) {
            tvPutExplain.setText(R.string.eye);
        }

        else if(category1.equals("외과")) {
            tvPutExplain.setText(R.string.surgery);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if(category1.equals("내과")) {
            tvPutExplain.setText(R.string.internal);
        }

        else if(category1.equals("이비인후과")) {
            tvPutExplain.setText(R.string.otolaryngology);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if(category1.equals("치과")) {
            tvPutExplain.setText(R.string.dental);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if(category1.equals("한의원")) {
            tvPutExplain.setText(R.string.oriental);
        }

        else if(category1.equals("소아과")) {
            tvPutExplain.setText(R.string.kids);
        }

        else if(category1.equals("성형외과")) {
            tvPutExplain.setText(R.string.plastic);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if(category1.equals("정신과")) {
            tvPutExplain.setText(R.string.mental);
        }

        else if(category1.equals("피부과")) {
            tvCategory.setText(category1);
            tvPutExplain.setText(R.string.dermatology);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if(category1.equals("산부인과")) {
            tvCategory.setText(category1);
            tvPutExplain.setText(R.string.obgyn);
            tvPutExplain.setMovementMethod(new ScrollingMovementMethod());
        }

        else if (category1.equals("기타")){
            tvCategory.setText(category1);
            tvPutExplain.setText(R.string.write);
            Toast.makeText(Explain2Activity.this, category2 +" 입력", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLocalSelect :
                Intent intent = new Intent(Explain2Activity.this, Explain3Activity.class);
//                intent.putExtra("category1", category1);

                if(category1.equals("소아과") || category1.equals("정신과")) {
                    intent.putExtra("category1", category1);
                    intent.putExtra("category2", category2);
                }
                else if(category1.equals("기타")) {
                    intent.putExtra("category1", category2);
                }
                else {
                    intent.putExtra("category1", category1);
                }
                startActivity(intent);
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}