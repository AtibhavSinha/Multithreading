package com.ati.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongRunningService {
	
	public void runLongRunningService(LongRunningProcess longRunningProcess){
		ExecutorService executoService = Executors.newCachedThreadPool();
		executoService.submit(longRunningProcess);
		executoService.shutdown();
	}

}
