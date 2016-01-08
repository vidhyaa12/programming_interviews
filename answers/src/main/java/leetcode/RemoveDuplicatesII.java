package leetcode;

/**
 * What if duplicates are allowed at most twice?
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
*/
public class RemoveDuplicatesII {
    public static int removeDuplicates(int[] A) {
        if (A.length <= 2) {
            return A.length;
        }

        int prev = 1; // point to previous
        int curr = 2; // point to current

        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }

        return prev + 1;
    }

    public static void main(String[] args) {
        int n = removeDuplicates(new int[]{1, 2, 3, 4});
        System.out.println(n);
    }
}
