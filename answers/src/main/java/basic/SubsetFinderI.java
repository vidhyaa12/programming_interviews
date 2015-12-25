package basic;

public class SubsetFinderI {
    private static void findSubsets(int array[]) {
        int numOfSubsets = 1 << array.length;

        for (int i = 0; i < numOfSubsets; i++) {
            int pos = 0;
            int bitmask = i;

            System.out.print("{");
            while (bitmask > 0) {
                if ((bitmask & 1) == 1) {
                    System.out.print(array[pos]);

                    if ((bitmask >> 1) != 0) {
                        System.out.print(", ");
                    }
                }
                bitmask >>= 1;
                pos++;
            }
            System.out.print("} ");
        }
    }

    public static void main(String[ ] args) {
        findSubsets(new int[]{1, 2, 3, 4});
    }
}
