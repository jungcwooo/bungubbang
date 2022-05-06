package com.jung.bungu.macha;

import java.util.Scanner;

import com.jung.bungu.service.BunguService;
import com.jung.bungu.serviceImpl.BunguServiceImpl;

public class bungubbang extends Thread {
//	Thread gsThread =new Thread(new bungubbang());

	Scanner scn = new Scanner(System.in);
	int redbeenprice = 600; // 팥 가격
	int custardprice = 700; // 슈크림 가격
	int redbeen, custard = 0; // 구워 놓은 붕어빵의 갯수
	int buyRedbeen, buyCustard = 0; // 손님이 구입할 붕어빵 갯수 선언

	private int bMonuy; // 게임 시작 후 돈
	private int bLike; // 고객 만족도
	private int bClean; // 위생지수
	private int bCount; // 시간 카운트
	private int bTime; // 현재 진행 시간
	private int menu; // 입력 받을 메뉴

	BunguService service = new BunguServiceImpl();
	String[] gsAge = { "젋은 ", "늙은 ", "어린 ", "나이를 알 수 없는" };
	String[] gsGender = { "남자", "여자", "어떤 사람", "사람" };

	int i = 0; // 몇번째로 방문한 손님인지
	int sec = 0;

	int sumTime = 0; // 게임시간 저장

