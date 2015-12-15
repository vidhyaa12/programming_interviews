package basic;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public boolean search(int n, int[] arr, int start, int end) {
        int mid = (start + end) / 2;

        if(end < start) {
            return false;
        }

        if (arr[mid] == n) {
            return true;
        }

        if (arr[mid] < n) {
            return search(n, arr, 0, mid - 1);
        }

        return search(n, arr, mid + 1, end);
    }

    public static int search(int[] a, int start, int end, int target) {
        int middle = (start + end) / 2;
        if (end < start) {
            return -1;
        }

        if (target == a[middle]) {
            return middle;
        } else if (target < a[middle]) {
            return search(a, start, middle - 1, target);
        } else {
            return search(a, middle + 1, end, target);
        }
    }
}
