import java.util.HashSet;

/**
 * Created by stsym on 12/12/2016.
 */
public class DivideNumbers {
    public static class Solution {
        public int divide(int dividend, int divisor) {
//            System.out.println("dividend: " + dividend + ", divisor:" + divisor);
            if (dividend == 0) {
                return 0;
            }

            if (divisor == 1) {
                return dividend;
            }

            if (divisor == 0) {
                return Integer.MAX_VALUE;
            }

            if (divisor == -1) {
                switch (dividend) {
                    case Integer.MAX_VALUE:
                        return Integer.MIN_VALUE;
                    case Integer.MIN_VALUE:
                        return Integer.MAX_VALUE;
                    default:
                        return -dividend;
                }
            }

            int res = 0;
            int sign = Integer.MIN_VALUE & ( dividend ^ divisor );

            if (dividend < 0) {
                dividend = ~dividend + 1;
            }

            if (divisor < 0) {
                divisor = ~divisor + 1;
            }

            if ( (divisor & 1) == 0 && divisor !=0 ){
                System.out.println("power of two");
                res = 1;
                while ((divisor & 1) == 0 && divisor !=0 ) {
                    res <<= 1;
                    divisor >>= 1;
                    dividend >>= 1;
                }
                if ( divisor == 0 ) {
                    return sign == 0 ? dividend : ~dividend - 1;
                }
            }

            if (divisor > dividend) {
                return 0;
            }

            int pos = Integer.numberOfLeadingZeros(divisor)-Integer.numberOfLeadingZeros(dividend);
            res = 0;

            divisor <<= pos;
            for (int i=pos; i>=0; i--){
                res <<= 1;

                if ( divisor <= dividend) {
                    dividend -= divisor;
                    res++;
                }
                divisor >>= 1;
            }

//            System.out.println(
//                    " sign_dividend=" + Integer.toBinaryString(sign_dividend) +
//                    " sign_divisor=" + Integer.toBinaryString(sign_divisor) +
//                    " (sign_dividend ^ sign_divisor)=" + Integer.toBinaryString((sign_dividend ^ sign_divisor)) +
//                    " ((sign_dividend ^ sign_divisor) & Integer.MIN_VALUE)=" +
//                            Integer.toBinaryString(((sign_dividend ^ sign_divisor) & Integer.MIN_VALUE)));

            res |= sign;
            System.out.println("Result: "+res);
            return res;
        }

        public int divideBySub(int dividend, int divisor) {
            System.out.println("dividend: " + dividend + ", divisor:" + divisor);
            int res = 0;
            if (divisor == 1)
                return dividend;
            if (divisor == -1)
                return (~dividend);
            if (divisor == 0)
                return Integer.MAX_VALUE;

            for (int a=Math.abs(dividend); a>=Math.abs(divisor); a-=Math.abs(divisor))
                res++;

            if (divisor > 0 && dividend > 0 || divisor < 0 && dividend < 0)
                return res;
            else
                return ~res;
        }
    }

    public static void bitwiceOperations(){
        int minusOne = -1;
        System.out.printf("       1: %s\n", Integer.toBinaryString(-minusOne));
        System.out.printf("      -1: %s\n", Integer.toBinaryString( minusOne));
        System.out.printf("     ~-1: %s\n", Integer.toBinaryString(~minusOne));
        System.out.printf("     ~-2: %s\n", Integer.toBinaryString(~(-2)));
        System.out.printf(" MAX_INT: %s\n", Integer.toBinaryString(Integer.MAX_VALUE ));
        System.out.printf(" MIN_INT: %s\n", Integer.toBinaryString(Integer.MIN_VALUE ));
        System.out.printf("~MAX_INT: %s\n", Integer.toBinaryString(~Integer.MAX_VALUE ));
        System.out.printf("~MIN_INT: %s\n", Integer.toBinaryString(~Integer.MIN_VALUE ));

        System.out.printf("-1 & MIN_INT: %s\n", Integer.toBinaryString(-1 & Integer.MIN_VALUE ));
        System.out.printf("-2 & MIN_INT: %s\n", Integer.toBinaryString(-2 & Integer.MIN_VALUE ));

        System.out.printf("-1 & MAN_INT: %s\n", Integer.toBinaryString(-1 & Integer.MAX_VALUE ));
        System.out.printf("-2 & MAN_INT: %s\n", Integer.toBinaryString(-2 & Integer.MAX_VALUE ));

        System.out.printf(" 1 & MAN_INT: %s\n", Integer.toBinaryString( 1 & Integer.MAX_VALUE ));
        System.out.printf(" 2 & MAN_INT: %s\n", Integer.toBinaryString( 2 & Integer.MAX_VALUE ));


    }

    public static void testDivision(){
        Solution solution = new Solution();

//        System.out.println(solution.divide(4, 2)==2);
//        System.out.println(solution.divide(1, 2)==0);
//        System.out.println(solution.divide(25, 4)==6);
//        System.out.println(solution.divide(25, -4)==-6);
//        System.out.println(solution.divide(-25, 4)==-6);
//        System.out.println(solution.divide(-25, -4)==6);
//        System.out.println(solution.divide(3, -1)==-3);
//        System.out.println(solution.divide(3, -2)==-1);
//        System.out.println(solution.divide(3,  2)== 1);
//        System.out.println(solution.divide(-2147483648, -1)==2147483647);
//        System.out.println(solution.divide(-2147483648, 2)==-1073741824);

        System.out.println("-2147483648 / -2  = " + solution.divide(Integer.MIN_VALUE, -2 ));
        System.out.println("-2147483648 >> 1= " + solution.divide(Integer.MIN_VALUE, -1 ));

//        System.out.println("MAX_INT -2: " + (Integer.MAX_VALUE + ~(-2)));
//        System.out.println("~-2147483648+1: " + (-2147483648-1));
//        System.out.println("~(-2147483648+1): " + ~(-2147483648+1));
//        System.out.println("~-1: " + ~-1);
    }

    public static void main(String [] args){
        //bitwiceOperations();
        testDivision();
    }
}
