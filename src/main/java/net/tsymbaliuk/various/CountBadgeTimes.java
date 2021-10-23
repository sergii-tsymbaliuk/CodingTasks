package net.tsymbaliuk.various;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We are working on a security system for a badged-access room in our company's building.

We want to find employees who badged into our secured room unusually often. We have an unordered list of names and entry times over a single day. Access times are given as numbers up to four digits in length using 24-hour time, such as "800" or "2250".

Write a function that finds anyone who badged into the room three or more times in a one-hour period. Your function should return each of the employees who fit that criteria, plus the times that they badged in during the one-hour period. If there are multiple one-hour periods where this was true for an employee, just return the earliest one for that employee.

badge_times = [
  ["Paul",      "1355"], ["Jennifer",  "1910"], ["Jose",    "835"],
  ["Jose",       "830"], ["Paul",      "1315"], ["Chloe",     "0"],
  ["Chloe",     "1910"], ["Jose",      "1615"], ["Jose",   "1640"],
  ["Paul",      "1405"], ["Jose",       "855"], ["Jose",    "930"],
  ["Jose",       "915"], ["Jose",       "730"], ["Jose",    "940"],
  ["Jennifer",  "1335"], ["Jennifer",   "730"], ["Jose",   "1630"],
  ["Jennifer",     "5"], ["Chloe",     "1909"], ["Zhang",     "1"],
  ["Zhang",       "10"], ["Zhang",      "109"], ["Zhang",   "110"],
  ["Amos",         "1"], ["Amos",         "2"], ["Amos",    "400"],
  ["Amos",       "500"], ["Amos",       "503"], ["Amos",    "504"],
  ["Amos",       "601"], ["Amos",       "602"],
];

Expected output (in any order)
   Paul: 1315 1355 1405
   Jose: 830 835 855 915 930
   Zhang: 10 109 110
   Amos: 500 503 504
                  Right
   Jose: 730 830 835 855 915 930 940 1615 1630 1640
        left
n: length of the badge records array

*/
public class CountBadgeTimes {


    private static Map<String, List<Integer>> buildMap(String[][] badgeTimes) {
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

        for (String [] bt : badgeTimes) {

            if (!map.containsKey(bt[0])) {
                map.put(bt[0], new ArrayList<>());
            }
            List<Integer> times = map.get(bt[0]);
            int num = Integer.valueOf(bt[1]);
            times.add(num / 100 * 60 + num % 100);
        }

        return map;
    }

    private static List<Integer> countEntriesWithingHour(List<Integer> entries) {
        Collections.sort(entries);
        int left = 0;
        int right = 0;
        for (; right < entries.size(); right++) {
            if (entries.get(right) - entries.get(left) <= 60) {
                continue;
            } else {
                if (right - left >= 3) {
                    List<Integer> result = new ArrayList<>();
                    for (; left < right; left ++) {
                        result.add(toMil(entries.get(left)));
                    }
                    return result;
                } else {
                    while (left <= right && entries.get(right) - entries.get(left) >= 60) {
                        left++;
                    }
                }
            }
        }
        if (right - left >= 3 && entries.get(right - 1) - entries.get(left) <= 60) {
            List<Integer> result = new ArrayList<>();
            for (; left < right; left ++) {
                result.add(toMil(entries.get(left)));
            }
            return result;
        }
        return List.of();
    }

    private static int toMil(int minutes) {
        return minutes / 60 * 100 + minutes % 60;
    }

    public static Map<String, List<Integer>> find1HTerms(String[][] badgeTimes) {
        Map<String, List<Integer>> times = buildMap(badgeTimes);
        // System.out.println("Parsed times: " + times);

        Map<String, List<Integer>> result = new HashMap<>();

        times.forEach((k, v) -> {
            List<Integer> entriesIn1H = countEntriesWithingHour(v);
            if (!entriesIn1H.isEmpty()) {
                result.put(k, entriesIn1H);
            }
        });
        return result;
    }



    public static void main(String[] argv) {
        String[][] badgeTimes = new String[][] {
                {"Paul", "1355"},
                {"Jennifer", "1910"},
                {"Jose", "835"},
                {"Jose", "830"},
                {"Paul", "1315"},
                {"Chloe", "0"},
                {"Chloe", "1910"},
                {"Jose", "1615"},
                {"Jose", "1640"},
                {"Paul", "1405"},
                {"Jose", "855"},
                {"Jose", "930"},
                {"Jose", "915"},
                {"Jose", "730"},
                {"Jose", "940"},
                {"Jennifer", "1335"},
                {"Jennifer", "730"},
                {"Jose", "1630"},
                {"Jennifer", "5"},
                {"Chloe", "1909"},
                {"Zhang", "1"},
                {"Zhang", "10"},
                {"Zhang", "109"},
                {"Zhang", "110"},
                {"Amos", "1"},
                {"Amos", "2"},
                {"Amos", "400"},
                {"Amos", "500"},
                {"Amos", "503"},
                {"Amos", "504"},
                {"Amos", "601"},
                {"Amos", "602"},
        };

        Map<String, List<Integer>> map = find1HTerms(badgeTimes);

        System.out.println("Result: " + map);

    }
}

