package net.tsymbaliuk.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

  static private class SolutionByLines {

    public List<List<String>> solveNQueens(int n) {
      int[] queens = new int[n]; // one queen per row
      for (int i = 0; i < n; i++) {
        queens[i] = i;
      }
      int currentQueen = 0;
      while(true) {
        break;
      }
      return null;
    }

    public void helper(char[][] board, int row, int n) {

    }

    private boolean checkTurn(char[][] board, int row, int col) {
      for (int i = 1; i <= row; i++) {
        if (board[row - i][col] == 'Q'
          || (col - i) >= 0 && board[col - i][col] =='Q'
          || (col + i) < board[row].length && board[col + i][col] =='Q') {
          return false;
        }
      }
      return true;
    }
  }


  static private class SolutionStraighForward {

    public List<List<String>> solveNQueens(int n) {
      int size = n;
      int[] solution = new int[size];
      List<List<String>> result = new ArrayList<>();
      tryPlaceQueen(solution, n, 0, 0, result);
      return result;
    }

    private void tryPlaceQueen(int[] solution, int n, int q, int start, List<List<String>> result) {
      int rest = n - q - 1;
      for (solution[q] = start; solution[q] < n * n - rest; solution[q]++) {
        if (legalTurn(solution, n, q)) {
          if (rest > 0) {
            tryPlaceQueen(solution, n, q + 1, solution[q] + 1, result);
          } else {
            result.add(unpackSolution(solution, n));
          }
        }
      }
    }

    private boolean legalTurn(int[] solution, int n, int q) {
      for (int i = 0; i < q; i++) {
        if (!isValid(solution[i], solution[q], n)) {
          return false;
        }
      }
      return true;
    }

    private boolean isValid(int posA, int posB, int n) {
      int rA = posA / n;
      int cA = posA % n;
      int rB = posB / n;
      int cB = posB % n;

      return !(rA == rB || cA == cB || Math.abs(rB - rA) == Math.abs(cB - cA));
    }

    private List<String> unpackSolution(int[] solution, int n) {
      char[][] board = new char[n][n];
      for (Integer pos : solution) {
        Arrays.fill(board[pos / n], '.');
        board[pos / n][pos % n] = 'Q';
      }
      ArrayList<String> result = new ArrayList<>();
      for (int i = 0; i < board.length; i++) {
        result.add(new String(board[i]));
      }
      return result;
    }
  }
}