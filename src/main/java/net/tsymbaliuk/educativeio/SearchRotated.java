package net.tsymbaliuk.educativeio;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SearchRotated {
    private static final Logger log = LogManager.getLogger(SearchRotated.class);

    static int binarySearchRotated(int[] arr, int key) {
        // TODO: Write - Your - Code
        if (arr == null | arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        int O_n = 0;
        while (start <= end) {
//            int mid = (left + right) / 2;
            int mid = start + (end - start) / 2;
            log.info(String.format("l: %s, m: %s, r:%s, a[l]: %s, a[m]: %s, a[r]: %s, k:%s",
                    start, mid, end, arr[start], arr[mid], arr[end], key));
            if (key == arr[mid]) {
                log.info("O_n: " + O_n);
                return mid;
            }
            if (key < arr[mid]) {
                if (arr[start] <= key) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (key <= arr[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            O_n++;
        }
        log.info("O_n: " + O_n);
        return -1;
    }

    public static int etalonSearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) end = mid - 1;
                else start = mid + 1;
            }
            else {
                if (target <= nums[end] && target > nums[mid]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int actual = SearchRotated.binarySearchRotated(new int[]{4, 5, 6, 1, 2, 3}, 3);

        actual = SearchRotated.binarySearchRotated(new int[]{3, 4, 5, 6, 1, 2}, 3);

    }
}
