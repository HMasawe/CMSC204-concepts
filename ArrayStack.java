package STACKS;

import java.util.Arrays;

public class ArrayStack<T> implements StackInterface<T> {
	// fields
	private T[] stack;
	private int topIndex;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 1000;
	
	// constructors
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayStack(int capacity) {
		checkCapacity(capacity);
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[capacity];
		this.stack = temp;
		this.topIndex = -1;
		this.integrityOK = true;
	}
	
	
	public void push(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}
	
	public T pop() {
		checkIntegrity();
		if (isEmpty()) {
			return null;
		} else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
		
	}
	
	public T peek() {
		checkIntegrity();
		if (isEmpty()) {
			return null;
		} else {
			return stack[topIndex];
		}
	}
	
	public boolean isEmpty() {
		return topIndex < 0;
	}
	
	public void clear() {
		while (topIndex > -1) {
			stack[topIndex] = null;
			topIndex--;
		}
	}
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a stack whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
		}
	}
	
	private void checkIntegrity() {
		if (!integrityOK) {
			throw new SecurityException("ArrayBag object is corrupt");
		}
	}
	
	private void ensureCapacity() {
		if (topIndex == stack.length - 1) {
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}
	
	
}
