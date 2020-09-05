package collections;

public interface Tree<K extends Comparable<K>,V> {
	public boolean add(K key, V value);
	public boolean update(K key, V value);
	public Node<K,V> remove(K key);
	public Node<K,V> search(K key);
	public int getWeight();
	public int getDepth();
	public void preOrder();
	public void inOrder();
	public void postOrder();
	public boolean treeIsEmpty();		
}
