import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The following table shows the first ten numbers in the
 * Fibonacci sequence:
 * 
 *      n  :  0  1  2  3  4  5  6  7  8  9 ...
 *  Fib(n) :  1  1  2  3  5  8 13 21 34 55 ...
 * 
 * @author <Dhruv Agnihotri>
 */

public class Fibonacci {


  public static int fib1(int n) {
	  if(n == 0)
	        return 1;
	    else if(n == 1)
	      return 1;
	   else
	      return fib1(n - 1) + fib1(n - 2);
  }


  public static int fib2(int n){
	  
	  if (n <= 2) {
	        return 1;
	    }
	    return fib2help(n, 1, 1, 1);		   

  }

  public static int fib2help(int x, int y, int curr, int prev){
	  if (x==y)
		  return curr;
	  else
		  return fib2help(x, y + 1, curr + prev, curr);
  }

  public static void main(String... args) {
    assert fib1(9) == 55;
    assert fib2(8) == 34;
    assert fib1(7) == 21;
    assert fib2(6) == 13;
  }
  
  /**
   * TODO: Run this class as a JUnit test. Add additional tests to
   * the following methods.
   */
  
  @Test
  public void testFib1() {
    assertEquals(55, fib1(9));
    assertEquals(34, fib1(8));
    assertEquals(21, fib1(7));
    assertEquals(13, fib1(6));
  }
  
  @Test
  public void testFib2() {
    assertEquals(55, fib2(9));
    assertEquals(34, fib1(8));
    assertEquals(21, fib1(7));
    assertEquals(13, fib1(6));

  
}

}