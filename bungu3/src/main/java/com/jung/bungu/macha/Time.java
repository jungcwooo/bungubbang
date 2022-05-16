package com.jung.bungu.macha;

import java.util.Scanner;

public class Time extends Thread {
	Scanner scn = new Scanner(System.in);
	bungubbang bb;
	
	public synchronized void run() {
		bb =new bungubbang();
		System.out.println("[ 게임 시작!! ]");
		while (true) {

			try {
				

				Thread.sleep(30000);
				bungubbang.bTime += 30; 
				System.out.println("\t\t\t\t\t\t\t"+bungubbang.bTime + "초 경과");

				if (bungubbang.bTime >= 300) {
					for (int i = 0; i < 3; i++) {
						Thread.sleep(500);
					PojangMacha.clearScreen();
					System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.println("          게임 시간이 끝났습니다              ");
					System.out.println("   ────────────────────────────────────────   ");
					System.out.println("    저장하시려면 숫자 '4'를 입력해주세요     ");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					bungubbang.bTime = 300;
					
					}
					break;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
