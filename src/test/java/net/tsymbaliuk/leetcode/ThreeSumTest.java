package net.tsymbaliuk.leetcode;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class ThreeSumTest {
  
  @Test
  public void testThatSolutionWorks() {
    
    List<List<Integer>> solution = ThreeSum.solve(new int[]{0,0,0,0});
    assertEquals(solution.toString(), "[0,0,0]");
  }
}
