package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vidhyaa on 21/02/18.
 */
public class MinimumRangeQuery {
    Map<RangeWithoutMin, Integer> rangeToMin;

    public MinimumRangeQuery() {
        rangeToMin = new HashMap<RangeWithoutMin, Integer>();
    }

    public void setRangeToMin(Map<RangeWithoutMin, Integer> rangeToMin) {
        this.rangeToMin = rangeToMin;
    }

    static class RangeWithoutMin {
        int start;
        int end;

        public RangeWithoutMin(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class RangeWithMin {
        int start;
        int end;
        int min;

        public RangeWithMin(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
        }
    }

    public int findMin(int[] nums, int i, int j) {
        // TODO: i <= j to validate range
        return rangeToMin.get(new RangeWithoutMin(i, j));
    }

    private void precompute(int[] nums) {
        Map<RangeWithoutMin, Integer> rangeToMin = new HashMap<RangeWithoutMin, Integer>();

        for (int i = 0; i < nums.length; i++) {
//            List<RangeWithMin> rangesWithStartI = new ArrayList<>();
            List<RangeWithMin> rangesWithStartI = getRanges(nums, i);

            for (RangeWithMin rangeWithMin: rangesWithStartI) {
                rangeToMin.put(new RangeWithoutMin(rangeWithMin.start, rangeWithMin.end), rangeWithMin.min);
            }
        }
        this.rangeToMin = rangeToMin;
    }

    private List<RangeWithMin> getRanges(int[] nums, int start) {
        List<RangeWithMin> rangesWithMins = new ArrayList<>();
        for (int j = start; j <= nums.length; j++) {
            RangeWithMin range = getRange(nums, start, j);
            rangesWithMins.add(range);
        }
        return rangesWithMins;
    }

    private RangeWithMin getRange(int[] nums, int start, int end) {
        int min = Integer.MAX_VALUE;

        for (int i = start; i <= end; i++) {
            if (nums[i] > min) {
                min = nums[i];
            }
        }

        return new RangeWithMin(start, end, min);
    }
}
