package basic;

/**
 * Created by vidhyaa on 09/12/17.
 */
public class RemoveAdjacentDuplicates {
    /*
     * Given a string, complete the given function to recursively remove the adjacent duplicate characters and return the resultant string.
     * If there are no characters left in the resultant string, return "-1" (without quotes).
     * Sample Test Cases
     * Sample Input: ABCCBCBA
     * Output: ACBA

     * Explanation: (ABCCBCBA --> ABBCBA --> ACBA)
     * Sample Input: AA
     * Sample Output: -1
     */

    public static String removeAdjDups(String str) {
        // this is the result StringBuilder which does not have adjacent duplicates
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < str.length()) {
            // check whether the character at position i is identical to the last character that we have seen.
            if (result.length() > 0 && str.charAt(i) == result.charAt(result.length() - 1)) {
                // skip the duplicate char at position i
                i++;

                // check if more adjacent characters match the last seen character
                while (i < str.length() && str.charAt(i) == result.charAt(result.length() - 1)) {
                    // skip duplicates found in this process.
                    i++;
                }

                // Per requirements, the last seen character should be deleted since at least 1 adjacent duplicate was found
                // At this point we know that there are no more characters identical to the last seen character, so we can delete it
                result.deleteCharAt(result.length() - 1);
            } else {
                // This character is either the first or is different from the last seen character.
                // Add it to the result
                result.append(str.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
}
