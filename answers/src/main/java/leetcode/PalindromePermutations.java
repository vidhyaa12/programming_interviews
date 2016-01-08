package leetcode;

import java.util.*;

/**
 * Created by vidhyaa on 12/29/15.
 */
public class PalindromePermutations {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> characterCounts = new HashMap<Character, Integer>();

        for (Character c : s.toCharArray()) {
            if (!characterCounts.containsKey(c)) {
                characterCounts.put(c, 1);
            } else {
                characterCounts.put(c, characterCounts.get(c) + 1);
            }
        }

        if (!canPermutePalindrome(s, characterCounts)) {
            return Collections.emptyList();
        }

        StringBuffer sb = new StringBuffer();
        Character charOccuringOnce = null;

        for (Character c : characterCounts.keySet()) {
            if (characterCounts.get(c) % 2 == 0) {
                for (int i = 0; i < characterCounts.get(c) / 2; i++) {
                    sb.append(c);
                }
            } else if (characterCounts.get(c) % 2 == 1) {
                charOccuringOnce = c;
            }
        }

        List<String> palindromeParts = getPerms(sb.toString());
        List<String> palindromePermuations = new ArrayList<String>();

        for (String p : palindromeParts) {
            String palindromeStr = p;
            if (charOccuringOnce != null) {
                palindromeStr += charOccuringOnce;
            }

            palindromePermuations.add(palindromeStr + new StringBuilder(p).reverse().toString());
        }
        return palindromePermuations;
    }

    private boolean canPermutePalindrome(String s, Map<Character, Integer> characterCounts) {
        if (s == null || s.length() == 0) {
            return true;
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

    private List<String> getPerms(String str) {
        if (str == null) {
            return Collections.emptyList();
        }

        List<String> permutations = new LinkedList<String>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        } else if (str.length() == 1) {
            return Arrays.asList(str);
        }

        char c = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = getPerms(remainder);

        for (String word : words) {
            permutations.add(word + c);
            for (int j = 0; j < word.length(); j++) {
                String s = word.substring(0, j) + c + word.substring(j);
                permutations.add(s);
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        PalindromePermutations palindromePermutations = new PalindromePermutations();
        List<String> palindromes = palindromePermutations.generatePalindromes("abcab");
        System.out.println();
    }
}
