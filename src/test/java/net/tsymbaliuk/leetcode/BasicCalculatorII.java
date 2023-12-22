package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCalculatorII {

    static class Solution {

        public int calculate(String s) {
            int current = 0;
            LinkedList<Integer> stack = new LinkedList<>();
            char oper = '+';
            s+='+';
            for (char c : s.toCharArray()) {
                if (isNum(c)) {
                    current = 10 * current + (c - '0');
                } else if (c != ' '){
                    if (oper == '+') {
                        stack.push(current);
                    } else if (oper == '-') {
                        stack.push(-current);
                    } else if (oper == '*') {
                        stack.push(stack.pop() * current);
                    } else /* c == '/' */{
                        stack.push(stack.pop() / current);
                    }
                    current = 0;
                    oper = c;
                }
            }

            while (!stack.isEmpty()) {
                current += stack.pop();
            }

            return current;
        }

        static final boolean isNum(char c) {
            return '0' <= c && c <= '9';
        }
    }


    @Test
    public void testCase() {
        Solution s = new Solution();

        //assertEquals(7, s.calculate("3+2*2"));
        assertEquals(1, s.calculate(" 3/2 "));
        assertEquals(5, s.calculate(" 3+5 / 2 "));

    }
}
