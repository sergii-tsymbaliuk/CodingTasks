package InterviewCake.AppleStocks;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppleStocksTest {
  @Test
  public void testSolution() {
    AppleStocks as = new AppleStocks(new int[]{10, 7, 5, 8, 11, 9});
    assertEquals(6, as.solution());
  } 
}
