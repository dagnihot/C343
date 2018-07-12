import java.util.ArrayList;
import java.util.List;

/**
 * TODO #2
 */

public class MatchBot extends TwitterBot {
  /**
   * Constructs a MatchBot to operate on the last numTweets of the given user.
   */
  public MatchBot(String user, int numTweets) {
    super(user, numTweets);
  }
  
  /**

   * Employs the KMP string matching algorithm to add all tweets containing 
   * the given pattern to the provided list. Returns the total number of 
   * character comparisons performed.
   */
  public int searchTweetsKMP(String pattern, List<String> ans) {
	  Result suzanne;
	  int[] flinks = new int[pattern.length() + 1];
	  int comps = StringMatch.buildKMP(pattern, flinks);
	  
	  for (String tweet: tweets){
		  
		 suzanne = StringMatch.runKMP(pattern, tweet, flinks);
		 comps += suzanne.comps;
		 if (suzanne.pos != -1){
			 ans.add(tweet);
		 }
	  }
    return comps;
  }
  
  /**
 
   * Employs the naive string matching algorithm to find all tweets containing 
   * the given pattern to the provided list. Returns the total number of 
   * character comparisons performed.
   */
  public int searchTweetsNaive(String pattern, List<String> ans) {
	  Result suzanne;
	  int[] flinks = new int[pattern.length() + 1];
	  int comps = 0;
	  
	  for (String tweet: tweets){
		  
		 suzanne = StringMatch.matchNaive(pattern, tweet);
		 comps += suzanne.comps;
		 if (suzanne.pos != -1){
			 ans.add(tweet);
		 }
	  }
    return comps;
  }
  
  /**
 
   * Employs the Boyer Moore string matching algorithm to find all tweets containing 
   * the given pattern to the provided list. Returns the total number of 
   * character comparisons performed.
   */
  public int searchTweetsBoyerMoore(String pattern, List<String> ans) {
	  Result suzanne;
	  int[] flinks = new int[pattern.length() + 1];
	  int comps = 0;
	  
	  for (String tweet: tweets){
		  
		 suzanne = StringMatch.matchBoyerMoore(pattern, tweet);
		 comps += suzanne.comps;
		 if (suzanne.pos != -1){
			 ans.add(tweet);
		 }
	  }
    return comps;
  }
  
  public static void main(String... args) {
    String handle = "realDonaldTrump", pattern = "America";
    MatchBot bot = new MatchBot(handle, 2000);
   
    // Search all tweets for the pattern.
    List<String> ansNaive = new ArrayList<>();
    int compsNaive = bot.searchTweetsNaive(pattern, ansNaive); 
    List<String> ansKMP = new ArrayList<>();
    int compsKMP = bot.searchTweetsKMP(pattern, ansKMP);  
    List<String> ansBoyerMoore = new ArrayList<>();
    int compsBoyerMoore = bot.searchTweetsBoyerMoore(pattern, ansKMP);
    System.out.println("naive comps = " + compsNaive + ", KMP comps = " + compsKMP + ", BoyerMoore comps = " + compsBoyerMoore);

    for (int i = 0; i < ansKMP.size(); i++) {
      String tweet = ansKMP.get(i);
      assert tweet.equals(ansNaive.get(i));
      System.out.println(i++ + ". " + tweet);
      System.out.println(pattern + " appears at index " + 
          tweet.toLowerCase().indexOf(pattern.toLowerCase()));
    }
    
    // Do something similar for the Boyer-Moore matching algorithm.
  
  }
}
