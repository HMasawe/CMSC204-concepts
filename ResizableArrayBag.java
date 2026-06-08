package BAGS;

import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
	
	// fields
	private T[] bag;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 1000;
	private boolean integrityOK = false;
	
	// Constructors
	
	public ResizableArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	public ResizableArrayBag(int capacity) {
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[capacity];
		this.bag = temp;
		this.numberOfEntries = 0;
		this.integrityOK = true;
	}
	@Override
	public boolean add(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		bag[numberOfEntries] = newEntry;
		numberOfEntries++;
		return true;
	}

	@Override
	public T remove() {
		checkIntegrity();
		T result = null;
		if (!isEmpty()) {
			result = bag[numberOfEntries - 1];
			bag[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return result;
	}

	@Override
	public boolean remove(T anEntry) {
		checkIntegrity();
		boolean found = false;
		int index = 0;
		while (!found && index < numberOfEntries) {
			if (anEntry.equals(bag[index])) {
				found = true;
				bag[index] = bag[numberOfEntries - 1];
				bag[numberOfEntries - 1] = null;
				numberOfEntries--;
			} else {
				index++;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		while(!isEmpty()) {
			remove();
		}
	}

	@Override
	public boolean contains(T anEntry) {
		checkIntegrity();
		boolean found = false;
		int index = 0;
		while (!found && index < numberOfEntries) {
			if (anEntry.equals(bag[index])) {
				found = true;
			} else {
				index++;
			}
		}
		return found;
		// OR return getFrequencyOf(anEntry) > 0;
	}

	@Override
	public int getCurrentSize() {
		return numberOfEntries;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		checkIntegrity();
		int count = 0;
		for (int i = 0; i < numberOfEntries; i++) {
			if (anEntry.equals(bag[i])) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		
		return numberOfEntries == 0;
	}

	@Override
	public T[] toArray() {
		checkIntegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = bag[index];
		}
		return result;
	}
	
	private void checkIntegrity() {
		if (!integrityOK) {
			throw new SecurityException("ResizableArrayBag object is corrupt");
		}
	}
	
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
		}
	}
	
	private void ensureCapacity() {
		if (numberOfEntries == bag.length) {
			int newSize = 2 * bag.length;
			checkCapacity(newSize);
			bag = Arrays.copyOf(bag, newSize);
		}
	}

}
