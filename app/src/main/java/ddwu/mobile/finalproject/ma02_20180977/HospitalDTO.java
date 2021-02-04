package ddwu.mobile.finalproject.ma02_20180977;

import java.io.Serializable;

public class HospitalDTO implements Serializable {

    private int _id;
    private String dutyAddr = null; //주소
    private String dutyName = null; //기관명
    private String dutyInf = null; //설명
    private String dutyEtc = null; //비고
    private String dutyDivNam = null; //비고
    private String dutyTel1 = null; //대표전화
    private String dutyTel3 = null; //응급실 전화
    //c가 끝나는 시간
    private String dutyTime1c = null;
    private String dutyTime2c = null;
    private String dutyTime3c = null;
    private String dutyTime4c = null;
    private String dutyTime5c = null;
    private String dutyTime6c = null;
    private String dutyTime7c = null;
    private String dutyTime8c = null;
    //s가 시작하는 시간
    private String dutyTime1s = null;
    private String dutyTime2s = null;
    private String dutyTime3s = null;
    private String dutyTime4s = null;
    private String dutyTime5s = null;
    private String dutyTime6s = null;
    private String dutyTime7s = null;
    private String dutyTime8s = null;

    //    private String hpid;
    private String dutyMapimg = null; //약도 img
    private String wgs84Lat; //위도
    private String wgs84Lon; //경도
//    private String imageLink;
//    private String imageFileName;       // 외부저장소에 저장했을 때의 파일명


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDutyAddr() {
        return dutyAddr;
    }

