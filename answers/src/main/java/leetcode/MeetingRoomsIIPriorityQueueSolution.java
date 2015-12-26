package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * For example, given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MeetingRoomsIIPriorityQueueSolution {
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
        if (null == intervals || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        int minRoom = 1;
        queue.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < queue.peek()) {
                minRoom++;
            } else {
                queue.poll();
            }
            queue.offer(intervals[i].end);
        }
        return minRoom;
    }
}
