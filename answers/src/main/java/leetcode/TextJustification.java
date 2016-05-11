package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedList = new ArrayList<String>();
        int curLen = 0;
        List<String> pendingWordsList = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            int appendLen = (pendingWordsList.size() == 0 ? 0 : 1) + words[i].length();
            if ((appendLen + curLen) > maxWidth) {
                justifiedList.add(justify(pendingWordsList, maxWidth));
                pendingWordsList.clear();
                pendingWordsList.add(words[i]);
                curLen = words[i].length();
            } else {
                curLen += appendLen;
                pendingWordsList.add(words[i]);
            }
        }
        justifiedList.add(justifyLastLine(pendingWordsList, maxWidth));
        return justifiedList;
    }

    public String justifyLastLine(List<String> pendingWordsList, int maxWidth) {
        String just = "";
        for (int i = 0; i < pendingWordsList.size() - 1; i++) {
            just += pendingWordsList.get(i);
            just += " ";
        }
        just += pendingWordsList.get(pendingWordsList.size() - 1);
        just += space(maxWidth - just.length());
        return just;
    }

    public String justify(List<String> pendingWordsList, int maxWidth) {
        String just = "";
        if (pendingWordsList.size() <= 1) {
            if (pendingWordsList.size() == 1) {
                just = pendingWordsList.get(0);
            }
            while (just.length() < maxWidth) {
                just += " ";
            }
        } else {
            int totLen = 0;
            for (String word : pendingWordsList) {
                totLen += word.length();
            }
            int numSpaces = pendingWordsList.size() - 1;
            int totalSpaceLen = maxWidth - totLen;
            int spacePerWord = totalSpaceLen / numSpaces;
            int wordsWithExtraSpace = (totalSpaceLen % numSpaces);
            int N = pendingWordsList.size();
            for (int i = 0; i < (N - 1); i++) {
                just += pendingWordsList.get(i);
                just += space(spacePerWord);
                if (wordsWithExtraSpace > 0) {
                    just += " ";
                    wordsWithExtraSpace--;
                }
            }
            just += pendingWordsList.get(N - 1);
        }
        return just;
    }

    public String space(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }
}
