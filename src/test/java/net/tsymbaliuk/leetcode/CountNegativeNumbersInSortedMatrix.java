package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.SourceDataLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountNegativeNumbersInSortedMatrix {
    class Solution {
        public int countNegatives(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

            int N = grid.length, M = grid[0].length;
            if (grid[0][0] < 0) return M * N;
            if (grid[N-1][M-1] >=0) return 0;

            int l = 0, r = N - 1, p = 0;

            while (l < r) {
                p = (l + r) / 2;
                if (grid[p][M-1] < 0) {
                    r = p - 1;
                } else {
                    l = p + 1;
                }
            }
            if (grid[l][M-1] >= 0) l++;

            if (l >= N) return 0;

            int res = 0;

            for (int i = l; i < N; i++) {
                if (grid[i][0] < 0) return res + (N - i) * M;
                l = 0; r = M - 1;
                while (l < r) {
                    p = (l + r) / 2;

                    if (grid[i][p] >= 0) {
                        l = p + 1;
                    } else {
                        r = p - 1;
                    }
                }
                if (grid[i][l] >= 0) l++;
                res += M - l;
            }

            return res;
        }
    }


    @Test
    public void testCase() {
        Solution s = new Solution();
        int[][] matrix = new int[][]{{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
//        assertEquals(8, s.countNegatives(matrix), "case 1");
//
//        matrix = new int[][]{{3,2},{1,0}};
//        assertEquals(0, s.countNegatives(matrix), "case 2");
//
//        matrix = new int[][]{{-1,-2},{-3,-1}};
//        assertEquals(4, s.countNegatives(matrix), "case 3");


        matrix = new int[][]{{3,2},{-3,-3},{-3,-3},{-3,-3}};
        assertEquals(6, s.countNegatives(matrix), "case 4");

    }
}
