package collections;

public class AVLTree<K extends Comparable<K>,V> extends BinarySearchTree<K,V> implements BinarySearchTreeInterface<K,V>{
		
	public AVLTree() {
		super();
	}
	
	public boolean add(K key, V value) {
		Node<K,V> addedNode = super.addBase(key, value);		
		
		if(addedNode != null) {
			//Replaces Node with AVLTreeNode
			AVLTreeNode<K,V> replacementNode = new AVLTreeNode<>(addedNode.getKey(), addedNode.getValue());
			
			if(addedNode == root) {
				root = replacementNode;			
			}
			else {
				Node<K,V> left = addedNode.getLeft();
				Node<K,V> right = addedNode.getRight();
				Node<K,V> parent = addedNode.getParent();
							
				replacementNode.setLeft(left);
				replacementNode.setRight(right);
				replacementNode.setParent(parent);
				
				if(right != null) {
					right.setParent(replacementNode);
				}
				
				if(left != null) {
					left.setParent(replacementNode);
				}
				
				if(parent.getLeft() == addedNode) {
					parent.setLeft(replacementNode);
				}
				else {
					parent.setRight(replacementNode);
				}
			}	
			
			AVLTreeNode<K,V> prevPrevCurrentNode = null;
			AVLTreeNode<K,V> prevCurrentNode = replacementNode;
			AVLTreeNode<K,V> currentNode = (AVLTreeNode<K,V>)replacementNode.getParent();
			
			boolean balance = false;
			
			while(!balance && currentNode != null) {
				currentNode.update();		
				
				if(Math.abs(currentNode.getBalanceFactor()) > 1) {
					balance = true;
				}
				else {
					prevPrevCurrentNode = prevCurrentNode;
					prevCurrentNode = currentNode;
					currentNode = (AVLTreeNode<K,V>)currentNode.getParent();
				}			
			}
			
			if(balance) {
				if(currentNode.getLeft() == prevCurrentNode) {
					
					if(prevCurrentNode.getRight() != null && prevCurrentNode.getRight() == prevPrevCurrentNode) {
						rotateLeft(prevCurrentNode);
					}			
					
					currentNode = rotateRight(currentNode);
				}
				else {
					
					if(prevCurrentNode.getLeft() != null && prevCurrentNode.getLeft() == prevPrevCurrentNode) {
						rotateRight(prevCurrentNode);
					}
					
					currentNode = rotateLeft(currentNode);
				}				
			}
			
			
			while(currentNode != null) {
				currentNode.update();
				currentNode = (AVLTreeNode<K,V>)currentNode.getParent();
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remove(K key) {
		boolean removed = super.remove(key);
		
		if(removed) {
			//balance tree, only nodes that changed
		}
			
		return removed;
	}
	
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns previous left
	private AVLTreeNode<K,V> rotateRight(AVLTreeNode<K,V> node) {
		if(node.getLeft() == null) {
			return node;
		}
		else {
			AVLTreeNode<K,V> left = (AVLTreeNode<K,V>)node.getLeft();
			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();
			
			//Updates node parent
			if(node != root) {
				if(parent.getLeft() == node) {
						parent.setLeft(left);
				}
				else {
					parent.setRight(left);
				}	
			}
			else {
				//Updates root
				root = left;
			}
			
			//Updates Node			
			node.setParent(left);
			node.setLeft(left.getRight());
			
			//Update left
			if(left.getRight() != null) {
				left.getRight().setParent(node);
			}
			left.setParent(parent);	//Sets null if node is root	
			left.setRight(node);
			
			//Update nodes heights and balance factors
			node.update();
			left.update();
			
			if(parent != null) {
				parent.update();	
			}
			
			return left;
		}		
	}
	//This method does not fix the balance factor of nodes that are not modified here
	//Returns previous right
	private AVLTreeNode<K,V> rotateLeft(AVLTreeNode<K,V> node) {
		if(node.getRight() == null) {
			return node;
		}
		else {
			AVLTreeNode<K,V> right = (AVLTreeNode<K,V>)node.getRight();
			AVLTreeNode<K,V> parent = (AVLTreeNode<K,V>)node.getParent();
			
			//Updates node parent
			if(node != root) {
				if(parent.getRight() == node) {
						parent.setLeft(right);
				}
				else {
					parent.setRight(right);
				}	
			}
			else {
				//Updates root
				root = right;
			}
			
			//Updates Node			
			node.setParent(right);
			node.setLeft(right.getLeft());
			
			//Update left
			if(right.getLeft() != null) {
				right.getLeft().setParent(node);
			}
			right.setParent(parent);	//Sets null if node is root	
			right.setLeft(node);
			
			//Update nodes heights and balance factors
			node.update();
			right.update();
			
			if(parent != null) {
				parent.update();	
			}
			
			return right;
		}	
	}	
}
