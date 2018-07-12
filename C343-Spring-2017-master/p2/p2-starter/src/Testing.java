import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for all TODO methods.
 */

public class Testing {
  
  @Test
  public void testOnBoard() {
    assertFalse(new Coord(3, 4).onBoard(4));
    assertTrue(new Coord(3, 4).onBoard(5));
    assertTrue(new Coord(1, 2).onBoard(6));
    assertFalse(new Coord(5,5).onBoard(1));
    assertTrue(new Coord(7, 8).onBoard(10));
    assertFalse(new Coord(10, 12).onBoard(0));
  }

}