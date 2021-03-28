package com.tomasfriends.bansikee_server.util;

public class PlantImgUtil {

    private static final String NONGSARO_URL = "http://www.nongsaro.go.kr/cms_contents/301/";

    private PlantImgUtil() {
    }

    public static String getPlantProfileImg(String imgUrl) {
        String firstImg = getFirstImg(imgUrl);
        return NONGSARO_URL + firstImg;
    }

    private static String getFirstImg(String imgUrl) {
        int index = imgUrl.indexOf("|");
        return imgUrl.substring(0, index);
    }
}
