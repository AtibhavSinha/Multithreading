package com.ati.fairness.simplelock;

public class Main {

	public static void main(String[] args) {
		Synchronizer synchronizer = new Synchronizer();
		(new Thread(new Thread1(synchronizer))).start();
		(new Thread(new Thread2(synchronizer))).start();
		
	}
	
//Result
//			Lock has been taken!! for Thread = Thread[Thread-0,5,main]
//			Started the long process for Thread!! = Thread[Thread-0,5,main]
//			Lock has been taken!! for Thread = Thread[Thread-1,5,main]
//			Ended the long process for Thread!! = Thread[Thread-0,5,main]
//			Lock has been released!! for Thread = Thread[Thread-0,5,main]
//			Started the long process for Thread!! = Thread[Thread-1,5,main]
//			Ended the long process for Thread!! = Thread[Thread-1,5,main]
//			Lock has been released!! for Thread = Thread[Thread-1,5,main]


}
