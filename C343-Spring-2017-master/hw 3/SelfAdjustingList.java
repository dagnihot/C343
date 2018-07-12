/**
 * hw3: Problem 5 starter code.
 */

public class SelfAdjustingList<T> extends DoublyLinkedList<T> {
	
public SelfAdjustingList(){
  head = new Node(null);
  head.next = head;
  head.prev = head;
}

public void add(T x){
	n++;
	Node next = head.next;
	Node pres = new Node(x, head, next);
	next.prev = pres;
	head.next = pres;
}

private T find(int i){

	if(i < 0 && i > size()){
		throw new IndexOutOfBoundsException();
	}
		add(remove(i));
		return get(0);
	}

  
  /**
   * Simple testing to get you started. Add more tests of your own!
   */
  public static void main(String... args) {
    SelfAdjustingList<Integer> xs = new SelfAdjustingList<>();
    for (int x = 1; x <= 10; x++)
      xs.add(x);
  //  for (int i = 0; i < xs.size(); i++)
  //    assert 10 - i == xs.get(i);
    for (int i = 0; i < xs.size(); i++) {
      int x = xs.get(i);
      assert x == xs.find(i);
    }
    for (int i = 0; i < xs.size(); i++) {
      int x = xs.find(i);
      assert x == xs.get(0);
    }
    System.out.println("All tests passed...");
  }
}
