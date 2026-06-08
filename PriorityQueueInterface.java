package QUEUES;

public interface PriorityQueueInterface<T extends Comparable<? super T>> {
	
	public void add(T newEntry);
	
	public T remove();
	
	public T peek();
	
	public boolean isEmpty();
	
	public int size();
	
	public void clear();

}
