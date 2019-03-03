package basic;

/**
 * Created by vidhyaa on 20/10/18.
 */

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
class GCD
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static int generalizedGCD(int num, int[] arr)
    {
        // WRITE YOUR CODE HERE
        int res = arr[0];
        for (int i=1;i<num;i++) {
            res = gcd(arr[i], res);
        }
        return res;
    }
    // METHOD SIGNATURE ENDS

    static int gcd(int a, int b) {
        if(a==0 || a==b) {
            return b;
        }
        if( b==0) {
            return a;
        }

        if(a > b) {
            return gcd(b, a%b);
        }
        return gcd(a, b%a);
    }

    public static void main(String[] args) {
        System.out.println(generalizedGCD(5, new int[]{2, 4,6, 8, 10}));
        System.out.println(generalizedGCD(5, new int[]{2, 3, 5, 7, 11}));
    }
}
