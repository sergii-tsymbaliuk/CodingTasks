package net.tsymbaliuk.leetcode;

/**
 * Solution to the <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/">
 * Median of two sorted arrays</a> on leetcode.
 */
public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }
    return hepler(nums1, nums2, 0, nums1.length - 1);
  }

  private double hepler(int[] nums1, int[] nums2, int l, int r) {
    int med1 = (l + r) / 2;
    int med2 = (nums1.length + nums2.length) / 2 - med1 - 1;
    if (med2 >= 0 && med2 < nums2.length) {
      // check is median
      if (nums1[med1] < nums2[med2 + 1] && nums1[med1 + 1] < nums2[med2]) {
        if ( (nums1.length + nums2.length) % 2 == 1) {
          return nums2[med2];
        } else {
          return (nums1[med1] + nums2[med2]) / 2.0;
        }
      }
      if (nums1[med1] > nums2[med2 + 1]) {
        return hepler(nums1, nums2, l, med1);
      } else {
        return hepler(nums1, nums2, med1, r);
      }
    } else {
      return nums1[med1];
    }
  }
}
