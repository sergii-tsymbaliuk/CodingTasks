package net.tsymbaliuk.meta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorOfPhoto {
    Solution s;

    @BeforeEach
    void setup() {
        s = new Solution();
    }

    /*
    N = 5
    C = APABA
    X = 1
    Y = 2
    Expected Return Value = 1
    */
    @Test
    void getArtisticPhotographCount_case1() {
        assertEquals(1, s.getArtisticPhotographCount(5, "APABA", 1, 2));
    }

    /*
    Sample test case #2
    Solved
    N = 5
    C = APABA
    X = 2
    Y = 3
    Expected Return Value = 0
    */
    @Test
    void getArtisticPhotographCount_case2() {
        assertEquals(0, s.getArtisticPhotographCount(5, "APABA", 2, 3));
    }

    /*
    Sample test case #3
    Wrong Answer
    N = 8
    C = .PBAAP.B
    X = 1
    Y = 3
    Expected Return Value = 3
    */
    @Test
    void getArtisticPhotographCount_case3() {
        assertEquals(3, s.getArtisticPhotographCount(8, ".PBAAP.B", 1, 3));
    }

    public static class Solution {
        public long getArtisticPhotographCount(int N, String C, int X, int Y) {
            // Write your code here
            int [] accumP = new int [N + 1];
            int [] accumB = new int [N + 1];

            for (int i = 0; i < N; i++) {
                accumP[i + 1] = accumP[i] + ((C.charAt(i) == 'P') ? 1 : 0);
                accumB[i + 1] = accumB[i] + ((C.charAt(i) == 'B') ? 1 : 0);
            }

            long res = 0;
            for (int i = 1; i < N - 1; i++) {
                if (C.charAt(i) == 'A') {
                    int l0 = Math.max(i - Y, 0);
                    int l1 = Math.max(i - X + 1, 0);
                    int r0 = Math.min(X + i, N);
                    int r1 = Math.min(Y + i + 1, N);

                    int leftP = (accumP[l1] - accumP[l0]);
                    int rightB = (accumB[r1] - accumB[r0]);
                    int leftB = (accumB[l1] - accumB[l0]);
                    int rightP = (accumP[r1] - accumP[r0]);
                    res += leftP * rightB;
                    res += leftB * rightP;
                }
            }
            return res;
        }
    }
}