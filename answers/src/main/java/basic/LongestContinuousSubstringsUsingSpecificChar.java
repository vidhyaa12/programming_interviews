package basic;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class LongestContinuousSubstringsUsingSpecificChar {
    public static Set<String> findLongestSingleCharSeq(String str) {
        if (StringUtils.isBlank(str)) {
            return Collections.emptySet();
        }

        int maxLength = 0;
        Set<String> singleCharSubstrings = new LinkedHashSet<String>();

        for (int i = 0; i < str.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int currSubstringLength = 1;
            stringBuilder.append(str.charAt(i));

            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    currSubstringLength++;
                    stringBuilder.append(str.charAt(j));
                } else {
                    break;
                }
            }

            if (currSubstringLength >= maxLength) {
                maxLength = currSubstringLength;
                singleCharSubstrings.add(stringBuilder.toString());
            }
        }

        Set<String> result = new LinkedHashSet<String>();

        for (String s : singleCharSubstrings) {
            if (s.length() == maxLength) {
                result.add(s);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findLongestSingleCharSeq("aabbcb").toArray()));
        System.out.println(Arrays.toString(findLongestSingleCharSeq("abc").toArray()));
        System.out.println(Arrays.toString(findLongestSingleCharSeq("abab").toArray()));
        System.out.println(Arrays.toString(findLongestSingleCharSeq("abccccaa").toArray()));
    }
}
