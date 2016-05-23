package com.ati.executor;

import java.util.Random;


class LongRunningProcess implements Runnable{
	
	@Override
	public void run() {
		//Common Code to download documents from a service. : Long (Time consuming) running process.
		System.out.println(Thread.currentThread().getName() +" File Download Started..............");
		Random random = new Random();
		int duration = random.nextInt(10000);
		try {
			Thread.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +" File Download Finished..............");
	}
	
}