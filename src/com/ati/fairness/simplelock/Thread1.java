package com.ati.fairness.simplelock;

public class Thread1 implements Runnable{
	
	private  Synchronizer synchronizer;
	
	public Thread1(Synchronizer synchronizer) {
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
