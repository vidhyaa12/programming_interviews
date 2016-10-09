package basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Most frequent substring
 */

public class MostFrequentSubstring {

    private static int getDistinctChars(String str) {
        boolean[] charMap = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            charMap[str.charAt(i) - 'a'] = true;
        }

        int charCount = 0;
        for (int i = 0; i < 26; i++) {
            if (charMap[i]) {
                charCount++;
            }
        }
        return charCount;
    }

    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Map<String, Integer> substrFreqMap = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        Integer N = Integer.parseInt(scanner.nextLine());

        String klmStr = scanner.nextLine();
        String[] klmArr = klmStr.split("\\s+");

        if (klmArr.length < 3) {
            throw new RuntimeException("K, L, M must be passed as space separated values in a String");
        }

        int K = Integer.valueOf(klmArr[0]);
        int L = Integer.valueOf(klmArr[1]);
        int M = Integer.valueOf(klmArr[2]);

        String str = scanner.nextLine();
        int len = str.length();
        Integer maxFrequency = 0;

        for (int startIdx = 0; startIdx < len; startIdx++) {
            boolean tooManyDistinctChars = false;
            for (int strLen = K; strLen <= L && (startIdx + strLen <= len) && !tooManyDistinctChars; strLen++) {
                String curSubstr = str.substring(startIdx, startIdx + strLen);
                if (getDistinctChars(curSubstr) <= M) {
                    Integer frq = substrFreqMap.containsKey(curSubstr) ? substrFreqMap.get(curSubstr) + 1 : 1;
                    substrFreqMap.put(curSubstr, frq);
                    maxFrequency = Math.max(maxFrequency, frq);
                } else {
                    tooManyDistinctChars = true;
                }
            }
        }
        System.out.println(maxFrequency);
    }
}