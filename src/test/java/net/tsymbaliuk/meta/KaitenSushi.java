package net.tsymbaliuk.meta;

// Write any import statements here

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * There are N dishes in a row on a kaiten belt, with the ith dish being of type Di. Some dishes may be of the same type as one another.
 * You're very hungry, but you'd also like to keep things interesting.
 * The N dishes will arrive in front of you, one after another in order, and
 * for each one you'll eat it as long as it isn't the same type as any of the previous K dishes you've eaten. Y
 * ou eat very fast, so you can consume a dish before the next one gets to you.
 * Any dishes you choose not to eat as they pass will be eaten by others.
 * Determine how many dishes you'll end up eating.
 * Please take care to write a solution which runs within the time limit.
 * Constraints:
 * 1 ≤ N ≤ 500,000
 * 1 ≤ K ≤ N
 * 1 ≤ 1,000,000
 */

class KaitenSushi {
    class Solution {

        public int getMaximumEatenDishCount(int N, int[] D, int K) {

            HashSet<Integer> eatenDishes = new HashSet<>();
            Deque<Integer> deque = new LinkedList<>();

            int res = 0;
            for (int d : D) {
                if (!eatenDishes.contains(d)) {
                    res++;
                    eatenDishes.add(d);
                    deque.offer(d);
                    if (deque.size() > K) {
                        eatenDishes.remove(deque.pop());
                    }
                }
            }
            return res;
        }
    }

    private static class TestInput {
        int N;
        int[] D;
        int K;
        int expected;
        String message;

        public TestInput(final int n, final int[] d, final int k, final int expected, final String message) {
            N = n;
            D = d;
            K = k;
            this.expected = expected;
            this.message = message;
        }
    }

    private TestInput[] testInputs = {
            new TestInput(6, new int[]{1, 2, 3, 3, 2, 1}, 1, 5, "Case 1"),
            new TestInput(6, new int[]{1, 2, 3, 3, 2, 1}, 2, 4, "Case 2"),
            new TestInput(7, new int[]{1, 2, 1, 2, 1, 2, 1}, 2, 2, "Case 3")
    };

    @Test
    public void testCase1() {

        wrapper(testInputs[0]);
        wrapper(testInputs[1]);
        wrapper(testInputs[2]);
    }

    public void wrapper(TestInput input) {
        Solution s = new Solution();
        int actual = s.getMaximumEatenDishCount(input.N, input.D, input.K);
        assertEquals(input.expected, actual, input.message);
    }
}

