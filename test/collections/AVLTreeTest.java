package collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	AVLTree<Integer,String> avlt;
	
	void setUp1() {
		avlt = new AVLTree<>();
	}
	
	void setUp2() {
		avlt = new AVLTree<>();
		avlt.add(7,"");
		avlt.add(10,"");
		avlt.add(15,"");
		avlt.add(1,"");
		avlt.add(2,"");
		avlt.add(4,"");	
		avlt.add(8,"");
		
	}

	void setUp3() {
		avlt = new AVLTree<>();
		avlt.add(5,"");
		avlt.add(3,"");
		avlt.add(7,"");
		avlt.add(2,"");
		avlt.add(4,"");
		avlt.add(6,"");
		avlt.add(8,"");
	}
	@Test
	void testAdd() {	
		setUp1();
		assertNull(avlt.getRoot());
		avlt.add(7,"");		
		avlt.add(10,"");		
		avlt.add(15,"");		
		avlt.add(1,"");
        avlt.add(2,"");		
		avlt.add(4,"");
		avlt.add(8,"");
		
		AVLTreeNode<Integer,String> root = (AVLTreeNode<Integer,String>)avlt.getRoot();
		AVLTreeNode<Integer,String> currentNode = root;
		
		
		assertEquals(7, currentNode.getKey());
		currentNode = (AVLTreeNode<Integer, String>) currentNode.getLeft();
		assertEquals(2, currentNode.getKey());
		assertEquals(1, currentNode.getLeft().getKey());
		assertEquals(4, currentNode.getRight().getKey());
		currentNode = (AVLTreeNode<Integer, String>) root.getRight();
		assertEquals(10, currentNode.getKey());
		assertEquals(8, currentNode.getLeft().getKey());
		assertEquals(15, currentNode.getRight().getKey());		
				
	}
	
	@Test
	void testRemove1() {
		setUp2();		
		assertTrue(avlt.remove(15));		
		assertTrue(avlt.remove(7));
		assertTrue(avlt.remove(2));
		assertTrue(avlt.remove(8));
		
		Node<Integer,String> root = avlt.getRoot();
		
		assertEquals(4,root.getKey());
		assertEquals(10,root.getRight().getKey());
		assertNull(root.getRight().getLeft());
		assertNull(root.getRight().getRight());
		assertEquals(1,root.getLeft().getKey());
		assertNull(root.getLeft().getLeft());
		assertNull(root.getLeft().getRight());
	}
	
	@Test
	void testRemove2() {
		setUp3();		
		assertTrue(avlt.remove(5));	
		assertTrue(avlt.remove(6));
		assertTrue(avlt.remove(3));
		assertTrue(avlt.remove(8));
		assertTrue(avlt.remove(2));	
		assertTrue(avlt.remove(4));
		assertTrue(avlt.remove(7));
		assertFalse(avlt.remove(0));
		Node<Integer,String> root = avlt.getRoot();
		assertNull(root);
		
		
	}	

}
