package basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MakeChangeImproved {
    public static int makeChange(int amount, List<Integer> denom) {
        Collections.sort(denom);
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = 0;
        }

        for (int i = 0; i < denom.size(); i++) {
            for (int j = denom.get(i); j < amount + 1; j++) {
                dp[j] += dp[j-denom.get(i)];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        List<Integer> denom = Arrays.asList(1, 5, 10, 25);
        // Use 10 coins with value 1 cent each
        // Use 2 coins with value 5 cents each
        // Use 5 coins with value 1 cent each and 1 coin with value 5 cents
        // Use 1 coin with value 10 cents
        System.out.println(makeChange(10, denom));
    }
}
