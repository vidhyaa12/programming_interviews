package basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringTokenizer {
    static class Token {
        int startOffset;
        int endOffset;
        String text;

        public Token(int startOffset, int endOffset, String text) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.text = text;
        }

        public int getStartOffset() {
            return startOffset;
        }

        public int getEndOffset() {
            return endOffset;
        }

        public String getText() {
            return text;
        }

        public String toString() {
            return this.startOffset + " " + this.endOffset + " " + this.text;
        }
    }
    public Iterator<Token> tokenize(String s) {
        char[] chars = s.toCharArray();

        int currentIndex = 0;
        List<Token> tokens = new ArrayList<Token>();
        StringBuilder sb = new StringBuilder();
        int tokenStartIdx = 0;
        while (currentIndex < chars.length) {
            if (chars[currentIndex] != ',') {
                sb.append(chars[currentIndex]);
                if (currentIndex == chars.length - 1) {
                    tokens.add(new Token(currentIndex, currentIndex + 1, sb.toString()));
                }
            } else {
                tokens.add(new Token(tokenStartIdx, currentIndex, sb.toString()));
                sb.setLength(0);
                tokenStartIdx = currentIndex + 1;
            }

            currentIndex++;
        }
       return tokens.iterator();
    }

    public static void main(String[] args) {
        StringTokenizer stringTokenizer = new StringTokenizer();

        String s = "a,b,c";

        Iterator<Token> tokenIterator = stringTokenizer.tokenize(s);
        while (tokenIterator.hasNext()) {
            System.out.print(tokenIterator.next() + " ");
            System.out.println();
        }
    }

    private void testTokenizer(String s, String[] expectedTokenStrs) {
        Iterator<Token> tokenIterator = new StringTokenizer().tokenize(s);
        int pos = 0;
        while (pos < expectedTokenStrs.length) {
            if (!tokenIterator.hasNext()) {
                throw new RuntimeException("Too few tokens !");
            }
            Token token = tokenIterator.next();
            System.out.println(expectedTokenStrs[pos] + " "+ token.text);
            System.out.println(s.substring(token.startOffset, token.endOffset) + " " + token.text);
            pos++;
        }



        if (tokenIterator.hasNext()) {
            throw new RuntimeException("More tokens found that expected !!");
        }
    }
}
