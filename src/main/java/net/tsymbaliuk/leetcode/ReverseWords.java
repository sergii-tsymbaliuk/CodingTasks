package net.tsymbaliuk.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by stsym on 12/12/2016.
 */
public class ReverseWords {
    public static class Solution {
        public String reverseWords(String s) {
            if (s == null)
                return null;

            s = s.trim();

            if (s.length() == 0)
                return "";

            List<String> words = Arrays.asList(s.split("\\s+"));
            Collections.reverse(words);
            return String.join(" ", words);
        }
    }


    public static void testDivision(){
        Solution solution = new Solution();

//        System.out.println(solution.divide(4, 2)==2);
//        System.out.println(solution.divide(1, 2)==0);
//        System.out.println(solution.divide(25, 4)==6);
//        System.out.println(solution.divide(25, -4)==-6);
//        System.out.println(solution.divide(-25, 4)==-6);
//        System.out.println(solution.divide(-25, -4)==6);
//        System.out.println(solution.divide(3, -1)==-3);
//        System.out.println(solution.divide(3, -2)==-1);
//        System.out.println(solution.divide(3,  2)== 1);
//        System.out.println(solution.divide(-2147483648, -1)==2147483647);
//        System.out.println(solution.divide(-2147483648, 2)==-1073741824);

        System.out.println(solution.reverseWords(""));
        System.out.println(solution.reverseWords("\n"));
        System.out.println(solution.reverseWords(null));
        System.out.println(solution.reverseWords("This is test"));

//        System.out.println("MAX_INT -2: " + (Integer.MAX_VALUE + ~(-2)));
//        System.out.println("~-2147483648+1: " + (-2147483648-1));
//        System.out.println("~(-2147483648+1): " + ~(-2147483648+1));
//        System.out.println("~-1: " + ~-1);
    }

    public static void main(String [] args){
        //bitwiceOperations();
        testDivision();
    }
}
