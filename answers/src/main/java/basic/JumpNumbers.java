package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vidhyaa on 23/10/18.
 * https://www.geeksforgeeks.org/print-all-jumping-numbers-smaller-than-or-equal-to-a-given-value/
 */

class JumpNumbers {
    public static void main(String[] args) {
        printJumpingNumber(105);
    }

    public static void printJumpingNumber(int X) {
        List<Integer> jumpNumbers = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            jumpNumbers = printJumpingNumberUtil(X, i, i, jumpNumbers);
        }
    }

    public static List<Integer> printJumpingNumberUtil(int X, int num, int lastNum, List<Integer> res) {
        if (num < X) {
            res.add(num);
            System.out.println(Arrays.toString(res.toArray()));
            System.out.print(" " + num);
            System.out.println();
            if (lastNum != 0) {
                printJumpingNumberUtil(X, new Integer(num + "" + (new Integer(lastNum) - 1)), lastNum - 1, res);
            }
            if (lastNum != 9) {
                printJumpingNumberUtil(X, new Integer(num + "" + (new Integer(lastNum) + 1)), lastNum + 1, res);
            }
        }
        return res;
    }
}