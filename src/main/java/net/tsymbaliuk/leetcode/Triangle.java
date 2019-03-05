package net.tsymbaliuk.leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    /*
     [2      ],
     [3,4    ],
     [6,5,7  ],
     [4,1,8,3]
    */
    int size = triangle.size();
    if (size == 0)
      return 0;

    ListIterator<List<Integer>> lines = triangle.listIterator(triangle.size());
    List<Integer> line = lines.next();
    int[] dp = new int[size + 1];
    int i = 0;
    for (Integer cost : line) {
      dp[i++] = cost;
    }

    dp[i] = dp[i - 1];
    i = size - 2;
    for (; lines.hasNext(); i--) {
      int j = 0;
      line = lines.next();
      for (Integer cost : line) {
        dp[j] = cost + ((dp[j] < dp[j + 1]) ? dp[j] : dp[j + 1]);
        j++;
      }
    }
    return dp[0];
  }
}