package ddwu.mobile.finalproject.ma02_20180977;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapMIAActivity extends AppCompatActivity implements OnMapReadyCallback {
    final static String TAG = "MapMIAActivity";

    final static int PERMISSION_REQ_CODE = 100;

    private LocationManager locManager;
    private String bestProvider;

    double hospitalLat;
    double hospitalLon;
    String hospitalName;
    private Marker hospitalMarker;

    /*UI*/
    private GoogleMap mGoogleMap;
    private MarkerOptions markerOptions;
    LatLng currentLoc;
    private Geocoder geocoder;

    /*DATA*/
    private PlacesClient placesClient;

    double currentLat = R.string.init_lat;
    double currentLong = R.string.init_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_m_i_a);
        Intent intent = getIntent();
        hospitalLat = Double.parseDouble(intent.getStringExtra("lat"));
        hospitalLon = Double.parseDouble(intent.getStringExtra("lon"));
        hospitalName = intent.getStringExtra("name");

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /*Passive 가 아닌 GPS 또는 Network provider 중 선택이 필요할 경우 기준 설정
         * 실제 환경에서는 이 주석을 해제해야함*/
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        criteria.setAltitudeRequired(false);
        criteria.setCostAllowed(false);
        bestProvider = locManager.getBestProvider(criteria, true);

        mapLoad();

//        bestProvider = LocationManager.GPS_PROVIDER;

        Places.initialize(getApplicationContext(), getResources().getString(R.string.api_key));
        placesClient = Places.createClient(MapMIAActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map_mia, menu); //한번만 호출이 됨, 바꿔치기 하면서 생성 가능
        return true; //잘 만들어졌을 경우에는 true, 넘길 경우 false
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mapMia_hAddress:
                LatLng latLng = new LatLng(hospitalLat, hospitalLon);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(hospitalName + " 주소")
                        .setIcon(R.mipmap.somsom)
                        .setMessage(geoCoding(latLng))
                        .setPositiveButton("확인", null);
                Dialog dlg = builder.create();
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();
                break;
            case R.id.menu_mapMia_myAddress:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

                builder2.setTitle("현재 내 주소")
                        .setIcon(R.mipmap.somsom)
                        .setMessage(geoCoding(currentLoc))
                        .setPositiveButton("확인", null);
                Dialog dlg2 = builder2.create();
                dlg2.setCanceledOnTouchOutside(false);
                dlg2.show();
                break;
            case R.id.menu_mapMia_explain:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

                builder3.setTitle("이렇게 사용해보세요.")
                        .setIcon(R.mipmap.somsom)
                        .setMessage("선택한 병원을 지도에서 볼 수 있는 화면이에요.\n"
                                + "내 위치가 기준으로 되어있으니,\n" +
                                "지도에 병원 표시가 보이지 않는다면\n" + "지도를 축소해보세요.\n\n" +
                                "내 위치인 파란 점을 클릭하면\n" + "현 주소를 볼 수 있어요.\n\n" +
                                "병원 표시도 클릭해보세요.\n" + "주소가 나와요."
                        )
                        .setPositiveButton("확인", null);
                Dialog dlg3 = builder3.create();
                dlg3.setCanceledOnTouchOutside(false);
                dlg3.show();
                break;
        }
        return true;
    }

    private void mapLoad() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapMIAActivity.this);
    }



    /*위치 조사 시작 - 권한 확인 필요*/
    private void getCurrentLocation () {
        if (checkPermission()) {
            locManager.requestLocationUpdates(bestProvider, 2000, 5, locListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*위치 조사 종료 - 반드시 추가!*/
        locManager.removeUpdates(locListener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        Log.d(TAG, "Map ready");

        getCurrentLocation();
        markerOptions = new MarkerOptions(); //지도가 준비되지 않으면 마커가 준비될 필요가 없기 때문에 지도가 준비될 때 만들어줌
        if (checkPermission()) {
            mGoogleMap.setMyLocationEnabled(true);
            if (locManager.getLastKnownLocation(bestProvider) != null) {
                Location location = locManager.getLastKnownLocation(bestProvider);
                currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
            }
            else
                currentLoc = new LatLng(hospitalLat, hospitalLon);
        }
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 13));

        LatLng latLng = new LatLng(hospitalLat, hospitalLon);

        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title(hospitalName);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        //google 맵에 전달
        hospitalMarker = mGoogleMap.addMarker(options);
        hospitalMarker.showInfoWindow(); //showInfoWindow를 생략할 경우 마커를 터치해야 윈도우가 나타남

        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(MapMIAActivity.this, "내 위치를 조정합니다.", Toast.LENGTH_SHORT).show();
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 13));
                return false;
            }
        });

        mGoogleMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                Toast.makeText(MapMIAActivity.this, "현재 주소\n" + geoCoding(currentLoc),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                //지오코딩부분
                Toast.makeText(MapMIAActivity.this, "병원 위치\n" + geoCoding(marker.getPosition()).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            currentLat = location.getLatitude();
            currentLong = location.getLongitude();
            currentLoc = new LatLng(currentLat, location.getLongitude());
        }
    };

    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MapMIAActivity.this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 퍼미션을 획득하였을 경우 맵 로딩 실행
                mapLoad();
            } else {
                // 퍼미션 미획득 시 액티비티 종료
                Toast.makeText(MapMIAActivity.this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


//    private void startCPSearch() {
//        // Use fields to define the data types to return.
//        List<Place.Field> placeFields = Collections.singletonList(Place.Field.ID);
//
//        // Use the builder to create a FindCurrentPlaceRequest.
//        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);
//
//        // Call findCurrentPlace and handle the response (first check that the user has granted permission).
//
//        if (checkPermission()) {
//            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
//            placeResponse.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
//                @Override
//                public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
//                    if (task.isSuccessful()){
//                        FindCurrentPlaceResponse response = task.getResult();
//                        for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
////                            Log.i(TAG, String.format("Place ID: %s", placeLikelihood.getPlace().getId()));
//                            Log.i(TAG, String.format("Place '%s' has likelihood: %f",
//                                    placeLikelihood.getPlace().getId(),
//                                    placeLikelihood.getLikelihood()));
//                        }
//                    } else {
//                        Exception exception = task.getException();
//                        if (exception instanceof ApiException) {
//                            ApiException apiException = (ApiException) exception;
//                            Log.e(TAG, "Place not found: " + apiException.getStatusCode());
//                        }
//                    }
//                }
//            });
//        }
//    }

    private String geoCoding(LatLng latLng) {
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
//        위도/경도에 해당하는 주소 정보를 Geocoder 에게 요청
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

//        결과로부터 주소 추출
        if (addresses == null || addresses.size()  == 0) {
            Toast.makeText(MapMIAActivity.this, "주소를 찾지 못했습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Address addressList = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            for(int i = 0; i <= addressList.getMaxAddressLineIndex(); i++) {
                addressFragments.add(addressList.getAddressLine(i));
            }
            return TextUtils.join(System.getProperty("line.separator"),
                    addressFragments);
            //TextUtils.join(개형문자, 문자열 list)
            //문자열 list에 담긴 여러 문자열들을 첫 번째 매개변수인 개행문자로 구분해가며 하나의 문자열로 변환함
        }
        return null;
    }

}