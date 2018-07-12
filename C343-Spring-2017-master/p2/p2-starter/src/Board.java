import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Board represents the current state of the game. Boards know their dimension, 
 * the collection of tiles that are inside the current flooded region, and those tiles 
 * that are on the outside.
 * 
 * @author <Dhruv Agnihotri>
 */

public class Board {
  private Map<Coord, Tile> inside, outside;
  private int size;
  
  /**
   * Constructs a square game board of the given size, initializes the list of 
   * inside tiles to include just the tile in the upper left corner, and puts 
   * all the other tiles in the outside list.
   */
  public Board(int size) {
    // A tile is either inside or outside the current flooded region.
    inside = new HashMap<>();
    outside = new HashMap<>();
    this.size = size;
    for (int y = 0; y < size; y++)
      for (int x = 0; x < size; x++) {
        Coord coord = new Coord(x, y);
        coord.Size(size);
        outside.put(coord, new Tile(coord));
      }
    // Move the corner tile into the flooded region and run flood on its color.
    Coord.ORIGIN.Size(size);
    Tile corner = outside.remove(Coord.ORIGIN);
    inside.put(Coord.ORIGIN, corner);
    flood(corner.getColor());
  }
  
  /**
   * Returns the tile at the specified coordinate.
   */ 
  public Tile get(Coord coord) {
	  coord.Size(size);
    if (outside.containsKey(coord))
      return outside.get(coord);
    return inside.get(coord);
  }
  
  /**
   * Returns the size of this board.
   */
  public int getSize() {
    return size;
  }
  
  /**
   * 
   * Returns true iff all tiles on the board have the same color.
   */
  public boolean fullyFlooded() {
	// check if the outside region is empty or not. if it is empty, return true.
	  if (outside.isEmpty()){
		  return true;
	  }
	return false;	  
  }
  
  /**
   * Updates this board by changing the color of the current flood region 
   * and extending its reach.
   */
  public void flood(WaterColor color) {
	  //check whether the neighbor is in the inside or outside region. 
	  //if inside, do nothing. of outside, do a for each loop on each neighbor and change the color of that neighbor.

	  //currently floods only the top row, one tile at a time
	  //game also produces an empty graph

	  
	  List<Tile> inRegion = new ArrayList<Tile>();
	  
	  for (Tile tile : inside.values()){
		  tile.setColor(color);
		  
	  for (Coord adjacent : tile.getCoord().neighbors(size)){
		  if (outside.containsKey(adjacent))
			  
			  if (outside.get(adjacent).getColor() == color)
				  inRegion.add(outside.get(adjacent));
	  }
	  }
	 
	  for (Tile adjacent : inRegion){
		  floodthetile(adjacent, color);
	  }
  }
/**
 * helper function being called for the flood function to help move the outside tiles of the same color to the inside region.	
 * @param tile
 * @param color
 */
  private void floodthetile (Tile tile, WaterColor color){
	  tile.setColor(color);
	  
	  outside.remove(tile.getCoord());
	  
	  inside.put(tile.getCoord(), tile);
	  
	  for (Coord next : tile.getCoord().neighbors(size)){
		  
		  if (outside.containsKey(next))
			  
			  if (outside.get(next).getColor() == color)
				  
				  floodthetile(outside.get(next), color);
	  }
	  

	  
	  }
 
  
  /**
   * TODO
   * 
   * Explore a variety of algorithms for handling a flood. Use the same interface 
   * as for flood above, but add an index so your methods are named flood1,
   * flood2, ..., and so on. Then, use the batchTest() tool in Driver to run game
   * simulations and then display a graph showing the run times of all your different 
   * flood functions. Do not delete your flood code attempts. For each variety of 
   * flood, including the one above that you eventually settle on, write a comment
   * that describes your algorithm in English. For those implementations that you
   * abandon, state your reasons.
  
  public void flood1(WaterColor color) {
   I couldnt figure this out so I am putting pseudocode here for an attempt at partial credit.
   
   step 1 - go through all inside tiles
   step 2 - get neighbors
   step 3 - check if neighbors are inside or outside. 
   step 4 - if yes, then move on. if no, check the neighbor's color. if the neighbor's color is same as current tile, move that neighbor
   inside and recall flood recursively.
   
   for (Tile : tile getNeighbors(size)
   		if (neighbor is inside{
   		if (neighbor.getColor()==Origin.getColor()){
   		put.neighbor inside;
   		flood1();
   		}
    
  }
  
  public void flood2(WaterColor color) {
  }
   */
  
  /**
   * Returns the "best" GameColor for the next move. 
   * 
   * the game right now only floods the top row, one tile at a time and produces an empty graph
   */
  public WaterColor suggest() {
	  //make a count of all colors in the outside region and return the color that has the most occurrence
	//for (Tile tile: outside.values()){
	//	int count = Collections.frequency(outside, outside.getColor());
   //  }
	//return count;	
    WaterColor cornerColor = inside.get(Coord.ORIGIN).getColor();
    return WaterColor.pickOneExcept(cornerColor);
  }
  
  /**
   * Returns a string representation of this board. Tiles are given as their
   * color names, with those inside the flooded region written in uppercase.
   */ 
  public String toString() {
    StringBuilder ans = new StringBuilder();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Coord curr = new Coord(x, y);
        WaterColor color = get(curr).getColor();
        ans.append(inside.containsKey(curr) ? color.toString().toUpperCase() : color);
        ans.append("\t");
      }
      ans.append("\n");
    }
    return ans.toString();
  }
  
  /**
   * Simple testing.
   */
  public static void main(String... args) {
    // Print out boards of size 1, 2, ..., 5
    int n = 5;
    for (int size = 1; size <= n; size++) {
      Board someBoard = new Board(size);
      System.out.println(someBoard);
    }
  }
}






