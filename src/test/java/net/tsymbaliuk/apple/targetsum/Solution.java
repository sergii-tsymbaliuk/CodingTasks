package net.tsymbaliuk.apple.targetsum;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    /*
     * Click `Run` to execute the snippet below!
     */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * Find all solutions where the numbers sum to a given target. Same numbers may be chosen from candidates unlimited number of times.  All numbers are positive.

Example: Given [2,6,3,7] Target 7
    - { [7], [2,2,3] }
      Given [5,3,2] Target 8
    - { [2,2,2,2], [2,3,3], [3,5] }
 */

    public List<List<Integer>> count(List<Integer> candidates, int target) {

        List<List<Integer>>[] sum = new List[target + 1];
        for (int i = 0; i <= target; i ++) {
            sum[i] = new ArrayList<>();
        }
        sum[0].add(List.of());

        for (int c : candidates) {
            for (int s = c; s <= target; s ++) {
                int remainder = s - c;
                for (List<Integer> nums : sum[remainder]) {
                    List<Integer> res = new ArrayList<>(nums);
                    res.add(c);
                    sum[s].add(res);
                }
            }
        }

        return sum[target];
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<List<Integer>> actual1 = s.count(List.of(2,6,3,7), 7);
        List<String> actual1String = actual1.stream().map(List::toString).collect(Collectors.toList());
        System.out.println("Expected: { [7], [2,2,3] }, Actual: " +
                String.join(", ", actual1String));

        List<List<Integer>> actual2 =  s.count(List.of(5,3,2), 8);
        List<String> actual2String = actual2.stream().map(List::toString).collect(Collectors.toList());
        System.out.println("Expected: { [2,2,2,2], [2,3,3], [3,5] }, Actual: " +
                String.join(", ", actual2String));
    }
}

