package basic;

/**
 * Print every third prime number
 */
public class PrintPrimes {
    public static void printPrimes(int maxNum) {
        int primeNumCandidate = 0;
        int count = 0;

        while (primeNumCandidate <= maxNum) {

            if (isPrime(primeNumCandidate)) {
                // System.out.println(primeNumCandidate);
                if (count % 3 == 0) {
                    System.out.println(primeNumCandidate);
                }

                count++;
            }
            primeNumCandidate++;
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        printPrimes(100);
    }
}
