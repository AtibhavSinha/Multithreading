package com.ati.fairness.fairlock;

public class Synchronizer {
	FairLock fairLock = new FairLock();
	// Compared to Drop we have not made the method as synchronized so the thread executing this
	// method will not have lock on the this class's object.
	//Instead of that we are implementing out own locking mechanism.
	//Instead of locking the Synchronizer object thread is locking the Lock object.
	public void doSynchronized() throws InterruptedException {
		System.out.println("Lock has been taken!! for Thread = "+Thread.currentThread());
		this.fairLock.lock();
		// critical section, do a lot of work which takes a long time
		System.out.println("Started the long process for Thread!! = "+Thread.currentThread());
		Thread.sleep(5000);
		System.out.println("Ended the long process for Thread!! = "+Thread.currentThread());
		this.fairLock.unlock();
		System.out.println("Lock has been released!! for Thread = "+Thread.currentThread());
	}

}