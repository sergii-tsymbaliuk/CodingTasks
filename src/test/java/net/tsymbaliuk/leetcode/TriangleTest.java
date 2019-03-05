package net.tsymbaliuk.leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class TriangleTest {

  @Test
  public void minimumTotal() {
    Triangle solution = new Triangle();
    int [][] input = {{2}, {3,4}, {6,5,7}, {4,1,8,3}};
    assertEquals(11, solution.minimumTotal(toList(input)));
  }

  private List<List<Integer>> toList(int [][] source) {
    List<List<Integer>> result = new ArrayList<>();
    for(int[] arr : source) {
      result.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }
    return result;
  }
}