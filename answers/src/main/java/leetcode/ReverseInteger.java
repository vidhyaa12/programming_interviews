package leetcode;

/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321click to show spoilers.
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
 * then the reverse of 1000000003 overflows. How should you handle such cases?
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public static int reverse(int x) {
        boolean isNeg = false;
        int maxDiff = Integer.MAX_VALUE / 10;
        if (x < 0) {
            isNeg = true;
        }

        x = Math.abs(x);
        int reversedNum = 0;
        int mod = 0;
        while (x != 0) {
            if (x == Integer.MIN_VALUE || (!isNeg && reversedNum > maxDiff)) {
                return 0;
            }
            mod = x % 10;
            reversedNum = reversedNum * 10 + mod;
            x /= 10;
        }

        if (reversedNum < 0) {
            return 0;
        }
        return isNeg ? -1 * reversedNum : reversedNum;
    }

    public static void main(String[] args) {
        int n = reverse(12);
        System.out.println(n);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.abs(-2147483648));
    }
}
