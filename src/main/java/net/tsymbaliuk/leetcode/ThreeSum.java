package net.tsymbaliuk.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
  public static List<List<Integer>> solve(int[] nums) {
    return new ThreeSum().new Solution().threeSum(nums);
  }
  
  
  class Solution {
  
    public List<List<Integer>> threeSum(int[] nums) {
      ArrayList res = new ArrayList();
      if (nums.length < 3) {
        return res;
      }
      
      Arrays.sort(nums);
      if (Arrays.binarySearch(nums, 0, 0, nums.length) < 0 
         && (nums[0] > 0 || nums[nums.length - 1] < 0)) {
        return res;
      }
      for (int l = 0; l < nums.length - 2 && nums[l] <= 0; l++) {
        if ( l > 0 && nums[l-1] == nums[l]) {
          continue;
        }
        for (int m = l + 1, r = nums.length - 1; m < r; ) {
          int sum = nums[l] + nums[m] + nums[r];
          if (sum == 0) {
            res.add(Arrays.asList(nums[l], nums[m], nums[r]));
            while (r > m && nums[r] == nums[r - 1]) { r--; }
            while (r > m && nums[m] == nums[m + 1]) { m++; }
            r--;
            m++;
          } else {
            if (sum < 0) {
              m++;
            } else {
              r--;
            }
          } 
        }
      }
      return res;
    }
  }
}
