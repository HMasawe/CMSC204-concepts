package STACKS;

public class LinkedStack<T> implements StackInterface<T> {
	
	// fields
	private Node topNode;
	
	// contructor
	public LinkedStack() {
		this.topNode = null;
	}
	
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}
	
	public T pop() {
		T top = peek();
		topNode = topNode.getNextNode();
		return top;
	}
	
	public T peek() {
		if (isEmpty()) {
			return null; // or throw exception
		} else {
			return topNode.getData();
		}
	}
	
	public boolean isEmpty() {
		return topNode == null;
		
	}
	
	public void clear() {
		topNode = null;
		// while (!isEmpty()) pop();
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
