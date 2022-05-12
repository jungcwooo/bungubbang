package com.jung.bungu.macha;

public class Time extends Thread {
	bungubbang bb;
	int bTime =0; // 현재 진행 시간
	
	public void run() {
		bb =new bungubbang();
		System.out.println("[ 게임 시작!! ]");
		while (true) {

			try {
				

				Thread.sleep(10000);
				bTime += 10; 
				System.out.println(bTime + "초 경과");

				if (bTime >= 10) {
					System.out.println("게임 시간이 끝났습니다.");
					bb.i = 99;
					break;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
