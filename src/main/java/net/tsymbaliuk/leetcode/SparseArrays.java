package net.tsymbaliuk.leetcode;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Created by stsym on 12/23/2016.
 */

public class SparseArrays {

  static class Solution {

    public static void main(String[] args) {
      /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner scanner = new Scanner(System.in);
      LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
      int numWords = scanner.nextInt();
      scanner.nextLine();
      for (int i = 0; i < numWords; i++) {
        String word = scanner.nextLine();
        if (words.keySet().contains(word)) {
          words.put(word, words.get(word) + 1);
        } else {
          words.put(word, 1);
        }
      }

      LinkedHashMap<String, Integer> queries = new LinkedHashMap<>();
      int numQueries = scanner.nextInt();
      scanner.nextLine();
      for (int i = 0; i < numQueries; i++) {
        String query = scanner.nextLine();
        if (words.keySet().contains(query)) {
          queries.put(query, words.get(query));
        } else {
          queries.put(query, 0);
        }
      }

      queries.forEach((s, n) -> System.out.println(n));
    }
  }

  public static void main(String[] args) {
    Solution.main(args);
  }
}
