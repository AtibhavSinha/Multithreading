package com.ati;

public class SynchronizedExample {

	private static Counter counter = new Counter();

	private static class CounterUser1
			implements Runnable {
		public void run() {
			counter.increment();// When one thread is executing a synchronized method for an object
			// System.out.println("My Name is "+Thread.currentThread().getName());
			System.out.println("CounterUser1: After Increment c should be 1");
			System.out.println("CounterUser1: After Increment c is" + counter.value());
		}
	}

	private static class CounterUser2
			implements Runnable {
		public void run() {
			counter.decrement();// other threads that invoke synchronized methods for the same
								// object block (suspend execution) until the first thread is done
								// with the object.
			// System.out.println("My Name is "+Thread.currentThread().getName());
			System.out.println("CounterUser2: After decrement c should be -1");
			System.out.println("CounterUser2: After decrement c is" + counter.value());
		}
	}

	private static class Counter {
		private int c = 0;

		public synchronized void increment() {
			System.out.println("C increment start");
			c++;
			System.out.println("C increment end");
		}

		public synchronized void decrement() {
			System.out.println("C decrement start");
			c--;
			System.out.println("C decrement end");
		}

		public synchronized int value() {
			return c;
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new CounterUser1());
		t1.setName("CounterUser1");
		Thread t2 = new Thread(new CounterUser2());
		t2.setName("CounterUser2");
		t2.start();
		t1.start();

	}

}
