package net.tsymbaliuk.leetcode.DivideTwoIntegers;

class Solution {
  public int divide(int dividend, int divisor) {
    boolean sign = false;
    if (divisor == dividend) { return 1; }
    if (divisor == 1) { return dividend; }
    if (divisor == -1) {
      return (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -dividend;
    }
    if (divisor == Integer.MIN_VALUE) {
      if (dividend == Integer.MIN_VALUE) return 1;
      return 0;
    }
    if (divisor == Integer.MAX_VALUE) {
      if (dividend == Integer.MAX_VALUE) return 1;
      if (dividend == Integer.MIN_VALUE) return -1;
      return 0;
    }
    String dd = Integer.toString(dividend);
    String dr = Integer.toString(divisor);
    if (dd.charAt(0) == '-') {
      dd = dd.substring(1);
      sign = true;
    }
    if (dr.charAt(0) == '-') {
      dr = dr.substring(1);
      sign = (sign)? false : true;
    }
    divisor = Integer.parseInt(dr);

    int dd_len = dd.length();
    int dr_len = dr.length();
    if (dividend == 0 || dd_len < dr_len) {
      return 0;
    }

    if (dividend == divisor) {
      return (sign) ? -1 : 1;
    }

    long cur;
    long[] result = new long[dd_len - dr_len + 1];
    long rest = (dr_len > 1) ? Long.valueOf(dd.substring(0, dr_len - 1)) : 0;

    for (int i = 0; i <= dd_len - dr_len; i++) {
      cur = Long.valueOf(Long.toString(rest) + dd.charAt(i + dr_len - 1));
      long[] res = div(cur, divisor);
      result[i] = res[0];
      rest = res[1];
    }

    StringBuilder sb = new StringBuilder();
    if (sign) {
      sb.append('-');
    }

    for (long aResult : result) {
      sb.append(Long.toString(aResult));
    }

    long res = Long.valueOf(sb.toString());

    if (res > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    if (res < Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int) res;
  }

  long[] div(long dd, long dr) {
    long d = 0;
    while (dd >= dr) {
      dd -= dr;
      d++;
    }
    return new long[]{d, dd};
  }

  int divide2(int dividend, int divisor) {
    if (dividend == 0) {
      return 0;
    }

    boolean sign = dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0;

    long ldd = Math.abs((long)dividend);
    long ldr = Math.abs((long)divisor);

    if (ldd < ldr) return 0;
    if (ldd == ldr) return (sign) ? -1 : 1;
    if (ldr == 1) return (sign) ? align(-ldd) : align(ldd);

    long res = divide2(ldd, ldr);
    return align((sign) ? -res : res);
  }

  long divide2(long ldd, long ldr) {
    if (ldd < ldr) return 0;

    long res = 1;
    long sum = ldr;
    while (sum + sum < ldd) {
      sum += sum;
      res += res;
    }
    return res + divide2(ldd - sum, ldr);
  }

  int align(long x) {
    if (x > Integer.MAX_VALUE) return Integer.MAX_VALUE;
    else
    if (x < Integer.MIN_VALUE) return Integer.MIN_VALUE;
    else
      return (int)x;
  }
}