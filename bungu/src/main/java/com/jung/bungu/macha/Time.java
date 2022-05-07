package com.jung.bungu.macha;

public class Time extends Thread {
	public bungubbang bb;

	public void run() {
		bb = new bungubbang();
		while (true) {

			try {
				

				Thread.sleep(10000);
				bb.setbTime(bb.getbTime()+10); 
				System.out.println(bb.getbTime() + "초 경과");

				if (bb.getbTime() >= 180) {
					System.out.println("게임 시간이 끝났습니다.");

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
