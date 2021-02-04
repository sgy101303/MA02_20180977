package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Explain3Activity extends AppCompatActivity {


    public static final String TAG = "Explain3Activity";

    String category1;
    String category2="";

    String selectLocal;
    EditText etLocal;
    //    Button btnCall;
    ListView lvList;
    String apiAddressLocal;
    String apiAddressLocalQ0;
//    String apiAddressImg;
//    Button btnLocalSearch;

    String query;

    HospitalAdapter adapter;
    ArrayList<HospitalDTO> resultList;
    //    ArrayList<HospitalDTO> resultParsingList;
    PortalHospitalParser parser;
    //    NaverHospitalParser parser;
//    NaverImgParser parserImg;
    NetworkManager networkManager;
//    ImageFileManager imgFileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain3);

        etLocal = findViewById(R.id.etLocal);
        lvList =findViewById(R.id.lvList);

        Intent intent = getIntent();
        category1 = intent.getStringExtra("category1");

        resultList = new ArrayList();
        adapter = new HospitalAdapter(Explain3Activity.this, R.layout.listview_hospital, resultList);
        lvList.setAdapter(adapter);

        apiAddressLocal = getResources().getString(R.string.api_url_portal);
        apiAddressLocalQ0 = getResources().getString(R.string.api_url_portal_Q0);
//        apiAddressImg = getResources().getString(R.string.api_url_img);
        parser = new PortalHospitalParser();
//        parserImg = new NaverImgParser();
        networkManager = new NetworkManager(Explain3Activity.this);
//        networkManager.setClientId(getResources().getString(R.string.client_id));
//        networkManager.setClientSecret(getResources().getString(R.string.client_secret));
//        imgFileManager = new ImageFileManager(Explain3Activity.this);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HospitalDTO hospitalDTO =resultList.get(position);
                Intent intent1 = new Intent(Explain3Activity.this, MoreInfoActivity.class);
                intent1.putExtra("hospital", hospitalDTO);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 임시 파일 삭제
//        imgFileManager.clearTemporaryFiles();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLocalSearch :

//                Log.d(TAG, query);
                // OpenAPI 주소와 query 조합 후 서버에서 데이터를 가져옴
                // 가져온 데이터는 파싱 수행 후 어댑터에 설정

                //지역 전체보기일 때
                if(resultList.size()==0){
                    Toast.makeText(Explain3Activity.this, "시/구를 먼저 선택해주세요." , Toast.LENGTH_SHORT).show();
//                    if(category1.equals("전체")) { //지역에 카테고리까지 전체보기고 시군구만 입력할 때
//                        try {
//                            new NetworkAsyncTask().execute(apiAddressLocal + "&" + URLEncoder.encode("Q1","UTF-8") + "="
//                                    //                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
//                                    + URLEncoder.encode(etLocal.getText().toString(), "UTF-8"));
//                            //                    new NetworkAsyncTask().execute(apiAddressImg
//                            ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
//                            //                            + URLEncoder.encode(query, "UTF-8"));
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    else {//지역은 전체고 카테고리랑 시군구 입력
//                        try {
//                            new NetworkAsyncTask().execute(apiAddressLocal + "&" + URLEncoder.encode("Q1","UTF-8") + "="
//                                    + URLEncoder.encode(etLocal.getText().toString(), "UTF-8") + "&" + URLEncoder.encode("QN","UTF-8") + "="
//                                    + URLEncoder.encode(category1, "UTF-8"));
//                            //                    new NetworkAsyncTask().execute(apiAddressImg
//                            ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
//                            //                            + URLEncoder.encode(query, "UTF-8"));
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
                else if(etLocal.getText().length() == 0) {
                    Toast.makeText(Explain3Activity.this, "입력 먼저 해주세요." , Toast.LENGTH_SHORT).show();
                }
                else { //지역
//                    Toast.makeText(Explain3Activity.this, etLocal.getText().toString() + " 검색" , Toast.LENGTH_SHORT).show();
                    if (!isOnline()) {
                        Toast.makeText(Explain3Activity.this, "네트워크를 사용가능하게 설정해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    if(category1.equals("전체")) { //카테고리까지 전체보기고 지역하고 시군구 입력할 때
                        try {
                            new NetworkAsyncTask().execute(apiAddressLocalQ0 + URLEncoder.encode(selectLocal, "UTF-8")
                                    + "&" + URLEncoder.encode("Q1","UTF-8") + "="
                                    + URLEncoder.encode(etLocal.getText().toString(), "UTF-8"));
                            //                    new NetworkAsyncTask().execute(apiAddressImg
                            ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
                            //                            + URLEncoder.encode(query, "UTF-8"));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    else {//지역, 카테고리, 시군구 다 입력
                        try {
                            new NetworkAsyncTask().execute(apiAddressLocalQ0 + URLEncoder.encode(selectLocal, "UTF-8")
                                    + "&" + URLEncoder.encode("Q1","UTF-8") + "="
                                    + URLEncoder.encode(etLocal.getText().toString(), "UTF-8")
                                    + "&" + URLEncoder.encode("QN","UTF-8") + "="
                                    + URLEncoder.encode(category1, "UTF-8"));
                            //                    new NetworkAsyncTask().execute(apiAddressImg
                            ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
                            //                            + URLEncoder.encode(query, "UTF-8"));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_local, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_local_explain:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("이렇게 사용해보세요.")
                        .setIcon(R.mipmap.somsom)
                        .setMessage("찾고싶은 병원을 검색하는 페이지입니다.\n\n"
                                + "먼저 전체보기 혹은 시/구 선택에서 찾고싶은 지역을 선택해주세요.\n" +
                                "더 자세한 지역을 보고싶을 땐 직접 시/군/구를 입력하여 찾아보세요.\n\n" +
                                "리스트는 병원 이름 순으로 출력됩니다.")
                        .setPositiveButton("확인", null);
                Dialog dlg = builder.create();
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();

                break;

            case R.id.menu_all :
                if (!isOnline()) {
                    Toast.makeText(Explain3Activity.this, "네트워크를 사용가능하게 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
                selectLocal = "전체보기";
                if(category1.equals("전체"))
                    new NetworkAsyncTask().execute(apiAddressLocal);
                else {
                    try {
                        new NetworkAsyncTask().execute(apiAddressLocal + "&" + URLEncoder.encode("QN","UTF-8") + "="
                                //                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
                                + URLEncoder.encode(category1, "UTF-8"));
                        //                    new NetworkAsyncTask().execute(apiAddressImg
                        ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
                        //                            + URLEncoder.encode(query, "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.seoul:
            case R.id.incheon:
            case R.id.daejeon:
            case R.id.gwangju:
            case R.id.daegu:
            case R.id.ulsan:
            case R.id.busan:
            case R.id.gyeonggi:
            case R.id.gangwon:
            case R.id.sejong:
            case R.id.chungnam:
            case R.id.chungbuk:
            case R.id.jeonnam:
            case R.id.jeonbuk:
            case R.id.gyeongnam:
            case R.id.gyeongbuk:
            case R.id.jeju:
                if (!isOnline()) {
                    Toast.makeText(Explain3Activity.this, "네트워크를 사용가능하게 설정해주세요.", Toast.LENGTH_SHORT).show();
                }
                query = item.getTitle().toString();
                selectLocal = query;

                Log.d(TAG, query);
                Toast.makeText(Explain3Activity.this, query + " 선택" , Toast.LENGTH_SHORT).show();
                // OpenAPI 주소와 query 조합 후 서버에서 데이터를 가져옴
                // 가져온 데이터는 파싱 수행 후 어댑터에 설정

                if(category1.equals("전체")) {
                    try {
                        new NetworkAsyncTask().execute(apiAddressLocalQ0 + URLEncoder.encode(query, "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Toast.makeText(Explain3Activity.this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try {
                        new NetworkAsyncTask().execute(apiAddressLocalQ0 + URLEncoder.encode(query, "UTF-8")
                                + "&" + URLEncoder.encode("QN","UTF-8") + "=" + URLEncoder.encode(category1, "UTF-8"));
                        //                    new NetworkAsyncTask().execute(apiAddressImg
                        ////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
                        //                            + URLEncoder.encode(query, "UTF-8"));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

//                try {
//                    new NetworkAsyncTask().execute(apiAddressLocalQ0 + URLEncoder.encode(query, "UTF-8"));
////                    new NetworkAsyncTask().execute(apiAddressLocal + "&" + URLEncoder.encode("Q0","UTF-8") + "="
////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
////                            + URLEncoder.encode(query, "UTF-8"));
////                    new NetworkAsyncTask().execute(apiAddressImg
//////                        + URLEncoder.encode(query, "UTF-8")); 에러날 수 있어서 try-catch로 묶기
////                            + URLEncoder.encode(query, "UTF-8"));
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
                break;
        }
        return true;
    }

    class NetworkAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(Explain3Activity.this, "Wait", "Downloading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String address = strings[0];
            String result = null;
            // networking
            result = networkManager.downloadContents(address);
            if(result==null) return "Error!";

            Log.d(TAG, result);
            // parsing
            resultList = parser.parse(result);

//            resultParsingList = new ArrayList();
//            if(category1.equals("전체") == false) {
//                for (int i = 0; i < resultList.size(); i++) {
//                    if (resultList.get(i).getDutyName().contains(category1)) {
//                        resultParsingList.add(resultList.get(i));
//                    }
//                }
//                resultList = resultParsingList;
//            }

//            초기에 응답 느리지만 중간에 이미지 로딩 안나옴
//            for(NaverBookDto dto : resultList) {
//                Bitmap bitmap = networkManager.downloadImage(dto.getImageLink());
//                if(bitmap != null) {
//                    imgFileManager.saveBitmapToTemporary(bitmap,dto.getImageLink());
//                }
//            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            //parsing
//            resultList = parser.parse(result);
            adapter.setList(resultList);    // Adapter 에 결과 List 를 설정 후 notify
            if(resultList.size() == 0) {
                Toast.makeText(Explain3Activity.this, "결과를 찾을 수 없습니다." , Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Explain3Activity.this, category1 + " 검색 결과가 " + resultList.size() + " 개 있습니다.", Toast.LENGTH_SHORT).show();
                if (!selectLocal.equals("전체보기")) {
                    etLocal.setEnabled(true);
                }
            }
            progressDlg.dismiss();
        }

    }


    /* 네트워크 환경 조사 */
    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}