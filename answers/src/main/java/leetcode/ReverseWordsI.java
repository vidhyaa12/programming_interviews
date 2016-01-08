package leetcode;

/**
 * Created by vidhyaa on 1/1/16.
 */
public class ReverseWordsI {
    public static String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        String result = "";
        StringBuilder sb = new StringBuilder();
        s = s.trim();

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
        return result.replaceAll("\\s+", " ").trim();
    }

    public static void main(String[ ] args) {
        String result = reverseWords("   a   b ");
        System.out.println(result);
        System.out.println(result.length());
    }
}