	public int gaustRun(String lId) {
		int sum = 0;
		int monuy = 0;
		while (i < 30) {// 하루 30명의 손님만 받음
			int timer = (int) (3000 * (Math.random()) + 3000); // 랜덤한시간으로 손님이 오게하기위해 10~20초(차후 조정 너무 빠름)
			try {
				// 5초마다 메시지 표시
				showNumber();
				System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
				System.out.println("야생의 " + guest() + "이(가) 등장했다");
				System.out.println("==========================================");
				guestorder();
				System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");

				join();// 한명의 손님의 주문이 끝나지 않으면 다음손님은안온다

				makingmenu(); // 붕어빵 판매 & 굽기 & 청소 등
				sleep(timer);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("게임이 종료 되었습니다.");
		sum += monuy;

		return monuy;

	}

	private void showNumber() {
		System.out.println((++i) + "번째 손님");

	}

	private void showSec() {
		System.out.println((++sec) + "초 경과");

	}

	private String guest() { // 손님의 남여노소를 랜덤하게 짜는 메서드 (랜덤한 손님이 오게끔보이기 위해)

		return (gsAge[(int) (4 * (Math.random()))] + gsGender[(int) (4 * (Math.random()))]);
	}

	private void guestorder() { // 한 손님이 최대로 주문할수 있는 붕어빵의 갯수 8개 (팥,슈크림 4개씩)
		this.buyRedbeen = (int) (9 * (Math.random()));
		this.buyCustard = (int) (9 * (Math.random()));

		if ((buyRedbeen + buyCustard) > 0 && (buyRedbeen + buyCustard) <= 8) {

			if (buyRedbeen == 0 && custard >= 1) {
				System.out.println("아저씨 슈붕 " + buyCustard + "개 주세요");

			} else if (custard == 0 && buyRedbeen >= 1) {
				System.out.println("아저씨 팥붕 " + buyRedbeen + "개 주세요");

			} else
				System.out.println("아저씨 팥붕 " + buyRedbeen + "개랑 슈붕 " + buyCustard + "개 주세요");

		} else {
			System.out.println("메뉴를 고르고있습니다.");
			this.buyRedbeen = (int) (9 * (Math.random()));
			this.buyCustard = (int) (9 * (Math.random()));
			if ((buyRedbeen + buyCustard) > 0 && (buyRedbeen + buyCustard) <= 8) {
				if (buyRedbeen == 0 && buyCustard >= 1) {
					System.out.println("아저씨 슈붕 " + buyCustard + "개 주세요");

				} else if (buyCustard == 0 && buyRedbeen >= 1) {
					System.out.println("아저씨 팥붕 " + buyRedbeen + "개 주세요");

				} else if (buyCustard == 0 && buyRedbeen == 0) {
					System.out.println("손님은 입맛을 다시며 포장마차를 떠났다.");
				} else {
					System.out.println("아저씨 팥붕 " + buyRedbeen + "개랑 슈붕 " + buyCustard + "개 주세요");
				}

			} else {
				System.out.println("손님은 맛있어 보이지 않는다며 포장마차를 떠났다.");
			}

		}
	}

	public void setting() { // 게임시작시 초기화 해줘야할 값
		bMonuy = 0;
		bLike = 0;
		bClean = 20;
		bCount = 0;
		bTime = 0;

	}

	public void makingmenu() {
		int sum = 0;
		while (true) {
			System.out.println("==================");
			System.out.println("= 1. 붕어빵 굽기 =");
			System.out.println("= 2. 붕어빵 판매 =");
			System.out.println("= 3. 붕어빵 갯수 =");
			System.out.println("= 4. 청소하기    =");
			System.out.println("= 5. 손님 보내기 =");
			System.out.println("= 6. 나가기      =");
			System.out.println("==================");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());

			if (menuNo == 1) {
				making();

			} else if (menuNo == 2) {
				buy();

			} else if (menuNo == 3) {
				number();

			} else if (menuNo == 4) {
				clean();

			} else if (menuNo == 5) {
				break;
			} else if (menuNo == 6) { // 게임을 종료하고 첫번째 메뉴로 가고 싶은데 그게 안됨 그냥 종료되어버림
				i = 99;
				break;
			} else {
				break;
			}

		}
	}

	private void making() { // 붕어빵굽기
		while (true) {
			System.out.println("========================");
			System.out.println("= 1. 팥붕어빵 굽기     =");
			System.out.println("= 2. 슈크림붕어빵 굽기 =");
			System.out.println("= 3. 붕어빵 갯수       =");
			System.out.println("= 4. 나가기            =");
			System.out.println("========================");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());
			if (menuNo == 1) {
				System.out.println("팥붕어빵 몇개를 구울까요? (최대 5개)");
				int x = Integer.parseInt(scn.next());
				if (x > 0 && x <= 5) {
					if ((x + redbeen) >= 0 && (x + redbeen) < 10) {
						redbeen += x;
					} else {
						System.out.println("너무 많이 구웠습니다 자리가 부족합니다");
						System.out.println("현재 팥붕어빵 갯수 : " + redbeen + "개");
					}
				}
			} else if (menuNo == 2) {
				System.out.println("슈크림붕어빵 몇개를 구울까요?(최대 5개)");
				int x = Integer.parseInt(scn.next());
				if (x > 0 && x <= 5) {
					if ((x + custard) >= 0 && (x + custard) <= 10) {
						custard += x;
					} else {
						System.out.println("너무 많이 구웠습니다 자리가 부족합니다");
						System.out.println("현재 슈크림붕어빵 갯수 : " + custard + "개");
					}
				}

			} else if (menuNo == 3) {
				number();
			} else if (menuNo == 4) {
				break;
			} else {

				break;
			}
		}
	}

	private void buy() { // 붕어빵 팔기
		System.out.println("팜");
	}

	private void number() { // 붕어빵 갯수

		System.out.println("팥붕어빵 갯수");
		for (int i = 0; i < redbeen; i++) {
			System.out.print("●");
		}
		for (int j = 0; j < 10 - redbeen; j++) {
			System.out.print("○");
		}
		System.out.print(" : " + redbeen + "개");
		System.out.println();

		System.out.println("슈크림붕어빵 갯수");
		for (int i = 0; i < custard; i++) {
			System.out.print("●");
		}
		for (int j = 0; j < 10 - custard; j++) {
			System.out.print("○");
		}
		System.out.print(" : " + custard + "개");
		System.out.println();

	}

	private void clean() { // 청결도
//		macha.setBClean(macha.getBClean() - 15);
		System.out.println("청소");
	}

	public void cleaner() {

	}
}
