package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3 {
    static class Solution {

        public double getHitProbability(int R, int C, int[][] G) {
            // Write your code here
            if (G == null || G.length == 0 || G[0].length == 0) {
                return 0;
            }
            double ships = 0.0;
            for (int[] row : G) {
                for (int cell : row) {
                    ships += cell;
                }
            }
            return ((double)ships) / G.length * G[0].length;
        }

    }


    @Test
    public void testCase() {
        Solution s = new Solution();

        double p = s.getHitProbability(2, 3, new int[][]{
        {0, 0, 1},
        {1, 0, 1}});

        assertTrue(doubleEqual(p, 0.50000000, 1.0/1000000));

        p = s.getHitProbability(2, 2, new int[][]{{1, 1},{1, 1}});

        assertTrue(doubleEqual(p, 1.00000000, 1.0/1000000));
    }

    boolean doubleEqual(double a, double b, double precision) {
        return Math.abs(a - b) < precision;
    }
}
