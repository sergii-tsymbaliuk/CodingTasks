package InterviewCake.AppleStocks;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppleStocksTest {
  @Test
  public void testSolution6() {
    AppleStocks as = new AppleStocks(new int[]{10, 7, 5, 8, 11, 9});
    assertEquals(6, as.solution());
  } 
  
  
  @Test
  public void testSolution5() {
    AppleStocks as = new AppleStocks(new int[]{7,1,5,3,6,4});
    assertEquals(5, as.solution());
  }
  
  @Test
  public void testSolution0(){
    assertEquals(0, new AppleStocks(new int[]{7,6,4,3,1}).solution());
  }
}
