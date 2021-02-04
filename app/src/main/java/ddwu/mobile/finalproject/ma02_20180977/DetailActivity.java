package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    EditText etName;
    EditText etTelephone;
    EditText etAddress;

    String name;
    String telephone;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etName = findViewById(R.id.etName);
        etTelephone = findViewById(R.id.etTelephone);
        etAddress = findViewById(R.id.etAddress);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        telephone = intent.getStringExtra("telephone");
        address = intent.getStringExtra("address");

        etName.setText(name);
        if(telephone == null) {
            etTelephone.setText("정보 없음");
        }
        else
            etTelephone.setText(telephone);
        if(address!=null)
            etAddress.setText(address);
    }

    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnReservD:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                builder.setTitle("필독")
//                        .setIcon(R.mipmap.somsom)
//                        .setMessage("리스트 찾기에서 병원명을 검색하여\n"
//                                + "진료 시간을 알아보고,\n" +
//                                "자신에게 필요한 시간에 알람을 설정해보세요.\n")
//                        .setPositiveButton("확인", null);
//                Dialog dlg = builder.create();
//                dlg.setCanceledOnTouchOutside(false);
//                dlg.show();
//
//                break;
            case R.id.btnReviewD:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

                builder2.setTitle("필독")
                        .setIcon(R.mipmap.somsom)
                        .setMessage("리스트 찾기에서 병원명을 검색하여\n"
                                + "병원 정보를 확인한 후,\n" +
                                "방문 기록 일지를 작성해보세요.\n")
                        .setPositiveButton("확인", null);
                Dialog dlg2 = builder2.create();
                dlg2.setCanceledOnTouchOutside(false);
                dlg2.show();
                break;
            case R.id.btnCallD:
                if(telephone!=null) {
                    Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telephone));
                    startActivity(intentCall);
                }
                else
                    Toast.makeText(DetailActivity.this, "전화번호가 제공되지 않습니다.",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_detail_explain:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("즐겨찾기, 리뷰 작성, 예약은?")
                        .setIcon(R.mipmap.somsom)
                        .setMessage("이 페이지는 근처 병원의 간단한 정보만 나타내는 페이지에요.\n\n"
                                + "즐겨찾기와 리뷰 작성, 예약 서비스는 " +
                                "이전 화면의 리스트로 찾기 버튼을 클릭하여 병원명을 검색 후 이용해보세요.")
                        .setPositiveButton("확인", null);
                Dialog dlg = builder.create();
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();

                break;
        }
        return true;
    }
}