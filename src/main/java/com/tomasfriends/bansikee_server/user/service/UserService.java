package com.tomasfriends.bansikee_server.user.service;

import com.tomasfriends.bansikee_server.user.domain.FavoritesUser;
import com.tomasfriends.bansikee_server.user.domain.UserMyPage;
import com.tomasfriends.bansikee_server.user.dto.ReqUserDto;
import com.tomasfriends.bansikee_server.user.dto.ResPlantLikeListDto;
import com.tomasfriends.bansikee_server.user.repository.FavoritesUserRepository;
import com.tomasfriends.bansikee_server.user.repository.UserMyPageRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class UserService {

    private FavoritesUserRepository favoritesUserRepository;
    private UserMyPageRepository userMyPageRepository;
    public UserService(FavoritesUserRepository favoritesUserRepository, UserMyPageRepository userMyPageRepository){

        this.favoritesUserRepository = favoritesUserRepository;
        this.userMyPageRepository = userMyPageRepository;
    }

    public List<ResPlantLikeListDto> getPlantLikeList(Integer userIdx){

        List<FavoritesUser> userLikeResult = favoritesUserRepository.findByUserIdx(userIdx);

        List<ResPlantLikeListDto> userLikePlants = userLikeResult
                .stream()
                .map(ResPlantLikeListDto::of)
                .collect(Collectors.toList());

        for(int i = 0; i < userLikePlants.size(); i++) {
            userLikePlants.get(i).setLike(true);
            userLikePlants.get(i).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+userLikePlants.get(i).getPlantImgUrl().split("\\|")[0]);

        }

        return userLikePlants;
    }

    public void patchUserInfo(Integer userIdx, ReqUserDto reqUserDto){
        UserMyPage userMyPage = userMyPageRepository.findById(userIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userIdx));

        userMyPage.update(reqUserDto.getName(),reqUserDto.getUserImageUrl());
    }
}
