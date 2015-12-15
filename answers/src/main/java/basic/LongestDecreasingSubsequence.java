package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestDecreasingSubsequence {

    public static int getLDSLength(int[] arr) {
        int[] prev = new int[arr.length];
        int[] lds = new int[arr.length];

        lds[0] = 1;
        prev[0] = -1;
        for (int k = 1; k < arr.length; k++) {
            lds[k] = 1;
            prev[k] = -1;
        }


        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] >= arr[i]) {
                     if ((lds[j] + 1) > lds[i]) {
                        lds[i] = lds[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        int maxLength = 0;
        int ldsEndIndex = 0;
        for (int i = 0; i < lds.length; i++) {
            if (lds[i] > maxLength) {
                maxLength = lds[i];
                ldsEndIndex = i;
            }
        }

        int index = ldsEndIndex;
        List<Integer> result = new ArrayList<Integer>();

        while (index >= 0) {
            result.add(arr[index]);
            index = prev[index];
        }

        Collections.reverse(result);
        System.out.println("Longest decreasing subsequence = " + Arrays.toString(result.toArray()));
        return maxLength;
    }

    public static void computeAndDisplayLDSL(int[] arr) {
        System.out.println("Input = " + Arrays.toString(arr));
        System.out.println("Length of the longest decreasing subsequence = " + getLDSLength(arr));
    }

    public static void main(String[] args) {
        /*
        int[] arr1 = {1, -1, 2, -2, 3, 5};
        int[] arr2 = {-1, -2, -3, -4, -5};
        int[] arr3 = {1, 2, 3, 4, 5};
        int[] arr4 = {1, 2, 3, -4, 5, 6, 7, 8};

        computeAndDisplayLDSL(arr1);
        computeAndDisplayLDSL(arr2);
        computeAndDisplayLDSL(arr3);
        computeAndDisplayLDSL(arr4);
        */

        int[] arr5 = {1};
        int[] arr6 = {1,5,4};
        computeAndDisplayLDSL(arr5);
        computeAndDisplayLDSL(arr6);
    }
}
