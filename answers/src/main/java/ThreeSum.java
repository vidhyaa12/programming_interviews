import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vidhyaa on 26/10/18.
 */

// -1 -1 -1 1 1 1 0 0 0

// -5, -2, 1, 4, 2
// -5, -2, 1, 2, 4
// -5 + -2 + 4 = -3
    // -5 + 1 + 4 = 0

   // 1, 2, 3, 4
    // 4, 3, 2, 1

   // -7, -6, -5, -4

   //  -100, -99, -98, 1, 98
   //
public class ThreeSum {
    public List<Integer> getZeroSumTriplets(int[] arr) {
        // edge case - null or empty array
        Arrays.sort(arr);

        int N = arr.length;

        if (N < 3) {
            return Collections.emptyList();
        }

        int p1 = 0, p2 = p1 + 1, p3 = N - 1;

        while (p1 < N - 1) {
            p2 = p1 + 1;
            while (p2 < p3) {
                int currSum = arr[p1] + arr[p2] + arr[p3];

                if (currSum == 0) {
                   return Arrays.asList(arr[p1], arr[p2], arr[p3]);
                } else if (currSum < 0) {
                    p2++;
                    // handle duplicates of arr[p2]
                } else {
                    p3--;
                    // handle duplicates of arr[p3]
                }

            }
            p1++;
        }
        return Collections.emptyList();
    }
}
