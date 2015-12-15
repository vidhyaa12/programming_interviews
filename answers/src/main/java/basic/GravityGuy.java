package basic;

import java.io.IOException;
import java.util.Scanner;

/**
 * Chef likes to play games a lot. Gravity Guy is one such interesting game.
 "Gravity Guy is an arcade side-scrolling game in which the player controls Gravity Guy by tapping the screen to switch gravity. The objective in this game is to run as far as possible while avoiding being trapped by obstacles, falling, or flying off the screen. If hit by obstacles, the game is over."
 Chef is so addicted to this game that each night he dreams of himself being in the game as Gravity Guy. He has two lanes in front of him represented by two strings of equal length named as L1 and L2. Each of the two lanes consists of some clean blocks represented by '.' and some dirty blocks represented by '#'.
 Chef can start running from the beginning of any of the two lanes and cannot step over any dirty block ( '#' ) encountered during his journey. He has to complete his journey by reaching the end block of any of the two lanes.
 Chef can use the following jumps to reach his destination. Considering chef is at xth block of some lane.
 He can jump to x+1th block of the same lane.
 He can switch gravity quickly and jump to xth block of the other lane.
 He can switch gravity and jump to x+1th block of the other lane.
 You have to tell him whether he can reach his destination or not. If it is possible for him to reach his destination, then Chef is interested in knowing the minimum number of gravity switches required to reach the destination.
 Input

 First line of input contains a single integer T denoting the number of test cases. Each test case consists of 2 lines. First line of each test case contains a string denoting lane L1. Second line of each test case contains a string denoting lane L2.
 Output

 For each test case, print "Yes" (without quotes) in the first line if Chef is able to reach the destination followed by a line containing an integer denoting minimum number of gravity switches required to reach to the destination. Print a single line containing the word "No" (without quotes) otherwise.
 Constraints

 1 ≤ T ≤ 105
 1 ≤ |L1| ≤ 2 × 105, where |S| denotes the length of string S
 |L1| = |L2|
 Subtasks

 Subtask 1 (25 points)
 Sum of |L1| over all test cases in one file it at most 200.
 Only "Yes"/"No" response will be evaluated.

 Subtask 2 (25 points)
 Sum of |L1| over all test cases in one file it at most 200.

 Subtask 3 (25 points)
 Sum of |L1| over all test cases in one file it at most 106.
 Only "Yes"/"No" response will be evaluated.
 */
public class GravityGuy {
    private static String str1 = "#.#";
    private static String str2 = ".#.";
    Character bad = '#';
    Character good = '.';
    private static final int IMPOSSIBLE_NUM = 1000000;

    public static void main(String[] args) throws IOException {
        GravityGuy gravityGuy = new GravityGuy();

        Scanner in = new Scanner(System.in);
        int numberOfTests = in.nextInt();

        for (int i = 0; i < numberOfTests; i++) {
            str1 = in.next();
            str2 = in.next();

            if (gravityGuy.isSuccess(str1, str2, 0)) {
                System.out.println("Yes");
                int minMoves = gravityGuy.getMinMoves(str1, str2);
                if (minMoves < IMPOSSIBLE_NUM) {
                    System.out.println(minMoves);

                }
            } else {
                System.out.println("No");
            }
        }
    }

    private boolean isSuccess(String currentStr, String otherStr, int current) {

        if (current == 0 && bad == currentStr.charAt(current) && bad == otherStr.charAt(current)) {
            return false;
        }

        if(current == (currentStr.length() - 1)) {
            return true;
        }

        if (good == currentStr.charAt(current + 1)) {
            //Move to next field on same row
            return isSuccess(currentStr, otherStr, current + 1);
        } else if (good == otherStr.charAt(current+1)) {
            return isSuccess(otherStr, currentStr, current + 1);
        }

        return false;
    }

    private boolean canTraverseLane(String lane1, String lane2) {
        for(int i=0;i< lane1.length();i++) {
            // ......#...
            // ......#...
            if (lane1.charAt(i) == '#' && lane2.charAt(i) =='#') {
                //Can't change lanes and you are stuck
                return false;
            }
        }
        return true;
    }

    private int getMinMoves(String lane1, String lane2) {
        int[][] dp = new int[2][];
        int N = lane1.length();

        dp[0] = new int[lane1.length()];
        dp[1] = new int[lane2.length()];
        //dp[0][i] represents mimimum number of switches needed to reach till lane1[i]. It is INFINITY if lane1[i]='#'
        //dp[1][i] represents mimimum number of switches needed to reach till lane2[i]. It is INFINITY if lane2[i]='#'

        if (lane1.charAt(0) == good) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = IMPOSSIBLE_NUM;
        }

        if (lane2.charAt(0) == good) {
            dp[1][0] = 0;
        } else {
            dp[1][0] = IMPOSSIBLE_NUM;
        }

        for (int i = 1; i < N; i++) {
            dp[0][i] = computeGravitySwitchesForParticularLane(dp, 0, lane1, lane2, i);
            dp[1][i] = computeGravitySwitchesForParticularLane(dp, 1, lane2, lane1, i);
        }

        return Math.min(dp[0][N - 1], dp[1][N - 1]);
    }

    private int computeGravitySwitchesForParticularLane(int[][] dp, int laneId, String lane, String otherLane, int i) {
        dp[laneId][i] = IMPOSSIBLE_NUM;
        if (good.equals(lane.charAt(i))) {
            if (good.equals(lane.charAt(i - 1))) {
                dp[laneId][i] = Math.min(dp[laneId][i], dp[laneId][i - 1]);
            }

            // switch from otherLane[i] to lane[i] cannot be smaller than dp[0][i-1] and dp[1][i-1]

            if (good.equals(otherLane.charAt(i - 1))) {
                dp[laneId][i] = Math.min(dp[1 - laneId][i - 1] + 1, dp[laneId][i]);
            }
        }
        return dp[laneId][i];
    }
}
