package leetcode;

import java.util.Random;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class FindKthLargestInArray {
    public static int findKthLargest(int[] arr, int k) {
        if (arr == null || k < 0) {
            return 0;
        }

        int n = arr.length - k;
        int left = 0;
        int right = arr.length - 1;
        Random rand = new Random();
        while (right >= left) {
            int pivotIndex = partition(arr, left, right, rand.nextInt(right - left + 1) + left);
            if (pivotIndex == n) {
                return arr[pivotIndex];
            } else if (pivotIndex < n) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return -1;
    }

    private static int partition(int[] arr, int left, int right, int pivot) {
        int pivotVal = arr[pivot];
        swap(arr, pivot, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotVal) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static void swap(int[] arr, int i1, int i2) {
        if (i1 != i2) {
            int temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }
    }

    public static void main(String[] args) {
        int result = findKthLargest(new int[]{1}, 1);
        System.out.println(result);
    }
}
