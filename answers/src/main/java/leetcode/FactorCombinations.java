package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Factor Combinations
 */
public class FactorCombinations{
    public static List<List<Integer>> getFactors(int N) {
        List<List<Integer>> factorLists = new ArrayList();
        if (N < 0) {
            return factorLists;
        }
        List<LinkedList<Integer>> l = getFactorsHelper(N, N);
        for(List<Integer> factorList : l) {
            factorLists.add(factorList);
        }
        return factorLists;
    }

    public static List<LinkedList<Integer>> getFactorsHelper(int n, int N) {
        List<LinkedList<Integer>> factorLists = new ArrayList();
        LinkedList<Integer> factorList = new  LinkedList<Integer>();
        if (n < N) {
            factorList.add(n);
            System.out.println(n + " " + N);
            System.out.println(Arrays.toString(factorList.toArray()));
            factorLists.add(factorList);
        }

        for (int factor=2;factor<=(n/2);factor++) {
            if(n % factor == 0) {
                List<LinkedList<Integer>> remainingFactorCombos = getFactorsHelper(n/factor, N);
                for (LinkedList<Integer> factorCombo : remainingFactorCombos) {
                    if(factor >= factorCombo.get(factorCombo.size()-1)) { // ascending order
                        factorCombo.add(factor);
                        factorLists.add(factorCombo);
                        // System.out.println(n + " " + N);
                        System.out.println(Arrays.toString(factorCombo.toArray()));
                    } else {
                        //System.out.println("Unable to append " + factor + " to " + factorCombo.get(factorCombo.size()-1));
                    }
                }
            }
        }
        return factorLists;
    }

    public static void main(String[] args) {
        int N = 16;
        List<List<Integer>> factorCombinations = getFactors(N);
    }
}