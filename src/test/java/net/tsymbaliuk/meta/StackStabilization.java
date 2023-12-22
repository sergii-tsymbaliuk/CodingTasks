package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * There's a stack of N inflatable discs, with the ith disc from the top having an initial radius of R[i] inches.
 * The stack is considered unstable if it includes at least one disc whose radius is larger than or equal to that of the disc directly under it. In other words, for the stack to be stable, each disc must have a strictly smaller radius than that of the disc directly under it.
 * As long as the stack is unstable, you can repeatedly choose any disc of your choice and deflate it down to have a radius of your choice which is strictly smaller than the disc’s prior radius. The new radius must be a positive integer number of inches.
 * Determine the minimum number of discs which need to be deflated in order to make the stack stable, if this is possible at all.
 * If it is impossible to stabilize the stack, return −1 instead.
 * Constraints
 * 1 ≤ N ≤ 50
 * 1 ≤ R[i] ≤1,000,000,000
 */
public class StackStabilization {
    class Solution {

        public int getMinimumDeflatedDiscCount(int N, int[] R) {
            // Write your code here
            if (R == null || R.length == 0) return 0;
            int res = 0;

            for (int i = N - 1; i > 0; i--) {
                if (R[i] < i) return -1;
                if (R[i - 1] >= R[i]) {
                    res++;
                    R[i - 1] = R[i] - 1;
                }
            }
            if (R[0] <= 0) return -1;
            return res;
        }

    }

    @Test
    public void testCase() {
        Solution s = new Solution();
        assertEquals(3, s.getMinimumDeflatedDiscCount(5, new int[]{2, 5, 3, 6, 5}), "case1");
        assertEquals(2, s.getMinimumDeflatedDiscCount(3, new int[]{100, 100, 100}), "case2");
        assertEquals(-1, s.getMinimumDeflatedDiscCount(4, new int[]{6, 5, 4, 3}), "case3");
    }
}
