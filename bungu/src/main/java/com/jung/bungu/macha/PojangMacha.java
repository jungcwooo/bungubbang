package com.jung.bungu.macha;

import java.util.Scanner;

public class PojangMacha {
	Scanner scn = new Scanner(System.in);
	private int bMonuy; // 게임 시작 후 돈
	private int bLike;	// 고객 만족도
	private int bClean; // 위생지수
	private int bCount; // 시간 카운트
	private int bTime;  // 현재 진행 시간
	private int menu;  // 입력 받을 메뉴
	
	public void setting() {		// 게임시작시 초기화 해줘야할 값
		bMonuy = 0;
		bLike = 0;
		bClean = 20;
		bCount = 0;
		bTime = 0;
		
		
	}
	
	public void login() {
		int menuNo = 0;

		System.out.println("메뉴를 입력하세요");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");

		menuNo = Integer.parseInt(scn.next());
		switch (menuNo) {
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			System.out.println("프로그램을 종료합니다");
			break;

		default:
			System.out.println("잘못 입력 하셨습니다.");
			
		}

	}
}
