package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallestSubseqOfDistinctChars {
    class Solution {
        public String smallestSubsequence(String s) {
            int[] last = new int[26];

            for (int i = 0; i < s.length(); i++) {
                last[s.charAt(i) - 'a'] = i;    // {a: 2, c: 7, b: 6, d: 4}
            }
            boolean[] used = new boolean[26];
            LinkedList<Character> deq = new LinkedList<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);   // 1, b

                if (used[c - 'a']) {    // [c: t, ]
                    continue;
                }

                while (!deq.isEmpty() && deq.getLast() > c && i < last[deq.getLast() - 'a']) {
                    used[deq.removeLast() - 'a'] = false;
                }

                deq.addLast(c);         // c
                used[c - 'a'] = true;
            }
            StringBuilder sb = new StringBuilder();
            for (char c : deq) {
                sb.append(c);
            }
            return new String(sb);
        }
    }


    @Test
    public void testCase() {
        Solution s = new Solution();

        assertEquals("acdb", s.smallestSubsequence("cbacdcbc"), "one");
        assertEquals("abc", s.smallestSubsequence("bcabc"), "two");

    }
}
