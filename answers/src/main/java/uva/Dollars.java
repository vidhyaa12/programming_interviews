package uva;

import java.util.*;

/**
 * New Zealand currency consists of $100, $50, $20, $10, and $5 notes and $2, $1, 50c, 20c, 10c and 5c coins.
 * Write a program that will determine, for any given amount, in how many ways that amount may be made up.
 * Changing the order of listing does not increase the count.
 */
public class Dollars {
    public static long[] makeChange(int amount, int[] denom) {
        long[] dp = new long[amount+1];

        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            dp[i] = 0;
        }

        for (int i = 0; i < denom.length; i++) {
            for (int j = denom[i]; j <= amount; j++) {
                dp[j] += dp[j - denom[i]];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] denom = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        float maxAmount = 300.00f;
        long[] results = makeChange((int) (maxAmount * 100), denom);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            float amount = sc.nextFloat();
            if (amount == 0.00) {
                break;
            }

            System.out.print(String.format("%6.2f%17d", amount, results[((int) (amount * 100))]));
            System.out.println();
        }
    }

}