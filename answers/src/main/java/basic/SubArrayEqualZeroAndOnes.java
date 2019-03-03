package basic;

/**
 * Created by vidhyaa on 05/11/18.
 */

import java.util.ArrayList;

/*
 * largest subarray with equal number of 0s and 1s
 given an array containing only 0s and 1s,find the largest subarray which contain equal no of 0s and 1s.

 e.g.
 input: array[] = {1,0,1,1,1,0,0}
 output: 1 to 6

 input: array[] = {1,1,1,1}
 output: not found

 input: array[] = {0,0,1,1,0}
 output: 0 to 3 or 1 to 4

 subarray ending at index i - 1: has one less zero or 1
 dp[i][j]

 i - 1, i, ... , j - 1, j
 maxLen

 if (dp[i][j - 1] && (i >= 1 && arr[i - 1] == 0 && arr[j] == 1) {
    maxLen = Math.max(maxLen, j - (i - 1) + 1);
 }

 // kranti@apple.com

 */

class SubArrayEqualZeroAndOnes {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
//        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        int[] arr = new int[]{1,0,1,1,1,0,0};
        getMaxSeqLength(arr);
    }

    public static int getMaxSeqLength(int[] arr) {
//     if (dp[i][j - 1] && (i >= 1 && arr[i - 1] == 0 && arr[j] == 1) {
        // maxLen = Math.max(maxLen, j - (i - 1) + 1);

        boolean[][] dp = new boolean[arr.length][arr.length];
        int N = arr.length;

        for (int i = 1; i < arr.length; i++) {
            dp[i][i] = false;
        }

        dp[0][0] = true;

        int maxLen = 0; int startIdx = -1; int endIdx = -1;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                System.out.println("i = " + i + " j = " + j + " arr[i] = " + arr[i] + " arr[j] = " + arr[j]);

                System.out.println("dp[i][j - 1] = " + dp[i][j - 1]);
                if (dp[i][j - 1] && (i >= 1 && arr[i - 1] != arr[j])) {
                    System.out.println("check");
                    dp[i][j] = true;
                    maxLen = Math.max(maxLen, j - i); // compute best seq len
                    if (j - i > maxLen) {
                        startIdx = i;
                        endIdx = j;
                    }
                }
            }
        }

        if (maxLen == 0) {
            System.out.println("not found");
        } else {
            System.out.println(startIdx + " to " + endIdx);
        }
        return maxLen;
    }
}

