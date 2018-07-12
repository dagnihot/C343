import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * TODO: Complete the implementation of this class.
 * 
 * The keys in the heap must be stored in an array.
 * 
 * There may be duplicate keys in the heap.
 * 
 * The constructor takes an argument that specifies how objects in the 
 * heap are to be compared. This argument is a java.util.Comparator, 
 * which has a compare() method that has the same signature and behavior 
 * as the compareTo() method found in the Comparable interface. 
 * 
 * Here are some examples of a Comparator<String>:
 *    (s, t) -> s.compareTo(t);
 *    (s, t) -> t.length() - s.length();
 *    (s, t) -> t.toLowerCase().compareTo(s.toLowerCase());
 *    (s, t) -> s.length() <= 3 ? -1 : 1;  
 */

public class Heap<E> implements PriorityQueue<E> {
  protected List<E> keys;
  private Comparator<E> comparator;

  
  /**
   * Creates a heap whose elements are prioritized by the comparator.
   */
  public Heap(Comparator<E> comparator) {
	  keys = new ArrayList<E>();
	  this.comparator = comparator;
    
  }

  /**
   * Returns the comparator on which the keys in this heap are prioritized.
   */
  public Comparator<E> comparator() {
    return comparator;
  }

  /**
   * Returns the top of this heap. This will be the highest priority key. 
   * @throws NoSuchElementException if the heap is empty.
   */
  public E peek() {
	  if (keys.isEmpty())
		  throw new NoSuchElementException();
	  else 
		  return keys.get(0);
  }

  /**
   * Inserts the given key into this heap. Uses siftUp().
   */
  public void insert(E key) {
	  keys.add(key);
	  siftUp(size() - 1);
  }

  /**
   * Removes and returns the highest priority key in this heap.
   * @throws NoSuchElementException if the heap is empty.
   */
  public E delete() {
	  if (keys.isEmpty()){
		  throw new NoSuchElementException(); 
	  }
	  if (keys.size() == 1){
		  return keys.remove(0);
	  }
	  else {
    	E ans = peek();
    	swap(0, size() - 1);
    	keys.remove(size() - 1);
    	siftDown(0);
    	return ans;
	  }
  }

  /**
   * Restores the heap property by sifting the key at position p down
   * into the heap.
   */
  public void siftDown(int p) {
	  int leftChild = getLeft(p);
	  if (leftChild < size()){
		  int maxChild = leftChild;
		  int rightChild = getRight(p);
		  if (rightChild < size() && comparator.compare(keys.get(rightChild), keys.get(maxChild)) < 0){
			  maxChild = rightChild;
		  }
		  if (comparator.compare(keys.get(maxChild), keys.get(p)) < 0){
			  swap(p, maxChild);
			  siftDown(maxChild);
		  }
	  }
    
  }
  
  /**
   * Restores the heap property by sifting the key at position q up
   * into the heap. (Used by insert()).
   */
  public void siftUp(int q) {
	  while (q > 0){
		  int p = getParent(q);
		  if (comparator.compare(keys.get(p), keys.get(q)) < 0){
			  return;
		  }
		  swap (p, q);
		  siftUp(p);
	  }
}
  

  /**
   * Exchanges the elements in the heap at the given indices in keys.
   */
  public void swap(int i, int j) {
	  E temp = keys.get(i);
	  keys.set(i, keys.get(j));
	  keys.set(j, temp);
    
  }
  
  /**
   * Returns the number of keys in this heap.
   */
  public int size() {
    return keys.size();
  }

  /**
   * Returns a textual representation of this heap.
   */
  public String toString() {
    return keys.toString();
  }
  
  /**
   * Returns the index of the left child of p.
   */
  public static int getLeft(int p) {
    return 2 * p + 1;
  }

  /**
   * Returns the index of the right child of p.
   */
  public static int getRight(int p) {
    return getLeft(p) + 1;
  }

  /**
   * 
   * Returns the index of the parent of p.
   */
  public static int getParent(int p) {
	  if (p % 2 == 0){
		  return p/2 - 1;
	  }
	  else 
		  return p/2;
  }
  
}
