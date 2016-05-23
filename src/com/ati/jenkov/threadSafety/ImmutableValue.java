package com.ati.jenkov.threadSafety;

public class ImmutableValue {

	private final int value;

	public ImmutableValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	// If you need to perform operations on the ImmutableValue instance you can do so by returning a
	// new instance with the value resulting from the operation.

	// Notice how the add() method returns a new ImmutableValue instance with the result of the add
	// operation, rather than adding the value to itself.
	//If so then one thread may have different copy of value then other.(not expected)
	public ImmutableValue add(int valueToAdd) {
		return new ImmutableValue(this.value + valueToAdd);
	}

}
