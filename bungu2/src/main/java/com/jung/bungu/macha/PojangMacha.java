package com.jung.bungu.macha;

import java.util.List;
import java.util.Scanner;

import com.jung.bungu.service.BunguService;
import com.jung.bungu.serviceImpl.BunguServiceImpl;
import com.jung.bungu.userVO.UserInfo;

import lombok.Data;

@Data
public class PojangMacha extends Thread {
	UserInfo vo = new UserInfo();
	Scanner scn = new Scanner(System.in);
	bungubbang make = new bungubbang();
	UserInfo vo1;
	int havecleaner = 0;
	int havetle = 0;

	BunguService service = new BunguServiceImpl();
	String lId;

	public void run() {
//		mainba();
		mainba2(); // 사용
//		maintip();
		maintip2(); // 사용
		login();
	}

	public int login() {
		clearScreen();
		while (true) {

			mainmenulogin();

			int menuNo = menuselect();

			if (menuNo == 1) {
				loginnow();
				break;

			} else if (menuNo == 2) {
				joinuser();

			} else if (menuNo == 3) {
				rank();
				continue;

			} else if (menuNo == 4) {
				GG();
				break;
			} else {
				clearScreen();
				outOfRange();
			}
		}
		return 0;

	}

	private void loginnow() {// 로그인 하는 메서드
		System.out.println("━━━━━━━━━━━━━로그인━━━━━━━━━━━━━━");
		System.out.print("   ID를 입력해주세요 => ");
		lId = scn.nextLine();
		System.out.print("   Passwd를 입력해주세요 => ");
		String lPasswd = scn.nextLine();

		int result = service.Login(lId, lPasswd);

		if (result == 1) {
			clearScreen();
			game(lId);

		} else if (result == 0) {
			clearScreen();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("            비밀번호가 다릅니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		} else if (result == -1) {
			clearScreen();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("          아이디를 찾을 수 없습니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		} else {

		}
	}

	private void mainmenulogin() { // 로그인창 목록 메서드
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("     붕어빵 타이쿤에 오신 것을 환영합니다     ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("   1. 로그인  2. 회원가입  3. 랭킹  4. 종료  ");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		System.out.print("선택 => ");
	}

	private int menuselect() { // 메뉴 선택 메서드
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(scn.nextLine());

		} catch (Exception e) {
			clearScreen();
			System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(" \t     숫자만 입력해주세요");
			System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		}
		return menuNo;
	}

	private void joinuser() { // 회원가입
		clearScreen();
		UserInfo user = joinuser1();
		int result = service.insertuser(user);
		if (result == 1) {
			System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(" \t  회원가입이 완료되었습니다.");
			System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		} else if (result == 0) {
			System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(" \t     중복된 ID입니다.");
			System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		}
	}

	private UserInfo joinuser1() { // 회원가입
		UserInfo user = new UserInfo();
		System.out.println("┏━━━━━━━━━━━━━━━━━회원가입━━━━━━━━━━━━━━━━━━━┓");
		System.out.print("          이름를 입력해주세요 => ");
		user.setName(scn.nextLine());
		System.out.print("          ID를 입력해주세요 => ");
		user.setId(scn.nextLine());
		System.out.print("          Passwd를 입력해주세요 => ");
		user.setPasswd(scn.nextLine());
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		return user;
	}

	private int game(String lId) { // 로그인 할때 입력 받은 ID값을 계속 사용 (로그인 상태)
		havecleaner = service.haveCleaner(lId);
		havetle = service.haveCase(lId);
		boolean isTure = true;
		vo1 = service.selectuser(lId);
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t " + vo1.getName() + "님 환영합니다.");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		while (isTure) {
			int monuy = 0;
			logingList();
			int menuNo = menuselect();
			if (menuNo == 1) {

				make.setting();
				monuy = make.gaustRun(lId);
				service.savingMonuy(monuy, lId);

				return monuy;
			} else if (menuNo == 2) {
				menuNo = userInfo(lId);

			} else if (menuNo == 3) {
				productUpgade();
				return 0;
			} else if (menuNo == 4) {
				clearScreen();
				System.out.println("로그아웃 합니다.");

				isTure = false;
				return login();
			} else if (menuNo == 5) {
				outOfRange();
			}
		}
		clearScreen();
		return 0;

	}

	private int userInfo(String lId) {
		boolean isTure = true;
		while (isTure) {
			make.cleannn(lId);
			int monuy = 0;
			vo1 = service.selectuser(lId);
			clearScreen();
			InformationList();

			int menuNo = menuselect();
			System.out.println();

			if (menuNo == 1) {
				myInfo();
				continue; // 내 정보를 조회 한 후 다시 메뉴로 돌아가기 위해 컨티뉴
			} else if (menuNo == 2) {
				passwdchange();
				continue; // 비밀 번호는 변경한 후 다시 메뉴로 돌아가기 위해 컨티뉴, 원래 비번 바꾸고 다시 로그인은 국룰이지만

			} else if (menuNo == 3) {

				service.deleteuser(lId);
				sleeep(3);
				return login(); // 탈퇴를 하고 다시 메뉴로 돌아가는것을 방지하기 위해

			} else if (menuNo == 4) {

				make.cleannn(lId);
				myProduct();

			} else if (menuNo == 5) {
				isTure = false;
				clearScreen();
				break;
			}
		}
		return 0;
	}

	private void myInfo() {
		vo = service.selectuser(lId);
		clearScreen();
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.print("   ");
		vo.toString();
		System.out.println();
		System.out.println("    장비 : " + make.cleanerpro + ", " + make.tlepro);
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		sleeep(3);
	}

	private void myProduct() {
		clearScreen();
		make.cleannn(lId);
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t " + vo1.getName() + "님의 장비");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("     " + make.cleanerpro + " , 배율 : " + make.clnumber);
		System.out.println("     " + make.tlepro + " , 갯수 : " + make.tlnumber);
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		sleeep(3);
	}

	private void logingList() {
		System.out.println("       ┏━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("          1. 게임 시작       ");
		System.out.println("          2. 내 정보         ");
		System.out.println("          3. 상점            ");
		System.out.println("          4. 로그아웃        ");
		System.out.println("       ┗━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("선택 => ");
	}

	private void InformationList() {
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("\t   " + vo1.getName() + "님의 정보창");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("       ┏━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("          1. 내 정보 조회    ");
		System.out.println("          2. 비밀번호 변경   ");
		System.out.println("          3. 회원 탈퇴       ");
		System.out.println("          4. 장비 보기       ");
		System.out.println("          5. 돌아가기        ");
		System.out.println("       ┗━━━━━━━━━━━━━━━━━━━┛");
		System.out.print("선택 => ");
	}

	private void passwdchange() {
		clearScreen();
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("      변경 하실 비밀번호를 입력하세요");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		String upPasswd = scn.nextLine();

		int result = service.updateuser(upPasswd, lId);
		if (result == 1) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("         비밀번호가 변경되셨습니다");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		} else {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("         변경에 실패하셨습니다");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		}
		sleeep(3);
	}

	private void deleteuser() {
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("   정말로 회원탈퇴를 진행하시겠습니까?(Y/N)");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		String check = scn.nextLine();

		if (check.equalsIgnoreCase("y")) {
			int result = service.deleteuser(lId);

			if (result == 1) {
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("         정상적으로 탈퇴되셨습니다.");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			} else if (result == 0) {
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("           잘못입력하셨습니다.");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
		} else if (check.equalsIgnoreCase("n")) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("               취소 하셨습니다.");
			System.out.println("          이전 페이지로 돌아갑니다.");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		}

		sleeep(3);
	}

	public void gameRuner() {

	}

	public void gameOver() {
		game(lId);
	}

	private void rank() {
		clearScreen();
		List<UserInfo> li = service.userRank();
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("           랭              킹");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		for (int i = 0; i < li.size(); i++) {
			System.out.print("  " + (i + 1));
			li.get(i).rank();
			System.out.println();
		}
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		sleeep(4);
		clearScreen();
	}

	private int productUpgade() {
		boolean isTure = true;
		while (isTure) {
			int monuy = 0;
			clearScreen();
			System.out.println("┏━━업그레이드 상점━━┓");
			System.out.println("    1. 붕어빵틀     ");
			System.out.println("    2. 청소도구    ");
			System.out.println("    3. 돌아가기        ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = menuselect();
			System.out.println();
			if (menuNo == 1) {
				tleUpgade();
			} else if (menuNo == 2) {
				cleanerUpgade();
			} else if (menuNo == 3) {
				clearScreen();
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
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    붕어빵을 구울 때 떨어지는 청결도가 감소합니다. ");
			System.out.println("                                  가격 : 10,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			String check = scn.nextLine();

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
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    붕어빵을 구울 때 떨어지는 청결도가 감소합니다. ");
			System.out.println("                                  가격 : 20,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			String check = scn.nextLine();

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
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    붕어빵을 구울 때 떨어지는 청결도가 감소합니다. ");
			System.out.println("                                  가격 : 30,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			String check = scn.nextLine();

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

		sleeep(3);
	}

	private void tleUpgade() { // 빵틀 업그레이드
		make.cleannn(lId);
		int upgade1 = 10000; // 장비들의 가격
		int upgade2 = 20000;
		int upgade3 = 30000;
		if (havetle == 0) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    한번의 구울 수 있는 붕어빵의 갯수가 증가합니다 ");
			System.out.println("                                  가격 : 10,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			String check = scn.nextLine();

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
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    한번의 구울 수 있는 붕어빵의 갯수가 증가합니다 ");
			System.out.println("                                  가격 : 20,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			String check = scn.nextLine();

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
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("    한번의 구울 수 있는 붕어빵의 갯수가 증가합니다 ");
			System.out.println("                                  가격 : 30,000원");
			System.out.println("          업그레이드 하시겠습니까?  (Y/N) ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			String check = scn.nextLine();

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
		sleeep(3);
	}

	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	public static void sleeep(double time) {
		try {
			sleep((long) (time * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void outOfRange() {
		System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("         목록의 숫자만 입력해주세요");
		System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}
	
	static void GG() {
		clearScreen();
		System.out.println(" ██████╗     ██████╗ \r\n"
				+ "██╔════╝    ██╔════╝ \r\n"
				+ "██║  ███╗   ██║  ███╗\r\n"
				+ "██║   ██║   ██║   ██║\r\n"
				+ "╚██████╔╝██╗╚██████╔╝\r\n"
				+ " ╚═════╝ ╚═╝ ╚═════╝ \r\n"
				+ "                     \r\n"
				+ "");
	}

	public void mainba() {
		System.out.println(
				"     ` ``        ``                           ``    `     `      `` `````                              `` ``                    ````` `                           \r\n"
						+ "     ##  `        ``##`                       ##       ```     `` ` `` ##`                             ` ##``                    ` `##       `  ````````````` `   \r\n"
						+ "     ##  ```````   `##        ` `##`          ##     ##` `## ##`  ##   ##              `````````````   ` ## `     `` ``#`   `      `##       `################`   \r\n"
						+ "     #################`      `##```###`      `##     ## ``##`##`  ##`` ##              `############   ` ## `     `###``####       `##         `           `##`   \r\n"
						+ "     ##   `````` ` `##`    `###```  `##`      ##     #######`#######`` ######           ## ``          ` ##      `##`` ` `###`     `##       `````     ` ```## `  \r\n"
						+ "    `#################      ## `     `##`    `##     ##```##`##` `##`` ##`  `           ##      ` `    ` ## `   ``##` `   `###`    `##       ``###############`   \r\n"
						+ "    ```````````````````   ``##`     ``##########     ## ``##`##`  ##`` ##               ###########    ` ####### ##`  `    `##`    `##       ` `      `   ` ##`   \r\n"
						+ "`` `                       `## `     `##`     ##     #######`####### ``##`              ##``    ```    ` ##      ##``      `## `   `## ` `########################\r\n"
						+ "`########################`` ##`    ` ###`     ##           ` ` `  ``   ##`              ## ``           `##      `## `` `  ##``    `##       ```     `##          \r\n"
						+ " ```      ``##````        ` `### `  ###``     ##           `##########`` `              ## ``          ` ##     ``###``  `##`      `##        ``     `##          \r\n"
						+ "    ` ####``  ``####``        `######`        ##       ``###````  ` ``###`              ###################         ######` `      `##        ##     `## `        \r\n"
						+ "    `##    `  ` ````##`      ``  `   `        ##        ### `` ``  `` `###              `  ` `   ` ` ` ` ##     ``    `    ``      `##        ##     `   `        \r\n"
						+ "    `### `  `     `###                        ##       ` ###` `   `   ###                              ` ##                        `##        ##               `  \r\n"
						+ "    ` ``###########`` `                       ##           `##########`  `                              `##``                   ` ``##       `#################`  \r\n"
						+ "");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(
				"                                                            ============================================");
		System.out.println("                                                              게임을 시작하려면 아무런 키를 입력하세요");
		System.out.println(
				"                                                            ============================================");
		try {
			Integer.parseInt(scn.nextLine());

		} catch (Exception e) {
		}
	}

	public void mainba2() {
		boolean isTrue = true;

		clearScreen();
		System.out.println("   ___                                ___    ___                          _ \r\n"
				+ "  / __\\ _   _  _ __    __ _  _   _   / __\\  / __\\  __ _  _ __    __ _    / \\\r\n"
				+ " /__\\//| | | || '_ \\  / _` || | | | /__\\// /__\\// / _` || '_ \\  / _` |  /  /\r\n"
				+ "/ \\/  \\| |_| || | | || (_| || |_| |/ \\/  \\/ \\/  \\| (_| || | | || (_| | /\\_/ \r\n"
				+ "\\_____/ \\__,_||_| |_| \\__, | \\__,_|\\_____/\\_____/ \\__,_||_| |_| \\__, | \\/   \r\n"
				+ "                      |___/                                     |___/       \r\n"
				+ "                                                 _____                                  \r\n"
				+ "                                                /__   \\ _   _   ___  ___    ___   _ __  \r\n"
				+ "                                                  / /\\/| | | | / __|/ _ \\  / _ \\ | '_ \\ \r\n"
				+ "                                                 / /   | |_| || (__| (_) || (_) || | | |\r\n"
				+ "                                                 \\/     \\__, | \\___|\\___/  \\___/ |_| |_|\r\n"
				+ "                                                        |___/                           \r\n" + "");
		System.out.println();
		System.out.println();
		System.out.println("                 ============================================");
		System.out.println("                   게임을 시작하려면 아무런 키를 입력하세요");
		System.out.println("                 ============================================");
		System.out.println();
		try {
			scn.nextLine();

		} catch (Exception e) {

		}
	}

	public void maintip() {

		clearScreen();
		System.out.println("   __             _  _                        \r\n"
				+ "  / /   ___    __| |(_) _ __    __ _          \r\n"
				+ " / /   / _ \\  / _` || || '_ \\  / _` |         \r\n"
				+ "/ /___| (_) || (_| || || | | || (_| | _  _  _ \r\n"
				+ "\\____/ \\___/  \\__,_||_||_| |_| \\__, |(_)(_)(_)\r\n"
				+ "                               |___/          \r\n" + "       _____  _        \r\n"
				+ "      /__   \\(_) _ __  \r\n" + "        / /\\/| || '_ \\ \r\n"
				+ "       / /   | || |_) |              게임을 시작하면 기본 재료가 지급됩니다.\r\n" + "       \\/    |_|| .__/ \r\n"
				+ "                |_|    \r\n" + "");

		PojangMacha.sleeep(1.5);

		clearScreen();
		System.out.println("   __             _  _                                 \r\n"
				+ "  / /   ___    __| |(_) _ __    __ _                   \r\n"
				+ " / /   / _ \\  / _` || || '_ \\  / _` |                  \r\n"
				+ "/ /___| (_) || (_| || || | | || (_| | _  _  _  _  _  _ \r\n"
				+ "\\____/ \\___/  \\__,_||_||_| |_| \\__, |(_)(_)(_)(_)(_)(_)\r\n"
				+ "                               |___/                   \r\n" + "       _____  _        \r\n"
				+ "      /__   \\(_) _ __  \r\n" + "        / /\\/| || '_ \\ \r\n"
				+ "       / /   | || |_) |              게임을 시작하기전 장비를 업그레이드해보세요!\r\n" + "       \\/    |_|| .__/ \r\n"
				+ "                |_|    \r\n" + "");
		PojangMacha.sleeep(1.5);

		clearScreen();
		System.out.println("   __             _  _                                          \r\n"
				+ "  / /   ___    __| |(_) _ __    __ _                            \r\n"
				+ " / /   / _ \\  / _` || || '_ \\  / _` |                           \r\n"
				+ "/ /___| (_) || (_| || || | | || (_| | _  _  _  _  _  _  _  _  _ \r\n"
				+ "\\____/ \\___/  \\__,_||_||_| |_| \\__, |(_)(_)(_)(_)(_)(_)(_)(_)(_)\r\n"
				+ "                               |___/                            \r\n" + "       _____  _        \r\n"
				+ "      /__   \\(_) _ __  \r\n" + "        / /\\/| || '_ \\ \r\n"
				+ "       / /   | || |_) |              게임을 종료하면 남은 돈은 저장됩니다.\r\n" + "       \\/    |_|| .__/ \r\n"
				+ "                |_|    \r\n" + "");
		PojangMacha.sleeep(1.5);
	}

	public void maintip2() {
		String[] tip2 = { "(_)(_)(_)", "(_)(_)(_)(_)(_)", "(_)(_)(_)(_)(_)(_)(_)",
				"(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)" };
		String[] tip = { "게임을 시작하기전 장비를 업그레이드해보세요!", "게임을 종료하면 남은 돈은 저장됩니다.", "게임을 시작하면 기본 재료가 지급됩니다.",
				"손님이 요구하는 붕어빵을 판매하는 게임입니다!", "손님마다 원하는 붕어빵의 종류와 갯수가 다릅니다.", "장비를 업그레이드 할수록 게임 진행이 쉬워집니다.",
				"재료가 부족하면 붕어빵 재료 구매 탭에서 구매해야합니다.", "처음 게임 시작 후 기본 재료의 갯수를 생각해서 붕어빵을 구워야합니다.",
				"손님이 원하는 갯수보다 봉투에 담는 갯수가 적으면 만족도가 하락합니다.", "청결도가 -15가 되면 게임오버입니다." };
		for (int i = 0; i < 4; i++) {
			int x = (int) (Math.random() * tip.length);
			clearScreen();
			System.out.print("   __             _  _                                 \r\n"
					+ "  / /   ___    __| |(_) _ __    __ _                   \r\n"
					+ " / /   / _ \\  / _` || || '_ \\  / _` |                  \r\n"
					+ "/ /___| (_) || (_| || || | | || (_| |                    \r\n"
					+ "\\____/ \\___/  \\__,_||_||_| |_| \\__, / " + tip2[i] + "\r\n"
					+ "                               |___/                   \r\n" + "       _____  _        \r\n"
					+ "      /__   \\(_) _ __  \r\n" + "        / /\\/| || '_ \\ \r\n"
					+ "       / /   | || |_) |              " + "\r\n" + "       \\/    |_|| .__/ \r\n"
					+ "                |_|                           ");
			slowPrint(tip[x], 50);
			PojangMacha.sleeep(0.35);
		}

	}

	public static void slowPrint(String message, long millisPerChar) {
		for (int i = 0; i < message.length(); i++) {
			System.out.print(message.charAt(i));

			try {
				Thread.sleep(millisPerChar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}