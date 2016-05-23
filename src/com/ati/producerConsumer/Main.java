package com.ati.producerConsumer;

public class Main {

	public static void main(String[] args) {
        Drop drop = new Drop();
        //Drop is shared between producer and consumer
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
	
}
