package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetFinderII {
    public static List<List<Integer>> findSubsets(int[] S) {
        if (S == null) {
            return null;
        }

        Arrays.sort(S);

        List<List<Integer>> allSubsets = new ArrayList<List<Integer>>();

        for (int i = 0; i < S.length; i++) {
            List<List<Integer>> currentSubsets = new ArrayList<List<Integer>>();

            // get sets that are already in result
            for (List<Integer> subsets : allSubsets) {
                currentSubsets.add(new ArrayList<Integer>(subsets));
            }

            //add S[i] to existing sets
            for (List<Integer> subset : currentSubsets) {
                subset.add(S[i]);
            }

            //add S[i] only as a set
            currentSubsets.add(Arrays.asList(S[i]));

            allSubsets.addAll(currentSubsets);
        }

        // add empty set
        allSubsets.add(new ArrayList<Integer>());

        return allSubsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets(new int[]{1, 2, 3, 4});
        System.out.println();
    }
}
