package net.tsymbaliuk.leetcode;

import java.util.List;

public class Triangle {
  public int minimumTotal(List<List<Integer>> t) {
    if (t == null || t.isEmpty()) {
      return 0;
    }
    final int s = t.size();
    final int[] dp = new int[s];
    List<Integer> c = t.get(s - 1);
    for (int i = 0; i < s; i++)
      dp[i] = c.get(i);

    for (int i = s - 2; i >= 0; i--) {
      c = t.get(i);
      for (int j = 0; j <= i; j++) {
        dp[j] = c.get(j) + ((dp[j] < dp[j + 1]) ? dp[j] : dp[j + 1]);
      }
    }
    return dp[0];
  }
}
