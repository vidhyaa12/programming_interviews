package leetcode;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {
    // To solve this problem, we need to find the two loop conditions.
    // One loop goes from 0 to the length of the shortest string - 1.
    // The other is iteration over every element of the string array.
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            if (minLen > str.length()) {
                minLen = str.length();
            }
        }

        if (minLen == 0) {
            return "";
        }

        for (int j = 0; j < minLen; j++) {
            char prev = '0';
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    prev = strs[i].charAt(j);
                    continue;
                }

                if (strs[i].charAt(j) != prev) {
                    return strs[i].substring(0, j); // returns "" when j = 0
                }
            }
        }

        return strs[0].substring(0, minLen);
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"a", "b", "c"};
        System.out.println(longestCommonPrefix(strings));
        System.out.println("b".substring(0, 0));

//        String[] strings = new String[]{"a", "abab", "aca"};
//        System.out.println(longestCommonPrefix(strings));
    }
}
