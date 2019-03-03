package basic;

/**
 * Created by vidhyaa on 02/11/18.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class ArrayIntersection {
    public static List<Integer> getIntersection(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(arr1);
        // Arrays.sort(arr2);
        List<Integer> result = new ArrayList<>();

        Set<Integer> numsInBiggerArr = new HashSet<>();
        for (Integer n : arr2) {
            numsInBiggerArr.add(n);
        }

        int lastSeenNum = arr1[0]; int i = 0;
        while (i < arr1.length) {
            int currNum = arr1[i];

            if (currNum == lastSeenNum) { // fix the bug here.
                while (i < arr1.length && currNum == lastSeenNum) {
                    currNum = arr1[i];
                    lastSeenNum = currNum;
                    i++;
                }
                System.out.println(i);
                System.out.println(currNum);
                result.add(lastSeenNum);
            } else {
                result.add(currNum);
                lastSeenNum = currNum;
            }
            i++;

        }

//     Set<Integer> numsInSmallerArr = new HashSet<>();
//     int[] biggerArr = null;

//     if (arr1.length < arr2.length) {
//       for (Integer n : arr1) {
//         numsInSmallerArr.add(n);
//         biggerArr = arr2;
//       }
//     } else {
//       for (Integer n : arr2) {
//         numsInSmallerArr.add(n);
//         biggerArr = arr1;
//       }
//     }

//     List<Integer> result = new ArrayList<>();
//     for (Integer n : biggerArr) {
//       if (numsInSmallerArr.contains(n)) {
//         result.add(n);
//       }
//     }
        return result;
    }

    public static void main(String[] args) {
        int[] arr2 = new int[]{50, 100, 5, 6, 7, 5, 5, 5, 5, 5, 6, 6, 6, 700};
        int[] arr1 = new int[]{100, 5, 5, 5, 5, 5};
        List<Integer> result = getIntersection(arr1, arr2);
        System.out.println(Arrays.toString(result.toArray()));
    }
}

