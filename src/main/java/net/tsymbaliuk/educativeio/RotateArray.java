package net.tsymbaliuk.educativeio;

import java.util.List;

public class RotateArray {
    public static void rotateArray(List<Integer> arr, int n) {
        int size = arr.size();
        n = n % size;
        if (n == 0) {
            return;
        }
        if (n < 0) {
            n = size + n;
        }
        rotate(arr, 0, size - 1);
        rotate(arr, 0, n - 1);
        rotate(arr, n, size - 1);
    }

    private static void rotate(List<Integer> arr, int start, int end) {
        while (start < end) {
            int t = arr.get(start);
            arr.set(start, arr.get(end));
            arr.set(end, t);
            start++; end--;
        }
    }
}
