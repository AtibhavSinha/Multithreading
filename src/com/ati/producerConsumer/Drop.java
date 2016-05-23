package com.ati.producerConsumer;

public class Drop {
	// Message sent from producer
	// to consumer.
	private String message;
	// True if consumer should wait
	// for producer to send message,
	// false if producer should wait for
	// consumer to retrieve message.
	private boolean empty = true;

	public synchronized String take() {
		// Wait until message is
		// available.
		while (empty) {
			try {
				// Causes the current thread to wait until another thread invokes the
				// * {@link java.lang.Object#notify()} method or the
				// * {@link java.lang.Object#notifyAll()} method for this object.
				// * The current thread must own this object's monitor. The thread
				// * releases ownership of this monitor and waits until another thread
				// * notifies threads waiting on this object's monitor to wake up
				// * either through a call to the {@code notify} method or the
				// * {@code notifyAll} method. The thread then waits until it can
				// * re-obtain ownership of the monitor and resumes execution.
				// Also tells
				// * A thread waits on an object's monitor by calling one of the
				// * {@code wait} methods.

				// Why synchronized? : To execute wait we need to have lock on the object so keep
				// wait inside the sync method.(happens before relationship)
				// Suppose d is the object we're using to invoke wait. When a thread invokes d.wait,
				// it must own the intrinsic lock for d — otherwise an error is thrown. Invoking
				// wait inside a synchronized method is a simple way to acquire the intrinsic lock.

				// Now we have to release the lock on d so that other thread get lock on it and
				// notify all other thread(waiting for the lock) that some thing important happened.

				// When wait is invoked, the thread releases the lock and suspends execution.
				wait();
			}
			catch (InterruptedException e) {
			}
		}
		// Toggle status.
		empty = true;
		// Notify producer that
		// status has changed.
		notifyAll();
		return message;
	}

	public synchronized void put(String message) {
		// Wait until message has
		// been retrieved.
		while (!empty) {
			try {
				wait();
			}
			catch (InterruptedException e) {
			}
		}
		// Toggle status.
		empty = false;
		// Store message.
		this.message = message;
		// Notify consumer that status
		// has changed.
		// After setting the message(doing your job) notify all the thread, change the status before
		// notifying.

		// Wakes up all threads that are waiting on this object's monitor. A
		// * thread waits on an object's monitor by calling one of the
		// * {@code wait} methods.

		// * The awakened threads will not be able to proceed until the current
		// * thread relinquishes the lock on this object (empty = false). The awakened threads
		// * will compete in the usual manner with any other threads that might
		// * be actively competing to synchronize on this object

		notifyAll();
	}
}

// A thread that calls wait() on any object becomes inactive until another thread calls notify() on
// that object(Same). In order to call either wait() or notify the calling thread must first obtain
// the
// lock on that object. In other words, the calling thread must call wait() or notify() from inside
// a synchronized block.

// As you can see both the waiting and notifying thread calls wait() and notify() from within a
// synchronized block. This is mandatory! A thread cannot call wait(), notify() or notifyAll()
// without holding the lock on the object the method is called on. If it does, an
// IllegalMonitorStateException is thrown.

// But, how is this possible? Wouldn't the waiting thread keep the lock on the monitor object
// (myMonitorObject) as long as it is executing inside a synchronized block? Will the waiting thread
// not block the notifying thread from ever entering the synchronized block in doNotify()? The
// answer is no. Once a thread calls wait() it releases the lock it holds on the monitor object.
// This allows other threads to call wait() or notify() too, since these methods must be called from
// inside a synchronized block. 