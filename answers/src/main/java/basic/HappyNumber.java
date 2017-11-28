package basic;

import java.util.HashSet;
import java.util.Set;

/**
 * 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> numsSeenSoFar = new HashSet<Integer>();

        while (!numsSeenSoFar.contains(n)) {
            numsSeenSoFar.add(n);
            n = getNextNumber(n);

            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    private int getNextNumber(int n) {
        int nextNum = 0;
        while (n > 0) {
            int digit = n % 10;
            nextNum += Math.pow(digit, 2);
            n /= 10;
        }

        return nextNum;
    }
}
