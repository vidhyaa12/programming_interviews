package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets should contain numbers [1, 9], have size k and the numbers in the subset should sum to target
 * Given subset size and target sum, generate all subsets that sum to target
 * Same number can be used multiple times in a subset
 * [[1,2,9],[1,3,8],[1,4,7],[1,5,6],[2,2,8],[2,3,7],[2,4,6],[2,5,5],[3,3,6],[3,4,5],[4,4,4]]
 */
public class CombinationSumWithDuplicatesAllowed {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> numbers = new ArrayList<Integer>();
        dfs(result, 1, n, numbers, k);
        return result;
    }

    public void dfs(List<List<Integer>> result, int start, int sum, List<Integer> currentSubset, int k) {
        if (currentSubset.size() == k) {
            if ( sum == 0) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.addAll(currentSubset);
                result.add(temp);
            }
        } else {
            for (int i = start; i <= Math.min(9, sum); i++){
                currentSubset.add(i);
                dfs(result, i, sum - i, currentSubset, k);
                currentSubset.remove(currentSubset.size() - 1);
            }
        }
    }
}
