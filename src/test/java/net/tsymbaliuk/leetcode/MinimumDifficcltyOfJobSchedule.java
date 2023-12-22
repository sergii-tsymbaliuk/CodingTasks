package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumDifficcltyOfJobSchedule {
    @Test
    public void testSolution() {
        Solution s = new Solution();

        assertEquals(7, s.minDifficulty(new int[]{6,5,4,3,2,1}, 2), "Case 1");
        assertEquals(-1, s.minDifficulty(new int[]{9,9,9}, 4), "Case 2");
        assertEquals(3, s.minDifficulty(new int[]{1,1,1}, 3), "Case 3");
        assertEquals(843, s.minDifficulty2(new int[]{ 11, 111,  22, 222,  33, 333,  44, 444}, 6), "Case 4");
        assertEquals(843, s.minDifficulty(new int[]{ 11, 111,  22, 222,  33, 333,  44, 444}, 6), "Case 4");
    }

}

class Solution {
    int [][] meme;

    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        int n = jobDifficulty.length;

        int [][]dp = new int[d][n];

        dp[0][n - 1] = jobDifficulty[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[0][i] = Math.max(dp[0][i + 1], jobDifficulty[i]);
        }

        for (int days = 1; days < d; days++) {
            for (int i = d - days - 1; i < n - days ; i++) {
                int hardest = Integer.MIN_VALUE;
                int minDiff = Integer.MAX_VALUE;
                for (int j = i; j < n - days; j++) {
                    hardest = Math.max(hardest, jobDifficulty[j]);
                    minDiff = Math.min(minDiff, hardest + dp[days - 1][j + 1]);
                }
                dp[days][i] = minDiff;
            }
        }
        return dp[d - 1][0];
    }

    public int minDifficultyBad(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;

        if (jobDifficulty.length < d) return -1;

        meme = new int[d + 1][n];
        meme[0][n - 1] = jobDifficulty[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            meme[0][i] = jobDifficulty[i];
        }

        int minSchedule = Integer.MAX_VALUE;
        for (int days = 1; days <= d; days++) {
            for (int i = 0; i < n - days; i++) {
                minSchedule = Integer.MAX_VALUE;
                int maxJob = Integer.MIN_VALUE;
                for (int j = i; j < n - days; j++) {
                    maxJob = Math.max(maxJob, jobDifficulty[j]);
                    minSchedule = Math.min(minSchedule, maxJob + meme[days - 1][j + 1]);
                }
                meme[days][i] = minSchedule;
            }
        }
        return minSchedule;
    }

    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        // If we cannot schedule at least one job per day,
        // it is impossible to create a schedule
        if (n < d) {
            return -1;
        }

        int dp[][] = new int[d + 1][n];
        for (int[] row: dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Set base cases
        dp[d][n - 1] = jobDifficulty[n - 1];

        // On the last day, we must schedule all remaining jobs, so dp[i][d]
        // is the maximum difficulty job remaining
        for (int i = n - 2; i >= 0; i--) {
            dp[d][i] = Math.max(dp[d][i + 1], jobDifficulty[i]);
        }

         for (int day = d - 1; day > 0; day--) {
//        for (int day_ = 1 ; day_ < d ; day_++) {
//            int day = d - day_;
            for (int i = day - 1; i < n - (d - day); i++) {
                int hardest = 0;
                // Iterate through the options and choose the best
                for (int j = i; j < n - (d - day); j++) {
                    hardest = Math.max(hardest, jobDifficulty[j]);
                    // Recurrence relation
                    dp[day][i] = Math.min(dp[day][i], hardest + dp[day + 1][j + 1]);
                }
            }
        }

        return dp[1][0];
    }


//    int getMinDifficulty(int[] jobDiff, int start, int d, int days) {
//        if (meme[start][days] == -1) {
//            int maxDiff = jobDiff[start];
//            int minDiff = Integer.MAX_VALUE;
//
//            for (int i = start; i + days < jobDiff.length; i++) {
//                maxDiff = Math.max(maxDiff, jobDiff[i]);
//                if (days > 0) {
//                    minDiff = Math.min(minDiff, maxDiff + getMinDifficulty(jobDiff, i + 1, days));
//                } else {
//                    minDiff = Math.min(minDiff, maxDiff);
//                }
//
//            }
//            meme[start][days] = minDiff;
//        }
//        return meme[start][days];
//    }
}
