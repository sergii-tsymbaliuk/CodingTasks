package net.tsymbaliuk.InterviewCake.AppleStocks;

/**
 * First, I wanna know how much money I could have made yesterday if I'd been trading Apple stocks all day.
 *
 * So I grabbed Apple's stock prices from yesterday and put them in a list called stock_prices, where:
 * 
 *  The indices are the time (in minutes) past trade opening time, which was 9:30am local time.
 *  The values are the price (in US dollars) of one share of Apple stock at that time.
 *  So if the stock cost $500 at 10:30am, that means stock_prices[60] = 500.
 * 
 *  Write an efficient function that takes stock_prices and returns the best profit I could have made from one purchase and one sale of one share of Apple stock yesterday.
 * 
 *  For example:
 * 
 *  stock_prices = [10, 7, 5, 8, 11, 9]
 * 
 *  get_max_profit(stock_prices)
 *  # Returns 6 (buying for $5 and selling for $11)
 * 
 *  No "shorting"—you need to buy before you can sell. Also, you can't buy and sell in the same time step—at least 1 minute has to pass.
 */
public class AppleStocks {
  int [] prices;
    
  public AppleStocks(int [] prices) {
    this.prices = prices;
  }
  
  public int solution() {
    return solutionSaveTwoMax();
  }
  
  private int solutionSaveTwoMax(){
    int maxKeep = prices[1] - prices[0];
    int maxStartOver = maxKeep;
    
    for (int i = 2; i < this.prices.length; i++){
      int startOver = prices[i] - prices[i - 1];
      maxStartOver = Math.max(maxStartOver + startOver, startOver);
      maxKeep = Math.max(maxKeep, maxKeep + startOver);
      maxKeep = Math.max(maxKeep, maxStartOver);
    } 
    return Math.max(maxKeep, maxStartOver);
  }  
  
  private int solutionBruteForce(){
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < this.prices.length - 1; i++){
      for (int j = i + 1; j < this.prices.length; j++) {
        if (prices[j] - prices[i] > max) {
          max = prices[j] - prices[i];
        }
      }
    } 
    return max;
  }
}
