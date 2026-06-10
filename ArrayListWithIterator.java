package ITERATORS;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListWithIterator<T> implements ListWithIteratorInterface<T> {
	
	// fields
	private T[] list;
	private int numberOfEntries;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 1000;
	
	// constructors
	public ArrayListWithIterator() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayListWithIterator(int capacity) {
		checkCapacity(capacity);
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[capacity];
		this.list = temp;
		this.numberOfEntries = 0;
		this.integrityOK = true;
	}
	
	public void add(T newEntry) {
		checkIntegrity();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		
	}
	
	public boolean add(int newPosition, T newEntry) {
		checkIntegrity();
		boolean result = false;
		if (newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			if (newPosition <= numberOfEntries) {
				makeRoom(newPosition);
				list[newPosition] = newEntry;
				numberOfEntries++;
				ensureCapacity();
				result = true;
			}
			
		} else {
			throw new IndexOutOfBoundsException("given position of add's new entry is out of bounds");
		}
		return result;
	}
	
	public T remove(int givenPosition) {
		checkIntegrity();
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			T result = list[givenPosition];
			if (givenPosition < numberOfEntries) {
				removeGap(givenPosition);
				numberOfEntries--;
			}
			return result;
			
		} else {
			throw new IndexOutOfBoundsException("illegal postion given to a remove operation.");
		}
		
	}
	
	public void clear() {
		int pos = numberOfEntries;
		while (pos > 0) {
			remove(pos);
			pos--;
		}
	}
	
	public boolean replace(int givenPosition, T newEntry) {
		boolean result = true;
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			list[givenPosition - 1] = newEntry;
		} else {
			result = false;
		}
		return result;
	}
	
	public T getEntry(int givenPosition) {
		T result = null;
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			result = list[givenPosition - 1];
			
		}
		return result;
	}
	
	public T[] toArray() {
		checkIntegrity();
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for (int i = 0; i < numberOfEntries; i++) {
			result[i] = list[i + 1];
		}
		return result;
	}
	
	public boolean contains(T anEntry) {
		checkIntegrity();
		boolean found = false;
		int index = 1;
		while (!found && index <= numberOfEntries) {
			if (anEntry.equals(list[index])) {
				found = true;
			}
			index++;
		}
		return found;
	}
	
	public int getLength() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	public Iterator<T> getIterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		// fields
		private int nextIndex;
		private boolean wasNextCalled;
		
		// Constructor
		private ArrayListIterator() {
			this.nextIndex = 1;
			this.wasNextCalled = false;
		}

		@Override
		public boolean hasNext() {
			return nextIndex <= numberOfEntries;
		}

		@Override
		public T next() {
			checkIntegrity();
			if (hasNext()) {
				wasNextCalled = true;
				T nextEntry = list[nextIndex];
				nextIndex++;
				return nextEntry;
			} else {
				throw new NoSuchElementException("illegal to call next. iterator is after and of list");
			}
		}
		
		public void remove() {
			if (wasNextCalled) {
				ArrayListWithIterator.this.remove(nextIndex - 1);
				nextIndex--;
				wasNextCalled = false;
			} else {
				throw new IllegalStateException("illegal to call remove(). next() was not called");
			}
		}
		
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
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			list = Arrays.copyOf(list, newCapacity + 1);
		}
	}
	
	private void makeRoom(int position) {
		
		// assert (givenPosition >= 1) && (givenPosition <= numberOfEntries + 1);
	    // assert numberOfEntries < list.length;
		for (int index = numberOfEntries; index >= position; index--) {
	        list[index + 1] = list[index];
	    }
	}
	
	private void removeGap(int position) {
		for (int index = position; index < numberOfEntries; index++) {
			list[index] = list[index + 1];
		}
	}
}
