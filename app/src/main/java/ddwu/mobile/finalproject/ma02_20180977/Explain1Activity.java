package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Explain1Activity extends AppCompatActivity {

    private RadioGroup rg;
    EditText edText;
    EditText ethospitalName;
    final int BACK_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain1);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(changeListener);
        edText = findViewById(R.id.etCategory);
        ethospitalName = findViewById(R.id.ethospitalName);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        edText.setFocusableInTouchMode(false);
//        ethospitalName.setFocusableInTouchMode(false);
//        radiobtnAll.setFocusableInTouchMode(true);
//        radiobtnAll.requestFocus();
    }

    RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.radioButtonWrite) {
                edText.setEnabled(true);
                ethospitalName.setEnabled(false);
//                edText.setFocusableInTouchMode(true);
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            else if(i == R.id.radiobtnHName) {
                ethospitalName.setEnabled(true);
                edText.setEnabled(false);
//                ethospitalName.setFocusableInTouchMode(true);
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            else {
                ethospitalName.setEnabled(false);
                edText.setEnabled(false);
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext1to2:
                RadioButton radioButton = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String checkValue = radioButton.getText().toString();
                Intent intent = null;

                if(checkValue.equals("전체 검색 (모든 병원을 보겠습니다.)")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "전체");
                }
                else if(checkValue.equals("안과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "안과");
                }
                else if(checkValue.equals("외과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "외과");
                }
                else if(checkValue.equals("내과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "내과");
                }
                else if(checkValue.equals("이비인후과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "이비인후과");
                }
                else if(checkValue.equals("치과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "치과");
                }
                else if(checkValue.equals("한의원")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "한의원");
                }
                else if(checkValue.equals("소아과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "소아과");
                    intent.putExtra("category2", "소아병원");
                }
                else if(checkValue.equals("성형외과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "성형외과");
                }
                else if(checkValue.equals("정신과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "정신과");
                    intent.putExtra("category2", "정신병원");
                }
                else if(checkValue.equals("피부과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "피부과");
                }
                else if(checkValue.equals("산부인과")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "산부인과");
                }
                else if(checkValue.equals("기타")) {
                    intent = new Intent(Explain1Activity.this, Explain2Activity.class);
                    intent.putExtra("category1", "기타");
                    intent.putExtra("category2", edText.getText().toString());
                }
                else if(checkValue.equals("병원 이름을 검색할래요.")) {
                    intent = new Intent(Explain1Activity.this, SearchByNameActivity.class);
                    intent.putExtra("hospitalName", ethospitalName.getText().toString());
                }
                startActivityForResult(intent, BACK_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BACK_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_CANCELED:
                    Toast.makeText(this, "카테고리를 다시 선택해주세요.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}