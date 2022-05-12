package com.jung.bungu.service;

import java.util.List;

import com.jung.bungu.userVO.UserInfo;

public interface BunguService {

	List<UserInfo> userSelect(); // 전체 회원 목록 리스트

	List<UserInfo> userRank(); // 유저 랭킹

	UserInfo selectuser(String lId); // 한명 리스트

	int insertuser(UserInfo user); // 회원 가입

	int updateuser(String upPasswd,String lId); // 회원 수정

	int deleteuser(String lId); // 회원 삭제

	int Login(String userId, String userPasswd);

	int savingMonuy(int Monuy, String id); // 돈저장

	int userMonuy(String lId); // 사용자가 가진 돈반환

	int useMonuy(int Monuy, String lId); // 사용자가 장비를 삿을때 돈 차감
	int upCleaner(int Product, String lId); // 구매한 청소도구 값 저장
	int haveCleaner(String lId); // 가지고있는 청소도구 값 반환
	int upTle(int Product, String lId) ;
	int haveCase(String lId);
}
