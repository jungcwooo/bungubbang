package com.jung.bungu.macha;

import java.util.Scanner;

import com.jung.bungu.service.BunguService;
import com.jung.bungu.serviceImpl.BunguServiceImpl;

import lombok.Data;

public class bungubbang extends Thread {
//	Thread gsThread =new Thread(new bungubbang());
//	
	Clean cl = new Clean();
	Time ti = new Time();
	PojangMacha po;
	Scanner scn = new Scanner(System.in);
	int redbeenprice = 600; // 팥 가격
	int custardprice = 700; // 슈크림 가격
	int redbeen, custard = 0; // 구워 놓은 붕어빵의 갯수, 나중에 판매하면 - 해야함
	int buyRedbeen, buyCustard = 0; // 손님이 구입할 붕어빵 갯수 선언

	private int bMonuy = 0; // 게임 시작 후 돈
	private int bLike = 0; // 고객 만족도
	int bClean = 0; // 위생지수
	int bTime; // 현재 진행 시간
	private int bCount; // 시간 카운트
	private int menu; // 입력 받을 메뉴

	public int getbTime() {
		return bTime;
	}

	public void setbTime(int bTime) {
		this.bTime = bTime;
	}

	public int getbClean() {
		return bClean;
	}

	public void setbClean(int bClean) {
		this.bClean = bClean;
	}

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
			int timer = (int) (3000 * (Math.random())); // 랜덤한시간으로 손님이 오게하기위해 0~3초
			// (멀티 쓰레드로 여러가지 해보았으나 실패) 다음에는 멀티쓰레드를 고려해서 클래스와 메서드를 설계해야 할것
			try {
//				Runnable cl = new Clean();
//				Thread cle = new Thread(cl);

				showNumber();
				System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
				System.out.println("야생의 " + guest() + "이(가) 등장했다");
				System.out.println("==========================================");
				guestorder();
				System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");

//				cle.start();
//				ti.start();
				join();// 한명의 손님의 주문이 끝나지 않으면 다음손님은안온다
				makingmenu(); // 붕어빵 판매 & 굽기 & 청소 등
				sleep(timer);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("게임이 종료 되었습니다.");
		sum += monuy;

		return bMonuy;

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

			if (buyRedbeen == 0 && buyCustard >= 1) {
				System.out.println("아저씨 슈붕 " + buyCustard + "개 주세요");

			} else if (buyCustard == 0 && buyRedbeen >= 1) {
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
		int y = 0;
		while (y == 0) {
			System.out.println("□■□■□■□■□■□■□■□■□■□■");
			System.out.println("■ 1. 붕어빵 굽기   □");
			System.out.println("□ 2. 붕어빵 판매   ■");
			System.out.println("■ 3. 붕어빵 갯수   □");
			System.out.println("□ 4. 청소하기      ■");
			System.out.println("■ 5. 현재 상황     □"); // 여기에 모인 돈 , 몇번쨰 손님인지, 청결도, 만족도 체크
			System.out.println("□ 6. 다음 손님     ■");
			System.out.println("■ 7. 게임 끝내기   □");
			System.out.println("□■□■□■□■□■□■□■□■□■□■");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());

			if (menuNo == 1) {
				making(); // 붕어빵을 구우면 붕어빵 갯수 변수가 그만큼 오르고 판매하면 그만큼 내려가야함

			} else if (menuNo == 2) {
				buy(); // 이제 구현해야하는데 이게 좀 어려워보임
				// 손님이 딱 오면 손님이 원하는 붕어빵 개수랑 이프문 써서 내가 파는 갯수가 맞으면 손님으 만족도가 상승
				// 만약 더 많거나 적으면 이프문써서 다른 패널티 부여
				// 그러면 이제 내가 구워놓은 붕어빵 변수에서 차감 시킴

			} else if (menuNo == 3) {
				number(); // 붕어빵이 몇개 남아있는지 확인하는 메서드

			} else if (menuNo == 4) {
				cleaner(); // 청결도를 올리는 거니까 자동으로 청결도를 내리게 하는 스레드 써야함

			} else if (menuNo == 5) {
				checknow();
				// 현재 상태 체크 메서드
			} else if (menuNo == 6) {
				// 손님이 보내진다 (나중에 진상 손님이나 무리한 요구를 하는 손님들을 만들어 그냥 보내게 만들자)
				break;
			} else if (menuNo == 7) { // 게임을 종료하고 첫번째 메뉴로 가고 싶은데 그게 안됨 그냥 종료되어버림
				System.out.println("정말로 진행중인 게임을 나가시겠습니까? (Y/N)");
				String check = scn.next();
				if (check.equalsIgnoreCase("y")) {
					i = 99; // 손님의 수를 강제로 30 이상으로 만들어서 게임이 종료되게 함.
					y = 99;
					break;
				} else if(check.equalsIgnoreCase("n")){
					System.out.println("취소하셨습니다");
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}

			} else {
				System.out.println("잘못 입력하셨습니다");
				System.out.println("1 ~ 7번의 메뉴를 입력해주세요.");
			}

		}
	}

