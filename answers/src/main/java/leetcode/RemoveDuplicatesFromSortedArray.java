package leetcode;

/**
 * return count of unique elements
 */

public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                count++;
            }
        }
        return (A.length - count);
    }

    public static void main(String[] args) {
        int n = removeDuplicates(new int[]{1, 1, 2});
        System.out.println(n);
    }
}
