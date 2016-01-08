package leetcode;

import java.util.Arrays;

/**
 * http://techieme.in/minimum-number-of-coins/
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Let us say that we have an array of denominations D[a1, a2, a3, …., ai-1, ai, …, an-1, an].
 * And we are calculating the sum Sk.
 * Our task is to find the biggest coin which is smaller than Sk.
 * Now suppose we find a coin ap from the denominations array which is the biggest coin smaller than Sk.
 * This means we have already achieved a sum of ap and we only need to achieve the difference Sk – ap.
 * The way we are solving it, we have already calculated the minimum number of coins required for all the smaller sums.
 * This means that the minimum number of coins required to achieve Sk – ap is already known to us.
 * Hence the minimum number of coins required to achieve Sk is 1 + the minimum number of coins required to achieve Sk – ap.
 */
public class CoinChangeII {
    public static int coinChange(int[] denoms, int amount) {
        if (denoms == null || denoms.length == 0 || amount == 0) {
            return 0;
        }

        Arrays.sort(denoms);
        int[] nums = new int[amount + 1];
        nums[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            nums[i] = Integer.MAX_VALUE - 1000;
        }

        for (int i = 0; i < amount + 1; i++) {
            for (int j = denoms.length - 1; j >= 0; j--) {
                if (denoms[j] <= i) {
                    nums[i] = Math.min(1 + nums[i - denoms[j]], nums[i]);
                }
            }
        }

        return nums[amount] == Integer.MAX_VALUE - 1000 ? -1 : nums[amount];
    }

    public static void main(String[] args) {
        int result = coinChange(new int[]{2}, 3);
        System.out.println(result);
    }
}
