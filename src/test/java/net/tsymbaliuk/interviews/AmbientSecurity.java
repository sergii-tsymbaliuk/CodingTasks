package net.tsymbaliuk.interviews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AmbientSecurity {


    // 200 ends ->188, 186,
    // 1 to 100 loops
    public static class Solution {

        public static void main(String args[]) throws Exception {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT */
            Solution s = new Solution();
            int answer = 0;
            for (int i = 0; i < 1000; i++) {
                ArrayList<Integer> data = s.prepData(100);
                answer += s.countLoops(data);
            }
            System.out.println(String.format("Number of loops: %f", ((float)answer/1000.0f)));
            calculateOdds();
        }

        public ArrayList<Integer> prepData(int numRopes) {

            ArrayList<Integer> data = new ArrayList<>();
            for (int i = 0; i < numRopes; i++) {
                data.add(i);
                data.add(i);
            }
            // for (int i = 0; i < data.size() - 1; i++) {
            //     int idx = rnd.nextInt(data.size() - i);
            //     int t = data.get(idx);
            //     data.set(idx, data.get(i));
            //     data.set(i, t);
            // }
            // Collections.shuffle(data);
            return data;
        }

        //
        public int countLoops(ArrayList<Integer> ends) {
            int res = 0;
            while (ends.size() > 1) {
                Collections.shuffle(ends);
                Integer left = ends.get(0);
                Integer right = ends.get(1);
                if (left == right) {
                    res++;
                    ends.remove(left);
                    ends.remove(right);
                } else {
                    ends.remove(right);
                    ends.remove(right);
                }
            }
            // res += ends.size();
            return res;
        }

        public int countLoops2(ArrayList<Integer> ends) {
            int res = 0;
            for (int i = 0; i < ends.size(); i++) {
                // Collections.shuffle(ends);
                
                Integer left = ends.get(0);
                Integer right = ends.get(1);
                if (left == right) {
                    res++;
                    ends.remove(left);
                    ends.remove(right);
                } else {
                    ends.remove(right);
                    ends.remove(right);
                }
            }
            // res += ends.size();
            return res;
        }

        public static void calculateOdds() {
            float odds = (float) 0.0;
            for (int i = (100 * 2) -1; i >= 1; i -= 2) {
                odds += 1/(float)i;
            }
            System.out.println("Odds are : " + odds);
            return;
        }
    }
}
