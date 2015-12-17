package basic;

import org.apache.commons.lang3.StringUtils;

/**
 * Reverse the words in a sentence
 * e.g. Apples are red should be reversed to red are Apples
**/

public class ReverseWords {
    public String reverse(String s) {
        if (s == null) {
            return null;
        }
        String result = StringUtils.EMPTY;
        StringBuilder sb = new StringBuilder();

        int pos = s.length() - 1;

        while (pos >= 0) {
            if (' ' == s.charAt(pos)) {
                sb = sb.reverse();
                sb.append(' ');
                result += sb.toString();
                sb.setLength(0);
            } else {
                sb.append(s.charAt(pos));
                if (pos == 0) {
                    sb = sb.reverse();
                    result += sb.toString();
                    sb.setLength(0);
                }
            }

            pos--;
        }
        return result;
    }
}
