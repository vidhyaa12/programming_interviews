package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetMaxSum {
    public int[] makeSubset(int[] nums) {
        Arrays.sort(nums);
        int[] maxSubsetSumEndingAtIndex = new int[nums.length];
        int[] prev = new int[nums.length];

        for (int i = 0; i < maxSubsetSumEndingAtIndex.length; i++) {
            maxSubsetSumEndingAtIndex[i] = nums[i];
            prev[i] = -1;

            for (int j = 0; j < i; j++) {
                if (nums[j] != nums[i] - 1) {
                    if (maxSubsetSumEndingAtIndex[j] + nums[i] > maxSubsetSumEndingAtIndex[i]) {
                        maxSubsetSumEndingAtIndex[i] = maxSubsetSumEndingAtIndex[j] + nums[i];
                        prev[i] = j;
                    }
                }
            }
        }

        int winningSumEndIdx = 0;
        int maxSubsetSum = Integer.MIN_VALUE;

        for (int i = 0; i < maxSubsetSumEndingAtIndex.length; i++) {
            if (maxSubsetSumEndingAtIndex[i] > maxSubsetSum) {
                maxSubsetSum = maxSubsetSumEndingAtIndex[i];
                winningSumEndIdx = i;
            }
        }

        System.out.println("Printing subset with max sum for input " + Arrays.toString(nums));
        List<Integer> subsetWithMaxSum = new ArrayList<Integer>();
        int idx = winningSumEndIdx;
        while (idx >= 0) {
            subsetWithMaxSum.add(nums[idx]);
            idx = prev[idx];
        }

        Collections.reverse(subsetWithMaxSum);
        System.out.println(Arrays.toString(subsetWithMaxSum.toArray()));
        System.out.println();
        return maxSubsetSumEndingAtIndex;
    }

    public static void main(String[] args) {
        SubsetMaxSum subsetMaxSum = new SubsetMaxSum();
        int[] nums;

        nums = new int[]{1, 2, 3, 4, 5};
        subsetMaxSum.makeSubset(nums);

        nums = new int[]{1, 10, 100};
        subsetMaxSum.makeSubset(nums);

        nums = new int[]{4, 4, 4};
        subsetMaxSum.makeSubset(nums);

        nums = new int[]{1, 1, 2, 2, 3, 3};
        subsetMaxSum.makeSubset(nums);
    }
}
