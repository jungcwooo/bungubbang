package com.jung.bungu.macha;

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

//	Clean cl = new Clean();
	
	
	
//	Gaust gs = new Gaust();
	BunguService service = new BunguServiceImpl();
	String lId;
//	
	public void run() {
		login();
	}
	

	public void login() { // 로그인창
		while (true) {

			System.out.println("===============");
			System.out.println("= 1. 로그인   =");
			System.out.println("= 2. 회원가입 =");
			System.out.println("= 3. 종료     =");
			System.out.println("===============");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());

			if (menuNo == 1) { // 로그인 ID,PASSWD를 입력 받아서 Login메서드에 넣어주고 디비에 저장된 ID PASSWD와 일치하면 리턴값 1을 받아
				// game();메서드가 실행된다.
				System.out.println("==== 로그인 ====");
				System.out.print("ID를 입력해주세요 => ");
				lId = scn.next();
				System.out.print("Passwd를 입력해주세요 => ");
				String lPasswd = scn.next();

				int result = service.Login(lId, lPasswd);

				if (result == 1) {
					
					
					service.savingMonuy(game(lId), lId);

				} else {
					continue;
				}

				break;
			} else if (menuNo == 2) {
				int result = service.insertuser(vo);
				if (result == 1) {
					System.out.println("회원가입이 완료되었습니다.");
				} else {
					System.out.println("정보를 잘못입력하셨습니다.");
					continue;
				}

			} else if (menuNo == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

	}

	private int game(String lId) { // 로그인 할때 입력 받은 ID값을 계속 사용 (로그인 상태)
		boolean isTure = true;
		while (isTure) {
			int monuy = 0;
			System.out.println("====================");
			System.out.println("= 1. 게임 시작     =");
			System.out.println("= 2. 내 정보 조회  =");
			System.out.println("= 3. 비밀번호 변경 =");
			System.out.println("= 4. 회원 탈퇴     =");
			System.out.println("= 5. 종료          =");
			System.out.println("====================");

			int menuNo = 0;
			menuNo = Integer.parseInt(scn.next());

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

				vo = service.selectuser(lId);
				vo.toString();
				continue; // 내 정보를 조회 한 후 다시 메뉴로 돌아가기 위해 컨티뉴

			} else if (menuNo == 3) {

				int result = service.updateuser(lId);
				if (result == 1) {
					System.out.println("비밀번호가 변경되셨습니다.");
				} else {
					System.out.println("변경에 실패하셨습니다");
				}
				continue;  // 비밀 번호는 변경한 후 다시 메뉴로 돌아가기 위해 컨티뉴, 원래 비번 바꾸고 다시 로그인은 국룰이지만

			} else if (menuNo == 4) {

				service.deleteuser(lId);
				System.out.println("감사했습니다");

				break; // 탈퇴를 하고 다시 메뉴로 돌아가는것을 방지하기 위해

			} else if (menuNo == 5) {
				System.out.println("프로그램을 종료합니다.");

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

}
