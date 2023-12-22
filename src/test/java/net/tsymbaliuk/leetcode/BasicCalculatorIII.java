package net.tsymbaliuk.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicCalculatorIII {

    static class Solution {

        private static final Set<String> operators = Set.of("+", "-", "*", "/");

        public int calculate(String s) {
            String current = "0";
            LinkedList<String> stack = new LinkedList<>();
            char oper = '+';
            s+='+';
            for (char c : s.toCharArray()) {
                if (isNum(c)) {
                    current =  current + c;
                } else if (c != ' ') {
                    if (c == '(') {
                        stack.push("" + oper);
                        oper = '+';
                    } else {
                        if (oper == '+') {
                            stack.push(current);
                        } else if (oper == '-') {
                            stack.push('-' + current);
                        } else if (oper == '*') {
                            stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(current)));
                        } else if (oper == '/') {
                            stack.push(String.valueOf(Integer.parseInt(stack.pop()) / Integer.parseInt(current)));
                        }
                        current = "0";
                        oper = c;
                        if (c == ')') {
                            int res = 0;
                            while (!stack.isEmpty() && !operators.contains(stack.peek())) {
                                res += Integer.parseInt(stack.pop());
                            }
                            current = String.valueOf(res);
                            oper = stack.pop().charAt(0);
                        }

                    }
                }
            }

            int res = Integer.parseInt(current);
            while (!stack.isEmpty()) {
                res += Integer.parseInt(stack.pop());
            }

            return res;
        }

        static final boolean isNum(char c) {
            return '0' <= c && c <= '9';
        }
    }


    @Test
    public void testCase() {
        Solution s = new Solution();

        assertEquals(7, s.calculate("3+2*2"));
        assertEquals(1, s.calculate(" 3/2 "));
        assertEquals(5, s.calculate(" 3+5 / 2 "));

        assertEquals(2, s.calculate("1+1"));
        assertEquals(4, s.calculate("6-4/2"));
        assertEquals(21, s.calculate("2*(5+5*2)/3+(6/2+8)"));
    }
}
