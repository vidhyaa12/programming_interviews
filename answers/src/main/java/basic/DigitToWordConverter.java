package basic;


import java.util.Stack;

public class DigitToWordConverter {
    String[] digitWordMap = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private final String SPACE = " ";

    public String getWordsFromDigits(Integer number) {
        if (number == null) {
            return null;
        }

        if (number < 0) {
            throw new IllegalArgumentException("Input should be a positive integer !");
        }

        if (number >= 0 && number < 9) {
            return digitWordMap[number];
        }

        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<String>();

        while (number > 0) {
            stack.push(digitWordMap[number % 10]);
            number /= 10;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (sb.length() > 0) {
                sb.append(SPACE);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DigitToWordConverter digitToWordConverter = new DigitToWordConverter();
        System.out.println(digitToWordConverter.getWordsFromDigits(2));
        System.out.println(digitToWordConverter.getWordsFromDigits(1234));
    }
}
