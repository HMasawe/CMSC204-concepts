package QUEUES;

public class LinkedDeque<T> implements DequeInterface<T> {
	
	// fields
	private Node head;
	private Node tail;
	
	// constructors
	public LinkedDeque() {
		this.head = null;
		this.tail = null;
	}
	
	public void addToFront(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.setNextNode(head);
		if (isEmpty()) {
			tail = newNode;
		} else {
			head.setPreviousNode(newNode);
			head = newNode;
		}
	}
	
	public void addToBack(T newEntry) {
		Node newNode = new Node(newEntry);
		newNode.setPreviousNode(tail);
		if (isEmpty()) {
			head = newNode;
		} else {
			tail.setNextNode(newNode);
			tail = newNode;
		}
	}
	
	public T removeFront() {
		T front = null;
		if (!isEmpty()) {
			front = head.getData();
			head = head.getNextNode();
			if (head == null) {
				tail = null;
			} else {
				head.setPreviousNode(null);
			}
		}
		return front;
		
	}
	
	public T removeBack() {
		T back = null;
		if (!isEmpty()) {
			back = tail.getData();
			tail = tail.getPreviousNode();
			if (tail == null) {
				head = null;
			} else {
				tail.setNextNode(null);
			}
		}
		return back;
		
	}
	
	public T getFront() {
		return head.getData();
		
	}
	
	public T getBack() {
		return tail.getData();
		
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
		private Node previous;
		
		public Node(T data) {
			this(null, data, null);
		}
		
		public Node(Node previous, T data, Node next) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
		
		public T getData() {
			return data;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public Node getPreviousNode() {
			return previous;
		}
		
		public void setNextNode(Node next) {
			this.next = next;
		}
		
		public void setPreviousNode(Node previous) {
			this.previous = previous;
		}
		
		public void setData(T data) {
			this.data = data;
		}
		
	}
}
