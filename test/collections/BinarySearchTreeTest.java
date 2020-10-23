package collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
	
	BinarySearchTree<Integer,String> bst;
	BinarySearchTree<Integer,Integer> bst2;
	
	void setUp1() {
		bst = new BinarySearchTree<>();
	}
	
	void setUp2() {
		bst = new BinarySearchTree<>();
		bst.add(7,"");
		bst.add(10,"");
		bst.add(15,"");
		bst.add(1,"");
		bst.add(2,"");
		bst.add(4,"");
		bst.add(8,"");
		bst.add(3,"");
	}

	void setUp3() {
		bst = new BinarySearchTree<>();
		bst.add(5,"");
		bst.add(3,"");
		bst.add(7,"");
		bst.add(2,"");
		bst.add(4,"");
		bst.add(6,"");
		bst.add(8,"");
	}
	
	void setUp4() {
		bst2 = new BinarySearchTree<>();
		bst2.add(5,5);
		bst2.add(3,3);
		bst2.add(7,7);
		bst2.add(2,2);
		bst2.add(4,4);
		bst2.add(6,6);
		bst2.add(8,8);
	}
	@Test
	void testAdd() {	
		setUp1();
		assertNull(bst.getRoot());
		bst.add(7,"");
		bst.add(10,"");
		bst.add(15,"");
		bst.add(1,"");
		bst.add(2,"");
		bst.add(4,"");
		bst.add(8,"");
		bst.add(3,"");		
		
		Node<Integer,String> root = bst.getRoot();
		
		assertEquals(root.getKey(),7);
		assertEquals(root.getRight().getKey(),10);
		assertEquals(root.getRight().getRight().getKey(),15);
		assertEquals(root.getLeft().getKey(),1);
		assertEquals(root.getLeft().getRight().getKey(),2);
		assertEquals(root.getLeft().getRight().getRight().getKey(),4);
		assertEquals(root.getRight().getLeft().getKey(),8);
		assertEquals(root.getLeft().getRight().getRight().getLeft().getKey(),3);	
		
		
	}
	
	@Test
	void testRemoveLeaves() {
		setUp2();		
		assertTrue(bst.remove(3));
		assertTrue(bst.remove(8));
		assertTrue(bst.remove(15));
		
		Node<Integer,String> root = bst.getRoot();
		
		assertEquals(7,root.getKey());
		assertEquals(10,root.getRight().getKey());
		assertNull(root.getRight().getLeft());
		assertNull(root.getRight().getRight());
		assertEquals(1,root.getLeft().getKey());
		assertEquals(2,root.getLeft().getRight().getKey());
		assertEquals(4,root.getLeft().getRight().getRight().getKey());
		assertNull(root.getLeft().getRight().getRight().getLeft());
	}
	
	@Test
	void testRemoveNodeWithOneChild() {
		setUp2();		
		assertTrue(bst.remove(4));
		assertTrue(bst.remove(1));
		assertTrue(bst.remove(15));
		assertTrue(bst.remove(10));
		
		Node<Integer,String> root = bst.getRoot();
		
		assertEquals(7,root.getKey());
		assertEquals(8,root.getRight().getKey());
		assertNull(root.getRight().getLeft());
		assertNull(root.getRight().getRight());
		assertEquals(2,root.getLeft().getKey());
		assertNull(root.getLeft().getLeft());		
		assertEquals(3,root.getLeft().getRight().getKey());
		assertNull(root.getLeft().getRight().getRight());
		assertNull(root.getLeft().getRight().getLeft());
	}
	
	@Test
	void testRemoveNodeWithTwoChildren() {
		setUp3();
		assertFalse(bst.remove(0));
		assertTrue(bst.remove(5));
		assertTrue(bst.remove(3));
		assertTrue(bst.remove(6));
		
		Node<Integer,String> root = bst.getRoot();
		
		assertEquals(7,root.getKey());
		assertEquals(8,root.getRight().getKey());
		assertNull(root.getRight().getLeft());
		assertNull(root.getRight().getRight());
		assertEquals(4,root.getLeft().getKey());
		assertNull(root.getLeft().getRight());	
		assertEquals(2,root.getLeft().getLeft().getKey());
		assertNull(root.getLeft().getLeft().getRight());		
	}	
	
	@Test
	void testSearch() {
		setUp4();
		assertEquals(5,bst2.search(5));
		assertEquals(3,bst2.search(3));
		assertEquals(7,bst2.search(7));
		assertEquals(2,bst2.search(2));
		assertEquals(4,bst2.search(4));
		assertEquals(6,bst2.search(6));
		assertEquals(8,bst2.search(8));
		
		assertNull(bst2.search(-5));
		assertNull(bst2.search(20));
		assertNull(bst2.search(-15));
	}
}
