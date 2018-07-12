/**
 * Starter code for lab5. This is an implementation of BinarySearchTree 
 * for int data.
 * 
 * Implement the remove() method using the algorithm described by your AI.
 *
 * @author <Dhruv Agnihotri>
 */

public class BinarySearchTree implements Tree {

  class Node {
    int data;
    Node left, right;

    Node(int key) {
      this(key, null, null);
    }

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    boolean isLeaf() {
      return left == null && right == null;
    }
  }

  Node root;
  int n;

  /**
   * TODO
   * 
   * Removes the key from this tree. Must run in O(h) time, where h
   * is the height of the tree.
   */
  public void remove(int key) {

	    if(!this.contains(key))
	    return; 
	    n--;
	    root = removeHelper(key, root);
	  }

	  private Node removeHelper(int key, Node p) {
	  if(p.data > key)
		  p.left = removeHelper(key, p.left);
	  
	  else if(p.data < key)
		  p.right = removeHelper(key, p.right);
	  
	  else { 

	  if(p.isLeaf()) 
		  	p = null;

	  else if(p.right == null) 
		  p = p.left;

	  else if(p.left == null) 
		  p = p.right;

	  else { 
		  int smallestLeaf = getSmallestLeaf(p.right); 
		  p.right = removeHelper(smallestLeaf, p.right); 
		  p.data = smallestLeaf;
	  }

	  }

	  return p; 

	  }

	  private int getSmallestLeaf(Node p) {
	  if(p.left == null)
		  return p.data;
	  else
		  return getSmallestLeaf(p.left);

	  }
  
  /**
   * Inserts the key into this tree. Runs in O(h) time, where h is
   * the height of the tree.
   */
  public void insert(int key) {
    n++;
    root = insertHelper(key, root);
  }

  private Node insertHelper(int key, Node p) {
    if (p == null) 
      p = new Node(key);
    else if (key < p.data)
      p.left = insertHelper(key, p.left);
    else 
      // if keys are unique, it must be the case that key > p.data
      p.right = insertHelper(key, p.right);
    return p;
  }

  /**
   * Returns true iff key is in this tree. Runs in O(h) time, where h is
   * the height of the tree.
   */
  public boolean contains(int key) {
    return containsHelper(key, root);
  }

  private boolean containsHelper(int key, Node p) {
    if (p == null)
      return false;
    if (key == p.data)
      return true;
    if (key < p.data)
      return containsHelper(key, p.left);
    return containsHelper(key, p.right);
  }

  /**
   * Returns the number of keys in this tree.
   */
  public int size() {
    return n;
  }

  /**
   * Testing.
   */
  public static void main(String... args) {
    int[] a = new int[] { 3, 9, 7, 2, 1, 5, 6, 4, 8 };
    int[] b = new int[] { 1, 6, 4, 5, 8, 9, 7, 2 };
    Tree bst = new BinarySearchTree();
    assert bst.isEmpty();
    for (int key : a)
      bst.insert(key);
    assert bst.size() == a.length;
    for (int key : a)
      assert bst.contains(key);
    bst.remove(3);
    for (int key : b)
      assert bst.contains(key);
    assert !bst.contains(3);
    int n = bst.size();
    for (int key : b) {
      assert bst.contains(key);
      bst.remove(key);
      assert !bst.contains(key);
      n--;
      assert n == bst.size();
    }
    assert bst.isEmpty();
    System.out.println("Passed all the basic tests...");

    /**
     * TODO: As a challenge, arrange things so that attempts to remove
     * key that are not in the tree are simply ignored (and do no harm).
     */
    for (int key : a)
      bst.insert(key);
    n = bst.size();
    for (int key : a) {
      bst.remove(-key);
      assert n == bst.size();
    }
    System.out.println("Passed challenge tests...");
  }
}

interface Tree {
  void insert(int key);
  default void remove(int key) {
    throw new UnsupportedOperationException();
  }
  boolean contains(int key);
  int size();
  default boolean isEmpty() {
    return size() == 0;
  }
}

