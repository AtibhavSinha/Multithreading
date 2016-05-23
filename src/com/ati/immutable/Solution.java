package com.ati.immutable;


public class Solution {

	private static SynchronizedRGB color =
			new SynchronizedRGB(0, 0, 0, "Pitch Black");

	private static class Thread1
			implements Runnable {
		public void run() {
			synchronized (color) {
				int myColorInt = color.getRGB(); // Statement 1
				try {
					Thread.sleep(30);
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String myColorName = color.getName(); // Statement 2
				System.out.println("Thread 1 myColorInt="+ myColorInt);
				System.out.println("Thread 1 myColorName="+ myColorName);
			}
		}
	}

	private static class Thread2
			implements Runnable {
		public void run() {
			//Or
//			try {
//				Thread.sleep(3000);
//			}
//			catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			color.set(255, 0, 0, "Black by another thread");
			System.out.println("Thread2 myColorName="+ color.getName());
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		t2.start();
		t1.start();

	}

}
