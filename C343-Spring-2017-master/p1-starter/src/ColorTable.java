import java.awt.Color;
import java.util.*;
import java.util.Arrays;
import java.util.Random;

/**
 * @author (Dhruv Agnihotri)
 * 
 * A ColorTable represents a dictionary of frequency counts, keyed on Color.
 * It is a simplification of Map<Color, Integer>. The size of the key space
 * can be reduced by limiting each Color to a certain number of bits per channel.
 */

/**
 * Implement this class, including whatever data members you need and all of the
 * public methods below. You may create any number of private methods if you find
 * them to be helpful. Replace all TODO comments with appropriate javadoc style 
 * comments. Be sure to document all data fields and helper methods you define.
 */

public class ColorTable {

// variable declarations
	  int iC;
	  int bPC;
	  int cS;
	  double rT;
	  private long[][] array;
	  private int full;

	
  /**
   * Counts the number of collisions during an operation.
   */
  private static int numCollisions = 0;

  /**
   * Returns the number of collisions that occurred during the most recent get or
   * put operation.
   */
  public static int getNumCollisions() {
    return numCollisions;
  }

  /**
   * Constructs a color table with a starting capacity of initialCapacity. Keys in
   * the color key space are truncated to bitsPerChannel bits. The collision resolution
   * strategy is specified by passing either Constants.LINEAR or Constants.QUADRATIC for
   * the collisionStrategy parameter. The rehashThrehold specifies the maximum tolerable load 
   * factor before triggering a rehash.
   * 
   * @throws RuntimeException if initialCapacity is not in the range [1..Constants.MAX_CAPACITY]
   * @throws RuntimeException if bitsPerChannel is not in the range [1..8]
   * @throws RuntimeException if collisionStrategy is not one of Constants.LINEAR or Constants.QUADRATIC
   * @throws RuntimeException if rehashThreshold is not in the range (0.0..1.0] for a
   *                             linear strategy or (0.0..0.5) for a quadratic strategy
   */
  
  
  public ColorTable(int initialCapacity, int bitsPerChannel, int collisionStrategy, double rehashThreshold) { 
	  //run time stuff here
	  
	  //start of constructor
	  
		 if (bitsPerChannel >= 1 && bitsPerChannel <= 8) {
			   	bPC = bitsPerChannel;
			  }
			  else
			  	throw new RuntimeException(String.format("Unsupported number of bits per channel: %d",
			            bitsPerChannel)); 
			 
			
			
		 if (initialCapacity >= 1 && initialCapacity <= Constants.MAX_CAPACITY) {
			  		iC = initialCapacity;
			  }
			  else
			  	throw new RuntimeException("Unsupported initial capacity; use an int in the range [1..8]");

			
			
		 if (collisionStrategy == Constants.LINEAR || collisionStrategy <= Constants.QUADRATIC) {
			  		cS = collisionStrategy;
			  }
			  else
			  	throw new RuntimeException("Unsupported collision strategy; use an int in the range [1..8]");
			 
			
			
		 if (((collisionStrategy == Constants.LINEAR) && (rehashThreshold >= 0 && rehashThreshold <=1))
		 	|| ((collisionStrategy == Constants.QUADRATIC) && (rehashThreshold >= 0 && rehashThreshold <=0.5))){
				rT = rehashThreshold;
			  }
		  else
			  	throw new RuntimeException("Unsupported collision strategy; use an double in the range [1..8]");
			 
		 array = new long[2][initialCapacity];
		 
  }

  /**
   * Returns the number of bits per channel used by the colors in this table.
   */
  public int getBitsPerChannel() {
    return this.bPC;
  }

  /**
   * Returns the frequency count associated with color. Note that colors not
   * explicitly represented in the table are assumed to be present with a
   * count of zero. Uses Util.pack() as the hash function.
   */
  public long get(Color color) {
	  Util.pack(color, bPC);
	  
     // this is the pseudocode for the get function. This method compiles but I commented it out because of an error marker
	 // if (Util.pack(color, bPC) = null){
	//	  return 0;
	//  }
	//	  else
			  return array[0][iC];

  }

