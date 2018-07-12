import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/** 
 * Most of the work for this project involves implementing the
 * connectAllWires() method in this class. Feel free to create
 * any helper methods that you deem necessary. 
 * 
 * Your goal is to come up with an efficient algorithm that will 
 * find a layout that connects all the wires (if one exists) while
 * attempting to minimize the overall wire length.
 */

public class PathFinder {

  /**
   * Lays out a path connecting each wire on the chip, and then 
   * returns a map that associates a wire id numbers to the paths
   * corresponding to the connected wires on the grid. If it is 
   * not possible to connect the endpoints of a wire, then there
   * should be no association for the wire id# in the result.
   */

  public static Map<Integer, Path> connectAllWires(Chip chip) {
      Map<Integer, Path> ans = new HashMap <Integer, Path>();
      Queue<Coord> queue = new LinkedList<Coord>();
      Map<Coord, Coord> visited = new HashMap<Coord, Coord>();
      for (Wire w : chip.wires){
          Path p = new Path(w);
          queue.clear();
          visited.clear();
          visited.put(w.to, null);
          queue.offer(w.to);
          while (!queue.isEmpty() && !visited.containsKey(w.from)){
              Coord x = queue.poll();
              for (Coord c : chip.neighbors(x)){
                  if (!visited.containsKey(c) && chip.isAvailable(c, w.wireId)){
                      queue.offer(c);
                      visited.put(c, x);
                  }
              }
          }
          Coord x = visited.get(w.from);
          while(x != null){
            p.add(x);
            x = visited.get(x);
          }
          if(!p.isEmpty()){
            ans.put(w.wireId, p);
          }
          else{
            System.out.println("Could Not Connect Wire " + w.wireId);
          }
      }
      return ans;
  }
 
 
 
  /** 
   * Returns the sum of the lengths of all non-null paths in the given layout.
   */
  public static int totalWireUsage(Map<Integer, Path> layout) {
	  int sumOfWires = 0;
	  for(Path i : layout.values()){
		  if (i != null){
			  sumOfWires += i.size();
		  }
	  }
    return sumOfWires;
  }
}

