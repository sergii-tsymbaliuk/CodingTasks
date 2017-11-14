import java.util.HashSet;

/**
 * Created by stsym on 12/12/2016.
 */
public class BattleShip {

  public static class Solution {

    public int countBattleships(char[][] board) {
      if (board.length == 0 || board[0].length == 0) {
        return 0;
      }
      int count = 0;
      for (int i = 0; i < board.length - 1; i++) {
        for (int j = 0; j < board[i].length - 1; j++) {
          if (board[i][j] == 'X' && board[i + 1][j] == '.' && board[i][j + 1] == '.') {
            count++;
          }
        }
      }

      int last_i = board.length - 1;
      for (int j = 0; j < board[last_i].length - 1; j++) {
        if (board[last_i][j] == 'X' && board[last_i][j + 1] == '.') {
          count++;
        }
      }

      int last_j = board[last_i].length - 1;
      for (int i = 0; i < board.length - 1; i++) {
        if (board[i][last_j] == 'X' && board[i + 1][last_j] == '.') {
          count++;
        }
      }

      if (board[last_i][last_j] == 'X') {
        count++;
      }

      return count;
    }

  }

  public static void main(String[] args) {
    char[][] board = {
        {'X', '.', '.', '.', 'X'},
        {'X', '.', '.', '.', 'X'},
        {'.', '.', '.', '.', 'X'},
        {'X', '.', 'X', 'X', '.'},
        {'X', '.', '.', '.', 'X'}
    };
    Solution solution = new Solution();
    System.out.println(solution.countBattleships(board));

  }
}
