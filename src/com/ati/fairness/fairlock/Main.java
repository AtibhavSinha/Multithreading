package com.ati.fairness.fairlock;

public class Main {

	// Problem : At a time lot of thread are in waiting condition.So it may be possible that one
	// Thread may never gets it chance.Which is not fair.
	public static void main(String[] args) {
		Synchronizer synchronizer = new Synchronizer();
		(new Thread(new Thread1(synchronizer))).start();
		(new Thread(new Thread2(synchronizer))).start();
	}
	// Result
	// Lock has been taken!! for Thread = Thread[Thread-0,5,main]
	// Lock has been taken!! for Thread = Thread[Thread-1,5,main]
	// Started the long process for Thread!! = Thread[Thread-0,5,main]
	// Ended the long process for Thread!! = Thread[Thread-0,5,main]
	// Lock has been released!! for Thread = Thread[Thread-0,5,main]
	// Started the long process for Thread!! = Thread[Thread-1,5,main]
	// Ended the long process for Thread!! = Thread[Thread-1,5,main]
	// Lock has been released!! for Thread = Thread[Thread-1,5,main]
}
