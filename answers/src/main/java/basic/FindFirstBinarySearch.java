package basic;

public class FindFirstBinarySearch {
    // Implement findFirst(int N) in a sorted array using binary search
    // findFirst gives the index of first occurrence of integer N
    public int findFirstOccurence(int[] arr, int target, int low, int high) {
        int firstOccurrence = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);

            if (arr[mid] == target) {
                firstOccurrence = mid;
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (firstOccurrence != Integer.MIN_VALUE) {
            return firstOccurrence;
        }

        return -1;
    }

    public int findFirstOccurrence(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);

            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else if (low != mid) {
                high = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindFirstBinarySearch findFirstBinarySearch = new FindFirstBinarySearch();
        int[] arr = {1, 2, 2, 3, 4, 5};
        int result = findFirstBinarySearch.findFirstOccurence(arr, 2, 0, arr.length - 1);
        System.out.println(result);

        int res = findFirstBinarySearch.findFirstOccurrence(arr, 2, 0, arr.length - 1);
        System.out.println(res);
    }
}
