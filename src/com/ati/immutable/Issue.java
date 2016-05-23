package com.ati.immutable;


public class Issue {

	private static SynchronizedRGB color =
			new SynchronizedRGB(0, 0, 0, "Pitch Black");

	private static class Thread1
			implements Runnable {
		public void run() {
			int myColorInt = color.getRGB(); // Statement 1
			try {
				Thread.sleep(30);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			If another thread invokes color.set after Statement 1 but before Statement 2, 
//			the value of myColorInt won't match the value of myColorName. 
			String myColorName = color.getName(); // Statement 2
			System.out.println("Thread 1 myColorInt="+ myColorInt);
			System.out.println("Thread 1 myColorName="+ myColorName);
		}
	}

	private static class Thread2
			implements Runnable {
		public void run() {
			color.set(255, 0, 0, "Black by another thread");
			System.out.println("Thread2 myColorName="+ color.getName());
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		t2.start();
		t1.start();
		
//		Thread2 myColorName=Black by another thread
//				Thread 1 myColorInt=0
//				Thread 1 myColorName=Black by another thread

	}

}
