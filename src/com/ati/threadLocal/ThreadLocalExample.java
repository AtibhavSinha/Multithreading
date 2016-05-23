package com.ati.threadLocal;

public class ThreadLocalExample {

	public static class MyRunnable implements Runnable {

		private ThreadLocal<Integer> threadLocal =
				new ThreadLocal<Integer>();
		int test=0;

		@Override
		public void run() {
			threadLocal.set((int) (Math.random() * 100D));
			test =(int) (Math.random() * 100);
			try {
				//Wait for other thread to override the value set by the other Thread
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {
			}

			System.out.println("Thread local ="+threadLocal.get());
			System.out.println("Normal int"+test);
		}
	}

	public static void main(String[] args) {
		MyRunnable sharedRunnableInstance = new MyRunnable();

		Thread thread1 = new Thread(sharedRunnableInstance);
		Thread thread2 = new Thread(sharedRunnableInstance);

		thread1.start();
		thread2.start();
	}
	// Both threads execute the run() method, and thus sets different values on the ThreadLocal
	// instance. If the access to the set() call had been synchronized, and it had not been a
	// ThreadLocal object, the second thread would have overridden the value set by the first
	// thread.

	// However, since it is a ThreadLocal object then the two threads cannot see each other's
	// values. Thus, they set and get different values

}
