package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Note: Chapter 2 is a harder version of this puzzle. The only difference is a larger set of possible problem point values.
 * You are spectating a programming contest with N competitors, each trying to independently solve the same set of programming problems. Each problem has a point value, which is either 1 or 2.
 * On the scoreboard, you observe that the ith competitor has attained a score of Si, which is a positive integer equal to the sum of the point values of all the problems they have solved.
 * The scoreboard does not display the number of problems in the contest, nor their point values. Using the information available, you would like to determine the minimum possible number of problems in the contest.
 * Constraints
 * 1 ≤ N ≤ 500,000

 * 1 ≤ Si ≤ 1,000,000,000
 */
public class ScoreboardInference {

    class Solution {

        public int getMinProblemCount(int N, int[] S) {
            // Write your code here
            if (S == null || S.length == 0) return 0;

            int max = S[0];
            boolean hasOdd = false;
            for (int s : S) { // O(n)
                max = Math.max(max, s);
                hasOdd = hasOdd || (s % 2 > 0);
            }
            return max / 2 + ((hasOdd) ? 1 : 0);
        }
    }


    @Test
    public void testCase() {
        Solution s = new Solution();
        assertEquals(4, s.getMinProblemCount(6, new int[]{1, 2, 3, 4, 5, 6}));
        assertEquals(3, s.getMinProblemCount(4, new int[]{4, 3, 3, 4}));
        assertEquals(4, s.getMinProblemCount(4, new int[]{2, 4, 6, 8}));
    }
}
