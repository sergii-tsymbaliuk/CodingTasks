package net.tsymbaliuk.meta;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A family of frogs in a pond are traveling towards dry land to hibernate.
 * They hope to do so by hopping across a trail of N lily pads, numbered from 1 to N in order.
 * There are F frogs, numbered from 1 to F. Frog i is currently perched atop lily pad Pi.
 * No two frogs are currently on the same lily pad. Lily pad N is right next to the shore, and none of the frogs are initially on lily pad  N.
 * Each second, one frog may hop along the trail towards lily pad N.
 * When a frog hops, it moves to the nearest lily pad after its current lily pad which is not currently occupied
 * by another frog (hopping over any other frogs on intermediate lily pads along the way).
 * If this causes it to reach lily pad N, it will immediately exit onto the shore.
 * Multiple frogs may not simultaneously hop during the same second.
 * Assuming the frogs work together optimally when deciding which frog should hop during each second,
 * determine the minimum number of seconds required for all F of them to reach the shore.
 * Constraints
 * 2 ≤ N ≤ 10^12
 * 1 ≤ F ≤ 500,000
 * 1 ≤ Pi ≤ N−1
 */
public class Hops {
    class Solution {
        public long getSecondsRequired(long N, int F, long[] P) {
            // Write your code here
            long hops = 0;
            Arrays.sort(P);
            for (int i = 1; i < P.length; i++) {
                hops += P[i] - P[i-1] - 1;
            }
            hops += (F + N - P[P.length - 1] - 1) ;
            return hops;
        }
    }

    @Test
    public void testCase() {
        Solution s = new Solution();

        assertEquals(2, s.getSecondsRequired(3, 1, new long[]{1}));
        assertEquals(4, s.getSecondsRequired(6, 3, new long[]{5, 2, 4}));
    }
}
