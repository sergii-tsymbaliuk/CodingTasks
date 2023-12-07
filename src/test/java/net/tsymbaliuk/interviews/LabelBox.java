package net.tsymbaliuk.interviews;
import java.io.*;
import java.util.*;

public class LabelBox {
/*
Given an encoded string of the format "n[encoded_string]" where n is a positive integer, print out the decoded string which is encoded_string repeated n time(s).

Examples:

Input: "3[a]"
Output: "aaa"


Input: "2[a]3[bc]"
Output: "aabcbcbc"


Input: "2[a3[c]]"
Output: "acccaccc"
 */

    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */

    static class Solution {
        public boolean isValid(String s) {
            // "3[a]"
            //
            int stack = 0;
            for (int i = 0; i < s.length(); i++) {
                if (isNumber(s.charAt(i))) {
                    if (i + 1 == s.length()) {
                        return false;
                    }
                    if ( !isNumber(s.charAt(i + 1)) && '[' != s.charAt(i + 1)) {
                        return false;
                    }
                } else if (s.charAt(i) == '[') {
                    if (i == 0 || !isNumber(s.charAt(i - 1))) {
                        return false;
                    }
                    stack++;
                } else if (s.charAt(i) == ']') {
                    stack--;
                    if (stack < 0) return false;
                }
            }
            return stack == 0;
        }

        public String decode(String s/* encoded string */) {

            if (!isValid(s)) {
                throw new IllegalArgumentException("Encoded string synthax error: " + s);
            }

            StringBuilder sb  = new StringBuilder();

            int num = -1;
            int i = 0;

            while (i < s.length()) {
                char c = s.charAt(i);
                if (c == ']') { // subproblem
                    int subStrPos = sb.length() - 1;
                    while(subStrPos >=0 && sb.charAt(subStrPos) != '[') {
                        subStrPos--;
                    }
                    // subStrPos -> '['
                    int numEnd = subStrPos;
                    int numStart = numEnd - 1;
                    while (numStart >= 0 && isNumber(sb.charAt(numStart))) {
                        numStart--;
                    }
                    num = Integer.parseInt(sb.substring(numStart + 1, numEnd));

                    String subStr = sb.substring(subStrPos + 1, sb.length());

                    sb.setLength(numStart + 1);

                    for (int k = 0; k < num; k++) {
                        sb.append(subStr);
                    }
                } else {
                    sb.append(c);
                }
                i++;
            }

            return sb.toString();
        }

        boolean isNumber(char c) {
            return c >= '0' && c <= '9';
        }

        public static void main(String[] args) {
            Solution s = new Solution();

            System.out.println("Actual:" + s.decode("3[a]"));
            System.out.println("Expected:" + "aaa");

            System.out.println("Actual:" + s.decode("2[a]3[bc]"));
            System.out.println("Expected:" + "aabcbcbc");

            System.out.println("Actual:" + s.decode("2[a3[c]]"));
            System.out.println("Expected:" + "acccaccc");


            System.out.println("is valid: " + s.isValid("[]"));
            System.out.println("is valid: " + s.isValid("3["));
            System.out.println("is valid: " + s.isValid("3"));
            System.out.println("is valid: " + s.isValid("3]"));

            System.out.println("is valid: " + s.isValid("3[a]"));
            System.out.println("is valid: " + s.isValid("2[a]3[bc]"));
            System.out.println("is valid: " + s.isValid("2[a3[c]]"));

        }
    }

}
