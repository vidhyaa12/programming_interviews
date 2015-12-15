package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static int getLISLength(int[] arr) {
        int[] prev = new int[arr.length];
        int[] lis = new int[arr.length];

        lis[0] = 1;
        prev[0] = -1;
        for (int k = 1; k < arr.length; k++) {
            lis[k] = 0;
            prev[k] = -1;
        }


        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                     if ((lis[j] + 1) > lis[i]) {
                        lis[i] = lis[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        int maxLength = 0;
        int lisEndIndex = 0;
        for (int i = 0; i < lis.length; i++) {
            if (lis[i] > maxLength) {
                maxLength = lis[i];
                lisEndIndex = i;
            }
        }

        int index = lisEndIndex;
        List<Integer> result = new ArrayList<Integer>();

        while (index >= 0) {
            result.add(arr[index]);
            index = prev[index];
        }

        Collections.reverse(result);
        System.out.println("LIS = " + Arrays.toString(result.toArray()));
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, -1, 2, -2, 3, 5};
        System.out.println("Input = " + Arrays.toString(arr1));
        System.out.println("Length of LIS = " + getLISLength(arr1));

        System.out.println();
        int[] arr2 = {-1, -2, -3, -4, -5};
        System.out.println("Input = " + Arrays.toString(arr2));
        System.out.println("Length of LIS = " + getLISLength(arr2));

        System.out.println();
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Input = " + Arrays.toString(arr3));
        System.out.println("Length of LIS = " + getLISLength(arr3));

        int[] arr4 = {1, 2, 3, -4, 5, 6, 7, 8};
        System.out.println("Input = " + Arrays.toString(arr4));
        System.out.println("Length of LIS = " + getLISLength(arr4));
    }
}
