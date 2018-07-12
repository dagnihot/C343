/**
 * Tree interface and BinarySearchTree class from lec4b.
 *                          ^^^^^^
 * 
 * TODO: Pre-Lecture Exercise for lec5a.
 * Note: This is a team effort. Every member of your team is expected to 
 *       make non-trivial contributions towards your solution.
 * 
 * Make the following modifications:
 * (1) Add javadoc style comments to all methods.
 * (2) Implement BinarySearchTree.contains() so that it runs in O(h) time,
 *     where h is the height of the tree.
 * (3) Modify Tree and BinarySearchTree so that they are generic for any 
 *     Comparable type object.
 * (4) [challenge] Try to implement a sensible BinarySearchTree.toString()
 *      method. Recall that an inorder traversal of a BST yields a sorted 
 *      sequence.
 * (5) Test thoroughly in main(). Be sure to include tests on non-integer 
 *     data.
 * 
 * @author <insert your team name here (e.g., red1, red2, ..., green1, ...)>
 * @author <insert the name of your Scribe here>
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
   * 
   */
  public void insert(int key) {
    n++;
    root = insertHelper(key, root);
  }
  /**
   * 
   * @param key
   * @param p
   * @return
   */
  private Node insertHelper(int key, Node p) {
    if (p == null)
      return new Node(key);
    if (key < p.data)
      p.left = insertHelper(key, p.left);
    else
      p.right = insertHelper(key, p.right);
    return p;
  }
  
  /**
   * 
   */
  public boolean contains(int key) {
    return false;
  }
 /**
  *    
  */
  public int size() {
    return n;
  }
  
  public static void main(String... args) { 
    int[] a = new int[] { 3, 9, 7, 2, 1, 5, 6, 4, 8 };
    Tree bst = new BinarySearchTree();
    assert bst.isEmpty();
    for (int key : a)
      bst.insert(key);
    /**       3
     *      /   \
     *     2     9
     *    /     /
     *   1     7
     *       /   \
     *      5     8
     *     / \
     *    4   6
     */
    assert !bst.isEmpty();
    assert bst.size() == a.length;
    for (int key : a)
      assert bst.contains(key); 
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
