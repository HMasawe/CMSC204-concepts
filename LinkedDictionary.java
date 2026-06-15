package DICTIONARIES;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDictionary<K, V> implements DictionaryWithIteratorInterface<K, V> {
	
	// fields
	private Node head;
	private int numberOfEntries;
	
	// constructor
	public LinkedDictionary() {
		this.head = null;
		this.numberOfEntries = 0;
	}
	
	public V add(K key, V value) {
		
		if (key == null || value == null) {
	        throw new IllegalArgumentException("Cannot add null.");
		}
	    V result = null;
	    Node currentNode = head;

	    // Search the entire chain for duplicate keys
	    while (currentNode != null && !key.equals(currentNode.getKey())) {
	        currentNode = currentNode.getNextNode();
	    }
	    
	    if (currentNode != null) {
	        // Key exists: replace old value
	        result = currentNode.getValue();
	        currentNode.setValue(value);
	    } else {
	        // Key is new: insert at the beginning
	        Node newNode = new Node(key, value);
	        newNode.setNextNode(head);
	        head = newNode;
	        numberOfEntries++;
	    }
	    return result;

		
	}
	
	public V remove(K key) {
		V result = null;
	    Node currentNode = head;
	    Node nodeBefore = null;

	    // Track both nodes to un-link the target
	    while (currentNode != null && !key.equals(currentNode.getKey())) {
	        nodeBefore = currentNode;
	        currentNode = currentNode.getNextNode();
	    }
	    
	    if (currentNode != null) {
	        result = currentNode.getValue();
	        if (nodeBefore == null) {
	            // Remove the first node
	            head = currentNode.getNextNode();
	        } else {
	            // Bypass the current node
	            nodeBefore.setNextNode(currentNode.getNextNode());
	        }
	        numberOfEntries--;
	    }
	    return result;
	    
	    /*
	    if (key == null) 
	    {
	        throw new IllegalArgumentException("Key cannot be null.");
	    }

	    V result = null;
	    Node currentNode = firstNode;
	    Node nodeBefore = null;

	    // Search the sorted chain while tracking the previous node
	    while ((currentNode != null) && key.compareTo(currentNode.getKey()) > 0) 
	    {
	        nodeBefore = currentNode;
	        currentNode = currentNode.getNextNode();
	    }
	    
	    if ((currentNode != null) && key.equals(currentNode.getKey())) 
	    {
	        result = currentNode.getValue(); // Get the value to return

	        if (nodeBefore == null) 
	        {
	            // Case 1: Removing the very first node
	            firstNode = currentNode.getNextNode();
	        } 
	        else 
	        {
	            // Case 2: Removing a node further down the chain
	            nodeBefore.setNextNode(currentNode.getNextNode());
	        }

	        numberOfEntries--; // Update dictionary size
	    }

	    return result;
	    */
	    
	    

	    
	    
		
	}
	
	public V getValue(K key) {
		V result = null;
	    Node currentNode = head;

	    // Search sequentially until found or chain ends
	    while (currentNode != null && !key.equals(currentNode.getKey())) {
	        currentNode = currentNode.getNextNode();
	    }

	    if (currentNode != null) {
	        result = currentNode.getValue();
	    }
	    return result;
	    
	    /*
	    if (key == null) 
	    {
	        throw new IllegalArgumentException("Key cannot be null.");
	    }
	    V result = null;
	    Node currentNode = firstNode;

	    // Search the sorted chain
	    while ((currentNode != null) && key.compareTo(currentNode.getKey()) > 0) 
	    {
	        currentNode = currentNode.getNextNode();
	    }

	    // Check if the key was found
	    if ((currentNode != null) && key.equals(currentNode.getKey())) 
	    {
	        result = currentNode.getValue();
	    }

	    return result;
	    */
		
	}
	
	public boolean contains(K key) {
		return false;
		
	}
	
	public boolean isEmpty() {
		return head == null;
		
	}
	
	public int getSize() {
		return numberOfEntries;
		
	}
	
	public void clear() {
		head = null;
		numberOfEntries = 0;
	}
	
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}
	
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}
	
	private class KeyIterator implements Iterator<K> {
		// fields
		private Node nextNode;
		
		// constructor
		private KeyIterator() {
			this.nextNode = head;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public K next() {
			K result;
			if (hasNext()) {
				result = nextNode.getKey();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
		}
	}
	
	private class ValueIterator implements Iterator<V> {
		// fields
		private Node nextNode;
		// constructor
		private ValueIterator() {
			this.nextNode = head;
		}
		
		public boolean hasNext() {
			return nextNode != null;
		}
		
		public V next() {
			V result;
			if (hasNext()) {
				result = nextNode.getValue();
				nextNode = nextNode.getNextNode();
			} else {
				throw new NoSuchElementException();
			}
			return result;
			
		}
	}
	
	private class Node {
		// fields
		private K key;
		private V value;
		private Node next;
		
		// constructors
		private Node(K key, V value) {
			this(key, value, null);
		}
		
		private Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		private K getKey() {
			return key;
		}
		
		private V getValue() {
			return value;
		}
		
		private Node getNextNode() {
			return next;
		}
		
		private void setValue(V value) {
			this.value = value;
		}
		
		private void setNextNode(Node next) {
			this.next = next;
		}
	}

}
