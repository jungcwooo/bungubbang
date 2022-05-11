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
	UserInfo vo1;
	int havecleaner = 0;
	int havetle = 0;

	BunguService service = new BunguServiceImpl();
	String lId;

	public void run() {
//		mainba();
		mainba2();
//		maintip();
		maintip2();
		login();
	}

	public int login() { // 로그인창
		clearScreen();
		while (true) {
			int menuNo = 0;

			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("   1. 로그인  2. 회원가입  3. 랭킹  4. 종료  ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("선택 => ");
			try {
				menuNo = Integer.parseInt(scn.next());

			} catch (Exception e) {
				clearScreen();
				System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(" \t     숫자만 입력해주세요");
				System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}

			if (menuNo == 1) { // 로그인 ID,PASSWD를 입력 받아서 Login메서드에 넣어주고 디비에 저장된 ID PASSWD와 일치하면 리턴값 1을 받아
				// game();메서드가 실행된다.
				System.out.println("━━━━━━━━━━━━━로그인━━━━━━━━━━━━━━");
				System.out.print("   ID를 입력해주세요 => ");
				lId = scn.next();
				System.out.print("   Passwd를 입력해주세요 => ");
				String lPasswd = scn.next();

				int result = service.Login(lId, lPasswd);

				if (result == 1) {
					clearScreen();
					game(lId);

				} else {

					continue;
				}

				break;
			} else if (menuNo == 2) {
				clearScreen();
				int result = service.insertuser(vo);
				if (result == 1) {
					System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println(" \t  회원가입이 완료되었습니다.");
					System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				} else {

					continue;
				}

			} else if (menuNo == 3) {
				clearScreen();
				rank();
				continue;

			} else if (menuNo == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				clearScreen();
				System.out.println("   ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("         1 ~ 4의 숫자만 입력해주세요");
				System.out.println("   ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
		}
		return 0;

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
			System.out.println("      ┏━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("          1. 게임 시작       ");
			System.out.println("          2. 내 정보         ");
			System.out.println("          3. 상점            ");
			System.out.println("          4. 로그아웃        ");
			System.out.println("      ┗━━━━━━━━━━━━━━━━━━━┛");

			int menuNo = 0;
			System.out.print("선택 => ");
			try {
				menuNo = Integer.parseInt(scn.next());

			} catch (Exception e) {
				clearScreen();
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t숫자만 입력해주세요");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println();
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

				isTure = false;
				return login();
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

			int menuNo = 0;
			System.out.print("선택 => ");
			try {
				menuNo = Integer.parseInt(scn.next());

			} catch (Exception e) {
				clearScreen();
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t   숫자만 입력해주세요");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println();

			if (menuNo == 1) {
				vo = service.selectuser(lId);
				clearScreen();
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.print("   ");
				vo.toString();
				System.out.println();
				System.out.println("    장비 : " + make.cleanerpro + ", " + make.tlepro);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				sleeep(3);
				continue; // 내 정보를 조회 한 후 다시 메뉴로 돌아가기 위해 컨티뉴
			} else if (menuNo == 2) {

				int result = service.updateuser(lId);
				if (result == 1) {
					System.out.println("비밀번호가 변경되셨습니다.");
				} else {
					System.out.println("변경에 실패하셨습니다");
				}
				sleeep(3);
				continue; // 비밀 번호는 변경한 후 다시 메뉴로 돌아가기 위해 컨티뉴, 원래 비번 바꾸고 다시 로그인은 국룰이지만

			} else if (menuNo == 3) {

				service.deleteuser(lId);
				System.out.println("감사했습니다");
				sleeep(3);
				return login(); // 탈퇴를 하고 다시 메뉴로 돌아가는것을 방지하기 위해

			} else if (menuNo == 4) {
				clearScreen();
				make.cleannn(lId);
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t   " + vo1.getName() + "님의 장비");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("     " + make.cleanerpro + " , 배율 : " + make.clnumber);
				System.out.println("     " + make.tlepro + " , 갯수 : " + make.tlnumber);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				sleeep(3);
			} else if (menuNo == 5) {
				isTure = false;
				clearScreen();
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

			int menuNo = 0;
			System.out.print("선택 => ");
			try {
				menuNo = Integer.parseInt(scn.next());

			} catch (Exception e) {
				clearScreen();
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("\t   숫자만 입력해주세요");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
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

		sleeep(4);
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
		check();
	}

	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	public void check() {
		System.out.println("확인 : ENTER");
		scn.next();
		scn.nextLine();

	}

	public static void sleeep(double time) {
		try {
			sleep((long) (time * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
			Integer.parseInt(scn.next());

		} catch (Exception e) {
		}
	}
	public void mainba2() {
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
				+ "                                                        |___/                           \r\n"
				+ "");
		System.out.println();
		System.out.println();
		System.out.println(
				"                 ============================================");
		System.out.println("                   게임을 시작하려면 아무런 키를 입력하세요");
		System.out.println(
				"                 ============================================");
		try {
			Integer.parseInt(scn.next());

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
		String[] tip2 = { "(_)(_)(_)","(_)(_)(_)(_)(_)(_)","(_)(_)(_)(_)(_)(_)(_)(_)(_)"};
		String[] tip = { "게임을 시작하기전 장비를 업그레이드해보세요!", "게임을 종료하면 남은 돈은 저장됩니다.", "게임을 시작하면 기본 재료가 지급됩니다." };
		for (int i = 0; i < 3; i++) {
			int x = (int) (Math.random() * tip.length);
			clearScreen();
			System.out.println("   __             _  _                                 \r\n"
					+ "  / /   ___    __| |(_) _ __    __ _                   \r\n"
					+ " / /   / _ \\  / _` || || '_ \\  / _` |                  \r\n"
					+ "/ /___| (_) || (_| || || | | || (_| |                    \r\n"
					+ "\\____/ \\___/  \\__,_||_||_| |_| \\__, / " + tip2[i] + "\r\n"
					+ "                               |___/                   \r\n" + "       _____  _        \r\n"
					+ "      /__   \\(_) _ __  \r\n" + "        / /\\/| || '_ \\ \r\n"
					+ "       / /   | || |_) |              " + tip[x] + "\r\n" + "       \\/    |_|| .__/ \r\n"
					+ "                |_|    \r\n" + "");
			PojangMacha.sleeep(1.5);
		}

	}
}