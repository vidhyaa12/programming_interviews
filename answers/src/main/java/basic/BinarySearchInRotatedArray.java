package basic;

public class BinarySearchInRotatedArray {
    /* Searches an element target in a pivoted sorted array arr[]
   of size n */
    public int pivotedBinarySearch(int arr[], int n, int target) {
        int pivot = findPivot(arr, 0, n - 1);

        // If we didn't find a pivot, then array is not rotated at all
        if (pivot == -1) {
            return binarySearch(arr, 0, n - 1, target);
        }

        // If we found a pivot, then first compare with pivot and then
        // search in two subarrays around pivot
        if (arr[pivot] == target) {
            return pivot;
        }
        if (arr[0] <= target) {
            return binarySearch(arr, 0, pivot - 1, target);
        }
        return binarySearch(arr, pivot + 1, n - 1, target);
    }

    // assume all elements are unique in the array
    public int findPivot(int[] arr, int low, int high) {
        // base cases
        if (high < low){
            return -1; //impossible index for "not found"
        }
        if (high == low) {
            return low;
        }

        int mid = (low + high)/2;   /*low + (high - low)/2;*/
        if (mid < high && arr[mid] > arr[mid + 1])
            return mid;
        if (mid > low && arr[mid] < arr[mid - 1])
            return (mid-1);
        if (arr[low] >= arr[mid])
            return findPivot(arr, low, mid - 1);
        return findPivot(arr, mid + 1, high);
    }

    public int binarySearch(int[] arr, int low, int high, int target) {
        while (high >= low) {
            int pos = low + ((high - low) / 2);
            if (arr[pos] > target) {
                high = pos - 1;
            } else if (arr[pos] < target) {
                low = pos + 1;
            } else {
                return pos;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 2};
        BinarySearchInRotatedArray bsr = new BinarySearchInRotatedArray();
        int pivot = bsr.findPivot(arr, 0, 3);
        System.out.println("pivot is at index = " + pivot);
        int result = bsr.pivotedBinarySearch(arr, arr.length, 1);
        System.out.println("target is at index = " + result);
    }
}
