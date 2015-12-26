package leetcode;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 * For example, given [[0, 30],[5, 10],[15, 20]],
 * return false
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 * int start;
 * int end;
 * Interval() { start = 0; end = 0; }
 * Interval(int s, int e) { start = s; end = e; }
 * }
 */

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public class MeetingRoomsI {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1)
            return true;

        // sort intervals by using self-defined Comparator
        List<Interval> meetings = Arrays.asList(intervals);
        Collections.sort(Arrays.asList(intervals), new IntervalComparator());

        Interval prev = meetings.get(0);
        for (int i = 1; i < meetings.size(); i++) {
            Interval curr = meetings.get(i);

            if (prev.end > curr.start) {
                // overlapping case
                return false;
            }
            prev = curr;
        }
        return true;
    }
}

class IntervalComparator implements Comparator<Interval> {
    public int compare(Interval i1, Interval i2) {
        return i1.start - i2.start;
    }
}