package basic;

public class LongestCommonSubsequence {
    private static final String ZERO_STR = "0";
    public static void findLCS(char[] a, char[] b) {
        int[][] lcs = new int[a.length + 1][b.length + 1];
        String[][] solution = new String[a.length + 1][b.length + 1];
        // if a is null then LCS of a, b =0
        for (int i = 0; i <= b.length; i++) {
            lcs[0][i] = 0;
            solution[0][i] = ZERO_STR;
        }

        // if b is null then LCS of a, b =0
        for (int i = 0; i <= a.length; i++) {
            lcs[i][0] = 0;
            solution[i][0] = ZERO_STR;
        }

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i-1] == b[j-1]) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                    solution[i][j] = "diagonal";
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);

                    // if the order of conditions in this if - else is changed, we would get another solution for cases where more
                    // than one solution is correct
                    // current code returns only one of the solutions
                    if (lcs[i][j] == lcs[i][j-1]) {
                        solution[i][j] = "left";
                    } else {
                        solution[i][j] = "top";
                    }
                }
            }
        }

        String x = solution[a.length][b.length];
        String answer = "";
        int rowIndex = a.length;
        int columnIndex = b.length;
        while (!ZERO_STR.equals(x)) {
            if (solution[rowIndex][columnIndex] == "diagonal") {
                answer = a[rowIndex - 1] + answer;
                rowIndex--;
                columnIndex--;
            } else if (solution[rowIndex][columnIndex] == "left") {
                columnIndex--;
            } else if (solution[rowIndex][columnIndex] == "top") {
                rowIndex--;
            }
            x = solution[rowIndex][columnIndex];
        }

        System.out.println(answer);

        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                System.out.print(" " + lcs[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        findLCS("abcdab".toCharArray(), "abdce".toCharArray());
    }
}
