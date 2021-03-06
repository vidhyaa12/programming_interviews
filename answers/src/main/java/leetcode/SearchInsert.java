package leetcode;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 */
public class SearchInsert {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;

        return searchInsert(A, target, 0, A.length - 1);
    }

    public int searchInsert(int[] A, int target, int start, int end) {
        int mid = (start + end) / 2;

        if (target == A[mid]) {
            return mid;
        } else if (target < A[mid]) {
            return start < mid ? searchInsert(A, target, start, mid - 1) : start;
        } else {
            return end > mid ? searchInsert(A, target, mid + 1, end) : (end + 1);
        }
    }
}
