package basic.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vidhyaa on 12/11/17.
 */
public class CombinationSumTwo {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)  {
            return new ArrayList<List<Integer>>();
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();

        List<Integer> current = new ArrayList<Integer>();
        Arrays.sort(candidates);

        combinationSum(candidates, target, 0, current, result);

        return new ArrayList<List<Integer>>(result);
    }

    public static void combinationSum(int[] candidates, int target, int j, List<Integer> curr, Set<List<Integer>> result){
        if (target == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>(curr);
            result.add(temp);
            return;
        }

        for (int i = j; i < candidates.length; i++){
            if (target < candidates[i])  {
                return;
            }

            // leetcode solution is wrong. the check below is needed since each number cannot be used more than once in a combination
            //if (curr.size() == 0 || !curr.contains(candidates[i])) {
            curr.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i + 1, curr, result);
            curr.remove(curr.size()-1);
            //}
        }
    }

    public static void main(String args[]) {
       int[] candidates = {1, 1, 1, 1, 1};
        List<List<Integer>> result = combinationSum2(candidates, 3);
        for (List<Integer> subResult : result) {
            System.out.printf("SubResult : %s%n", Arrays.toString(subResult.toArray()));
        }
    }
}
