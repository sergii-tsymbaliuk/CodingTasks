import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
US stock market opens at 10 AM each day and Oracle stocks fluctuates continuously every microsecond. Write a functionality for RobinHood app that provides the current median stock value from the time market starts on that day whenever user opens the app 
Example: 100, 101, 95, 96, 94, 100, 102, 100, 101, 100
Should return: Median stock value = 100
         
Implement getMedianStockValue() and addStockValue(int stockPrice) in a class. Add test coverage.

Median is the middle value in the ordered list of values. If the number of values in a list are odd, its the middle value where if its even number of values, it’s the mean of middle two values.
*/

public class Solution {

    PriorityQueue<Integer> higher = new PriorityQueue<>();
    PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
    int median = 0;

    public int getMedianStockValue() {
      	return median; 
    }
    
    
    public void addStockValue(int stockPrice) {
        lower.offer(stockPrice);
        rebalance();
    }
    
    private void rebalance() {
        boolean odd = (higher.size() + lower.size()) % 2 > 0;
        while (!higher.isEmpty() && !lower.isEmpty() && higher.peek() < lower.peek()) {
            lower.offer(higher.poll());
        }

        while (lower.size() > higher.size() + (odd ? 1 : 0)) {
            higher.offer(lower.poll());
        }
        
        median = odd ? lower.peek() : (lower.peek() + higher.peek()) / 2;
    }
   

 public static void main(String[] args) {
        Solution solution = new Solution();
        int [] prices = new int[]{100, 101, 95, 96, 94, 100, 102, 100, 101, 100};
        for (int p : prices) {
            solution.addStockValue(p);
            System.out.print(String.format("Addedd price %d, median: %d\n", p, solution.getMedianStockValue()));
        }
   }
}
