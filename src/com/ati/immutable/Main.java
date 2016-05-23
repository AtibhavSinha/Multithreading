package com.ati.immutable;

public class Main {

	public static void main(String[] args) {
		SynchronizedRGB color =
				new SynchronizedRGB(0, 0, 0, "Pitch Black");
		int myColorInt = color.getRGB(); // Statement 1
		color.set(255, 255, 255, "Black by another thread");
		String myColorName = color.getName(); // Statement 2
		
		System.out.println("myColorInt="+ myColorInt);
		System.out.println("myColorName="+ myColorName);
		
//		If another thread invokes color.set after Statement 1 but before Statement 2, the 
//		value of myColorInt won't match the value of myColorName. (Two thread are shaing the color object)
//		To avoid this outcome, the two statements must be bound together
		
//		synchronized (color) {
//		    int myColorInt = color.getRGB();
//		    String myColorName = color.getName();
//		} 
	}
}
