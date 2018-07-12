import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class BinaryTree implements Tree {
  
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
   * Problem 5
   * @param root
   */
  public static void pruneLeaves(Node root){
	  if (root != null){
		  pruneHelper(root.left);
		  pruneHelper(root.right);
	  }
  }
 
  private static void pruneHelper (Node node){
	  if (node == null){
		  return;
	  }
	  if (node.left == null && node.right == null){
		  node = null;
	  }
	  pruneHelper(node.left);
	  pruneHelper(node.right);
	  
  }
  /**
   * Problem 6
   * Used Array List
   */
  ArrayList<Integer>swag = new ArrayList<>();
  
  public List levelOrder(){
	  int h = height(root);
	  for (int i = 1; i <= h; i++){
		  levelHelper(root, i);
	  }
	  return swag;
  }
 
  
  public List levelHelper (Node root, int level){
	  if (root == null){
		  return swag;
	  }
	  if (level == 1){
		  swag.add(root.data);
	  }
	  else if (level > 1){
		  levelHelper(root.left, level - 1);
		  levelHelper(root.right, level - 1);
	  }
	  return swag;
  }
  /**
   * inserts a new node to the right or left
   *@param (int key)
   */
  
  public void insert(int key) {
    n++;
    if (root == null)
      root = new Node(key);
    else if (root.left == null)
      root.left = new Node(key);
    else if (root.right == null)
      root.right = new Node(key);
    else if (Math.random() < 0.5)
      root = new Node(key, root, null);
    else
      root = new Node(key, null, root);
  }
 
  /**
   * 
   *@param (int key)
   */
  public boolean contains(int key) {
    return containsHelper(key, root);
  }
  
  
  /**
   * 
   * @param key
   * @param p
   * @return containsHelper(key, p.left) || containsHelper(key, p.right);
   */
  private boolean containsHelper(int key, Node p) {
    if (p == null)
      return false;
    if (p.data == key)
      return true;
    return containsHelper(key, p.left) || containsHelper(key, p.right);
  }
  
  /**
   * @return n
   */
  public int size() {
    return n;
  }
 
  public int height(){
	  return height(root); 
	  }
  
  /**
   * produces the longest path of the tree
   * @param node
   * @return height(node.left)+ height(node.right); 
   */
  private static int height(Node node){
	  if(node == null)        
		    return 0;  
		  if(node.left ==null && node.right==null)       
		    return 1;             
		  else  
		    return height(node.left)+ height(node.right); 	  
  }
  
  public static void main(String... args) {
    int[] a = new int[] { 3, 9, 7, 2, 1, 5, 6, 4, 8 };
    BinaryTree tr = new BinaryTree();
    assert tr.isEmpty();
    for (int key : a)
      tr.insert(key);
    assert tr.size() == a.length;
    assert !tr.root.isLeaf();
    for (int key : a)
      assert tr.contains(key);
    try { 
      tr.remove(3);
    }
    catch (UnsupportedOperationException e) {
    }  
    assert tr.height() == 5;
    System.out.println("Passed all tests...");
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

