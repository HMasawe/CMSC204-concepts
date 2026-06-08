package BAGS;

public interface BagInterface<T> {
	
	public boolean add(T newEntry);
	
	public T remove();
	
	public boolean remove(T anEntry);
	
	public void clear();
	
	public boolean contains(T anEntry);
	
	public int getCurrentSize();
	
	public int getFrequencyOf(T anEntry);
	
	public boolean isEmpty();
	
	public T[] toArray();

}
