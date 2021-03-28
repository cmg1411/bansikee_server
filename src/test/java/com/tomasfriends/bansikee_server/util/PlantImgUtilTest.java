package com.tomasfriends.bansikee_server.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlantImgUtilTest {

    @DisplayName("디비의 식물 이미지 유알엘 정보에 농사로 유알엘을 붙여서 반환한다. 여러개의 이미지 중 하나만 반환한다.")
    @Test
    void imgTest() {
        // given
        String dbUrl = "12957_MF_ATTACH_01.jpg|12957_MF_ATTACH_02.jpg|12957_MF_ATTACH_03.jpg|12957_MF_ATTACH_04.jpg|12957_MF_ATTACH_05.jpg|12957_MF_REPR_ATTACH_01.jpg";

        // when
        String result = PlantImgUtil.getPlantProfileImg(dbUrl);

        // then
        Assertions.assertThat(result).isEqualTo("http://www.nongsaro.go.kr/cms_contents/301/12957_MF_ATTACH_01.jpg");
    }
}