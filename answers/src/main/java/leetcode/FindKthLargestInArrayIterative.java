package leetcode;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class FindKthLargestInArrayIterative {
    public int findKthLargest(int[] arr, int k) {
        if (arr == null || k < 0) {
            return 0;
        }

        return getKth(arr.length - k + 1, arr, 0, arr.length - 1);
    }

    public int getKth(int k, int[] arr, int start, int end) {
        int left = start;
        int right = end;
        int pivot = arr[right];

        while (true) {
            while (arr[left] < pivot && left < right) {
                left++;
            }

            while (arr[right] >= pivot && right > left) {
                right--;
            }

            if (left == right) {
                break;
            }

            swap(arr, left, right);
        }

        swap(arr, left, end);

        if (k == left + 1) {
            return pivot;
        } else if (k < left + 1) {
            return getKth(k, arr, start, left - 1);
        } else {
            return getKth(k, arr, left + 1, end);
        }
    }

    public void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}
