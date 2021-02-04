package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExpHospital:
                Intent intent1 = new Intent(MainActivity.this, Explain1Activity.class);
                startActivity(intent1);
                break;
            case R.id.btnSearchHospital:
                Intent intent2 = new Intent(MainActivity.this, ShowHospitalActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //한번만 호출이 됨, 바꿔치기 하면서 생성 가능
        return true; //잘 만들어졌을 경우에는 true, 넘길 경우 false
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_bookmark:
                Intent intent1 = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_review:
                Intent intent2 = new Intent(MainActivity.this, ReviewActivity.class);
                startActivity(intent2);
                break;
            case R.id.menu_introduce:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("컴퓨터학과 3학년 심가연입니다.")
                        .setIcon(R.mipmap.somsom)
                        .setMessage("요근래 질병 관련 이슈가 끊이지 않아 건강에 대한 유의가 각별해진만큼 건강관리가 중요해졌습니다.\n"
                                + "그런만큼 몸에 증상이 나타날 시 병원 가는 것이 선택이 아닌 필수로 자리 잡았고,\n" +
                                "쉽게 근처의 병원을 찾아 병원들의 정보를 제공해주고자 어플을 개발했습니다.")
                        .setPositiveButton("확인", null);
                Dialog dlg = builder.create();
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();

                break;
            case R.id.menu_exit:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        })
                        .setNegativeButton("취소", null);
                Dialog dlg2 = builder2.create();
                dlg2.setCanceledOnTouchOutside(false);
                dlg2.show();
                break;
        }
        return true;
    }
}