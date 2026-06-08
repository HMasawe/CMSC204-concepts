package QUEUES;

public class ArrayDeque<T> implements DequeInterface<T> {
	
	// fields
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 1000;
	
	// constructors
	public ArrayDeque() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayDeque(int capacity) {
		checkCapacity(capacity);
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[capacity + 1];
		this.queue = temp;
		this.frontIndex = 0;
		this.backIndex = capacity;
		this.integrityOK = true;
	}
	
	public void addToFront(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		frontIndex = (frontIndex - 1 + queue.length) % queue.length;
		queue[frontIndex] = newEntry;
	}
	
	public void addToBack(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}
	
	public T removeFront() {
		T front = getFront();
		queue[frontIndex] = null;
		frontIndex = (frontIndex + 1) % queue.length;
		return front;
	}
	
	public T removeBack() {
		T back = getBack();
		queue[backIndex] = null;
		backIndex = (backIndex - 1 + queue.length) % queue.length;
		return back;
		
	}
	
	public T getFront() {
		checkIntegrity();
		return queue[frontIndex];
	}
	
	public T getBack() {
		checkIntegrity();
		return queue[backIndex];
		
	}
	
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % queue.length;
	}
	
	public void clear() {
		while (!isEmpty()) {
			removeFront();
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
		T[] oldQueue = queue;
		int oldSize = oldQueue.length;
		int newSize = 2 * oldSize;
		checkCapacity(newSize - 1);
		integrityOK = false;
		@SuppressWarnings("unchecked")
		T[] temp = (T[])new Object[newSize];
		queue = temp;
		for (int i = 0; i < oldSize - 1; i++) {
			queue[i] = oldQueue[frontIndex];
			frontIndex = (frontIndex + 1) % oldSize;
			
		}
		frontIndex = 0;
		backIndex = oldSize - 2;
		integrityOK = true;
	}

}
