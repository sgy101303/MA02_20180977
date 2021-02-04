package ddwu.mobile.finalproject.ma02_20180977;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

public class ShowHospitalActivity extends AppCompatActivity implements OnMapReadyCallback {

    final static String TAG = "ShowHospitalActivity";
    int count = 0;

    final static int PERMISSION_REQ_CODE = 100;

    private LocationManager locManager;
    private String bestProvider;


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
        setContentView(R.layout.activity_hospital_map);

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
        placesClient = Places.createClient(ShowHospitalActivity.this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                searchStart();
                break;
            case R.id.btnList :
                Intent intent = new Intent(ShowHospitalActivity.this, Explain1Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void mapLoad() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ShowHospitalActivity.this);
    }

    private void searchStart() {
//        mGoogleMap.clear();//지도 클리어
        Location lastLocation = null;
        if (checkPermission()) {
            lastLocation = locManager.getLastKnownLocation(bestProvider);
        }
        new NRPlaces.Builder().listener(placesListener)
                .key(getResources().getString(R.string.api_key))
                .latlng(lastLocation.getLatitude(),lastLocation.getLongitude())
                .radius(5000)
                .type(PlaceType.HOSPITAL)
                .build()
                .execute();
    }

    /*현재 사용 중인 Provider 로부터 전달 받은 최종 위치의 주소 확인 - 권한 확인 필요*/
    private void getLastLocation() {
        if (checkPermission()) {
            Location lastLocation = locManager.getLastKnownLocation(bestProvider);
            currentLat = lastLocation.getLatitude();
            currentLong = lastLocation.getLongitude();
        }
    }


    /*위치 조사 시작 - 권한 확인 필요*/
    private void getCurrentLocation () {
        if (checkPermission()) {
            locManager.requestLocationUpdates(bestProvider, 5000, 13, locListener);
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
        getLastLocation();

        if (checkPermission()) {
            mGoogleMap.setMyLocationEnabled(true);
        }
        if (locManager.getLastKnownLocation(bestProvider) != null) {
            Location location = locManager.getLastKnownLocation(bestProvider);
            currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 13));
        }
        else {
            currentLoc = new LatLng(R.string.init_lat, R.string.init_lng);
        }
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 13));
        markerOptions = new MarkerOptions(); //지도가 준비되지 않으면 마커가 준비될 필요가 없기 때문에 지도가 준비될 때 만들어줌

        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(ShowHospitalActivity.this, "내 위치를 조정합니다.", Toast.LENGTH_SHORT).show();
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 13));
                return false;
            }
        });

        mGoogleMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                Toast.makeText(ShowHospitalActivity.this, "현재 주소 : " + geoCoding(currentLoc),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                String placeId = marker.getTag().toString();
                final String clickPlaceId = placeId;

                AlertDialog.Builder builderTitleSearch = new AlertDialog.Builder(ShowHospitalActivity.this)
                        .setTitle("상세보기")
                        .setMessage("이 병원을 자세히 보시겠습니까?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getPlaceDetail(clickPlaceId);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ShowHospitalActivity.this, "검색이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                Dialog dlgT = builderTitleSearch.create();
                dlgT.setCanceledOnTouchOutside(false);
                dlgT.show();

                //지오코딩부분
//                Toast.makeText(MainActivity.this, geoCoding(marker.getPosition()).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPlaceDetail(String placeId) {
        List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME,
                Place.Field.PHONE_NUMBER, Place.Field.ADDRESS);

        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();

        placesClient.fetchPlace(request).addOnSuccessListener(
                new OnSuccessListener<FetchPlaceResponse>() {
                    @Override
                    public void onSuccess(FetchPlaceResponse response) {
                        Place place = response.getPlace();
                        Log.d(TAG, "Place found: " + place.getName());
                        Log.d(TAG, "Phone: " + place.getPhoneNumber());
                        Log.d(TAG, "Address: " + place.getAddress());
                        callDetailActivity(place);
                    }
                }
        ).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.d(TAG, "Place not found : " + statusCode + " " + e.getMessage());
                        }
                    }
                }
        );
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            currentLat = location.getLatitude();
            currentLong = location.getLongitude();
            currentLoc = new LatLng(currentLat, location.getLongitude());
        }
    };

    private void callDetailActivity(Place place) {
        Intent intent = new Intent(ShowHospitalActivity.this, DetailActivity.class);
        intent.putExtra("name", place.getName());
        intent.putExtra("telephone", place.getPhoneNumber());
        intent.putExtra("address", place.getAddress());
        intent.putExtra("latlng", place.getLatLng());

        startActivity(intent);
    }

    PlacesListener placesListener = new PlacesListener() {

        @Override
        public void onPlacesSuccess(final List<noman.googleplaces.Place> places) {
            Log.d(TAG, "Adding Markers");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //final로 places 선언해야 나중에 안없어지고 계속 사용 가능, 실행하다 없어질 수 있으니까
                    for (noman.googleplaces.Place place : places) {
                        markerOptions.title(place.getName());
                        markerOptions.position(new LatLng(place.getLatitude(), place.getLongitude()));
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                        Marker newMarker = mGoogleMap.addMarker(markerOptions);
                        newMarker.showInfoWindow();
                        newMarker.setTag(place.getPlaceId());
                        Log.d(TAG, place.getPlaceId() + " : " + place.getName());
                    }
                }
            });

        }

        @Override
        public void onPlacesFailure(PlacesException e) { }

        @Override
        public void onPlacesStart() { }

        @Override
        public void onPlacesFinished() { }
    };

    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ShowHospitalActivity.this,
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
                Toast.makeText(ShowHospitalActivity.this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ShowHospitalActivity.this, "주소를 찾지 못했습니다.", Toast.LENGTH_SHORT).show();
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