  /**
   * TODO
   * 
   * Associates the count with the color in this table. Do nothing if count is less than
   * or equal to zero. Uses Util.pack() as the hash function.
   */
  
  public void put(Color color, long count) {
 
	  if (count <= 0) {
		  // does nothing
	  }
		  else 
			  Util.pack(color, bPC);
	  hashfunction(color);
	   
  }
  
  // this lookup function is where the collision strategy will be selected
  int lookup(Color color){
	  int start = hashfunction(color), i = start, k = 0;
	  while (array[0][i] != 0 && array[0][i] != Util.pack(color, bPC)) {
		  k++;
		  if (cS == Constants.LINEAR)
			  i = (start + k) % this.getCapacity();
		  if (cS == Constants.QUADRATIC)
			  i = (int)(start + Math.pow(k, 2)) % this.getCapacity();
			  
	  }
	  return i;
  }

  /**
   * TODO
   * 
   * Increments the frequency count associated with color. Note that colors not
   * explicitly represented in the table are assumed to be present with a
   * count of zero.
   */
  public void increment(Color color) {
	  //call inside of put function
	  //similar to get and put
	  //once you compute the index, increment the count by 1

  }

  /**
   * Returns the load factor for this table.
   */
  public double getLoadFactor() {
    return (getSize() / getCapacity() );
  }

  /**
   * Returns the size of the internal array representing this table.
   */
  public int getCapacity() {
    return this.cS;
  }

  /**
   * Returns the number of key/value associations in this table.
   */
  public int getSize() {
    return full;
  }

  /**
   * Returns true iff this table is empty.
   */
  public boolean isEmpty() {
	  if (full == 0)
		  return true;
	  else
		  return false;
  }

  /**
   * TODO
   * 
   * Increases the size of the array to the smallest prime greater than double the 
   * current size that is of the form 4j + 3, and then moves all the key/value 
   * associations into the new array. 
   * 
   * Hints: 
   * -- Make use of Util.isPrime().
   * -- Multiplying a positive integer n by 2 could result in a negative number,
   *    corresponding to integer overflow. You should detect this possibility and
   *    crop the new size to Constants.MAX_CAPACITY.
   * 
   * @throws RuntimeException if the table is already at maximum capacity.
   */
  private void rehash() { 
	  

  }

  /**
   * TODO
   * 
   * Returns an Iterator that marches through each color in the key color space and
   * returns the sequence of frequency counts.
   */
  public Iterator iterator() {
    return null;
  }

  /**
   * Returns a String representation of this table.
   */
  public String toString() {
    return Arrays.toString(array);
  }

  /**
   * Returns the count in the table at index i in the array representing the table.
   * The sole purpose of this function is to aid in writing the unit tests.
   */
  public long getCountAt(int i) { 
    return array[1][i];
  }
  
  
  private int hashfunction (Color color){
 
	   return Util.pack(color, bPC) % getCapacity();
  }
  
  
  
  
  /**
   * Simple testing.
   */
  public static void main(String[] args) {
    ColorTable table = new ColorTable(3, 6, Constants.QUADRATIC, .49);
    int[] data = new int[] { 32960, 4293315, 99011, 296390 };
    for (int i = 0; i < data.length; i++) 
      table.increment(new Color(data[i]));
    System.out.println("capacity: " + table.getCapacity()); // Expected: 7
    System.out.println("size: " + table.getSize());         // Expected: 3
    
    /* The following automatically calls table.toString().
       Notice that we only include non-zero counts in the String representation.
       
       Expected: [3:2096,2, 5:67632,1, 6:6257,1]
       
       This shows that there are 3 keys in the table. They are at positions 3, 5, and 6.
       Their color codes are 2096, 67632, and 6257. The code 2096 was incremented twice.
       You do not have to mimic this format exactly, but strive for something compact
       and readable.
       */
    System.out.println(table);  
  }
}
