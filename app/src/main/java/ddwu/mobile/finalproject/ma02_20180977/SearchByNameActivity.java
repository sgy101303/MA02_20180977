package ddwu.mobile.finalproject.ma02_20180977;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchByNameActivity extends AppCompatActivity {
    public static final String TAG = "SearchByNameActivity";

    //    final int UPDATE_CODE = 100;
    HospitalBookmarkDBHelper helper;
    Cursor cursor;

    String hospitalName;
    ListView lvList;
    String apiAddressLocalQN;
//    String apiAddressLocal;
//    String apiAddressLocalQ0;
//    String apiAddressImg;

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
        setContentView(R.layout.activity_search_by_name);
        lvList =findViewById(R.id.lvSearchName);

        Intent intent = getIntent();
        hospitalName = intent.getStringExtra("hospitalName");

        helper = new HospitalBookmarkDBHelper(SearchByNameActivity.this);

        resultList = new ArrayList();
        adapter = new HospitalAdapter(SearchByNameActivity.this, R.layout.listview_hospital, resultList);
        lvList.setAdapter(adapter);

        apiAddressLocalQN = getResources().getString(R.string.api_url_portal_QN);
//        apiAddressLocalQ0 = getResources().getString(R.string.api_url_portal_Q0);
//        apiAddressImg = getResources().getString(R.string.api_url_img);
        parser = new PortalHospitalParser();
//        parserImg = new NaverImgParser();
        networkManager = new NetworkManager(SearchByNameActivity.this);
//        networkManager.setClientId(getResources().getString(R.string.client_id));
//        networkManager.setClientSecret(getResources().getString(R.string.client_secret));
//        imgFileManager = new ImageFileManager(Explain3Activity.this);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HospitalDTO hospitalDTO =resultList.get(position);
                Intent intent1 = new Intent(SearchByNameActivity.this, MoreInfoActivity.class);
                intent1.putExtra("hospital", hospitalDTO);
                startActivity(intent1);
            }
        });

        if (!isOnline()) {
            Toast.makeText(SearchByNameActivity.this, "네트워크를 사용가능하게 설정해주세요.", Toast.LENGTH_SHORT).show();
        }

//        Log.d(TAG, hospitalName);
//        Toast.makeText(SearchByNameActivity.this, hospitalName + " 검색" , Toast.LENGTH_SHORT).show();
        // 가져온 데이터는 파싱 수행 후 어댑터에 설정
        try {
            new NetworkAsyncTask().execute(apiAddressLocalQN + URLEncoder.encode(hospitalName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(SearchByNameActivity.this, "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    class NetworkAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(SearchByNameActivity.this, "Wait", "Downloading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            String address = strings[0];
            String result = null;
            // networking
            result = networkManager.downloadContents(address);
            if(result==null) return "Error!";

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
                Toast.makeText(SearchByNameActivity.this, "결과를 찾을 수 없습니다." , Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(SearchByNameActivity.this, hospitalName + " 이름의 병원이 " + resultList.size() + " 개 있습니다." , Toast.LENGTH_SHORT).show();
            }
//            adapter.notifyDataSetChanged();
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