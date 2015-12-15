package basic;

public class MakeChange {
    public static int makeChange(int amount, int[] denom, int[][] arr, int index) {
        if (arr[amount] [index] > 0) {
            return arr[amount] [index];
        }
        if (index >= denom.length - 1) {
            return  1;
        }

        int ways = 0;
        int denomAmount = denom[index];

        for (int i = 0; i * denomAmount <= amount; i++) {
            ways += makeChange(amount - (i * denomAmount), denom, arr, index + 1);
        }

        arr[amount] [index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int[] denom = {25, 10, 5, 1};
        int amount = 20;
        int[][] arr = new int[amount+1][denom.length];

        // 2 dimes
        // 1 dime, 10 pennies
        // 1 dime, 1 nickel, 5 pennies

        // 4 nickels
        // 3 nickels, 5 pennies
        // 2 nickels, 1 dime
        // 2 nickels, 10 pennies
        // 1 nickel, 2 dimes

        // 20 pennies
        int result = makeChange(amount, denom, arr, 0);
        System.out.printf(String.valueOf(result));
    }
}
