package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * For example, given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomsII {
    /**
     * Definition for an interval.
     * public class Interval {
     * int start;
     * int end;
     * Interval() { start = 0; end = 0; }
     * Interval(int s, int e) { start = s; end = e; }
     * }
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int max = 1;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;//sort by finish time
            }
        });


        for (int i = 0; i < intervals.length; i++) {
            int count = 1;//start from 1, since the current meeting also needs one room
            for (int j = i + 1; j < intervals.length; j++) { //j starts at i + 1, otherwise there is no point of sorting
                if (intervals[j].start < intervals[i].end) {
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}
