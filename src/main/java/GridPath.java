import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class GridPath {

}

class State {
    int r;
    int c;
    int cost;
    int k;

    State(int r, int c, int k, int cost) {
        this.r = r;
        this.c = c;
        this.cost = cost;
        this.k = k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c, k);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof State)) return false;
        State st = (State) other;
        return st.r == this.r && st.c == this.c && this.cost == st.cost && this.k == st.k;
    }
}

class Solution {

    public static final int[][] steps = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int shortestPath(int[][] grid, int k) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;
        int rows = grid.length, cols = grid[0].length;
        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }

        int[] target = new int[]{rows - 1, cols - 1};

        LinkedList<State> pq = new LinkedList<>();
        int len = rows * cols;
        Map<Long, Integer> visited = new HashMap<>();

        State st = new State(0, 0, k, 0);
        pq.addLast(st); // i, j, remainingQuota, path cost
        visited.put(0L, k);

        while (!pq.isEmpty()) {
            st = pq.removeFirst();
            if (st.r == target[0] && st.c == target[1]) return st.cost;

            for (int i = 0; i < 4; i++) {
                int nr = st.r + steps[i][0];
                int nc = st.c + steps[i][1];

                if (nr > target[0] || nr < 0 || nc > target[1] || nc < 0) continue;
                int nk = st.k - grid[nr][nc];
                if (nk < 0) continue;
                State newState = new State(nr, nc, nk, st.cost + 1);
                if (visited.getOrDefault(0L + nr * cols + nc, 0) > nk) continue;
                pq.addLast(newState);
                visited.put(0L + nr * cols + nc, nk);
            }

        }
        return -1;
    }

}
