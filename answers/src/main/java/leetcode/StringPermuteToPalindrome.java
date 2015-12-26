package leetcode;


import java.util.HashMap;
import java.util.Map;

public class StringPermuteToPalindrome {
    public static boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Map<Character, Integer> characterCounts = new HashMap<Character, Integer>();

        for (Character c: s.toCharArray()) {
            if (!characterCounts.containsKey(c)) {
                characterCounts.put(c, 1);
            } else {
                characterCounts.put(c, characterCounts.get(c) + 1);
            }
        }

        if (characterCounts.keySet().size() == 1) {
            return true;
        }

        // palindrome is possible
        // 1. if all characters have even count
        // 2. if only 1 character has count 1 and all other characters have even count
        // 3. length of the String is odd and 1 character has count 1 and other characters have equal count
        // 4. length of the String is odd and 1 character has odd count and other characters have even count
        int numOfCharsWithEvenCount = 0;
        int numOfCharsWithOddCountGreaterThanOne = 0;
        int numOfCharsWithCountOne = 0;

        for (Character c : characterCounts.keySet()) {
            if (characterCounts.get(c) % 2 == 0) {
                numOfCharsWithEvenCount++;
            } else {
                if (characterCounts.get(c) == 1) {
                    numOfCharsWithCountOne++;
                } else {
                    numOfCharsWithOddCountGreaterThanOne++;
                }
            }
        }

        if (characterCounts.keySet().size() == s.length()) {
            return false;
        }

        if (s.length() % 2 != 0) {
            if (numOfCharsWithCountOne == 1) {
                return numOfCharsWithOddCountGreaterThanOne == 0 || (numOfCharsWithOddCountGreaterThanOne == numOfCharsWithEvenCount);
            } else if (numOfCharsWithOddCountGreaterThanOne == 1) {
                return true;
            }
            return false;
        }

        if (s.length() % 2 == 0 && numOfCharsWithCountOne == 0 && numOfCharsWithOddCountGreaterThanOne == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[ ] args) {
        String s = "civil";
        boolean b = canPermutePalindrome(s);
        System.out.println(b);
    }
}
