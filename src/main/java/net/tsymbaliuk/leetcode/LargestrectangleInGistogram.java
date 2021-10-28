package net.tsymbaliuk.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class LargestrectangleInGistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {
            if ( stack.isEmpty() || i < heights.length && heights[stack.peek()] <= heights[i]) {
                stack.push(i);
            } else {
                // stack : 1, 2, 3
                // i: 4
                // t: 3, s: 1, 2; a:
                while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] > heights[i])) {
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        maxArea = Math.max(maxArea, heights[top] * i);
                    } else {
                        maxArea = Math.max(maxArea, heights[top] * (i - 1 - stack.peek()));
                    }
                }
                stack.push(i);
            }
        }
        return maxArea;
    }
}