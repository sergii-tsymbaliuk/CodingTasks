import java.util.HashSet;

/**
 * Created by stsym on 12/12/2016.
 */
public class BattleShip {
    public static class Solution {
        public int countBattleships(char[][] board) {
            boolean vertical = false;
            HashSet<Integer> verticals = new HashSet<>();
            int count=0;
            for (int i=0; i<board.length; i++){
                boolean horisontal = false;
                for (int j=0; j<board[i].length; j++){

                    if (board[i][j] == 'X' && !horisontal && !verticals.contains(j)){
                        System.out.println("X["+i+","+j+"]");

                        if (i==board.length-1 || board[i+1][j] != 'X'){
                            System.out.println("1111");
                            if (j==board[i].length-1 || board[i][j+1] != 'X'){
                                System.out.println("Count ["+i+","+j+"]:"+count);
                                count++;
                                System.out.println("2222");
                                continue;
                            } else {
                                horisontal = true;
                                continue;
                            }
                        } else {
                            verticals.add(j);
                        }
                    }

                    if (verticals.contains(j)){
                        if (board[i][j] != 'X' || i == board.length-1){
                            verticals.remove(j);
                            System.out.println("vert: ["+i+","+j+"] count="+count);
                            count++;
                        } else {
                            System.out.println("WTF vertical, ["+i+","+j+"], count="+count+"?");
                        }
                    }

                    if (horisontal) {
                        if (board[i][j] != 'X' || j == board[i].length - 1) {
                            horisontal = false;
                            System.out.println("hor: ["+i+","+j+"] count="+count);
                            count++;
                        } else {
                            System.out.println("WTF horisontal, ["+i+","+j+"], count="+count+"?");
                        }
                    }

                }
            }
            return count;
        }
    }
    public static void main(String [] args){
        char [][] board  = {
                {'X','.','.','.','X'},
                {'X','.','.','.','X'},
                {'.','.','.','.','X'},
                {'X','.','X','X','.'},
                {'X','.','.','.','X'}
        };
        Solution solution = new Solution();
        System.out.println(solution.countBattleships(board));

    }
}
