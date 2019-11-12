package net.tsymbaliuk.leetcode;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MedianOfTwoSortedArraysTest {
  private MedianOfTwoSortedArrays solution;

  @Before
  public void setUp() {
    this.solution = new MedianOfTwoSortedArrays();
  }

  @Test
  public void findMedianSortedArrays_thatOddWorks() {
    int [] nums1 = {1, 3};
    int [] nums2 = {2};

    double real = solution.findMedianSortedArrays(nums1, nums2);
    assertEquals(real, 2.0, 0);
  }

  @Test
  public void findMedianSortedArrays_thatEvenWorks() {
    int [] nums1 = {1, 2};
    int [] nums2 = {3, 4};

    double real = solution.findMedianSortedArrays(nums1, nums2);
    assertEquals(real, 2.5, 0);
  }
}