package com.ati.jenkov.threadSafety;

public class Calculator {

	// It is important to remember, that even if an object is immutable and thereby thread safe, the
	// reference to this object may not be thread safe. Look at this example:

	// Therefore, even if the Calculator class uses an immutable object internally, it is not itself
	// immutable, and therefore not thread safe.

	private ImmutableValue currentValue = null;

	public ImmutableValue getValue() {
		return currentValue;
	}

	public void setValue(ImmutableValue newValue) {
		this.currentValue = newValue;
	}

	public void add(int newValue) {
		this.currentValue = this.currentValue.add(newValue);
	}

	// To make the Calculator class thread safe you could have declared the getValue(), setValue(),
	// and add() methods synchronized. That would have done the trick.
}
