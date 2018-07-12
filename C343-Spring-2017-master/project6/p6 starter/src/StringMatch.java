/**
 * TODO #1
 */

public class StringMatch {

  /**

   * Returns the result of running the naive algorithm to match pattern in text.
   */
  public static Result matchNaive(String pattern, String text) {  
	  int m = pattern.length();
	  int n = text.length();
	  int ans = 0, i = 0, j = 0;
	  int count = 0;
	  
	  if (m == 0){
		  return new Result(0, 0);
		  
	  }
	  while (ans <= n - m){
		  if (pattern.charAt(i) == text.charAt(j)){
			  //keep matching
			  count++;
			  if (i == m - 1){
				  return new Result(j - (m - 1), count);
			  }
			  i++;
			  j++;
			  
		  }
		  else {
			  //slide pattern forward and start over
			  i = 0;
			  j = ans = ans +1;
			  count++;
		  }
	  }
    return new Result(-1, count);
  }
  
  /**
 
   * Populates flink with the failure links for the KMP machine associated with the
   * given pattern, and returns the cost in terms of the number of character comparisons.
   */
  public static int buildKMP(String pattern, int[] flink) {
	  int m =  pattern.length();
	  int i = 2, count = 0;
	  if (m == 0){
		  return count;
	  }
	  flink[0] = -1;
	  flink[1] = 0;
	 
	  while (i <= m){
		  int j = flink [i - 1];
		  count++;
		  while (j != -1 && pattern.charAt(j) != pattern.charAt(i - 1)){
			  j = flink[j];
			  count++;
		  }
			flink[i] = j + 1;
			i++;
	  }
	  
    return count;
  }
  
  /**
 
   * Returns the result of running the KMP machine specified by flink (built for the
   * given pattern) on the text.
   */
  public static Result runKMP(String pattern, String text, int[] flink) {
	  int m = text.length();
	  int n = pattern.length();
	  int state =  -1;
	  int count = 0;
	  int j = -1;
	  char c = pattern.charAt(state + 1);
	  if (n == 0){
		  return new Result(0,0);
	  }
	  while (true){
		  if (state == -1 || c == pattern.charAt(state)){
			  state++;
		  
			  if (state == n){
			  return new Result(j - n + 1, count);
			  }
			  
			  j++;
			  
			  if (j == m){
			  return new Result(-1, count);
			  }
			  
			  c = text.charAt(j);
		  
		  } else {
			  state = flink[state];
		  
		  }
		  count++;
	  }
		  
	  }

  /**
   * Returns the result of running the KMP algorithm to match pattern in text. The number
   * of comparisons includes the cost of building the machine from the pattern.
   */
  public static Result matchKMP(String pattern, String text) {
    int m = pattern.length();
    int[] flink = new int[m + 1];
    int comps = buildKMP(pattern, flink);
    Result ans = runKMP(pattern, text, flink);
    return new Result(ans.pos, comps + ans.comps);
  }
  
  /**
 
   * Populates delta1 with the shift values associated with each character in the
   * alphabet. Assume delta1 is large enough to hold any ASCII value.
   */
  public static void buildDelta1(String pattern, int[] delta1) {
    // array with index between 0 and 127
	 int m = pattern.length();
	 for (int i = 0; i< delta1.length; i++)
		 delta1[i] = m;
		 
	 for (int i = 0; i < m; i++)
		 delta1[pattern.charAt(i)] = m -i -1;
  }

  /**
 
   * Returns the result of running the simplified Boyer-Moore algorithm using the
   * delta1 table from the pre-processing phase.
   */
  public static Result runBoyerMoore(String pattern, String text, int[] delta1) {
	  Result swag = new Result(-1, 0);
	  
	  int n = text.length();
	  int m = pattern.length();
	  int i = m - 1; 
	  int j = 0;
	  
	  if (m == 0){
		  return new Result(0,0);
	  }
	  while (i < n){
		  swag.comps++;
		  if (text.charAt(i - j) == pattern.charAt(m - j - 1)){
			  j++;
			  if (j == m){
				  return new Result(i - m + 1, swag.comps);
			  }
		  }
		  else {
			  char swag2 = text.charAt(i - j);
			  if (swag2 >= Constants.SIGMA_SIZE){
				  i+= m;
			  }
			  else {
				  i+= Math.max(1, delta1[swag2] - j);
			  }
			  j = 0;
					  
		  }
				  
	  }
	  
    return swag;
  }
  
  /**
   * Returns the result of running the simplified Boyer-Moore algorithm to match 
   * pattern in text. 
   */
  public static Result matchBoyerMoore(String pattern, String text) {
    int[] delta1 = new int[Constants.SIGMA_SIZE];
    buildDelta1(pattern, delta1);
    return runBoyerMoore(pattern, text, delta1);
  }

}
