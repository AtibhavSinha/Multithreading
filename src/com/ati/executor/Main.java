package com.ati.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	// Using Executor 
	public static void main(String[] args) {

		// We need not to create the Thread ExecutorService will take care of managing the Thread.
//		ExecutorService executoService = Executors.newCachedThreadPool();
//		executoService.submit(new LongRunningProcess());
//		executoService.shutdown();
		
		// How can we get result back from the Thread LongRunningProcess.
		// How the LongRunningProcess can return me a exception. 
		// Use Callable (Runnable) and call(run) method. Advantage : Callable can return a value (Future).
		
		ExecutorService executoServiceWithReturn = Executors.newCachedThreadPool();
		Future<Integer> future = executoServiceWithReturn.submit(new LongRunningProcessWithReturn());
		try {
			// We havn't waited for Thread to finish.
			// If we dont wait for Thread to finish get will be blocking (code will not execute ahead).
			// until unless the thread is terminated / get return value from thread.
			System.out.println("Result is : "+future.get());
			//future.isDone();
			//future.cancel(mayInterruptIfRunning);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(ExecutionException e){
			System.out.println("Error occured: "+e.getMessage());
		}
		executoServiceWithReturn.shutdown();
		
		// Traditional Way
		// We can spawn multiple Thread to download the documents parallely. 4 Doc's -> 4 Thread.
//		Thread thread1 = new Thread(new LongRunningProcess());
//		thread1.setName("First Thread!!");
//		thread1.start();
//		
//		Thread thread2 = new Thread(new LongRunningProcess());
//		thread2.setName("Second Thread!!");
//		thread2.start();
	}

}

//LongRunningProcess longRunningProcess = new LongRunningProcess();
//
//LongRunningService longRunningService1 = new LongRunningService();
//longRunningService1.runLongRunningService(longRunningProcess);
//
//LongRunningService longRunningService2 = new LongRunningService();
//longRunningService2.runLongRunningService(longRunningProcess);
