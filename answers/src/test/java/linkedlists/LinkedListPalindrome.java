package linkedlists;

import java.util.List;
import java.util.Stack;

/**
 * Created by vidhyaa on 10/28/15.
 */
public class LinkedListPalindrome {
    public static boolean isLinkedListPalindrome(List<Character> inputList) {
        if (inputList == null || inputList.size() == 0) {
            return true;
        }

        int left = 0;
        int right = inputList.size() / 2;
        if (inputList.size() % 2 != 0) {
            right = (inputList.size() / 2) + 1;
        }

        Stack<Character> stack = new Stack<Character>();
        while (left < right) {
            stack.push(inputList.get(left));
            left++;
        }

        while (!stack.isEmpty()) {
            if (!inputList.get(right).equals(stack.pop())) {
                return false;
            }
        }

        return true;
    }
}
