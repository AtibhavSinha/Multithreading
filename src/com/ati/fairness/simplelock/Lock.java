package com.ati.fairness.simplelock;

public class Lock {
	private boolean isLocked = false;
	private Thread lockingThread = null;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
		lockingThread = Thread.currentThread();
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException(
					"Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		notify();
	}
}

// The following three common causes can lead to starvation of threads in Java:
//
// Threads with high priority swallow all CPU time from threads with lower priority.
//
// Threads are blocked indefinately waiting to enter a synchronized block, because other threads are
// constantly allowed access before it.
//
// Threads waiting on an object (called wait() on it) remain waiting indefinitely because other
// threads are constantly awakened instead of it.
