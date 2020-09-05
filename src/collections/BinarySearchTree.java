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
		if(root != null) {
			return removeRecursive(key,root,null);
		}
		else {
			return null;
		}
	}
	
	private Node<K,V> removeRecursive(K key, Node<K,V> currentNode, Node<K,V> parent){
		if(key.compareTo(currentNode.getKey()) == 0) {
			if(currentNode.getLeft() == null && currentNode.getRight() == null) {
				if(parent.getRight() == currentNode) {
					parent.setRight(null);
				}
				else {
					parent.setLeft(null);
				}
			}
			else if(currentNode.getLeft() != null && currentNode.getRight() == null) {
				currentNode.getLeft().setFather(parent);
				if(parent.getRight() == currentNode) {					
					parent.setRight(currentNode.getLeft());
				}
				else {
					parent.setLeft(currentNode.getLeft());
				}
			}
			else if(currentNode.getLeft() == null && currentNode.getRight() != null) {
				currentNode.getRight().setFather(parent);
				if(parent.getRight() == currentNode) {					
					parent.setRight(currentNode.getLeft());
				}
				else {
					parent.setLeft(currentNode.getLeft());
				}
			}
			else {
				Node<K,V> toReplace = getMin(currentNode.getRight());
				
				K keySave = currentNode.getKey();
				V valueSave = currentNode.getValue();
				
				currentNode.setKey(toReplace.getKey());
				currentNode.setValue(toReplace.getValue());
				
				removeRecursive(toReplace.getKey(),currentNode.getRight(),currentNode);
				
				currentNode = new Node<K,V>(keySave, valueSave); //So that returned node does not have relations in the list but has the attributes of the "deleted" node.
			}
			return currentNode;
		}
		else if(key.compareTo(currentNode.getKey()) >  0) {
			return removeRecursive(key, currentNode.getRight(),currentNode);
		}
		else {
			return removeRecursive(key, currentNode.getLeft(),currentNode);
		}	
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
