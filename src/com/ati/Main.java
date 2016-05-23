package com.ati;

public class Main {
	 public static void main(String[] args){
		    System.out.println(Thread.currentThread().getName());
//		    Out put will be random because threads will run in parallel
		    for(int i=0; i<10; i++){
		      new Thread("" + i){
		        public void run(){
		          System.out.println("Thread: " + getName() + " running");
		        }
		      }.start();
		    }
		  }
}
