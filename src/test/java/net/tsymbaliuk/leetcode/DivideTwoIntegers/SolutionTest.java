package net.tsymbaliuk.leetcode.DivideTwoIntegers;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class SolutionTest {
  Solution s = new Solution();

  @Test
  public void divide() {
    assertEquals(2, s.divide(-2147483648,-1017100424));
    assertEquals(-1, s.divide(-1, 1));
    assertEquals(1247, s.divide(537825, 431));
    assertEquals(3, s.divide(10, 3));
    assertEquals(1, s.divide(1, 1));
    assertEquals(0, s.divide(0, 10));
    assertEquals(Integer.MAX_VALUE, s.divide(-2147483648, -1));
    assertEquals(-1073741824, s.divide(-2147483648, 2));
    assertEquals(715827882, s.divide(-2147483648, -3));
    assertEquals(0, s.divide(-1010369383, -2147483648));
  }

  @Test
  public void divide2() {
    assertEquals(2, s.divide2(-2147483648,-1017100424));
    assertEquals(-1, s.divide2(-1, 1));
    assertEquals(1247, s.divide2(537825, 431));
    assertEquals(3, s.divide2(10, 3));
    assertEquals(1, s.divide2(1, 1));
    assertEquals(0, s.divide2(0, 10));
    assertEquals(Integer.MAX_VALUE, s.divide2(-2147483648, -1));
    assertEquals(-1073741824, s.divide2(-2147483648, 2));
    assertEquals(715827882, s.divide2(-2147483648, -3));
    assertEquals(0, s.divide2(-1010369383, -2147483648));
  }
}