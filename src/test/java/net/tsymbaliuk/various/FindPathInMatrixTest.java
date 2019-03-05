package net.tsymbaliuk.various;

import static org.junit.Assert.*;

import java.util.Arrays;
import net.tsymbaliuk.various.FindPathInMatrix.Vertex;
import org.junit.Test;

public class FindPathInMatrixTest {
  private static final int[][] TEST_MATRIX = {
    {0,  -1,  0,  0,  0,  0, -1,  0,  0,  0 },
    {0,  -1,  0, -1,  0,  0,  0, -1,  0,  0 },
    {0,   0,  0, -1,  0,  0, -1,  0,  0,  0 },
    {0,  -1, -1, -1,  0, -1, -1,  0, -1,  0 },
    {0,   0,  0, -1,  0,  0, -1,  0,  0, -1 },
    {0,  -1,  0,  0,  0,  0,  0,  0, -1, -1 },
    {0,  -1, -1, -1, -1, -1, -1, -1, -1,  0 },
    {0,  -1,  0,  0,  0,  0,  0,  0,  0,  0 },
    {0,   0,  0, -1, -1, -1,  0, -1, -1,  0 }};

  private static final int[] Source = {0, 0};
  private static final int[] Destination = {3, 4};

  private static final int Output = 11;

  @Test
  public void thatFindsProperCost() {
    FindPathInMatrix fpin = new FindPathInMatrix(TEST_MATRIX);
    Vertex end = fpin.findPath(0, fpin.m2v(3,4));
    assertEquals(11, end.cost);
  }

  @Test
  public void thatPathIsCorrect() {
    FindPathInMatrix fpin = new FindPathInMatrix(TEST_MATRIX);
    Vertex end = fpin.findPath(0, fpin.m2v(3,4));
    assertNotNull(end);
    int[] path = fpin.getPath(end);
    assertEquals("[0, 10, 20, 21, 22, 12, 2, 3, 4, 14, 24, 34]", Arrays.toString(path));
  }
}