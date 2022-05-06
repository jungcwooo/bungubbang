package com.jung.bungu.service;

import java.util.List;

import com.jung.bungu.userVO.UserInfo;

public interface BunguService {

	List<UserInfo> userSelect(); // 전체 회원 목록 리스트

	List<UserInfo> userRank(); // 유저 랭킹

	UserInfo selectuser(String lId); // 한명 리스트

	int insertuser(UserInfo user); // 회원 가입

	int updateuser(String lId); // 회원 수정

	int deleteuser(String lId); // 회원 삭제

	int Login(String userId, String userPasswd);

	int savingMonuy(int Monuy, String id);



}
