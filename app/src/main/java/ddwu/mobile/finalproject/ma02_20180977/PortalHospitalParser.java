package ddwu.mobile.finalproject.ma02_20180977;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class PortalHospitalParser {
    public enum TagType { NONE, DUTYADDR, DUTYDIVNAM, DUTYETC, DUTYINF, DUTYNAME, DUTYTEL1, DUTYTEL3, DUTYTIME1C, DUTYTIME2C, DUTYTIME3C, DUTYTIME4C, DUTYTIME5C, DUTYTIME6C, DUTYTIME7C, DUTYTIME8C,
        DUTYTIME1S, DUTYTIME2S, DUTYTIME3S, DUTYTIME4S, DUTYTIME5S, DUTYTIME6S, DUTYTIME7S, DUTYTIME8S, DUTYMAPIMG, WGS84LAT, WGS84LON};

    final static String TAG_ITEM = "item";
    final static String TAG_DUTYADDR = "dutyAddr"; //주소
    final static String TAG_DUTYETC = "dutyEtc"; //비고
    final static String TAG_DUTYINF = "dutyInf"; //기관 설명 상세
    final static String TAG_DUTYDIVNAM = "dutyDivNam"; // 병원분류명
    final static String TAG_DUTYNAME = "dutyName"; //기관명
    final static String TAG_DUTYTEL1 = "dutyTel1"; //대표전화
    final static String TAG_DUTYTEL3 = "dutyTel3"; //응급실 전화
    //c가 끝나는 시간
    final static String TAG_DUTYTIME1C = "dutyTime1c";
    final static String TAG_DUTYTIME2C = "dutyTime2c";
    final static String TAG_DUTYTIME3C = "dutyTime3c";
    final static String TAG_DUTYTIME4C = "dutyTime4c";
    final static String TAG_DUTYTIME5C = "dutyTime5c";
    final static String TAG_DUTYTIME6C = "dutyTime6c";
    final static String TAG_DUTYTIME7C = "dutyTime7c";
    final static String TAG_DUTYTIME8C = "dutyTime8c";
    //s가 시작하는 시간
    final static String TAG_DUTYTIME1S = "dutyTime1s";
    final static String TAG_DUTYTIME2S = "dutyTime2s";
    final static String TAG_DUTYTIME3S = "dutyTime3s";
    final static String TAG_DUTYTIME4S = "dutyTime4s";
    final static String TAG_DUTYTIME5S = "dutyTime5s";
    final static String TAG_DUTYTIME6S = "dutyTime6s";
    final static String TAG_DUTYTIME7S = "dutyTime7s";
    final static String TAG_DUTYTIME8S = "dutyTime8s";

//    final static String TAG_HPID = "hpid";

    final static  String TAG_DUTYMAPIMG = "dutyMapimg"; //약도
    final static String TAG_WGS84LAT = "wgs84Lat"; //위도
    final static String TAG_WGS84LON = "wgs84Lon"; //경도

    public ArrayList<HospitalDTO> parse(String xml) {

        ArrayList<HospitalDTO> resultList = new ArrayList();
        HospitalDTO dto = null;

        PortalHospitalParser.TagType tagType = PortalHospitalParser.TagType.NONE;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals(TAG_ITEM)) {
                            dto = new HospitalDTO();
                        } else if (parser.getName().equals(TAG_DUTYADDR)) {
                            if (dto != null) tagType = TagType.DUTYADDR;
                        } else if (parser.getName().equals(TAG_DUTYDIVNAM)) {
                            if (dto != null) tagType = TagType.DUTYDIVNAM;
                        } else if (parser.getName().equals(TAG_DUTYETC)) {
                            if (dto != null) tagType = TagType.DUTYETC;
                        } else if (parser.getName().equals(TAG_DUTYINF)) {
                            if (dto != null) tagType = TagType.DUTYINF;
                        } else if (parser.getName().equals(TAG_DUTYNAME)) {
                            if (dto != null) tagType = TagType.DUTYNAME;
                        } else if (parser.getName().equals(TAG_DUTYTEL1)) {
                            if (dto != null) tagType = TagType.DUTYTEL1;
                        } else if (parser.getName().equals(TAG_DUTYTEL3)) {
                            if (dto != null) tagType = TagType.DUTYTEL3;
                        } else if (parser.getName().equals(TAG_DUTYTIME1C)) {
                            if (dto != null) tagType = TagType.DUTYTIME1C;
                        } else if (parser.getName().equals(TAG_DUTYTIME2C)) {
                            if (dto != null) tagType = TagType.DUTYTIME2C;
                        } else if (parser.getName().equals(TAG_DUTYTIME3C)) {
                            if (dto != null) tagType = TagType.DUTYTIME3C;
                        } else if (parser.getName().equals(TAG_DUTYTIME4C)) {
                            if (dto != null) tagType = TagType.DUTYTIME4C;
                        } else if (parser.getName().equals(TAG_DUTYTIME5C)) {
                            if (dto != null) tagType = TagType.DUTYTIME5C;
                        } else if (parser.getName().equals(TAG_DUTYTIME6C)) {
                            if (dto != null) tagType = TagType.DUTYTIME6C;
                        } else if (parser.getName().equals(TAG_DUTYTIME7C)) {
                            if (dto != null) tagType = TagType.DUTYTIME7C;
                        } else if (parser.getName().equals(TAG_DUTYTIME8C)) {
                            if (dto != null) tagType = TagType.DUTYTIME8C;
                        } else if (parser.getName().equals(TAG_DUTYTIME1S)) {
                            if (dto != null) tagType = TagType.DUTYTIME1S;
                        } else if (parser.getName().equals(TAG_DUTYTIME2S)) {
                            if (dto != null) tagType = TagType.DUTYTIME2S;
                        } else if (parser.getName().equals(TAG_DUTYTIME3S)) {
                            if (dto != null) tagType = TagType.DUTYTIME3S;
                        } else if (parser.getName().equals(TAG_DUTYTIME4S)) {
                            if (dto != null) tagType = TagType.DUTYTIME4S;
                        } else if (parser.getName().equals(TAG_DUTYTIME5S)) {
                            if (dto != null) tagType = TagType.DUTYTIME5S;
                        } else if (parser.getName().equals(TAG_DUTYTIME6S)) {
                            if (dto != null) tagType = TagType.DUTYTIME6S;
                        } else if (parser.getName().equals(TAG_DUTYTIME7S)) {
                            if (dto != null) tagType = TagType.DUTYTIME7S;
                        } else if (parser.getName().equals(TAG_DUTYTIME8S)) {
                            if (dto != null) tagType = TagType.DUTYTIME8S;
//                        } else if (parser.getName().equals(TAG_HPID)) {
//                            if (dto != null) tagType = TagType.HPID;
                        } else if (parser.getName().equals(TAG_DUTYMAPIMG)) {
                            if (dto != null) tagType = TagType.DUTYMAPIMG;
                        } else if (parser.getName().equals(TAG_WGS84LAT)) {
                            if (dto != null) tagType = TagType.WGS84LAT;
                        } else if (parser.getName().equals(TAG_WGS84LON)) {
                            if (dto != null) tagType = TagType.WGS84LON;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(TAG_ITEM)) {
                            resultList.add(dto);
                            dto = null;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {
                            case DUTYADDR:
                                dto.setDutyAddr(parser.getText());
                                break;
                            case DUTYDIVNAM:
                                dto.setDutyDivNam(parser.getText());
                                break;
                            case DUTYETC:
                                dto.setDutyEtc(parser.getText());
                                break;
                            case DUTYINF:
                                dto.setDutyInf(parser.getText());
                                break;
                            case DUTYNAME:
                                dto.setDutyName(parser.getText());
                                break;
                            case DUTYTEL1:
                                dto.setDutyTel1(parser.getText());
                                break;
                            case DUTYTEL3:
                                dto.setDutyTel3(parser.getText());
                                break;
                            case DUTYTIME1C:
                                dto.setDutyTime1c(parser.getText());
                                break;
                            case DUTYTIME2C:
                                dto.setDutyTime2c(parser.getText());
                                break;
                            case DUTYTIME3C:
                                dto.setDutyTime3c(parser.getText());
                                break;
                            case DUTYTIME4C:
                                dto.setDutyTime4c(parser.getText());
                                break;
                            case DUTYTIME5C:
                                dto.setDutyTime5c(parser.getText());
                                break;
                            case DUTYTIME6C:
                                dto.setDutyTime6c(parser.getText());
                                break;
                            case DUTYTIME7C:
                                dto.setDutyTime7c(parser.getText());
                                break;
                            case DUTYTIME8C:
                                dto.setDutyTime8c(parser.getText());
                                break;
                            case DUTYTIME1S:
                                dto.setDutyTime1s(parser.getText());
                                break;
                            case DUTYTIME2S:
                                dto.setDutyTime2s(parser.getText());
                                break;
                            case DUTYTIME3S:
                                dto.setDutyTime3s(parser.getText());
                                break;
                            case DUTYTIME4S:
                                dto.setDutyTime4s(parser.getText());
                                break;
                            case DUTYTIME5S:
                                dto.setDutyTime5s(parser.getText());
                                break;
                            case DUTYTIME6S:
                                dto.setDutyTime6s(parser.getText());
                                break;
                            case DUTYTIME7S:
                                dto.setDutyTime7s(parser.getText());
                                break;
                            case DUTYTIME8S:
                                dto.setDutyTime8s(parser.getText());
                                break;
//                            case HPID:
//                                dto.setHpid(parser.getText());
//                                break;
                            case DUTYMAPIMG:
                                dto.setDutyMapimg(parser.getText());
                                break;
                            case WGS84LAT:
                                dto.setWgs84Lat(parser.getText());
                                break;
                            case WGS84LON:
                                dto.setWgs84Lon(parser.getText());
                                break;
                        }
                        tagType = PortalHospitalParser.TagType.NONE;
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
