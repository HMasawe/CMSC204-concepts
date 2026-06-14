package DICTIONARIES;

import java.util.Arrays;

public class ArrayDictionary<K, V> implements DictionaryInterface<K, V> {
	
	// fields
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 1000;
	
	public ArrayDictionary() {
		this(DEFAULT_CAPACITY);
	}
	// constructors
	public ArrayDictionary(int capacity) {
		checkCapacity(capacity);
		@SuppressWarnings("unchecked")
		Entry<K, V>[] temp = (Entry<K, V>[])new Entry[capacity];
		this.dictionary = temp;
		this.numberOfEntries = 0;
		this.integrityOK = true;
	}
	
	public V add(K key, V value) {
		checkIntegrity();
		if (key == null || value == null) {
			throw new IllegalArgumentException();
		} else {
			V result = null;
			int keyIndex = locateIndex(key);
			if (keyIndex < numberOfEntries) { // AND key.equals(dictionary[keyIndex].getKey())
				result = dictionary[keyIndex].getValue();
				dictionary[keyIndex].setValue(value);
			} else {
				/*
				 * makeRoom(keyIndex)
				 * dictionary[keyIndex] = new Entry<>(key, value);
				 */
				dictionary[numberOfEntries] = new Entry<>(key, value);
				numberOfEntries++;
				ensureCapacity();
			}
			return result;
		}
		
		
	}
	
	public V remove(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries) { // AND key.equals(dictionary[keyIndex].getKey())
			result = dictionary[keyIndex].getValue();
			//removeGap(keyIndex);
			numberOfEntries--;
			
		}
		return result;
		
		
		
	}
	
	public V getValue(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
		if (keyIndex < numberOfEntries && key.equals(dictionary[keyIndex].getKey())) {
	        result = dictionary[keyIndex].getValue();
	    }
		return result;
		
	}
	
	public boolean contains(K key) {
		return getValue(key) != null;
		
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	public int getSize() {
		return numberOfEntries;
	}
	
	public void clear() {
		checkIntegrity();
		int index = numberOfEntries;
		while (!isEmpty()) {
			dictionary[index] = null;
			index--;
		}
	}
	
	private int locateIndex(K key) {
		int index = 0;
		while (index < numberOfEntries && !key.equals(dictionary[index].getKey())) {
			index++;
		}
		return index;
	}
	
 	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
		}
	}
 	
 	private void makeRoom(int keyIndex) {
 		for (int index = numberOfEntries - 1; index >= keyIndex; index--) {
 	        dictionary[index + 1] = dictionary[index];
 	    }
 	}
 	
 	private void removeGap(int keyIndex) {
 		for (int i = keyIndex; i < numberOfEntries - 1; i++) {
 			dictionary[i] = dictionary[i - 1];
 		}
 		
 		dictionary[numberOfEntries - 1] = null;
 	}
	
 	private void checkIntegrity() {
		if (!integrityOK) {
			throw new SecurityException("ResizableArrayBag object is corrupt");
		}
	}
	private void ensureCapacity() {
		int size = getSize();
		if (size >= dictionary.length); {
			int newSize = 2 * size;
			checkCapacity(newSize);
			dictionary = Arrays.copyOf(dictionary, newSize);
		}
	}
  	private class Entry<K, V> {
		// fields
		private K key;
		private V value;
		
		// Constructor
		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		private K getKey() {
			return key;
		}
		
		private V getValue() {
			return value;
		}
		
		private void setValue(V value) {
			this.value = value;
		}
	}

}
