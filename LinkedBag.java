package BAGS;

public class LinkedBag<T> implements BagInterface<T> {
	
	// fields
	private Node firstNode;
	private int numberOfEntries;
	
	// contructors
	public LinkedBag() {
		this.firstNode = null;
		this.numberOfEntries = 0;
	}
	
	
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.setNextNode(firstNode);
		firstNode = newNode;
		numberOfEntries++;
		return true;
		
	}
	
	public T remove() {
		T result = null;
		if (!isEmpty()) {
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numberOfEntries--;
		}
		return result;
		
	}
	
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		if (nodeN != null) {
			nodeN.setData(firstNode.getData());
			remove();
		}
		return result;
		
	}
	
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}
	
	public boolean contains(T anEntry) {
		return getReferenceTo(anEntry) != null;
		
	}
	
	public int getCurrentSize() {
		return numberOfEntries;
		
	}
	
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int loopCounter = 0;
		Node currentNode = firstNode;
		while (loopCounter < numberOfEntries && currentNode != null) {
			if (anEntry.equals(currentNode.getData())) {
				frequency++;
			}
			loopCounter++;
			currentNode = currentNode.getNextNode();
		}
		return frequency;
		
	}
	
	public boolean isEmpty() {
		return firstNode == null;
		
	}
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		Node currentNode = firstNode;
		int index = 0;
		while (index < numberOfEntries && currentNode != null) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
		}
		return result;
		
	}
	
	private Node getReferenceTo(T entry) {
		Node currentNode = firstNode;
		boolean found = false;
		while (!found && currentNode != null) {
			if (entry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return currentNode;
	}
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T data) {
			this(data, null);
		}
		
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		public T getData() {
			return data;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public void setNextNode(Node next) {
			this.next = next;
		}
		
		public void setData(T data) {
			this.data = data;
		}
		
	}
	
	

}
