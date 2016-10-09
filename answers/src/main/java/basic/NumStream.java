package basic;

import java.util.ArrayList;
import java.util.List;

// Stream of numbers in the range [1,9]
public class NumStream {
    int[] freqArray = new int[9];
    float totalSum = 0;
    float totalCount = 0;

    public NumStream() {
        for (int i = 0; i < 9; i++) {
            freqArray[i] = 0;
        }
    }

    // always insert 1 to 9
    public void insert(int n) {
        Integer freq = freqArray[n - 1];
        freqArray[n - 1] =  1 + freq;
        totalSum += n;
        totalCount++;
    }

    public Float getMean() {
        return totalSum / totalCount;
    }

    public int getMin() {
        for (int i = 0; i < freqArray.length; i++) {
            if (freqArray[i] > 0) {
                return i + 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    public float findKthSmallest(int pos, int k) {
        if (freqArray[pos] >= k) {
            return pos + 1;
        }

        return findKthSmallest(pos + 1, k - freqArray[pos]);
    }

    public Float getMedian() {
        if (totalCount % 2 == 1) {
            int k = (int) ((totalCount + 1) / 2);
            return Float.valueOf(findKthSmallest(0, k));
        }

        int midElementPos = (int) totalCount / 2;
        float midElement1 = findKthSmallest(0, midElementPos);
        float midElement2 = findKthSmallest(0, midElementPos + 1);

        //System.out.println("midElement1 = " + midElement1);
        //System.out.println("midElement2 = " + midElement2);

        return (midElement1 + midElement2) / 2;
    }

    public Float getMedianSlow() {
        List<Integer> allNums = new ArrayList<Integer>();

        for (int i = 0; i < freqArray.length; i++) {
            Integer freq = freqArray[i];
            for (int j = 0; j < freq; j++) {
                allNums.add(i + 1);
            }
        }

        int size = allNums.size();

        if (size % 2 == 1) {
            return Float.valueOf(allNums.get((size + 1)/ 2 - 1));
        } else {
            return Float.valueOf((allNums.get((size)/ 2) + allNums.get((size)/ 2 - 1)) / 2);
        }
    }

    public Integer getFrequency(int n) {
        return freqArray[n - 1];
    }

    public Float getMeanSlow() {
        Float sum = 0f;
        int countOfNums = 0;
        for (int i = 0; i < freqArray.length; i++) {
            sum += freqArray[i] * (i + 1);
            countOfNums = countOfNums + freqArray[i];
        }

        return sum / countOfNums;
    }

    public static void main(String[] args) {
        NumStream numStream = new NumStream();
        numStream.insert(1);
        numStream.insert(1);
        numStream.insert(2);
        numStream.insert(2);

        System.out.println("Mean = " + numStream.getMean());
        System.out.println("Median = " + numStream.getMedian());

        System.out.println("Min = " + numStream.getMin());
        //System.out.println("2nd smallest = " + numStream.get2ndSmallest());


//        ArrayList<Integer> numList = new ArrayList<Integer>();
//        numList.add(0, 1);
//        numList.add(0, 1);
//        numList.add(0, 1);
//        numList.add(0, 1);
//        System.out.println(numList);
    }
}
