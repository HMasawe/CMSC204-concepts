package ITERATORS;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {
	
	// fields
	private Node head;
	private Node tail;
	private int numberOfEntries;
	
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;
		}
		numberOfEntries++;
		// OR add(numberOfEntries + 1, newEntry);
	}
	
	public boolean add(int newPosition, T newEntry) {
		boolean result = true;
		if (newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			Node newNode = new Node(newEntry);
			if (isEmpty()) {
				head = newNode;
				tail = newNode;
			} else if (newPosition == 1) {
				newNode.setNextNode(head);
				head = newNode;
			} else if (newPosition == numberOfEntries + 1) {
				tail.setNextNode(newNode);
				tail = newNode;
			} else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
		return result;
	}
	
	public T remove(int givenPosition) {
		
		T result = null;
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			if (givenPosition == 1) {
				result = head.getData();
				head = head.getNextNode();
				if (numberOfEntries == 1) {
					tail = null;
				}
			} else {
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
				result = nodeToRemove.getData();
				if (givenPosition == numberOfEntries) {
					tail = nodeBefore;
				}
			}
			numberOfEntries--;
			return result;
		} else {
			throw new IndexOutOfBoundsException("illegal postion given to a remove operation.");
		}
		
	}
	
	public void clear() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}
	
	public boolean replace(int givenPosition, T newEntry) {
		boolean result = false;
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
			result = true;
		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}
	
	public T getEntry(int givenPosition) {
		T result = null;
		if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
			result = getNodeAt(givenPosition).getData();
		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		Node currentNode = head;
		int index = 0;
		while (index < numberOfEntries && currentNode != null) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}
	
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = head;
		while (!found && currentNode != null) {
			if (anEntry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}
	
	public int getLength() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		boolean result = false;
		if (numberOfEntries == 0) {
			result = true;
		}
		return result;
	}
	
	public Iterator<T> getIterator() {
		return new LinkedListIterator();
		
	}
	
	private Node getNodeAt(int givenPosition) {
		Node currentNode = head;
		
		for (int i = 1; i < givenPosition; i++) {
			currentNode = currentNode.getNextNode();
		}
		
		return currentNode;
		
	}
	
	private class LinkedListIterator implements Iterator<T> {
		// fields
		private Node nextNode;
		
		// constructor
		private LinkedListIterator() {
			this.nextNode = head;
		}
		
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				Node returnNode = nextNode;
				nextNode = nextNode.getNextNode();
				return returnNode.getData();
			} else {
				throw new NoSuchElementException("illegal to call next. iterator is after and of list");
			}
		}
		
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
