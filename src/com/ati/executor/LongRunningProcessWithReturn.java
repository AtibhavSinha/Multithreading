package com.ati.executor;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;

public class LongRunningProcessWithReturn implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() +" File Download Started..............");
		Random random = new Random();
		int duration = random.nextInt(10000);
		if(duration>8000){
			// Enough of waiting to complete the process. Throw the exception as Download Service seems down.
			throw new IOException("Enough of waiting to complete the process. Throw the exception as Download Service seems down.");
		}
		try {
			Thread.sleep(duration);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +" File Download Finished..............");
		return duration;
	}

}
