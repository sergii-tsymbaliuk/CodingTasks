package net.tsymbaliuk.atlassian;

import java.io.*;
import java.util.*;

/*

We are building a word processor and we would like to implement a "reflow" functionality that also applies full justification to the text.

Given an array containing lines of text and a new maximum width, re-flow the text to fit the new width. Each line should have the exact specified width. If any line is too short, insert '-' (as stand-ins for spaces) between words as equally as possible until it fits.

Note: we are using '-' instead of spaces between words to make testing and visual verification of the results easier.


lines = [ "The day began as still as the",
          "night abruptly lighted with",
          "brilliant flame" ]

reflowAndJustify(lines, 24) "reflow lines and justify to length 24" =>

        [ "The--day--began-as-still",
          "as--the--night--abruptly",
          "lighted--with--brilliant",
          "flame" ] // <--- a single word on a line is not padded with spaces

reflowAndJustify(lines, 25) "reflow lines and justify to length 25" =>

        [ "The-day-began-as-still-as"
          "the-----night----abruptly"
          "lighted---with--brilliant"
          "flame" ]

reflowAndJustify(lines, 26) "reflow lines and justify to length 26" =>

        [ "The--day-began-as-still-as",
          "the-night-abruptly-lighted",
          "with----brilliant----flame" ]

reflowAndJustify(lines, 40) "reflow lines and justify to length 40" =>

        [ "The--day--began--as--still--as-the-night",
          "abruptly--lighted--with--brilliant-flame" ]

reflowAndJustify(lines, 14) "reflow lines and justify to length 14" =>

        ['The--day-began',
         'as---still--as',
         'the------night',
         'abruptly',
         'lighted---with',
         'brilliant',
         'flame']

reflowAndJustify(lines, 15) "reflow lines and justify to length 15" =>

        ['The--day--began',
         'as-still-as-the',
         'night--abruptly',
         'lighted----with',
         'brilliant-flame']

lines2 = [ "a b", "c d" ]

reflowAndJustify(lines2, 20) "reflow lines2 and justify to length 20" =>

        ['a------b-----c-----d']

reflowAndJustify(lines2, 4) "reflow lines2 and justify to length 4" =>

        ['a--b',
         'c--d']

reflowAndJustify(lines2, 2) "reflow lines2 and justify to length 2" =>

        ['a',
         'b',
         'c',
         'd']

All Test Cases:
                 lines, reflow width
reflowAndJustify(lines, 24)
reflowAndJustify(lines, 25)
reflowAndJustify(lines, 26)
reflowAndJustify(lines, 40)
reflowAndJustify(lines, 14)
reflowAndJustify(lines, 15)
reflowAndJustify(lines2, 20)
reflowAndJustify(lines2, 4)
reflowAndJustify(lines2, 2)

n = number of words OR total characters

*/
public class Solution {
    List<String> wrapLines(String[] words, int maxLength) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if ((sb.length() + w.length() + 1) <= maxLength) {
                if (sb.length() != 0) {
                    sb.append('-');
                }
                sb.append(w);
            } else {
                res.add(sb.toString());
                sb = new StringBuilder();
                sb.append(w);
            }
        }

        if (sb.length() > 0) {
            res.add(sb.toString());
        }
        return res;
    }

    List<String> reflowAndJustify(String[] lines, int maxLength) {
        List<String> res = new ArrayList<>();
        List<String> sb = new ArrayList();
        int currentLength = 0;
        for (String l : lines) {
            String [] words = l.split(" ");
            for (String w : words) {
                if (currentLength == 0) {
                    sb.add(w);
                    currentLength += w.length();
                } else if (currentLength + w.length() + 1 <= maxLength) {
                    currentLength += 1 + w.length();
                    sb.add("-" + w);
                } else {
                    res.add(hiphenString(sb, maxLength, currentLength));
                    sb = new ArrayList<>();
                    sb.add(w);
                    currentLength = w.length();
                }
            }
        }

        if (sb.size() > 0) {
            res.add(hiphenString(sb, maxLength, currentLength));
        }
        return res;
    }

    public String hiphenString(List<String> words, int maxLen, int lineLength) {
        if (words.size() > 1) {
            int extraHiphens = maxLen - lineLength;

            while (extraHiphens > 0) {
                for (int i = 1; extraHiphens > 0 && i < words.size(); i++) {
                    words.set(i, "-" + words.get(i));
                    --extraHiphens;
                }
            }
        }
        return String.join("", words);
    }

    public static void main(String[] argv) {
        String[] lines = {"The day began as still as the","night abruptly lighted with","brilliant flame"};
        String[] lines2 = {"a b","c d"};


        Solution s = new Solution();

        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 24)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 25)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 26)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 40)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 14)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines, 15)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines2, 20)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines2, 4)));
        System.out.println("\n\n" + String.join("\n", s.reflowAndJustify(lines2, 2)));

//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words1, 13)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words1, 12)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words1, 20)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words2, 5)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words2, 30)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words3, 5)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words4, 5)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words5, 20)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words6, 20)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words6, 4)));
//        System.out.println("\n\n" + String.join("\n", s.wrapLines(words6, 1)));
        // TODO --- Run the test cases from above through your function, printing the returned results
    }
    // TODO --- Write your function, returning the result
}
