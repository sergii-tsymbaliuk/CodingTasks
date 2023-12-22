package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreOfAnArray {
    class Solution {
        public int findShortestSubArray(int[] nums) {
            HashMap<Integer, Integer> freq = new HashMap<>();
            HashMap<Integer, Integer> start = new HashMap<>();

            int maxF = nums[0];
            int minLen = 1;
            freq.put(nums[0], 0);

            for (int i = 0; i < nums.length; i++ ) {
                if (!start.containsKey(nums[i])) {
                    start.put(nums[i], i);
                }
                freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
                if (maxF == nums[i] || freq.get(nums[i]) > freq.get(maxF)) {
                    maxF = nums[i];
                    minLen = i - start.get(nums[i]) + 1;
                } else if (freq.get(nums[i]) == freq.get(maxF)) {
                    minLen = Math.min(minLen, i - start.get(nums[i]) + 1);
                }
            }
            return minLen;
        }
    }


    @Test
    public void testCases() {
        Solution s = new Solution();

        // assertEquals(2, s.findShortestSubArray(new int[]{1,2,2,3,1}), "case 1");
        assertEquals(6, s.findShortestSubArray(new int[]{1,2,2,3,1,4,2}), "case 2");
    }
}
