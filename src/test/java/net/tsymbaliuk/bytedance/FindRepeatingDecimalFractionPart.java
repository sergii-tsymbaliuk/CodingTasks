package net.tsymbaliuk.bytedance;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 Giving two integers, n as numerator, and d as denominator, please return a string which represents the repeating numbers after the decimal point.

 example input: n = 1, d = 9, output: "1"  1 / 9 = 0.111111111

 example input: n = 71, d = 70, output: "142857"  71/70 = 1.0142857142857....
 71 | 70
 70 | 10142
 100
 70
 300
 280
 200
 140
 */

public class FindRepeatingDecimalFractionPart {

    static String addNumbers(int n, int d) {
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        // n    d
        // 1    9
        int remainder = (n % d) * 10; // 1
        while (true) {
            if (map.containsKey(remainder)) { // no
                return sb.substring(map.get(remainder), sb.length());
            } else {
                sb.append(remainder / d); // 1
                map.put(remainder, sb.length() - 1); // 10, 0;
                remainder = (remainder % d) * 10; // 1
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(addNumbers(1, 2));
        System.out.println(addNumbers(1, 99));
        System.out.println(addNumbers(1, 9));
        System.out.println(addNumbers(71, 70));

    }


    @Test
    public void testCase() {
        assertEquals("0", addNumbers(1, 2), "1, 2");
        assertEquals("01", addNumbers(1, 99), "1, 99");
        assertEquals("1", addNumbers(1, 9), "1, 9");
        assertEquals("142857", addNumbers(71, 70), "71, 70");
    }
}
