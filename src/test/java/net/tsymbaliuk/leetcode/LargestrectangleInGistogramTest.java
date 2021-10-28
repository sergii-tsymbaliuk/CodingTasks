package net.tsymbaliuk.leetcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LargestrectangleInGistogramTest {

    @Test
    public void testLargestRectangleArea() {
        LargestrectangleInGistogram sol = new LargestrectangleInGistogram();

        int actual = sol.largestRectangleArea(new int[]{2,1,5,6,2,3});

        assertThat(actual, is(10));
    }
}