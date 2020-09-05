package collections;

public class Node<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private Node<K,V> right;
	private Node<K,V> left;
	private Node<K,V> father;
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() { return key;	}
	public V getValue() { return value;	}
	public Node<K,V> getRight() { return right; }
	public Node<K,V> getLeft() { return left; }
	public Node<K,V> getFather(){ return father; }
	
	public void setKey(K key) { this.key = key; }
	public void setValue(V value) { this.value = value; }
	public void setRight(Node<K, V> right) { this.right = right; }
	public void setLeft(Node<K, V> left) { this.left = left; }	
	public void setFather(Node<K, V> father) { this.father = father; }	
}
