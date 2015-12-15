package uva;

import java.util.*;

/**
 * A military contractor for the Department of Defense has just completed a series of preliminary tests
 for a new defensive missile called the CATCHER which is capable of intercepting multiple incoming
 offensive missiles. The CATCHER is supposed to be a remarkable defensive missile. It can move
 forward, laterally, and downward at very fast speeds, and it can intercept an offensive missile without
 being damaged. But it does have one major flaw. Although it can be fired to reach any initial elevation,
 it has no power to move higher than the last missile that it has intercepted.
 The tests which the contractor completed were computer simulations of battlefield and hostile
 attack conditions. Since they were only preliminary, the simulations tested only the CATCHER’s
 vertical movement capability. In each simulation, the CATCHER was fired at a sequence of offensive
 missiles which were incoming at fixed time intervals. The only information available to the CATCHER
 for each incoming missile was its height at the point it could be intercepted and where it appeared in
 the sequence of missiles. Each incoming missile for a test run is represented in the sequence only once.
 The result of each test is reported as the sequence of incoming missiles and the total number of
 those missiles that are intercepted by the CATCHER in that test.
 The General Accounting Office wants to be sure that the simulation test results submitted by the
 military contractor are attainable, given the constraints of the CATCHER. You must write a program
 that takes input data representing the pattern of incoming missiles for several different tests and outputs
 the maximum numbers of missiles that the CATCHER can intercept for those tests. For any incoming
 missile in a test, the CATCHER is able to intercept it if and only if it satisfies one of these two
 conditions:
 1. The incoming missile is the first missile to be intercepted in this test.
 -or-
 2. The missile was fired after the last missile that was intercepted and it is not higher than the last
 missile which was intercepted.
 Input
 The input data for any test consists of a sequence of one or more non-negative integers, all of which
 are less than or equal to 32,767, representing the heights of the incoming missiles (the test pattern).
 The last number in each sequence is -1, which signifies the end of data for that particular test and is
 not considered to represent a missile height. The end of data for the entire input is the number -1 as
 the first value in a test; it is not considered to be a separate test.
 Output
 Output for each test consists of a test number (Test #1, Test #2, etc.) and the maximum number
 of incoming missiles that the CATCHER could possibly intercept for the test. That maximum number
 appears after an identifying message. There must be at least one blank line between output for
 successive data sets.
 Note: The number of missiles for any given test is not limited. If your solution is based on an inefficient
 algorithm, it may not execute in the allotted time.
 */

public class CatcherLds {

    public static int getLDSLength(List<Integer> numbers) {
        int[] prev = new int[numbers.size()];
        int[] lds = new int[numbers.size()];

        lds[0] = 1;
        prev[0] = -1;
        for (int k = 1; k < numbers.size(); k++) {
            lds[k] = 1;
            prev[k] = -1;
        }


        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (numbers.get(j) >= numbers.get(i)) {
                     if ((lds[j] + 1) > lds[i]) {
                        lds[i] = lds[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        int maxLength = 0;
        int lisEndIndex = 0;
        for (int i = 0; i < lds.length; i++) {
            if (lds[i] > maxLength) {
                maxLength = lds[i];
                lisEndIndex = i;
            }
        }

        int index = lisEndIndex;
        List<Integer> result = new ArrayList<Integer>();

        while (index >= 0) {
            result.add(numbers.get(index));
            index = prev[index];
        }

        Collections.reverse(result);
        // System.out.println("Longest decreasing subsequence = " + Arrays.toString(result.toArray()));
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        int count = 0;
        while (sc.hasNext()) {
            int num = sc.nextInt();

            if (num != -1) {
                numbers.add(num);
            } else if (numbers.size() > 0) {
                count++;
                if (count > 1) {
                    System.out.println();
                }
                System.out.println("Test #" + count + ":");
                System.out.println("  maximum possible interceptions: " + getLDSLength(numbers));
                numbers.clear();
            }
        }
    }
}
