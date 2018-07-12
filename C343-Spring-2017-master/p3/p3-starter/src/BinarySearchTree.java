import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;





/**
 * TODO: This is your first major task.
 * 
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements Tree<K> {
  
  /**
   * A Node is a Location, which means that it can be the return value
   * of a search on the tree.
   */
  
  class Node implements Location<K> { 
    protected K data;
    protected Node left, right;
    protected Node parent;     // the parent of this node
    protected int height;      // the height of the subtree rooted at this node
    protected boolean dirty;   // true iff the key in this node has been removed

    /**
     * Constructs a leaf node with the given key.
     */
    public Node(K key) {
      this(key, null, null);
    }
    
    /**
     * Constructs a new node with the given values for fields.
     */
    public Node(K data, Node left, Node right) {
    	this.data = data;
    	this.left = left;
    	this.right = right;	
    }

    /**
     * Return true iff this node is a leaf in the tree.
     */
    protected boolean isLeaf() {
      return left == null && right == null;
    }
    
    /**
     * Performs a local update on the height of this node. Assumes that the 
     * heights in the child nodes are correct. This function *must* run in 
     * O(1) time.
     */
    protected void fixHeight() {
    	if (this.left != null)
    		this.height = this.left.height + 1;
    	else if (this.right != null)
    		this.height = this.right.height + 1;
    	else
    		this.height = 1;
    }
    
    /**
     * Returns the data in this node.
     */
    public K get() {
      return this.data;
    }

    /**
     * Returns the location of the node containing the inorder predecessor
     * of this node.
     */
    public Node getBefore() {
    	Node n = this;
    	if (n.left != null){
    		return getMax(n.left);
    	}
        Node p = n.parent;
        while(p != null && n == p.left){
        	n = p;
        	p = p.parent;
        }
        return p;
     }

    /**
     * Returns the location of the node containing the inorder successor
     * of this node.
     */
    public Node getAfter() {
    	Node n = this;
    	if (n.right != null){
    		return getMin(n.right);
    	}
        Node p = n.parent;
        while(p != null && n == p.right){
        	n = p;
        	p = p.parent;
        }
        return p;
    }
    
    public Node getMax (Node root){
    	if (root.right == null){
    		return root;
    	}
    	else{
    		return getMax(root.right);
    	}
    }
    
    public Node getMin (Node root){
    	if (root.left == null){
    		return root;
    	}
    	else{
    		return getMax(root.left);
    	}
    }
    
    
  }
  
  protected Node root;
  protected int n;
  protected BiPredicate<K, K> lessThan;
  
  /**
   * Constructs an empty BST, where the data is to be organized according to
   * the lessThan relation.
   */
  public BinarySearchTree(BiPredicate<K, K> lessThan) {
    this.lessThan = lessThan;
  }
  
  /**
   * Looks up the key in this tree and, if found, returns the (possibly dirty)
   * location containing the key.
   */
  public Node search(K key) {
	  Node head;
	  head = root;
	  while (head != null){
		  if (head.data.equals(key) && head.dirty){
			  return null;
		  }
		  if (head.data.equals(key)){
			  return head;
		  }
		  
		  if (lessThan.test(head.data, key)){
			  head = head.right;
		  }
	  
		  else{
		  head = head.left;
	  }
	  }
	  return head;
	  
	  }

  /**
   * Returns the height of this tree. Runs in O(1) time!
   */
  public int height() {
    return heightHelper(root);
  }
  
  
  private int heightHelper(Node node){
	  if(node == null)        
		    return 1;  
		  if(node.left == null && node.right == null)       
		    return 0;             
		  else  
		    return heightHelper(node.left)+ heightHelper(node.right); 	  
  }
  
  /**
   * Clears all the keys from this tree. Runs in O(1) time!
   */
  public void clear() {
	 n = 0;
	 this.root = null;
  }

  /**
   * Returns the number of keys in this tree.
   */
  public int size() {
    return n;
  }
  
  /**
   * Inserts the given key into this BST, as a leaf, where the path
   * to the leaf is determined by the predicate provided to the tree
   * at construction time. The parent pointer of the new node and
   * the heights in all node along the path to the root are adjusted
   * accordingly.
   * 
   * Note: we assume that all keys are unique. Thus, if the given
   * key is already present in the tree, nothing happens.
   * 
   * Returns the location where the insert occurred (i.e., the leaf
   * node containing the key).
   */
  public Node insert(K key) {
	  Node k = new Node(key); 
	  n++;
	  root = insertHelper(k, root, null);
	  return k;
  }
  
  
  private Node insertHelper(Node k, Node p, Node n) {
	    if (p == null){
	    	k.dirty = false;
	    	k.height = 1;
	    	k.parent = n;
	    }
	    else if (lessThan.test(k.data, p.data)){
	      p.left = insertHelper(k, p.left, p);
	    }
	    else if (p.data.equals(k.data) && p.dirty){
	    	p.dirty =  false;
	    	p.height = 1;
	    }
	   
	    else{ 
	      p.right = insertHelper(k, p.right, p);
	    }
	    return p;
	  }
  
  /** 
   * Returns true iff the given key is in this BST.
   */
  public boolean contains(K key) {
	  return containsHelper(key, root);
  }
  
  
  private boolean containsHelper(K key, Node p) {
	    if (p == null)
	      return false;
	    if (p.data == key)
	      return true;
	    return containsHelper(key, p.left) || containsHelper(key, p.right);
	  }
  
  

  /**
   * Removes the key from this BST. If the key is not in the tree,
   * nothing happens. Implement the removal using lazy deletion.
   */
  public void remove(K key) {
	  
	  root = removeHelper(key, root);
	    
  }
  
  private Node removeHelper(K key, Node p) {
	    if (p == null)  
	      return p;
	    if (key == p.data) {
	      if (p.left == null) {
	        n--;
	        return p.right;
	      }
	      else if (p.right == null) {
	        n--;
	        return p.left;
	      }
	      p.data = minValue(p.right);
	      p.right = removeHelper(p.data, p.right);      
	    }
	    else if (lessThan.test(key, p.data))
	      p.left = removeHelper(key, p.left);
	    else 
	      p.right = removeHelper(key, p.right);
	    return p;
	  }

	  /**
	   * Returns the minimal key in the (non-empty) tree rooted
	   * at p.
	   */
	  private K minValue(Node p) {
	    assert p != null;
	    K minval = p.data;
	    while (p.left != null) {
	      minval = p.left.data;
	      p = p.left;
	    }
	    return minval;
	  }
  
  
  
  /**
   * Clears out all dirty nodes from this BST.
   * 
   * Use the following algorithm:
   * (1) Let ks be the list of keys in this tree. 
   * (2) Clear this tree.
   * (2) For each key in ks, insert it into this tree.
   */
  public void rebuild() {
	  List<K>ks = keys();
	  this.clear();
	  
	  for (K k : ks)
		  this.insert(k);
  }
    
  /**
   * Returns a sorted list of all the keys in this tree.
   */
  public List<K> keys() {
	  // use recursion to build on the current list.
	  List<K> ks = new LinkedList<>();
	  Node p = root;
	  keysHelper(p, ks);
	  
    return ks;
  }
  
  private List<K> keysHelper(Node p, List<K> ks){
	  if (p != null){
		  if (p.dirty != true){
			  ks.add(p.data);
			  if (p.left != null){
				  keysHelper(p.left, ks);
			  }
			  if (p.right != null){
				  keysHelper(p.right, ks);
			  }
		  }
	  }
	  return ks;
  }

  /**
   * Returns a textual representation of this BST.
   */
  public String toString() {
	  String ans = "{";
	  if(root == null){
		  ans += "}";
	  }else{
		  ans += toStringHelper(root);
	  }
	  ans += "}";
	  
    return ans;
  }
  
  private String toStringHelper(Node node){
	  String ans = "";
	  if(node.isLeaf()){
		  ans += root.data.toString();
	  }else if(!node.left.isLeaf()){
		  toStringHelper(node.left);
	  }else if(!node.right.isLeaf()){
		  toStringHelper(node.right);}
	  return ans;
  } 
  
  
  
  
}
