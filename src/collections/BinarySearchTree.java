package collections;

public class BinarySearchTree<K extends Comparable<K>,V> implements Tree<K,V>{
	private Node<K,V> root;
	private int weight;

	public BinarySearchTree(){
		weight = 0;
	}		
	
	public boolean add(K key, V value) {
		if(root != null) {
			return addRecursive(key,value,root);
		}
		else {
			root = new Node<K,V>(key,value);
			weight++;
			return true;
		}				
	}
	
	private boolean addRecursive(K key, V value, Node<K,V> currentNode){
		if(key.compareTo(currentNode.getKey()) > 0) {
			Node<K,V> right = currentNode.getRight();
			if(right != null) {
				return addRecursive(key,value,right);
			}
			else {
				currentNode.setRight(new Node<K,V>(key,value));
				weight++;
				return true;
			}
		}
		else if(key.compareTo(currentNode.getKey()) < 0){
			Node<K,V> left = currentNode.getLeft();
			if(left != null) {
				return addRecursive(key,value,left);
			}
			else {
				currentNode.setLeft(new Node<K,V>(key,value));
				weight++;
				return true;
			}
		}	
		else {
			return false;
		}
	}

	@Override
	public boolean update(K key, V value) {
		Node<K,V> nodeToUpdate = search(key);
		
		if(nodeToUpdate != null) {
			nodeToUpdate.setValue(value);
			return true;
		}
		else {
			return false;
		}		
	}

	@Override
	public Node<K,V> remove(K key) {
		return null;
		/*Node<K,V> nodeToRemove = search(key);
		
		if(nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
			nodeToRemove.getFather().set
		}
		else if(nodeToRemove.getLeft() != null && nodeToRemove.getRight() != null) {
			
		}		
		else {
			
		}		
		
		
		return nodeToRemove;*/
	}
	
	private Node<K,V> getMax(Node<K,V> node) {
		while(node.getRight() != null) {
			node = node.getRight();
		}
		return node;	
	}
	
	private Node<K,V> getMin(Node<K,V> node){
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;	
	}
	

	@Override
	public Node<K,V> search(K key) {		
		if(root != null) {
			return searchRecursive(key,root);
		}
		else {
			return null;
		}		
	}
	
	private Node<K,V> searchRecursive(K key, Node<K,V> currentNode){		
		if(key.compareTo(currentNode.getKey()) == 0) {
			return currentNode;
		}
		else if(key.compareTo(currentNode.getKey()) >  0) {
			return searchRecursive(key, currentNode.getRight());
		}
		else {
			return searchRecursive(key, currentNode.getLeft());
		}		
	}
	

	public int getWeight() {
		return weight;
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void preOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postOrder() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean treeIsEmpty() {		
		return root == null;
	}
	

}
