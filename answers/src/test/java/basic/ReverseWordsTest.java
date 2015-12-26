package basic;

import leetcode.ReverseWords;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReverseWordsTest {
    @Test
    public void testReverseWordsInASentenceWithSpaces() {
        String sentence = "Apples are red";
        ReverseWords reverseWords = new ReverseWords();
        String result = reverseWords.reverse(sentence);
        Assert.assertEquals("red are Apples", result);
    }

    @Test
    public void testReverseWordsInASentenceWithNoSpaces() {
        String sentence = "Apples";
        ReverseWords reverseWords = new ReverseWords();
        String result = reverseWords.reverse(sentence);
        Assert.assertEquals("Apples", result);
    }
}
