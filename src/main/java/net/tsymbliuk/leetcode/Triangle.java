package net.tsymbliuk.leetcode;

import java.util.List;

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

    int[] dp = new int[size + 1];
    List<Integer> curr = triangle.get(size - 1);
    for (int i = 0; i < size; i++) {
      dp[i] = curr.get(i);
    }
    dp[size] = curr.get(size - 1);

    for (int i = triangle.size() - 2; i >= 0; i--) {
      curr = triangle.get(i);
      for (int j = 0; j <= i; j++) {
        dp[j] = curr.get(j) + ((dp[j] < dp[j + 1]) ? dp[j] : dp[j + 1]);
      }
    }
    return dp[0];
  }
}