package com.jung.bungu.service;

import java.util.List;

import com.jung.bungu.userVO.UserInfo;

public interface BunguService {
	
	List<UserInfo> userSelect(); // 전체 회원 목록 리스트
	List<UserInfo> userRank(); // 유저 랭킹
	UserInfo selectuser(UserInfo user); // 한명 리스트
	int insertuser(UserInfo user);	// 회원 가입
	int updateuser(UserInfo user);	// 회원 수정
	int deleteuser(UserInfo user);	// 회원 삭제
	
	
	
}
