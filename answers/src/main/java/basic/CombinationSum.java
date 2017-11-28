package basic;

// {1,2,0,-3,3,6}, 3
// {{1,2}, {1,2,0}, {3}, {6,-3,0}... {1,2,3,-3,0}}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
    public Set<List<Integer>> combinationSumCalculator(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new HashSet<List<Integer>>();
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        Arrays.sort(candidates);

        combinationSum(candidates, target, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void combinationSum(int[] candidates, int remainingSum, int j, List<Integer> curr, Set<List<Integer>> result) {
        // termination condition - check if remaining sum is 0
        if (remainingSum == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>(curr);
            result.add(temp);
            return;
        }

        for (int i = j; i < candidates.length; i++) {
            if (remainingSum < candidates[i]) {
                return;
            }

            curr.add(candidates[i]);
            combinationSum(candidates, remainingSum - candidates[i],  i + 1, curr, result);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = new int[]{1,2,0,-3,3,6};
        int target = 3;
        Set<List<Integer>> result = combinationSum.combinationSumCalculator(candidates, target);

        for (List<Integer> subset : result) {
            System.out.println(Arrays.toString(subset.toArray()));
        }
    }
}