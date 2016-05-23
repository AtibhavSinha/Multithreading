package com.ati.fairness.fairlock;

public class Thread2 implements Runnable{
	
	private Synchronizer synchronizer;

	public Thread2(Synchronizer synchronizer) {
		this.synchronizer= synchronizer;
	}

	@Override
	public void run() {
		try {
			synchronizer.doSynchronized();
		}
		catch (InterruptedException e) {
			System.out.println("Thread 1 got interrupted");
			e.printStackTrace();
		}
	}

}
