package basic;

import org.apache.commons.lang3.StringUtils;

/**
 * We are given 3 strings: str1, str2, and str3. Str3 is said to be a shuffle of str1 and str2
 * if it can be formed by interleaving the characters of str1 and str2
 * in a way that maintains the left to right ordering of the characters from each string.
 * For example, given str1=”abc” and str2=”def”, str3=”dabecf” is a valid shuffle
 * since it preserves the character ordering of the two strings.
 * So, given these 3 strings write a function that detects whether str3 is a valid shuffle of str1 and str2 -
 * See more at: http://www.ardendertat.com/2012/01/09/programming-interview-questions/#sthash.HA501BPF.dpuf
 */
public class StringShuffle {
    public boolean isValidShuffle(String s, String a, String b) {
        if (StringUtils.isBlank(s)) {
            if (StringUtils.isBlank(a) && StringUtils.isBlank(b)) {
                return true;
            } else {
                return false;
            }
        }

        if (StringUtils.isNotBlank(a) && s.charAt(0) == a.charAt(0) && isValidShuffle(s.substring(1), a.substring(1), b)) {
            return true;
        } else if (StringUtils.isNotBlank(b) && s.charAt(0) == b.charAt(0) && isValidShuffle(s.substring(1), a, b.substring(1))) {
            return true;
        }
        return false;
    }
}
