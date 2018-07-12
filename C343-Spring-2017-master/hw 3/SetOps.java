import java.util.LinkedList;
import java.util.List;

/**
 * hw3: Problem 2 starter code.
 */

public class SetOps {
  
  /**
   * Returns a list (without duplicates) containing all the items
   * in ls1 plus all the items in ls2. Note: ls1 and ls2 are 
   * unchanged by this method.
   */
	public static <T> List<T> union(List<T> ls1, List<T> ls2) {
		List<T> list = new LinkedList<T>();

		int i = 0, j = 0;

		while (i < ls1.size() && j < ls2.size()) {
			if (((Comparable) ls1.get(i)).compareTo(ls2.get(j)) < 0) {
				list.add(ls1.get(i));
				i++;
			}
			else if (((Comparable) ls1.get(i)).compareTo(ls2.get(j)) > 0) {
				list.add(ls2.get(j));
				j++;
			}
			else {
				list.add(ls1.get(i));
			}
		}
		return list;
	}

  /**
   * Returns a list (without duplicates) of all the items which
   * appear both in ls1 and in ls2. Note: ls1 and ls2 are
   * unchanged by this method.
   */
	public static <T> List<T> intersection(List<T> ls1, List<T> ls2) {
		List<T> list = new LinkedList<T>();

		int k = 0, l = 0;

		while (k < ls1.size() && l < ls2.size()) {
			if (((Comparable) ls1.get(k)).compareTo(ls2.get(l)) < 0) {
				list.add(ls1.get(k));
				k++;
			}
			else if (((Comparable) ls1.get(k)).compareTo(ls2.get(l)) > 0) {
				list.add(ls2.get(l));
				l++;
			}
			else {
				list.add(ls1.get(k));
			}
		}
		return list;
	}
  
  /**
   * Simple testing to get you started. Add more tests of your own!
   */
  public static void main(String... args) {
    List<String> ls1 = new LinkedList<>();
    ls1.add("ant");
    ls1.add("bat");
    ls1.add("cat");
    ls1.add("ant");  // this is a duplicate element
    ls1.add("fox");
    int n1 = ls1.size();
    System.out.println("ls1 = " + ls1);
    
    List<String> ls2 = new LinkedList<>();
    ls2.add("cat");
    ls2.add("dog");
    ls2.add("dog");  // this is a duplicate element
    ls2.add("emu");
    ls2.add("fox");
    ls2.add("gnu");
    int n2 = ls2.size();
    System.out.println("ls2 = " + ls2);
    
    List<String> ls3, ls4;
    ls3 = union(ls1, ls2);
    assert n1 == ls1.size();
    assert n2 == ls2.size();
    assert 7 == ls3.size();
    System.out.println("ls3 = " + ls3);

    ls4 = intersection(ls1, ls2);
    assert n1 == ls1.size();
    assert n2 == ls2.size();
    assert 2 == ls4.size();
    System.out.println("ls4 = " + ls4);
  }
}

