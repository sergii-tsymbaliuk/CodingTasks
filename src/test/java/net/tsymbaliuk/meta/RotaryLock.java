package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * You're trying to open a lock. The lock comes with a wheel which has the integers from 1 to N
 * arranged in a circle in order around it (with integers 1 and N adjacent to one another).
 * The wheel is initially pointing at 1.
 * For example, the following depicts the lock for N=10 (as is presented in the second sample case).
 * <p>
 * It takes 1 second to rotate the wheel by 1 unit to an adjacent integer in either direction,
 * and it takes no time to select an integer once the wheel is pointing at it.
 * The lock will open if you enter a certain code.
 * The code consists of a sequence of M integers, the ith of which is Ci.
 * <p>
 * Determine the minimum number of seconds required to select all M of the code's integers in order.
 * Please take care to write a solution which runs within the time limit.
 * <p>
 * Constraints:
 * 3 ≤ N ≤50,000,000
 * 1 ≤ M ≤ 1,000
 * 1 ≤ Ci ≤ N
 */


public class RotaryLock {
    class Solution {

        public long getMinCodeEntryTime(int N, int M, int[] C) {
            // Write your code here
            if (C == null || C.length == 0) return 0;

            int prev = 1;
            long time = 0;
            for (int i = 0; i < C.length; i++) {
                int cur = C[i];                   // 8
                int diff = Math.abs(prev - cur);  // 0
                time += Math.min(diff, N - diff); // 5 + 0
                prev = cur;                       // 4
            }
            return time;
        }

    }


    @Test
    public void test() {
        Solution s = new Solution();
        assertEquals(2, s.getMinCodeEntryTime(3, 3, new int[]{1, 2, 3}), "case1");
        assertEquals(11, s.getMinCodeEntryTime(10, 4, new int[]{9, 4, 4, 8}), "case2");
    }
}
