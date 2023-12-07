package com.tushar.interview.binarysearch;

import net.tsymbaliuk.leetcode.MedianOfTwoSortedArrays;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedianOfTwoSortedArrayOfDifferentLengthTest {

    private MedianOfTwoSortedArrayOfDifferentLength solution;

    @Before
    public void setUp() {
        this.solution = new MedianOfTwoSortedArrayOfDifferentLength();
    }

    @Test
    public void findMedianSortedArrays_thatOddWorks() {
        int [] nums1 = {1, 3};
        int [] nums2 = {2};

        double real = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.0, real, 0.0001);
    }

    @Test
    public void findMedianSortedArrays_thatEvenWorks() {
        int [] nums1 = {3, 4};
        int [] nums2 = {1, 2};

        double real = solution.findMedianSortedArrays(nums1, nums2);
        assertEquals(2.5, real, 0.0001);
    }
}