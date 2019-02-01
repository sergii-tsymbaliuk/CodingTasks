package WildcardStringMatching;

public class WildcardStringMatch {

  /**
   * Check if string s matches pattern p.
   *
   * @param string String to check
   * @param pattern Pattern
   * @return true if matches
   */
  public static boolean match(String string, String pattern) {
    char[] s = string.toCharArray();
    char[] p = pattern.toCharArray();
    boolean[][] dp = new boolean[s.length + 1][p.length + 1];

    dp[0][0] = true;
    for(int j = 1; j <= p.length; j++) {
      dp[0][j] = dp[0][j - 1] && p[j - 1] == '*';
    }

    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[i].length; j++){
        if (p[j-1] == '*') {
          dp[i][j] = dp[i - 1][j - 1] || dp[i-1][j] || dp[i][j-1] ;
        } else if (p[j-1] == '.' || s[i-1] == p[j-1]) {
          dp[i][j] = dp[i - 1][j - 1];
        } else
          dp[i][j] = false;
      }
    }
    return dp[s.length][p.length];
  }
}