	private void making() { // 붕어빵굽기
		int z = 0;
		while (z == 0) {
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");
			System.out.println("■ 1. 팥붕어빵 굽기     □");
			System.out.println("□ 2. 슈크림붕어빵 굽기 ■");
			System.out.println("■ 3. 붕어빵 갯수       □");
			System.out.println("□ 4. 메뉴로 돌아가기   ■");
			System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());
			if (menuNo == 1) {
				System.out.println("팥붕어빵 몇개를 구울까요? (최대 5개)");
				int x = Integer.parseInt(scn.next());
				if (x > 0 && x <= 5) {
					if ((x + redbeen) >= 0 && (x + redbeen) <= 10) {
						redbeen += x;
						bClean -= x;
						System.out.println("팥붕어빵 " + redbeen + "개를 구웠습니다");
						System.out.println("청결도 -" + x);
						if (bClean < -15) {
							System.out.println("위생 점검 왔습니다.");
							System.out.println("위생이 이게 뭡니까? 영업중지입니다 철거하세요");
							i = 99;
							z = 99;
							break;

						}
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
						bClean -= x;
						System.out.println("슈크림붕어빵 " + custard + "개를 구웠습니다");
						System.out.println("청결도 -" + x);
						if (bClean < -15) {
							System.out.println("위생 점검 왔습니다.");
							System.out.println("위생이 이게 뭡니까? 영업중지입니다 철거하세요");
							i = 99;
							z = 99;
							break;
						}
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

		System.out.printf("손님이 팥 %s개와 슈%s개를 원합니다.\n", buyRedbeen, buyCustard);
		System.out.print("팥붕어빵 몇개를 봉투에 담으시겠습니까? => ");
		int buy1 = Integer.parseInt(scn.next());
		System.out.print("슈크림붕어빵 몇개를 봉투에 담으시겠습니까? => ");
		int buy2 = Integer.parseInt(scn.next());

		if (buy1 <= redbeen && buy2 <= custard) {

			if (buy1 == buyRedbeen && buy2 == buyCustard) {
				System.out.println("오 정말 맛있어보이는 붕어빵이네요! 감사합니다");
				redbeen -= buy1;
				custard -= buy2;
				bMonuy = (redbeenprice * buy1) + (custardprice * buy2);
				bLike += 3; // 만족도 3증가

			} else if (buy1 == buyRedbeen && buy2 < buyCustard) {
				System.out.printf("슈크림의 갯수가 %d개 부족한거 같은데...\n", buyCustard - buy2);
				redbeen -= buy1;
				custard -= buy2;
				bMonuy += (redbeenprice * buy1) + (custardprice * buy2);
				bLike += 2; // 만족도 3증가
			} else if (buy1 == buyRedbeen && buy2 > buyCustard) {
				System.out.printf("오 슈붕 서비스인가요? 감사합니다! ㅋㅋ\n");
				redbeen -= buy1;
				custard -= buy2;
				bMonuy += (redbeenprice * buy1) + (custardprice * buy2);
				bLike += 2; // 만족도 3증가
			} else if (buy1 < buyRedbeen && buy2 == buyCustard) {
				System.out.printf("팥의 갯수가 %d개 부족한거 같은데...\n", buyRedbeen - buy1);
				redbeen -= buy1;
				custard -= buy2;
				bMonuy += (redbeenprice * buy1) + (custardprice * buy2);
				bLike -= 1; // 만족도 1감소
			} else if (buy1 > buyRedbeen && buy2 == buyCustard) {
				System.out.printf("오 팥붕 서비스인가요? 감사합니다! ㅋㅋ\n");
				redbeen -= buy1;
				custard -= buy2;
				bMonuy += (redbeenprice * buy1) + (custardprice * buy2);
				bLike += 2; // 만족도 3증가
			}
		} else {
			System.out.println("구워진 붕어빵의 수가 부족합니다");
		}

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

	private void clean() { // 청결도 자동으로 내려주는 메서드
		if (bClean < -15) {
			System.out.println("위생 점검 왔습니다.");
			System.out.println("위생이 이게 뭡니까? 영업중지입니다 철거하세요");
		}

	}

	private void cleaner() {// 청소 메서드
		// BClean= 15;
		bClean += 15;
		System.out.println("뽀득뽀득 청소 완료 청결도 + 15");
	}

	private void checknow() {
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■");
		System.out.printf("■ 가진 돈 :      %7d □\n", bMonuy);
		System.out.printf("□ 청결도  :      %7d ■\n", bClean);
		System.out.printf("■ 만족도  :      %7d □\n", bLike);
		System.out.printf("□ 앞으로 %2s명의 손님남음 ■\n", 30 - i);
		System.out.println("□■□■□■□■□■□■□■□■□■□■□■□■□■");
	}

}
