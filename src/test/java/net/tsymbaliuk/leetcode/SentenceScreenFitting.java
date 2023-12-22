package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SentenceScreenFitting {
    class Solution {
        public int wordsTyping(String[] sentence, int rows, int cols) {
            StringBuilder sb = new StringBuilder();
            for (String s : sentence) {
                sb.append(s).append(' ');
            }
            String s = sb.toString();
            int len = 0;
            for (int i = 0; i < rows; i++) {
                len += cols;
                while (s.charAt(len % s.length()) != ' ') len--;
                len++;
            }
            return len / s.length();
        }
    }




    @Test
    public void testCase() {
        Solution s = new Solution();

        assertEquals(1, s.wordsTyping(new String[]{"hello", "world"}, 2, 8));
        assertEquals(2, s.wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6));
        assertEquals(1, s.wordsTyping(new String[]{"i","had","apple","pie"}, 4, 5));
        assertEquals(10, s.wordsTyping(new String[]{"f","p","a"}, 8, 7));
    }
}
