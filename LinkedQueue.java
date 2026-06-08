package QUEUES;

public class LinkedQueue<T> implements QueueInterface<T> {
	
	// fields
	
	private Node head;
	private Node tail;
	
	// constructor
	
	public LinkedQueue() {
		this.head = null;
		this.tail = null;
		
	}
	
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			head = newNode;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;
		}
	}
	
	public T dequeue() {
		T front = getFront();
		head.setData(null);
		head = head.getNextNode();
		if (head == null) {
			tail = null;
		}
		return front;
		
	}
	
	public T getFront() {
		if (isEmpty()) {
			return null;
		} else {
			return head.getData();
		}
		
	}
	
	public boolean isEmpty() {
		return head == null && tail == null;
		
	}
	
	public void clear() {
		head = null;
		tail = null;
		
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
