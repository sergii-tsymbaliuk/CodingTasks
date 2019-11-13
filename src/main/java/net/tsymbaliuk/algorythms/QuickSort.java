package net.tsymbaliuk.algorythms;

public class QuickSort {

  public static void quickSort(int[] arr) {
    if (arr == null || arr.length == 0) { return; }
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int lo, int hi) {
    int pivot = hoarePartition(arr, lo, hi);
    if (pivot > lo)
      quickSort(arr, lo, pivot);
    if (pivot < hi)
      quickSort(arr, pivot + 1, hi);
  }

  private static int hoarePartition(int[] arr, int lo, int hi) {
    int p = arr[(lo + hi) / 2];
    int l = lo - 1;
    int r = hi + 1;
    while (true) {
      do {
        l++;
      } while (arr[l] < p);
      do {
        r--;
      } while (p < arr[r]);
      if (l >= r) return r;
      swap(arr, l, r);
    }
  }

  private static int lomutoPartition(int[] arr, int lo, int hi) {
    int p = hi;
    int i = lo;
    for (int j = i; j < hi; j++) {
      if (arr[j] < arr[p]) {
        swap(arr, i, j);
        i++;
      }
    }
    swap(arr, i, p);
    return i;
  }

  private static void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
}
