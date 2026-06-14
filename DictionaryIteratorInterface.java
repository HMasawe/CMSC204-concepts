package DICTIONARIES;

import java.util.Iterator;

public interface DictionaryIteratorInterface<K, V> {
	
	public Iterator<K> getKeyIterator();
	
	public Iterator<V> getValueIterator();
}
