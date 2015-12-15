package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumSumSubsequence {
    /**
     * Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence
     * of the given array such that the integers in the subsequence are sorted in increasing order.
     * For example, if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100),
     * if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) and
     * if the input array is {10, 5, 4, 3}, then output should be 10
     */

    public static int getMaxIncreasingSubsequenceSum(int[] arr) {
        int N = arr.length;
        int[] MaxSubSeqSumEndingInI = new int[N];
        int[] MaxSubSeqPredecessorIdx = new int[N];

        for (int i = 0; i < N; i++) {
            MaxSubSeqSumEndingInI[i] = arr[i];
            MaxSubSeqPredecessorIdx[i] = -1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                    if ((MaxSubSeqSumEndingInI[j] + arr[i]) > MaxSubSeqSumEndingInI[i]) {
                        MaxSubSeqSumEndingInI[i] = MaxSubSeqSumEndingInI[j] + arr[i];
                        MaxSubSeqPredecessorIdx[i] = j;
                    }
                }
            }
        }

        int globalMax = 0;
        int maxSubSeqEndIndex = 0;
        for (int i = 0; i < N; i++) {
            if (MaxSubSeqSumEndingInI[i] > globalMax) {
                globalMax = MaxSubSeqSumEndingInI[i];
                maxSubSeqEndIndex = i;
            }
        }

        int index = maxSubSeqEndIndex;
        List<Integer> result = new ArrayList<Integer>();

        while (index >= 0) {
            result.add(arr[index]);
            index = MaxSubSeqPredecessorIdx[index];
        }

        Collections.reverse(result);
        System.out.println(Arrays.toString(result.toArray()));

        return globalMax;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 101, 2, 3, 100, 4, 5};
        System.out.println(getMaxIncreasingSubsequenceSum(arr1));

        int[] arr2 = {3, 4, 5, 10};
        System.out.println(getMaxIncreasingSubsequenceSum(arr2));

        int[] arr3 = {10, 5, 4, 3};
        System.out.println(getMaxIncreasingSubsequenceSum(arr3));
    }
}
