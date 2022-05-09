package com.jung.bungu.macha;

import java.util.List;
import java.util.Scanner;

import javax.rmi.ssl.SslRMIServerSocketFactory;

import com.jung.bungu.service.BunguService;
import com.jung.bungu.serviceImpl.BunguServiceImpl;
import com.jung.bungu.userVO.UserInfo;

import lombok.Data;

@Data
public class PojangMacha extends Thread {
	UserInfo vo = new UserInfo();
	Scanner scn = new Scanner(System.in);
	bungubbang make = new bungubbang();

	int havecleaner = 0;
	int havetle = 0;

	BunguService service = new BunguServiceImpl();
	String lId;

	public void run() {
		login();
	}

	public int login() { // 로그인창
		while (true) {

			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("   1. 로그인  2. 회원가입  3. 랭킹  4. 종료  ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = 0;
			System.out.print("선택 => ");
			menuNo = Integer.parseInt(scn.next());

			if (menuNo == 1) { // 로그인 ID,PASSWD를 입력 받아서 Login메서드에 넣어주고 디비에 저장된 ID PASSWD와 일치하면 리턴값 1을 받아
				// game();메서드가 실행된다.
				System.out.println("━━━━━━━━━━━━━로그인━━━━━━━━━━━━━━");
				System.out.print("   ID를 입력해주세요 => ");
				lId = scn.next();
				System.out.print("   Passwd를 입력해주세요 => ");
				String lPasswd = scn.next();

				int result = service.Login(lId, lPasswd);

				if (result == 1) {

					game(lId);

				} else {
					continue;
				}

				break;
			} else if (menuNo == 2) {
				int result = service.insertuser(vo);
				if (result == 1) {
					System.out.println("회원가입이 완료되었습니다.");
				} else {

					continue;
				}

			} else if (menuNo == 3) {
				rank();
				continue;

			} else if (menuNo == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
		return 0;

	}

	private int game(String lId) { // 로그인 할때 입력 받은 ID값을 계속 사용 (로그인 상태)
		havecleaner = service.haveCleaner(lId);
		havetle = service.haveCase(lId);
		boolean isTure = true;
		while (isTure) {
			int monuy = 0;
			System.out.println("┏━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("  1. 게임 시작       ");
			System.out.println("  2. 내 정보         ");
			System.out.println("  3. 상점            ");
			System.out.println("  4. 로그아웃        ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = 0;
			System.out.print("선택 => ");
			menuNo = Integer.parseInt(scn.next());
			System.out.println();
			if (menuNo == 1) {
//				Runnable bba = new bungubbang();
//				Thread bbangThread = new Thread(bba);

				make.setting();
//				bbangThread.start();
//				cl.bb = make;
				monuy = make.gaustRun(lId);
				service.savingMonuy(monuy, lId);

				return monuy;
			} else if (menuNo == 2) {
				menuNo = userInfo(lId);

			} else if (menuNo == 3) {
				productUpgade();
				return 0;
			} else if (menuNo == 4) {

				System.out.println("로그아웃 합니다.");

				isTure = false;
				return login();
			} else if (menuNo == 5) {

				isTure = false;
				return login();
			}
		}
		return 0;

	}

	private int userInfo(String lId) {
		boolean isTure = true;
		while (isTure) {
			make.cleannn(lId);
			int monuy = 0;
			System.out.println("┏━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("  1. 내 정보 조회    ");
			System.out.println("  2. 비밀번호 변경   ");
			System.out.println("  3. 회원 탈퇴       ");
			System.out.println("  4. 장비 보기       ");
			System.out.println("  5. 돌아가기        ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = 0;
			System.out.print("선택 => ");
			menuNo = Integer.parseInt(scn.next());
			System.out.println();
			
			if (menuNo == 1) {
				vo = service.selectuser(lId);
				System.out.println(
						"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.print("   ");  vo.toString();
				System.out.println();
				System.out.println("    장비 : " + make.cleanerpro + ", " + make.tlepro);
				System.out.println(
						"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				continue; // 내 정보를 조회 한 후 다시 메뉴로 돌아가기 위해 컨티뉴
			} else if (menuNo == 2) {

				int result = service.updateuser(lId);
				if (result == 1) {
					System.out.println("비밀번호가 변경되셨습니다.");
				} else {
					System.out.println("변경에 실패하셨습니다");
				}
				continue; // 비밀 번호는 변경한 후 다시 메뉴로 돌아가기 위해 컨티뉴, 원래 비번 바꾸고 다시 로그인은 국룰이지만

			} else if (menuNo == 3) {

				service.deleteuser(lId);
				System.out.println("감사했습니다");
				return login(); // 탈퇴를 하고 다시 메뉴로 돌아가는것을 방지하기 위해

			} else if (menuNo == 4) {

				System.out.println(make.cleanerpro + " , 배율 : " + make.clnumber);
				System.out.println(make.tlepro + " , 갯수 : " + make.tlnumber);

			} else if (menuNo == 5) {
				isTure = false;
				break;
			}
		}
		return 0;
	}

	public void gameRuner() {

	}

	public void gameOver() {
		game(lId);
	}

	private void rank() {
		List li = service.userRank();
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		for (int i = 0; i < li.size(); i++) {
			System.out.print("  " + (i + 1) + "위 -");
			li.get(i).toString();
			System.out.println();
		}
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private int productUpgade() {
		boolean isTure = true;
		while (isTure) {
			int monuy = 0;
			System.out.println("┏━━업그레이드 상점━━┓");
			System.out.println("  1. 붕어빵틀     ");
			System.out.println("  2. 청소도구    ");
			System.out.println("  3. 돌아가기        ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = 0;
			System.out.print("선택 => ");
			menuNo = Integer.parseInt(scn.next());
			System.out.println();
			if (menuNo == 1) {
				tleUpgade();
			} else if (menuNo == 2) {
				cleanerUpgade();
			} else if (menuNo == 3) {
				return game(lId);
			} else {

			}

		}
		return 0;
	}

	private void cleanerUpgade() { // 청소도구 업그레이드
		make.cleannn(lId);
		int upgade1 = 10000; // 장비들의 가격
		int upgade2 = 20000;
		int upgade3 = 30000;

		if (havecleaner == 0) {
			System.out.println("※ 업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("가격 : 10,000원");
			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade1) {
						service.useMonuy(upgade1, lId);
						service.upCleaner(upgade1, lId);
						havecleaner = upgade1;
						make.productx(upgade1);
						System.out.println("업그레이드 완료되었습니다.");
					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}
		} else if (havecleaner == upgade1) {
			System.out.println("※ 업그레이드 하시겠습니까? (Y/N) ");
			System.out.println("가격 : 20,000원");

			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade2) {
						service.useMonuy(upgade2, lId);
						service.upCleaner(upgade2, lId);
						havecleaner = upgade2;
						make.productx(upgade2);
						System.out.println("업그레이드 완료되었습니다.");
					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}

		} else if (havecleaner == upgade2) {
			System.out.println("※ 업그레이드 하시겠습니까? (Y/N) ");
			System.out.println("가격 : 30,000원");
			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade3) {
						service.useMonuy(upgade3, lId);
						service.upCleaner(upgade3, lId);
						havecleaner = upgade3;
						make.productx(upgade3);
						System.out.println("업그레이드 완료되었습니다.");
					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}

		} else if (havecleaner == upgade3) {
			System.out.println("업그레이드할 장비가 없습니다.");
		}
	}

	private void tleUpgade() { // 빵틀 업그레이드
		make.cleannn(lId);
		int upgade1 = 10000; // 장비들의 가격
		int upgade2 = 20000;
		int upgade3 = 30000;

		if (havetle == 0) {
			System.out.println("※ 업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("가격 : 10,000원");
			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade1) {
						service.useMonuy(upgade1, lId);
						service.upTle(upgade1, lId);
						havetle = upgade1;
						make.productx(upgade1);
						System.out.println("업그레이드 완료되었습니다.");

					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}
		} else if (havetle == upgade1) {
			System.out.println("※ 업그레이드 하시겠습니까? (Y/N) ");
			System.out.println("가격 : 20,000원");

			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade2) {
						service.useMonuy(upgade2, lId);
						service.upTle(upgade2, lId);
						havetle = upgade2;
						make.productx(upgade2);
						System.out.println("업그레이드 완료되었습니다.");
					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}

		} else if (havetle == upgade2) {
			System.out.println("※ 업그레이드 하시겠습니까? (Y/N) ");
			System.out.println("가격 : 30,000원");
			String check = scn.next();

			if (check.equalsIgnoreCase("y")) {
				try {
					if (service.userMonuy(lId) >= upgade3) {
						service.useMonuy(upgade3, lId);
						service.upTle(upgade3, lId);
						havetle = upgade3;
						make.productx(upgade3);
						System.out.println("업그레이드 완료되었습니다.");
					} else {
						System.out.println("돈이 부족합니다.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (check.equalsIgnoreCase("n")) {
				System.out.println("취소 하셨습니다.");
			}

		} else if (havetle == upgade3) {
			System.out.println("업그레이드할 장비가 없습니다.");
		}
	}
}
