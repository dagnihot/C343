import java.util.HashMap;

/**
 * TODO: Complete the implementation of this class.
 */

public class FrequencyTable extends HashMap<Character, Integer> {
  /**
   * Constructs an empty table.
   */


  public FrequencyTable() {
    super();
  }
    
  /**
   * Constructs a table of character counts from the given text string.
   */
  
  public FrequencyTable(String text) {
   for (int i = 0; i < text.length(); i++){
	   char ch = text.charAt(i);
	   Integer value = super.get(new Character(ch));
	   if (value != null){
		   super.put(ch, new Integer(value + 1));
		   
	   }
	   else {
		   super.put(ch,  1);
	   }
   }
    	
  }
  
  
  

  
  /**
   * Returns the count associated with the given character. In the case that
   * there is no association of ch in the map, return 0.
   */
  
  @Override
  public Integer get(Object ch) {
	  if (!super.containsKey(ch)){
		  return 0;
	  }
	  else {
		  return super.get(ch);
	  }
  }
  
  
  

  
}
