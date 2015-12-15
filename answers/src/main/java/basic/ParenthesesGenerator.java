package basic;

/**
 * Print all combinations of balanced parentheses
 */
public class ParenthesesGenerator {
    public void combine(int n) {
        if (n > 0) {
            char[] chars = new char[2 * n];
            combine(0, n, 0, 0, chars);
        }
        return;
    }

    /**
     * Algorithm:
     * Keep track of counts of open and close brackets. Initialize these counts as 0.
     * Recursively call the combine function until close bracket count is equal to the given n.
     * If open bracket count becomes more than the close bracket count, then put a closing bracket
     * and recursively call for the remaining brackets.
     * If open bracket count is less than n, then put an opening bracket and call combine() for the remaining brackets.
     */
    public void combine(int pos, int n, int open, int close, char[] chars) {
        if (close == n) {
            System.out.println(new String(chars));
            return;
        } else {
            if (open > close) {
                chars[pos] = ')';
                combine(pos + 1, n, open, close + 1, chars);
            }
            if (open < n) {
                chars[pos] = '(';
                combine(pos + 1, n, open + 1, close, chars);
            }
        }
    }

    public static void main(String[] args) {
        ParenthesesGenerator parenthesesGenerator = new ParenthesesGenerator();
        int n = 4;
        parenthesesGenerator.combine(n);
    }
}