    public void setDutyAddr(String dutyAddr) {
        this.dutyAddr = dutyAddr;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyInf() {
        return dutyInf;
    }

    public void setDutyInf(String dutyInf) {
        this.dutyInf = dutyInf;
    }

    public String getDutyEtc() {
        return dutyEtc;
    }

    public void setDutyEtc(String dutyEtc) {
        this.dutyEtc = dutyEtc;
    }

    public String getDutyTel1() {
        return dutyTel1;
    }

    public void setDutyTel1(String dutyTel1) {
        this.dutyTel1 = dutyTel1;
    }

    public String getDutyTel3() {
        return dutyTel3;
    }

    public void setDutyTel3(String dutyTel3) {
        this.dutyTel3 = dutyTel3;
    }

    public String getDutyTime1c() {
        return dutyTime1c;
    }

    public void setDutyTime1c(String dutyTime1c) {
        this.dutyTime1c = dutyTime1c;
    }

    public String getDutyTime2c() {
        return dutyTime2c;
    }

    public void setDutyTime2c(String dutyTime2c) {
        this.dutyTime2c = dutyTime2c;
    }

    public String getDutyTime3c() {
        return dutyTime3c;
    }

    public void setDutyTime3c(String dutyTime3c) {
        this.dutyTime3c = dutyTime3c;
    }

    public String getDutyTime4c() {
        return dutyTime4c;
    }

    public void setDutyTime4c(String dutyTime4c) {
        this.dutyTime4c = dutyTime4c;
    }

    public String getDutyTime5c() {
        return dutyTime5c;
    }

    public void setDutyTime5c(String dutyTime5c) {
        this.dutyTime5c = dutyTime5c;
    }

    public String getDutyTime6c() {
        return dutyTime6c;
    }

    public void setDutyTime6c(String dutyTime6c) {
        this.dutyTime6c = dutyTime6c;
    }

    public String getDutyTime7c() {
        return dutyTime7c;
    }

    public void setDutyTime7c(String dutyTime7c) {
        this.dutyTime7c = dutyTime7c;
    }

    public String getDutyTime8c() {
        return dutyTime8c;
    }

    public void setDutyTime8c(String dutyTime8c) {
        this.dutyTime8c = dutyTime8c;
    }

    public String getDutyTime1s() {
        return dutyTime1s;
    }

    public void setDutyTime1s(String dutyTime1s) {
        this.dutyTime1s = dutyTime1s;
    }

    public String getDutyTime2s() {
        return dutyTime2s;
    }

    public void setDutyTime2s(String dutyTime2s) {
        this.dutyTime2s = dutyTime2s;
    }

    public String getDutyTime3s() {
        return dutyTime3s;
    }

    public void setDutyTime3s(String dutyTime3s) {
        this.dutyTime3s = dutyTime3s;
    }

    public String getDutyTime4s() {
        return dutyTime4s;
    }

    public void setDutyTime4s(String dutyTime4s) {
        this.dutyTime4s = dutyTime4s;
    }

    public String getDutyTime5s() {
        return dutyTime5s;
    }

    public void setDutyTime5s(String dutyTime5s) {
        this.dutyTime5s = dutyTime5s;
    }

    public String getDutyTime6s() {
        return dutyTime6s;
    }

    public void setDutyTime6s(String dutyTime6s) {
        this.dutyTime6s = dutyTime6s;
    }

    public String getDutyTime7s() {
        return dutyTime7s;
    }

    public void setDutyTime7s(String dutyTime7s) {
        this.dutyTime7s = dutyTime7s;
    }

    public String getDutyTime8s() {
        return dutyTime8s;
    }

    public void setDutyTime8s(String dutyTime8s) {
        this.dutyTime8s = dutyTime8s;
    }

//    public String getHpid() {
//        return hpid;
//    }

//    public void setHpid(String hpid) {
//        this.hpid = hpid;
//    }

    public String getDutyMapimg() {
        return dutyMapimg;
    }

    public void setDutyMapimg(String dutyMapimg) {
        this.dutyMapimg = dutyMapimg;
    }

    public String getWgs84Lat() {
        return wgs84Lat;
    }

    public void setWgs84Lat(String wgs84Lat) {
        this.wgs84Lat = wgs84Lat;
    }

    public String getWgs84Lon() {
        return wgs84Lon;
    }

    public void setWgs84Lon(String wgs84Lon) {
        this.wgs84Lon = wgs84Lon;
    }

    public String getDutyDivNam() {
        return dutyDivNam;
    }

    public void setDutyDivNam(String dutyDivNam) {
        this.dutyDivNam = dutyDivNam;
    }

    public HospitalDTO() {

    }

    public HospitalDTO(String dutyAddr, String dutyName, String dutyInf, String dutyEtc, String dutyDivNam, String dutyTel1, String dutyTel3, String dutyTime1c, String dutyTime2c, String dutyTime3c, String dutyTime4c, String dutyTime5c, String dutyTime6c, String dutyTime7c, String dutyTime8c, String dutyTime1s, String dutyTime2s, String dutyTime3s, String dutyTime4s, String dutyTime5s, String dutyTime6s, String dutyTime7s, String dutyTime8s, String dutyMapimg, String wgs84Lat, String wgs84Lon) {
        this.dutyAddr = dutyAddr;
        this.dutyName = dutyName;
        this.dutyInf = dutyInf;
        this.dutyEtc = dutyEtc;
        this.dutyDivNam = dutyDivNam;
        this.dutyTel1 = dutyTel1;
        this.dutyTel3 = dutyTel3;
        this.dutyTime1c = dutyTime1c;
        this.dutyTime2c = dutyTime2c;
        this.dutyTime3c = dutyTime3c;
        this.dutyTime4c = dutyTime4c;
        this.dutyTime5c = dutyTime5c;
        this.dutyTime6c = dutyTime6c;
        this.dutyTime7c = dutyTime7c;
        this.dutyTime8c = dutyTime8c;
        this.dutyTime1s = dutyTime1s;
        this.dutyTime2s = dutyTime2s;
        this.dutyTime3s = dutyTime3s;
        this.dutyTime4s = dutyTime4s;
        this.dutyTime5s = dutyTime5s;
        this.dutyTime6s = dutyTime6s;
        this.dutyTime7s = dutyTime7s;
        this.dutyTime8s = dutyTime8s;
//        this.hpid = hpid;
        this.dutyMapimg = dutyMapimg;
        this.wgs84Lat = wgs84Lat;
        this.wgs84Lon = wgs84Lon;
    }

    public HospitalDTO(int _id, String dutyAddr, String dutyName, String dutyInf, String dutyEtc, String dutyDivNam, String dutyTel1, String dutyTel3, String dutyTime1c, String dutyTime2c, String dutyTime3c, String dutyTime4c, String dutyTime5c, String dutyTime6c, String dutyTime7c, String dutyTime8c, String dutyTime1s, String dutyTime2s, String dutyTime3s, String dutyTime4s, String dutyTime5s, String dutyTime6s, String dutyTime7s, String dutyTime8s, String dutyMapimg, String wgs84Lat, String wgs84Lon) {
        this._id = _id;
        this.dutyAddr = dutyAddr;
        this.dutyName = dutyName;
        this.dutyInf = dutyInf;
        this.dutyEtc = dutyEtc;
        this.dutyDivNam = dutyDivNam;
        this.dutyTel1 = dutyTel1;
        this.dutyTel3 = dutyTel3;
        this.dutyTime1c = dutyTime1c;
        this.dutyTime2c = dutyTime2c;
        this.dutyTime3c = dutyTime3c;
        this.dutyTime4c = dutyTime4c;
        this.dutyTime5c = dutyTime5c;
        this.dutyTime6c = dutyTime6c;
        this.dutyTime7c = dutyTime7c;
        this.dutyTime8c = dutyTime8c;
        this.dutyTime1s = dutyTime1s;
        this.dutyTime2s = dutyTime2s;
        this.dutyTime3s = dutyTime3s;
        this.dutyTime4s = dutyTime4s;
        this.dutyTime5s = dutyTime5s;
        this.dutyTime6s = dutyTime6s;
        this.dutyTime7s = dutyTime7s;
        this.dutyTime8s = dutyTime8s;
//        this.hpid = hpid;
        this.dutyMapimg = dutyMapimg;
        this.wgs84Lat = wgs84Lat;
        this.wgs84Lon = wgs84Lon;
    }
}
