/**
 * Problem 10.62(a): Let A be an n-by-n matrix of zeros and ones. A submatrix
 * S of A is any group of contiguous entries that forms a square. Design an
 * O(n^2) algorithm that determines the size of the largest submatrix of ones
 * in A.
 */

public class LargestSquareSubmatrix {
  
  /**
   * Returns the size (i.e., side length) of the largest square of ones found
   * within the first n rows and first n columns of mat. Assume n > 0 and
   * n <= mat.length.
   */
  public static int largestSubSquare(int[][] mat, int n) {
    assert n > 0 && n <= mat.length;

    return 1;
  }
  
  public static void main(String... args) {
    int[][] mat = {
        { 1, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 1, 0, 0 },
        { 0, 0, 1, 1, 1, 0, 0, 0 },
        { 0, 0, 1, 1, 1, 0, 1, 0 },
        { 0, 0, 1, 1, 1, 1, 1, 1 },
        { 0, 1, 0, 1, 1, 1, 1, 0 },
        { 0, 1, 0, 1, 1, 1, 1, 0 },
        { 0, 0, 0, 1, 1, 1, 1, 0 },
    };
    
    assert 1 == largestSubSquare(mat, 1);
    assert 1 == largestSubSquare(mat, 2);
    assert 1 == largestSubSquare(mat, 3);
    assert 2 == largestSubSquare(mat, 4);
    assert 3 == largestSubSquare(mat, 5);
    assert 3 == largestSubSquare(mat, 6);
    assert 3 == largestSubSquare(mat, 7);
    assert 4 == largestSubSquare(mat, 8);
  }
}
