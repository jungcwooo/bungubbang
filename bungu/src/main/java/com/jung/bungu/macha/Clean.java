package com.jung.bungu.macha;

public class Clean extends Thread {
	public bungubbang bb;
	int bClean;

	public void run() {
		bClean =20;
		bb = new bungubbang();
		while (true) {
			try {
			bClean -= 1;
			System.out.println("청결도가 -1  감소하였습니다. 현재 청결도 " +bClean);
//			bb.bClean -= 1;
//			System.out.println("청결도가 -1  감소하였습니다. 현재 청결도 " + bb.getbClean());
//			if (bb.getbClean() <= -10) {
//				System.out.println("게임 오버");
//
//			}
			if (bClean <= -10) {
				System.out.println("게임 오버");
				
			}
			join();
			
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
