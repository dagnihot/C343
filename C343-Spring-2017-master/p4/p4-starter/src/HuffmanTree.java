import java.util.Comparator;
import java.util.Map.Entry;

/**
 * TODO: Complete the implementation of this class.
 * 
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {
  
  class Node {
    protected char key;
    protected int priority;
    protected Node left, right;
    
    public Node(int priority, char key) {
      this(priority, key, null, null);
    }
    
    public Node(int priority, Node left, Node right) {
      this(priority, '\0', left, right);
    }
    
    public Node(int priority, char key, Node left, Node right) {
      this.key = key;
      this.priority = priority;
      this.left = left;
      this.right = right;
    }
    
    public boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  protected Node root;
  
  /**
   * Creates a HuffmanTree from the given frequencies of letters in the
   * alphabet using the algorithm described in lecture.
   */
  public HuffmanTree(FrequencyTable charFreqs) {
    Comparator<Node> comparator = (x, y) -> {
      /**
       *  x and y are Nodes
       *  x comes before y if x's priority is less than y's priority
       */
    	if (x.priority < y.priority){
    		return -1;
    	}
    	if (x.priority == y.priority){
    		return 0;
    	}
    	else {
    		return 1;
    	}
    	
      
    };  
    PriorityQueue<Node> forest = new Heap<Node>(comparator);

    /**
     *Complete the implementation of Huffman's Algorithm.
     * Start by populating forest with leaves.
     */
    for (Entry<Character, Integer> value : charFreqs.entrySet()){
    	forest.insert(new Node(value.getValue(), value.getKey()));
    }
    Node one, two, both = null;
    while (forest.size() > 1){
    	one = forest.delete();
    	two = forest.delete();
    	both = new Node(one.priority + two.priority, one, two);
    	forest.insert(both);
    	
    }
    root = both;
  }
  
  /**
   * Returns the character associated with the prefix of bits.
   * 
   * @throws DecodeException if bits does not match a character in the tree.
   */
  public char decodeChar(String bits) {
	  char ans;
	  Node path = root;
	  while (path.left != null && path.right != null){
		  if (bits.substring(0, 1).equals("0")){
			  path = path.left;
			  
		  }
	  	  if (bits.substring(0, 1).equals("1")){
	  		  path = path.right;  
	  	  }
	  	bits = bits.substring(1);
	  	 
	  }
	  ans = path.key;
    return ans;
  }
    
  /**
   * Returns the bit string associated with the given character. Must
   * search the tree for a leaf containing the character. Every left
   * turn corresponds to a 0 in the code. Every right turn corresponds
   * to a 1. This function is used by CodeBook to populate the map.
   * 
   * @throws EncodeException if the character does not appear in the tree.
   */
  
  public String lookup(char ch) {
	  String let = lookupHelp(ch, root, "");
		if(let != null)
			return let;
		throw new EncodeException(ch);
	}
	
	public String lookupHelp(char ch, Node curr, String str) {
		if(curr == null)
			return null;
		if(ch == curr.key)
			return str;
		String right = lookupHelp(ch, curr.right, str + "1");
		if(right != null)
			return right;
		return lookupHelp(ch, curr.left, str + "0");
  }
}

