package basic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StringPermute {
    public List<String> permute(String s) {
        List<String> result = new LinkedList<String>();
        permute("", s, result);
        return result;
    }

    private void permute(String prefix, String remainder, List<String> result) {
        if (remainder.length() == 0) {
            result.add(prefix);
        }

        int remainderLen = remainder.length();

        for (int i = 0; i < remainderLen; i++) {
            String before = remainder.substring(0, i);
            String after = remainder.substring(i + 1, remainder.length());
            Character c = remainder.charAt(i);
            permute(prefix + c, before + after, result);
        }
    }

    public List<String> getPerms(String str) {
        if (str == null) {
            return Collections.emptyList();
        }

        List<String> permutations = new LinkedList<String>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }

        char c = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = getPerms(remainder);

        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                String s = word.substring(0, j) + word.charAt(j) + word.substring(j);
                permutations.add(s);
            }
        }
        return permutations;
    }
}
